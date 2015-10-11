'use strict';

angular.module('bookingMatches')

.factory('SportService', function($http, CONFIG) {
    return{
        list: function(){
            return $http.get(CONFIG.API_URL + 'sports/v1/sports');
        },
        save: function(name){
            return $http.post(CONFIG.API_URL + 'sports/v1/sports', { 
				name: name 
			});
        }
    }
});