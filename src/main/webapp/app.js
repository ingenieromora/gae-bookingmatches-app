'use strict';

angular.module('bookingMatches', [
    'ngRoute',
    'ui.bootstrap',
    'ngMap',
	'ngTable',
	'ui-notification'
])

.config(['$routeProvider', function($routeProvider) {
    $routeProvider.otherwise({redirectTo: '/main'});
}]);
