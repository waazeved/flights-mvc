define([ 'app' ], function(app) {
	app.controller('FlightListCtrl', [ '$scope', '$window', '$http', '$location', 'commonsService', 'CrudService',
		function($scope, $window, $http, $location, commonsService, CrudService) {

		$scope.flightsPagList = [];
		$scope.pageIndex = 0;
		$scope.pageLimit = 15;
		$scope.showLoadMoreButton = false;
		$scope.search = "";
		var loading = false;

		$scope.init = function() {
			$scope.list();
		};

		$scope.changeSearch = function(){
			$scope.flightsPagList = [];
			$scope.pageIndex = 0;
			$scope.showLoadMoreButton = false;
			$scope.list();
		}

		$scope.list = function() {
			if(loading == true){
				return;
			}

			loading = true;
			var s = encodeURIComponent($scope.search).trim();
			if(s == ""){
				CrudService.flight.findAll($scope.pageIndex, $scope.pageLimit).then(function(response) {
					success(response);
				}).catch(function(error) {
					error(error);
				});
			}
			else{
				CrudService.flight.search(s, $scope.pageIndex, $scope.pageLimit).then(function(response) {
					success(response);
				}).catch(function(error) {
					error(error);
				});
			}


		};

		var success = function(response){
			console.log(response);
			if(response.data != null && response.data.length > 0){
				for(var i = 0; i < response.data.length; i++){
					var dto = response.data[i];
					$scope.flightsPagList.push(dto);
				}
				$scope.pageIndex = $scope.pageIndex + 1;
				$scope.showLoadMoreButton = true;
				if(response.data.length < $scope.pageLimit){
					$scope.showLoadMoreButton = false;
				}
			}else{
				$scope.showLoadMoreButton = false;
			}
			loading = false;
		};

		var error = function(error){
			console.log(error);
			commonsService.error('Erro ao buscar usuários!');
			loading = false;
		};

		angular.element($window).bind("scroll", function() {
		    var windowHeight = "innerHeight" in window ? window.innerHeight : document.documentElement.offsetHeight;
		    var body = document.body, html = document.documentElement;
		    var docHeight = Math.max(body.scrollHeight, body.offsetHeight, html.clientHeight,  html.scrollHeight, html.offsetHeight);
		    windowBottom = windowHeight + window.pageYOffset;
		    if (windowBottom >= docHeight) {
		    	$scope.list();
		    }
		});





		} ]);
});