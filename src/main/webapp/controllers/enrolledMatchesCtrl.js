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
        data.items.map(function(inscription) {
            MatchService.get(inscription.matchId).then(function(match) {
                $scope.matches.push(match.data);
            });
        })
    });

    $scope.tableParams = new ngTableParams({},
    {
        total: $scope.matches.length,
        getData: function($defer, params) {
            $defer.resolve($scope.matches);
        }
    });

});
