'use strict';

angular.module('bookingMatches')

.controller('FBCtrl', function ($scope, $rootScope, FBService) {
    $scope.isLoggedIn = false;
    $scope.login = function() {
        FBService.login().then(function(response) {
            FBService.validate(response.authResponse);
            $rootScope.user = {
                id: response.userID,
                accessToken: response.accessToken
            };
            console.log(response);
            refresh();
        });
    };
    function refresh() {
        FBService.getMe().then( 
            function(response) {
                $scope.welcomeMsg = 'Bienvenido ' + response.name;
                $scope.isLoggedIn = true;
            },
            function(err) {
                $scope.welcomeMsg = 'Login con Facebook';
                console.log(err);
            }
        );
    }

    refresh();
});
