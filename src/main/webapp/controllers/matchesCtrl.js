'use strict';

angular.module('bookingMatches')

.config(['$routeProvider', function($routeProvider) {
    $routeProvider.when('/matches', {
        templateUrl: 'views/matches.html',
        controller: 'MatchesCtrl'
    });
}])

.controller('MatchesCtrl', function($scope, ngTableParams, MatchService, Notification) {

    $scope.matches = [];
    $scope.authorized = false;

    MatchService.getAll().success(function(data){
        $scope.matches = data.items;
    });

    $scope.tableParams = new ngTableParams({},
    {
        total: $scope.matches.length,
        getData: function($defer, params) {
            $defer.resolve($scope.matches);
        }
    });

    $scope.validateAdmin = function(){
        if($scope.username == 'admin' && $scope.password == 'admin'){
            $scope.authorized = true;
        }else{
            Notification.error({message: 'Login incorrecto'});
        }
    }
});