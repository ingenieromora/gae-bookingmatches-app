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

.run( function( $rootScope, $location, FBService) {
    // Load the facebook SDK asynchronously
    (function(){
    if (document.getElementById('facebook-jssdk')) {return;}

    var firstScriptElement = document.getElementsByTagName('script')[0];

    var facebookJS = document.createElement('script'); 
    facebookJS.id = 'facebook-jssdk';
    facebookJS.src = '//connect.facebook.net/en_US/all.js';

    firstScriptElement.parentNode.insertBefore(facebookJS, firstScriptElement);
   }());


    // register listener to watch route changes
    $rootScope.$on( "$routeChangeStart", function(event, next, current) {
        FBService.getLoginStatus().then(function(response) {
            if(response.status !== 'connected' && next.templateUrl != 'views/main.html'){
                $location.path( "/main" );
            }
        });
    });
});
