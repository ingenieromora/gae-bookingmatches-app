'use strict';

angular.module('bookingMatches')

.config(['$routeProvider', function($routeProvider) {
    $routeProvider.when('/matches/created/:userFbId/:userName', {
        templateUrl: 'views/createdMatches.html',
        controller: 'CreatedMatchesCtrl'
    });
}])
.config(['$routeProvider', function($routeProvider) {
    $routeProvider.when('/matches/created/:mode', {
        templateUrl: 'views/createdMatches.html',
        controller: 'CreatedMatchesCtrl'
    });
}])

.controller('CreatedMatchesCtrl', function($scope, $routeParams, ngTableParams, MatchService, FBService, localStorage, $location) {
    $scope.user = {};
    if($routeParams.mode){
        if($routeParams.mode == 'me') {
            $scope.user = localStorage.getUser();
            $scope.hideFriends = true;
        }else {
            $scope.user.name = 'Amigos';
        }
    }else{
        $scope.user = {
            fbId: $routeParams.userFbId,
            name: $routeParams.userName
        };
    }


    $scope.friendToGo = {};

    FBService.getFriends().then(function(response) {
        $scope.friends = response.data.data;
    });

    $scope.redirect = function(){
        $location.path("/matches/created/"+$scope.friendToGo.id+'/'+$scope.friendToGo.name);
    };


    $scope.matches = [];

    MatchService.getCreatedBy($scope.user.fbId).success(function(data){
        $scope.matches = data.items;
    });


    $scope.tableParams = new ngTableParams({},
    {
        total: $scope.matches.length,
        getData: function($defer, params) {
            $defer.resolve($scope.matches);
        }
    });

});