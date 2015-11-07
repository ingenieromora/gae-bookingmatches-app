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
                //espero que me devuelva el match con el jugador inscripto
                $scope.match = data;
                Notification.success({message: 'Jugador ' + $scope.user.fbId + ' inscripto exitosamente'});
            })
            .error(function(error) {
                Notification.error({message: error.name});
            });
    };
    
    $scope.unsubscribe = function() {
        MatchService.unsubscribe($routeParams.id, $scope.user.fbId)
            .success(function(data) {
                //espero que me devuelva el match con el jugador desinscripto
                $scope.match = data;
                Notification.success({message: 'Jugador ' + $scope.user.fbId + ' desinscripto exitosamente'});
            })
            .error(function(error) {
                Notification.error({message: error.name});
            });
    };
    
    $scope.delete = function() {
        MatchService.delete($routeParams.id)
            .success(function(data) {
                //retorno a /matches hasta que lo definamos
                Notification.success({message: 'Partido eliminado exitosamente'});
                $location.path('/matches');
            })
            .error(function(error) {
                Notification.error({message: error.name});
            });
    };

    FBService.getFriends().then(function(response) {
        $scope.friends = response.data;
    });

    $scope.recommend = function() {
        RecommendationService.save($routeParams.id, $scope.user.fbId, $scope.friendToRecommend);
    };
});