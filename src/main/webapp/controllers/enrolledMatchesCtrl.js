'use strict';

angular.module('bookingMatches')

.config(['$routeProvider', function($routeProvider) {
    $routeProvider.when('/enrolledMatches/', {
        templateUrl: 'views/enrolledMatches.html',
        controller: 'EnrolledMatchesCtrl'
    });
}])

.controller('EnrolledMatchesCtrl', function($scope, $routeParams, ngTableParams, MatchService, InscriptionService, localStorage) {
    $scope.matches = [];

    InscriptionService.getAllFor(localStorage.getUser().fbId).success(function(data){
        $scope.matches = angular.copy(data.items);
    });

    $scope.tableParams = new ngTableParams({},
    {
        total: $scope.matches.length,
        getData: function($defer, params) {
            $defer.resolve($scope.matches);
        }
    });

});
