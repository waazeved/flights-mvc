define([ 'app' ], function(app) {
	app.controller('PilotCtrl', [ '$scope', '$stateParams', '$http', '$location', 'commonsService', 'CrudService',
		function($scope, $stateParams, $http, $location, commonsService, CrudService) {

		$scope.pilot = {
                name : null
        };

		$scope.init = function(){
			$scope.clear();
		}


		$scope.save = function(){

			if(_.isEmpty($scope.pilot.name)){
				commonsService.warning('Name can not be empty.');
			}
			else{

				CrudService.pilot.save($scope.pilot).then(function(response) {

					if(response.data.success == true){
						$scope.clear();
						commonsService.success('New pilot saved with success!');
					} else{
						commonsService.error('Error saving pilot!');
					}


				}).catch(function(data, status, headers, config){
			    	console.log(data);
			    	commonsService.error('Error saving pilot!');
			    });
			 }

			};

			$scope.cancel = function(){
				$scope.clear();
				$location.path('/flight-list');
			}

			$scope.clear = function(){
				$scope.pilot.name = null;
			}

		} ]);
});