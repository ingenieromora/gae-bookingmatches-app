'use strict';

angular.module('bookingMatches')

.controller('FBCtrl', function ($scope, $rootScope, FBService, localStorage) {
    $scope.login = function() {
        FBService.login().then(function(response) {
            var user = {
                fbId: response.authResponse.userID,
                accessToken: response.authResponse.accessToken
            };

            FBService.validate(user).then(function(response){
                if(response.data.message == 'OK'){
                    refresh();
                }
            });
        });
    };

    function refresh() {
        if($rootScope.isLoggedIn){
            $rootScope.welcomeMsg = 'Bienvenido ' + localStorage.getUser().name;
        }else{
            $rootScope.welcomeMsg = 'Login con Facebook';
            FBService.getMe().then( 
                function(response) {
                    $rootScope.welcomeMsg = 'Bienvenido ' + response.name;
                    localStorage.setUser({
                        fbId: response.id,
                        accessToken: FBService.getAuthResponse()['accessToken'],
                        name: response.name
                    });
                    $rootScope.isLoggedIn = true;
                }
            );
        }
    }

    refresh();
});
