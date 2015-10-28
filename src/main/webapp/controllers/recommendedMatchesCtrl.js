'use strict';

angular.module('bookingMatches')

.config(['$routeProvider', function($routeProvider) {
    $routeProvider.when('/recommendedMatches/', {
        templateUrl: 'views/recommendedMatches.html',
        controller: 'RecommendedMatchesCtrl'
    });
}])

.controller('RecommendedMatchesCtrl', function($scope, $routeParams, ngTableParams, MatchService, RecommendationService, localStorage) {
    $scope.matches = [];

    RecommendationService.getAllFor(localStorage.getUser().fbId).success(function(data){
        data.items.map(function(recommendation) {
            MatchService.get(recommendation.matchId).then(function(match) {
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