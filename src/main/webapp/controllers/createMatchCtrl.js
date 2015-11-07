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
        
        $scope.match.date = $filter('date')($scope.date, 'yyyy-MM-dd');
        $scope.match.createdBy = $scope.user.fbId;
        MatchService.save($scope.match)
            .success(function(match) {

                Notification.success({message: 'Partido creado exitosamente'});

                FBService.postMatchToWall(match.id)
                    .success(function(fbresponse) {
                        Notification.success({message: 'Partido posteado en el muro exitosamente'});
                    })
                    .error(function(error) {
                        Notification.error({message: error.error.message});
                    });

                    $location.path('/matches/' + match.id);
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

    $scope.sendNotification = function(){
        FBService.sendNotification('una notificacion', $scope.user.fbId);
    };
});