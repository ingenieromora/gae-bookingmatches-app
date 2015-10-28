'use strict';

angular.module('bookingMatches')

.config(['$routeProvider', function($routeProvider) {
    $routeProvider.when('/matches/create', {
        templateUrl: 'views/matchForm.html',
        controller: 'CreateMatchCtrl'
    });
}])

.controller('CreateMatchCtrl', function($scope, $location, $filter, MatchService, SportService, FBService, Notification, localStorage) {
    $scope.sports = [];
    $scope.user = localStorage.getUser();
    $scope.match = {};

    SportService.list().success(function(data){
        $scope.sports = data.items;
    });

    $scope.saveMatch = function(){
        var address = $scope.positions[0];
        $scope.match.location = {
            latitude: address.lat,
            longitude: address.lng
        };
        
        $scope.match.date = $scope.date.getTime();
        $scope.match.createdBy = $scope.user.fbId;
        MatchService.save($scope.match)
            .success(function(match) {
                $location.path('/matches/' + match.id);
                Notification.success({message: 'Partido creado exitosamente'});
            })
            .error(function(error) {
                Notification.error({message: error.error.message});
            });
    }

    //DatePicker
    $scope.minDate = new Date();

    //Map
    $scope.positions = [];
    $scope.initialCenter = "-34.6087014,-58.4293986";
    $scope.validAddress = false;
    $scope.placeMarker = function(e) {
        $scope.positions = [];
        $scope.positions.push({lat:e.latLng.lat(),lng:e.latLng.lng()});
        $scope.validAddress = true;
    };

    //Facebook
    $scope.postToWall = function(){
        FBService.postMatchToWall();
    };

    $scope.sendNotification = function(){
        FBService.sendNotification('una notificacion', $scope.user.fbId);
    };
});