'use strict';

angular.module('bookingMatches')

.controller('FBCtrl', function ($scope, $rootScope, FBService, localStorage) {
    $scope.isLoggedIn = false;

    $scope.login = function() {
        FBService.login().then(function(response) {
            var user = {
                fbId: response.authResponse.userID,
                accessToken: response.authResponse.accessToken
            };

            FBService.validate(user).then(function(response){
                if(response.data.message == 'OK'){
                    refresh();
                    $scope.isLoggedIn = true;
                }
            });
        });
    };

    function refresh() {
        FBService.getMe().then( 
            function(response) {
                $scope.welcomeMsg = 'Bienvenido ' + response.name;
                localStorage.setUser({
                    fbId: response.id,
                    accessToken: FBService.getAuthResponse()['accessToken'],
                    name: response.name
                });
            },
            function(err) {
                $scope.welcomeMsg = 'Login con Facebook';
            }
        );
    }

    refresh();
});
