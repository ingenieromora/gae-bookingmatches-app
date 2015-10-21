'use strict';

angular.module('bookingMatches')
    .factory('localStorage', function ($window) {

        var localStorage = {};

        localStorage.set = function (key, value) {
            $window.localStorage[key] = value || '';
        };
        localStorage.get = function (key, defaultValue) {
            return $window.localStorage[key] || defaultValue;
        };

        localStorage.setJson = function (key, json) {
            localStorage.set(key, angular.toJson(json));
        };
        localStorage.getJson = function (key, defaultJson) {
            return angular.fromJson(localStorage.get(key, defaultJson));
        };

        localStorage.setUser = function(user) {
            localStorage.setJson('currentUser', user);
        };
        localStorage.getUser = function() {
            localStorage.getJson('currentUser');
        };

        return localStorage;
    });
