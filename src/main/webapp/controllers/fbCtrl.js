'use strict';

angular.module('bookingMatches')

.controller('FBCtrl', function ($scope, $facebook) {
    $scope.isLoggedIn = false;
    $scope.login = function() {
        $facebook.login().then(function() {
            refresh();
        });
    }
    function refresh() {
        $facebook.api("/me").then( 
            function(response) {
                $scope.welcomeMsg = "Bienvenido " + response.name;
                $scope.isLoggedIn = true;
            },
            function(err) {
                $scope.welcomeMsg = "Login con Facebook";
            }
        );
    }

    refresh();
});
