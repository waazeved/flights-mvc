define([
		'angular',
		'angular-ui-router',
		'angular-translate',
		'angular-dynamic-locale',
		'uiBootstrap',
		'lodash',
		'ng-file-upload',
		'angular-animate',
		'angular-toastr',
		'angular-idle',
		'angular-http-loader',
		'chosen',
		'angular-chosen',
		'services/index',
		'directives/index'],
	function (angular) {
		'use strict';
		var app = angular.module('app', [
			'ui.router',
			'pascalprecht.translate',
			'tmh.dynamicLocale',
			'ui.bootstrap',
			'ngAnimate',
			'toastr',
			'ngIdle',
			'ng.httpLoader',
			'localytics.directives',
			'ngSanitize',
			'app.services',
			'app.directives',
			'mm.acl'
		]);

		//###### App config #######//
		app.config([
			'$controllerProvider',
			'$compileProvider',
			'$filterProvider',
			'$provide',
			function ($controllerProvider, $compileProvider, $filterProvider, $provide) {
				app.controller = $controllerProvider.register;
				app.directive = $compileProvider.directive;
				app.filter = $filterProvider.register;
				app.factory = $provide.factory;
				app.service = $provide.service;
			}
		]);

		app.config(function (tmhDynamicLocaleProvider) {
			var pattern = 'application/vendor/angular-i18n/angular-locale_{{locale}}.js';
			tmhDynamicLocaleProvider.localeLocationPattern(pattern);
		});

		app.config(['KeepaliveProvider', 'IdleProvider',
			function (KeepaliveProvider, IdleProvider) {
				IdleProvider.idle(20 * 60);
				IdleProvider.timeout(30);
				KeepaliveProvider.interval(10);
			}]);

		app.config(function (toastrConfig) {
			angular.extend(toastrConfig, {
				autoDismiss: false,
				containerId: 'toast-container',
				maxOpened: 0,
				showCloseButton: true,
				newestOnTop: true,
				positionClass: 'toast-top-center',
				progressBar: true,
				preventDuplicates: false,
				preventOpenDuplicates: false,
				target: 'body'
			});
		});

		app.config(['httpMethodInterceptorProvider',
			function (httpMethodInterceptorProvider) {
				httpMethodInterceptorProvider.whitelistLocalRequests();
			}
		]);

		app.config(['AclServiceProvider', function (AclServiceProvider) {
			var myConfig = {
				storage: 'sessionStorage',
				storageKey: 'roles'
			};
			AclServiceProvider.config(myConfig);
			AclServiceProvider.resume();
		}]);

		app.config(function(IdleProvider, KeepaliveProvider) {
            // configure Idle settings
            IdleProvider.idle(20 * 60); // in seconds
            IdleProvider.timeout(30); // in seconds
            KeepaliveProvider.interval(10); // in seconds
        });
		
		app.directive("loader", function ($rootScope) {
		    return function ($scope, element, attrs) {
		        $scope.$on("loader_show", function () {
		            return element.removeClass('hide');
		        });
		         $scope.$on("loader_hide", function () {
		            return element.addClass('hide');
		        });
		    };
		});

		return app;
	});