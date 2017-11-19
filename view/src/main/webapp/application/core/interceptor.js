'use strict';
define(['app'], function (app) {

    app.config(['$httpProvider', function ($httpProvider) {

        var injectParams = ['$q', '$rootScope'];

        function interceptor($q, $injector) {

	        var hideLoading = function () {
	        	$injector.$broadcast("loader_hide");
	        }

	        var showLoading = function () {
	        	$injector.$broadcast("loader_show");
	        }

	        return {
	            request: function (config) {

	            	showLoading();

	            	var data = config.data;
	                return config;
	            },
	            requestError: function (rejection) {
	            	hideLoading();
	                return $q.reject(rejection);
	            },
	            response: function (response) {
	            	hideLoading();
	                return response;
	            },
	            responseError: function (rejection) {
	            	hideLoading();
	                switch (rejection.status) {
		                case 404:
	                        goToErrorPage(rejection.status);
	                        rejection.data = 'Service Unavailable';
	                        break;
	                    case 409:
	                        break;
	                    case 500:
	                        break;
		                }
	                return $q.reject(rejection);
	            }
	        };
	    }

        interceptor.$inject = injectParams;

        $httpProvider.interceptors.push(interceptor);
    }]);
});
