'use strict';

angular.module('bookingMatches')

.factory('RecommendationService', function($http, CONFIG) {
    return{
        get: function(id){
            return $http.get(CONFIG.API_URL + 'recommendations/v1/recommendations/' + id);
        },
        getAll: function(){
            return $http.get(CONFIG.API_URL + 'recommendations/v1/recommendations');
        },
        save: function(matchId, emitterId, receiverId){
            return $http.post(CONFIG.API_URL + 'recommendations/v1/recommendations', {
                'matchId': matchId,
                'emitterId': emitterId,
                'receiverId': receiverId
            });
        },
        delete: function(id){
            return $http.delete(CONFIG.API_URL + 'recommendations/v1/recommendations/' + id);
        }
    }
});