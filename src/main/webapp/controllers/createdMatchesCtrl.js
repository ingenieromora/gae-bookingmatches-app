'use strict';

angular.module('bookingMatches')

.config(['$routeProvider', function($routeProvider) {
    $routeProvider.when('/matches/created/:userId', {
        templateUrl: 'views/createdMatches.html',
        controller: 'CreatedMatchesCtrl'
    });
}])

.controller('CreatedMatchesCtrl', function($scope, $routeParams, ngTableParams, MatchService, FBService) {

    $scope.matches = [];

    MatchService.getCreatedBy($routeParams.userId).success(function(data){
        $scope.matches = data;
    });

    $scope.tableParams = new ngTableParams({},
    {
        total: $scope.matches.length,
        getData: function($defer, params) {
            $defer.resolve($scope.matches);
        }
    });

    FBService.getFriends().then(function(response) {
        $scope.friends = response.data;
    });

    $scope.friensToRecommend = [];
    $scope.recommend = function() {
        RecommendationService.addAll($scope.friensToRecommend);
    };
});