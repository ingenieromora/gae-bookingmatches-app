'use strict';

angular.module('bookingMatches')

.config(['$routeProvider', function($routeProvider) {
    $routeProvider.when('/recommendedMatches/', {
        templateUrl: 'views/recommendedMatches.html',
        controller: 'RecommendedMatchesCtrl'
    });
}])

.controller('RecommendedMatchesCtrl', function($scope, $routeParams, ngTableParams, MatchService, RecommendationService, localStorage, Notification, FBService) {
    $scope.recommendations = [];
    $scope.user = localStorage.getUser();

    $scope.refreshRecommendations = function(){
        RecommendationService.getAllFor(localStorage.getUser().fbId).success(function(data){
            $scope.recommendations = data.items;

            $scope.recommendations.map(function(recommendation) {
                MatchService.get(recommendation.match.id).then(function(match) {
                    recommendation.match = match.data;
                    if(!recommendation.match.starters) recommendation.match.starters = [];
                    if(!recommendation.match.alternates) recommendation.match.alternates = [];
                });
            })
        });
    }
    $scope.refreshRecommendations();


    $scope.tableParams = new ngTableParams({},
    {
        total: $scope.recommendations.length,
        getData: function($defer, params) {
            $defer.resolve($scope.recommendations);
        }
    });

    $scope.acceptRecommendation = function(recommendation){
        MatchService.subscribe(recommendation.match.id, $scope.user.fbId).then(function(data) {
                Notification.success({message: 'La inscripción se realizó exitosamente'});
                FBService.sendNotification('El jugador '+$scope.user.name+' se ha inscripto a un partido que creaste', recommendation.match.createdBy.fbId);

                if (recommendation.match.starters.length == (recommendation.match.playersNeeded-1)){
                    recommendation.match.starters.map(function(player) {
                        FBService.sendNotification('Se han completado las inscripciones para el partido '+recommendation.match.id, player.fbId);
                    });
                }

                $scope.deleteRecommendation(recommendation.id);
            }, function(error) {
                Notification.error({message: error.name});
            });

    }

    $scope.declineRecommendation = function(recommendation){
        $scope.deleteRecommendation(recommendation.id);
    }

    $scope.deleteRecommendation = function(id){
        RecommendationService.delete(id).then(function(){
            $scope.refreshRecommendations();
        });
    }

});