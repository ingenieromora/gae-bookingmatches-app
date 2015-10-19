'use strict';

angular.module('bookingMatches')

.config(['$routeProvider', function($routeProvider) {
    $routeProvider.when('/main', {
        templateUrl: 'views/main.html',
        controller: 'MainCtrl'
    });
}])

.controller('MainCtrl', ['$scope', function($scope, $facebook) {
    $scope.user = {
        id: 'TomD'
    };
}]);