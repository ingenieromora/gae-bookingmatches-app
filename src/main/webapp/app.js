'use strict';

angular.module('bookingMatches', [
    'ngRoute',
    'ui.bootstrap',
    'ngMap',
    'ngTable',
    'ui-notification',
    'ngFacebook'
])

.config(['$routeProvider', function($routeProvider) {
    $routeProvider.otherwise({redirectTo: '/main'});
}])

.config( function( $facebookProvider ) {
  $facebookProvider.setAppId('1496941813951092');
  $facebookProvider.setPermissions("user_friends,publish_actions");
})

.run( function( $rootScope ) {
    // Load the facebook SDK asynchronously
    (function(){
    if (document.getElementById('facebook-jssdk')) {return;}

    var firstScriptElement = document.getElementsByTagName('script')[0];

    var facebookJS = document.createElement('script'); 
    facebookJS.id = 'facebook-jssdk';
    facebookJS.src = '//connect.facebook.net/en_US/all.js';

    firstScriptElement.parentNode.insertBefore(facebookJS, firstScriptElement);
   }());
});
