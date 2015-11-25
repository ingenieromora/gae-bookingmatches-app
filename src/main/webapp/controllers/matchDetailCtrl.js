'use strict';

angular.module('bookingMatches')

.config(['$routeProvider', function($routeProvider) {
    $routeProvider.when('/matches/:id', {
        templateUrl: 'views/matchDetail.html',
        controller: 'MatchDetailCtrl'
    });
}])

.controller('MatchDetailCtrl', function($scope, $routeParams, $location, $filter, MatchService, RecommendationService, FBService, Notification, localStorage) {
    $scope.match = {};
    $scope.actions = {
        canDelete: false,
        canSubscribe: true
    };
    $scope.user = localStorage.getUser();

    $scope.refreshMatchData = function(){
        MatchService.get($routeParams.id).success(function(data){
            $scope.match = data;
            $scope.actions.canDelete = (data.createdBy.fbId == $scope.user.fbId);
            $scope.actions.canSubscribe = !$scope.isInMatch($scope.user.fbId);

            var timezoneDate = new Date(data.date);
            $scope.match.date = new Date(
                timezoneDate.getUTCFullYear(),
                timezoneDate.getUTCMonth(),
                timezoneDate.getUTCDate(),
                timezoneDate.getUTCHours(),
                timezoneDate.getUTCMinutes(),
                timezoneDate.getUTCSeconds()
            );

            $scope.positions = [];
            $scope.positions.push({ lat: data.location.latitude, lng: data.location.longitude });
            $scope.initialCenter = data.location.latitude + "," + data.location.longitude;

            $scope.parseUserNames();

        });
    };
    $scope.refreshMatchData();

    $scope.isInMatch = function(fbId) {
        var inStarters = [];
        var inAlternates = [];
        if($scope.match.starters){
            inStarters = $scope.match.starters.filter(function(player) {
                return player.fbId == fbId;
            });
        }
        if($scope.match.alternates){
            inAlternates = $scope.match.alternates.filter(function(player) {
                return player.fbId == fbId;
            });
        }
        return (inStarters.length || inAlternates.length);
    };

    $scope.userNamesMap = [];
    $scope.userNamesMap.push({fbId: $scope.user.fbId, name: $scope.user.name});
    FBService.getFriends().then(function(response){
        $scope.friends = response.data;

        $scope.friends.map(function(elem) {
            $scope.userNamesMap.push({fbId: elem.id, name: elem.name});
        });
    });

    $scope.parseUserNames = function() {
        $scope.match.creator = $scope.findPerson($scope.match.createdBy.fbId);

        if($scope.match.starters){
            $scope.match.starters = $scope.match.starters.map(function(elem){
                return $scope.findPerson(elem.fbId);
            });
        }
        if($scope.match.alternates){
            $scope.match.alternates = $scope.match.alternates.map(function(elem){
                return $scope.findPerson(elem.fbId);
            });
        }
    };

    $scope.findPerson = function(id){
        var person = $scope.userNamesMap.filter(function(elem){
            return elem.fbId == id;
        });
        return (person.length) ? person[0] : {fbId: id};
    }
    
    $scope.subscribe = function() {
        MatchService.subscribe($routeParams.id, $scope.user.fbId)
            .success(function(data) {
                Notification.success({message: 'La inscripción se realizó exitosamente'});
                FBService.sendNotification('El jugador '+$scope.user.name+' se ha inscripto a un partido que creaste', $scope.match.createdBy.fbId);

                if ($scope.match.starters.length == ($scope.match.playersNeeded-1)){
                    $scope.match.starters.map(function(player) {
                        FBService.sendNotification('Se han completado las inscripciones para el partido '+$scope.match.id, player.fbId);
                    });
                }

                $scope.refreshMatchData();
            })
            .error(function(error) {
                Notification.error({message: error.name});
            });
    };
    
    $scope.unsubscribe = function() {
        MatchService.unsubscribe($routeParams.id, $scope.user.fbId)
            .success(function(data) {
                Notification.success({message: 'Se ha eliminado la inscripción exitosamente'});
                $scope.refreshMatchData();
            })
            .error(function(error) {
                Notification.error({message: error.name});
            });
    };
    
    $scope.delete = function() {
        MatchService.delete($routeParams.id)
            .success(function(data) {
                Notification.success({message: 'Partido eliminado exitosamente'});
                $location.path('/matches/created/me');
            })
            .error(function(error) {
                Notification.error({message: error.name});
            });
    };

    $scope.recommend = function() {
        RecommendationService.save($routeParams.id, $scope.user.fbId, $scope.friendToRecommend)
            .success(function() {
                Notification.success({message: 'Partido recomendado exitosamente'});
            })
            .error(function(error) {
                Notification.error({message: error.name});
            });
    };
});