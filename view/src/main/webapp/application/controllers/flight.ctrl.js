define([ 'app' ], function(app) {
	app.controller('FlightCtrl', [ '$scope', '$stateParams', '$http', '$location', 'commonsService', 'CrudService',
		function($scope, $stateParams, $http, $location, commonsService, CrudService) {

		$scope.flight = {
                code : null
        };

		$scope.init = function(){
			$scope.clear();
		}


		$scope.save = function(){

			if(_.isEmpty($scope.flight.code)){
				commonsService.error('Name can not be empty.');
			}
			else{

				CrudService.flight.save($scope.flight).then(function(response) {

					if(response.data.success == true){
						$scope.clear();
						commonsService.success('New flight saved with success!');
					} else if(response.data.exception == "CodeIsAlreadyInUseException"){
						commonsService.warning('This code is already in use by another flight.');
					}else{
						commonsService.error('Error saving flight!');
					}


				}).catch(function(data, status, headers, config){
			    	console.log(data);
			    });
			 }

			};

			$scope.cancel = function(){
				$scope.clear();
				$location.path('/flight-list');
			}

			$scope.clear = function(){
				$scope.flight.name = null;
			}


		} ]);
});