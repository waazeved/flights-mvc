define([ 'app' ], function(app) {
	app.controller('AirplaneCtrl', [ '$scope', '$stateParams', '$http', '$location', 'commonsService', 'CrudService',
		function($scope, $stateParams, $http, $location, commonsService, CrudService) {

		$scope.airplane = {
                name : null
        };

		$scope.init = function(){
			$scope.clear();
		}


		$scope.save = function(){

			if(_.isEmpty($scope.airplane.name)){
				commonsService.warning('Name can not be empty.');
			}
			else{

				CrudService.airplane.save($scope.airplane).then(function(response) {

					if(response.data.success == true){
						$scope.clear();
						commonsService.success('New airplane saved with success!');
					} else if(response.data.exception == "NameIsAlreadyInUseException"){
						commonsService.warning('This name is already in use by another airplane.');
					}else{
						commonsService.error('Error saving aircraft!');
					}


				}).catch(function(data, status, headers, config){
			    	console.log(data);
			    	commonsService.error('Error saving aircraft!');
			    });
			 }

			};

			$scope.cancel = function(){
				$scope.clear();
				$location.path('/flight-list');
			}

			$scope.clear = function(){
				$scope.airplane.name = null;
			}

		} ]);
});