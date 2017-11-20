define([ 'app' ], function(app) {
	app.controller('FlightCtrl', [ '$scope', '$stateParams', '$http', '$location', 'commonsService', 'CrudService',
		function($scope, $stateParams, $http, $location, commonsService, CrudService) {

		$scope.flight = {
                code : null,
                departureCity : null,
                arrivalCity : null,
                start : null,
                end : null,
                status : null,
                pilotId : null,
				airplaneId : null
        };

		$scope.airplanes = [];
		$scope.pilots = [];
		$scope.airplane = null;
		$scope.pilot = null;

		$scope.init = function(){
			$scope.clear();
			$scope.loadAirplanes();
			$scope.loadPilots();
		}

		$scope.loadAirplanes = function(){
			if($scope.airplanes == null || $scope.airplanes.length == 0){
				CrudService.airplane.findAll().then(function(response) {
					$scope.airplanes = response.data;
				}).catch(function(error) {
					console.log(error);
					commonsService.error('Error searching airplanes!');
				});
			}
		};

		$scope.loadPilots = function(){
			if($scope.pilots == null || $scope.pilots.length == 0){
				CrudService.pilot.findAll().then(function(response) {
					$scope.pilots = response.data;
				}).catch(function(error) {
					console.log(error);
					commonsService.error('Error searching pilots!');
				});
			}
		};


		$scope.save = function(){

			console.log($scope.flight.pilotId );

			var regexDateTime = /[0-9]{4}-(0[1-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1]) (2[0-3]|[01][0-9]):[0-5][0-9]/;

			if(_.isEmpty($scope.flight.code)){
				commonsService.warning('Code can not be empty.');
			}
			else if(_.isEmpty($scope.flight.start) || !$scope.flight.start.match(regexDateTime)){
				commonsService.warning('Start date time must be in format: YYY-MM-DD HH:MM.');
			}
			else if(_.isEmpty($scope.flight.end) || !$scope.flight.end.match(regexDateTime)){
				commonsService.warning('End date time must be in format: YYY-MM-DD HH:MM.');
			}
			else if(_.isEmpty($scope.flight.status)){
				commonsService.warning('Status can not be empty.');
			}
			else if(_.isEmpty($scope.flight.departureCity)){
				commonsService.warning('Departure City can not be empty.');
			}
			else if(_.isEmpty($scope.flight.arrivalCity)){
				commonsService.warning('Arrival City can not be empty.');
			}
			else if(_.isEmpty($scope.flight.departureCity)){
				commonsService.warning('Status can not be empty.');
			}
			else if($scope.flight.pilotId == null){
				commonsService.warning('Pilot can not be empty.');
			}
			else if($scope.flight.airplaneId == null){
				commonsService.warning('Airplane can not be empty.');
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
			    	commonsService.error('Error saving flight!');
			    });
			 }

			};

			$scope.chooseAirplane = function(){
				$scope.flight.airplaneId = $scope.airplane.id;
			};

			$scope.choosePilot = function(){
				$scope.flight.pilotId = $scope.pilot.id;
			};

			$scope.cancel = function(){
				$scope.clear();
				$location.path('/flight-list');
			}

			$scope.clear = function(){
				$scope.flight.code = null;
				$scope.flight.departureCity = null;
				$scope.flight.arrivalCity = null;
				$scope.flight.start = null;
				$scope.flight.end = null;
				$scope.flight.status = null;
				$scope.flight.pilotId = null;
				$scope.flight.airplaneId = null;
				$scope.airplane = null;
				$scope.pilot = null;
			}


		} ]);
});