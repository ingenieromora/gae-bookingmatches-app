'use strict';

angular.module('bookingMatches')

.controller('FBCtrl', function ($scope, $rootScope, FBService) {
    $rootScope.isLoggedIn = false;
    $scope.login = function() {
        FBService.login().then(function(response) {
            var user = {
                fbId: response.authResponse.userID,
                accessToken: response.authResponse.accessToken
            };
            //FBService.validate(user);
            $rootScope.user = user;
            console.log(response);
            refresh();
        });
    };
    function refresh() {
        FBService.getMe().then( 
            function(response) {
                $scope.welcomeMsg = 'Bienvenido ' + response.name;
                $rootScope.isLoggedIn = true;
				$rootScope.user = {
                    fbId: response.id,
                    accessToken: FBService.getAuthResponse()['accessToken'],
                    name: response.name
                };
            },
            function(err) {
                $scope.welcomeMsg = 'Login con Facebook';
                $rootScope.isLoggedIn = false;
                console.log(err);
            }
        );
    }

    refresh();
});
