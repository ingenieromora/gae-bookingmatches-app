'use strict';

angular.module('bookingMatches')

.config(['$routeProvider', function($routeProvider) {
    $routeProvider.when('/matches', {
        templateUrl: 'views/matches.html',
        controller: 'MatchesCtrl'
    });
}])

.controller('MatchesCtrl', function($scope, ngTableParams, MatchService) {

	$scope.matches = [];
	
	MatchService.getAll().success(function(data){
		$scope.matches = data;
	});
	
	
	 $scope.tableParams = new ngTableParams({},
      {
        total: $scope.matches.length,
        getData: function($defer, params) {
			$defer.resolve($scope.matches);
        }
    });
});