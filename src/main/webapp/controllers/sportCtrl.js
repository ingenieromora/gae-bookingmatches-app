'use strict';

angular.module('bookingMatches')

.config(['$routeProvider', function($routeProvider) {
    $routeProvider.when('/sports', {
        templateUrl: 'views/sportForm.html',
        controller: 'SportCtrl'
    });
}])

.controller('SportCtrl', function($scope, SportService, Notification) {
    $scope.name = '';
    $scope.sports = [];

    SportService.list().success(function(data){
        $scope.sports = data.items;
    });

    $scope.saveSport = function(){
        if ($scope.name == '')
                Notification.warn({message: 'El nombre del deporte no puede ser vacío'});

        SportService.save($scope.name)
            .success(function(sport) {
                $scope.sports.push(sport);
                Notification.success({message: 'Deporte ' + sport.name + ' creado exitosamente'});
                $scope.name = '';
            })
            .error(function(error) {
                Notification.error({message: error.error.message});
            });
    }
});