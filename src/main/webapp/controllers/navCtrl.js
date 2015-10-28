'use strict';

angular.module('bookingMatches')

.controller('NavCtrl', function($scope, $location, localStorage) {
    $scope.isActive = function (viewLocation) { 
        return viewLocation === $location.path();
    };

    $scope.userId = (localStorage.getUser()) ? localStorage.getUser().fbId : null;

});