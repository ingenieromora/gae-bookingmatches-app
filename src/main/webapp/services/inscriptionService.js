'use strict';

angular.module('bookingMatches')

.factory('InscriptionService', function($http, CONFIG) {
    return{
        get: function(id){
            return $http.get(CONFIG.API_URL + 'matches/v1/matches/inscriptions/' + id);
        },
        getAll: function(){
            return $http.get(CONFIG.API_URL + 'matches/v1/matches/inscriptions/');
        },
        getAllFor: function(id){
            return $http.get(CONFIG.API_URL + 'matches/v1/matches/inscriptions/enrolled/'+id);
        },
        save: function(matchId, playerId){
            return $http.post(CONFIG.API_URL + 'matches/v1/matches/inscriptions/', {
                'matchId': matchId,
                'playerId': playerId
            });
        },
        delete: function(id){
            return $http.delete(CONFIG.API_URL + 'matches/v1/matches/inscriptions/' + id);
        }
    }
});