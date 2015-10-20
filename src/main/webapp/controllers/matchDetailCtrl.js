'use strict';

angular.module('bookingMatches')

.config(['$routeProvider', function($routeProvider) {
    $routeProvider.when('/matches/:id', {
        templateUrl: 'views/matchDetail.html',
        controller: 'MatchDetailCtrl'
    });
}])

.controller('MatchDetailCtrl', function($scope, $routeParams, $location, $filter, MatchService, Notification) {
    $scope.match = {};
	$scope.actions = {
		canDelete: false,
		canSubscribe: true, //TODO
		canUnsubscribe: true //TODO
	};
	$scope.user = {
		fbId: 1234,
		accessToken: "asdasdasd",
		name: "sarasa"
	};

    MatchService.get($routeParams.id).success(function(data){
        $scope.match = data;
		$scope.actions.canDelete = data.createdBy == $scope.user.fbId;

		//TODOMap
		$scope.positions = [];
		$scope.initialCenter = data.location.latitude + "," + data.location.longitude;
		$scope.validAddress = false;
		$scope.placeMarker = function(e) {
			$scope.positions = [];
			$scope.positions.push({lat:e.latLng.lat(),lng:e.latLng.lng()});
			$scope.validAddress = true;
		}
    });
	
	$scope.subscribe = function() {
		MatchService.subscribe($routeParams.id, $scope.user.id)
			.success(function(data) {
				//espero que me devuelva el match con el jugador inscripto
				$scope.match = data;
				Notification.success({message: 'Jugador ' + $scope.user.id + ' inscripto exitosamente'});
			})
			.error(function(error) {
				Notification.error({message: error.name});
			});
	};
	
	$scope.unsubscribe = function() {
		MatchService.unsubscribe($routeParams.id, $scope.user.id)
			.success(function(data) {
				//espero que me devuelva el match con el jugador desinscripto
				$scope.match = data;
				Notification.success({message: 'Jugador ' + $scope.user.id + ' desinscripto exitosamente'});
			})
			.error(function(error) {
				Notification.error({message: error.name});
			});
	};
	
	$scope.delete = function() {
		MatchService.delete($routeParams.id)
			.success(function(data) {
				//retorno a /matches hasta que lo definamos
				Notification.success({message: 'Partido eliminado exitosamente'});
				$location.path('/matches');
			})
			.error(function(error) {
				Notification.error({message: error.name});
			});
	};
});