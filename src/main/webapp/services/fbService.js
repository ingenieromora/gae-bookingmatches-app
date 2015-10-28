'use strict';

angular.module('bookingMatches')

.factory('FBService', function($http, CONFIG, $facebook, localStorage) {
    return{
        login: function(){
            return $facebook.login();
        },
        getLoginStatus: function(){
            return $facebook.getLoginStatus();
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
        getAppAccessToken: function(data){
            return $http.get(CONFIG.API_URL + 'facebook/v1/facebook/app-access-token');
        },
        postMatchToWall: function(id){
            var url = 'https://graph.facebook.com/v2.4/me/feed';
            url += '?name=Creé un nuevo Partido';
            url += '&link='+CONFIG.SITE_URL + 'matches/'+(id) ? id : '';
            url += '&picture=https://encrypted-tbn3.gstatic.com/images?q=tbn:ANd9GcR8XAswE_qVuGyt5Gtg88-a2kxAdIIlNvzr_RNFTR41qHMGOVhQ';
            url += '&caption=PARTIDO';
            url += '&description=Inscribite a mi partido';
            url += '&message=Inscribite a mi partido';
            url += '&access_token='+localStorage.getUser().accessToken;

            $http.post(url);
        },
        sendNotification: function(text, userId){
            this.getAppAccessToken().then(function(response) {
                var url = 'https://graph.facebook.com/v2.4/'+userId+'/notifications';
                url += '?access_token='+response.data.message;
                url += '&template='+text;
                url += '&href='+CONFIG.SITE_URL;

                $http.post(url);
            });
        }
    }
});