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
        canSubscribe: true, //TODO
        canUnsubscribe: true //TODO
    };
    $scope.user = localStorage.getUser();

    MatchService.get($routeParams.id).success(function(data){
        $scope.match = data;
        $scope.actions.canDelete = data.createdBy == $scope.user.fbId;

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
    });
    
    $scope.subscribe = function() {
        MatchService.subscribe($routeParams.id, $scope.user.fbId)
            .success(function(data) {
                Notification.success({message: 'La inscripción se realizó exitosamente'});
                FBService.sendNotification('El jugador '+$scope.user.name+' se ha inscripto a un partido que creaste', $scope.match.createdBy);
            })
            .error(function(error) {
                Notification.error({message: error.name});
            });
    };
    
    $scope.unsubscribe = function() {
        MatchService.unsubscribe($routeParams.id, $scope.user.fbId)
            .success(function(data) {
                Notification.success({message: 'Se ha eliminado la inscripción exitosamente'});
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

    FBService.getFriends().then(function(response) {
        $scope.friends = response.data;
    });

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