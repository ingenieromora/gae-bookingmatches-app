'use strict';

angular.module('bookingMatches')

.controller('FBCtrl', function ($scope, $rootScope, FBService) {
    $scope.isLoggedIn = false;
    $scope.login = function() {
        FBService.login().then(function(response) {
            var user = {
                fbId: response.authResponse.userID,
                accessToken: response.authResponse.accessToken
            };
            FBService.validate(user);
            $rootScope.user = user;
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
