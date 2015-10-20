'use strict';

angular.module('bookingMatches')

.factory('FBService', function($http, CONFIG, $facebook) {
    return{
        login: function(){
            return $facebook.login();
        },
        getAuthResponse: function(){
            return $facebook.getAuthResponse();
        },
        getMe: function(){
            return $facebook.api("/me");
        },
        getFriends: function(){
            return $facebook.api("/me/friends");
        },
        validate: function(data){
            return $http.post(CONFIG.API_URL + 'players/v1/players/validate', data);
        },
        postMatchToWall: function(id){
            $facebook.ui(
                {
                    method: 'feed',
                    name: 'Creé un nuevo Partido',
                    link: CONFIG.SITE_URL + 'matches/'+(id) ? id : '',
                    picture: 'https://encrypted-tbn3.gstatic.com/images?q=tbn:ANd9GcR8XAswE_qVuGyt5Gtg88-a2kxAdIIlNvzr_RNFTR41qHMGOVhQ',
                    caption: 'PARTIDO',
                    description: 'Inscribite a mi partido',
                    message: 'Inscribite a mi partido'
                },
                function(response) {
                    if (response && response.post_id) {
                        console.log('Post was published.');
                    } else {
                        console.log('Post was not published.');
                    }
                }
            );
        }
    }
});