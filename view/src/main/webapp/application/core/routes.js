define(['app'], function (app) {
	app.config(function ($stateProvider, $urlRouterProvider) {
		// For any unmatched url, redirect to /login
		$urlRouterProvider.otherwise("/flight-list");
		//
		// Now set up the states
		$stateProvider

				.state('master', {
					templateUrl: "application/views/master/master.html",
					controller: "MasterCtrl",
					abstract: true,
					data: {
		            	requireLogin: false
		            },
					resolve: {
						loadMasterCtrl: ["$q", function ($q) {
								var deferred = $q.defer();
								require(['controllers/master.ctrl'], function () {
									deferred.resolve();
								});
								return deferred.promise;
							}],
					},
				})

				.state('flight', {
					url : '/flight',
					templateUrl: 'application/views/flight/flight.html',
					controller: 'FlightCtrl',
					parent: "master",
					data: {
		            	requireLogin: false
		            },
					resolve: {
						loadHomeCtrl: ["$q", function ($q) {
								var deferred = $q.defer();
								require(['controllers/flight.ctrl'], function () {
									deferred.resolve();
								});
								return deferred.promise;
							}],
					},
				})

				.state('flightList', {
					url : '/flight-list',
					templateUrl: "application/views/flight/flight_list.html",
					controller: "FlightListCtrl",
					parent: "master",
					data: {
		            	requireLogin: false
		            },
					resolve: {
						loadMasterCtrl: ["$q", function ($q) {
								var deferred = $q.defer();
								require(['controllers/flight_list.ctrl'], function () {
									deferred.resolve();
								});
								return deferred.promise;
							}],
					},
				})

				.state('pilot', {
					url : '/pilot',
					templateUrl: "application/views/pilot/pilot.html",
					controller: "PilotCtrl",
					parent: "master",
					data: {
		            	requireLogin: false
		            },
					resolve: {
						loadMasterCtrl: ["$q", function ($q) {
								var deferred = $q.defer();
								require(['controllers/pilot.ctrl'], function () {
									deferred.resolve();
								});
								return deferred.promise;
							}],
					},
				})

				.state('airplane', {
					url : '/airplane',
					templateUrl: "application/views/airplane/airplane.html",
					controller: "AirplaneCtrl",
					parent: "master",
					data: {
		            	requireLogin: false
		            },
					resolve: {
						loadMasterCtrl: ["$q", function ($q) {
								var deferred = $q.defer();
								require(['controllers/airplane.ctrl'], function () {
									deferred.resolve();
								});
								return deferred.promise;
							}],
					},
				});
	});

	app.run(['$rootScope',function ($rootScope) {
		$rootScope.$on('$stateChangeStart', function (event, toState, toParams) {
			var requireLogin = toState.data.requireLogin;

			if (requireLogin && typeof sessionStorage.user === 'undefined') {
				event.preventDefault();
			}
		});
	}]);
});