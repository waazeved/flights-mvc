requirejs.config({
	waitSeconds : 200,
	baseUrl: 'application',
	paths: {
		'app': 'core/app',
		'jquery': 'vendor/jquery/dist/jquery.min',
		'bootstrap': 'vendor/bootstrap/dist/js/bootstrap.min',
		'angular': 'vendor/angular/angular',
		'angular-ui-router': 'vendor/angular-ui-router/release/angular-ui-router.min',
		'angular-translate': 'vendor/angular-translate/angular-translate.min',
		'uiBootstrap': 'vendor/angular-bootstrap/ui-bootstrap-tpls',
		'routes': 'core/routes',
		'translate': 'core/translate',
		'interceptor': 'core/interceptor',
		'angular-dynamic-locale': 'vendor/angular-dynamic-locale/dist/tmhDynamicLocale.min',
		'domReady': 'vendor/requirejs-domready/domReady',
		'lodash': 'vendor/lodash/lodash',
		'ng-file-upload': 'vendor/ng-file-upload/ng-file-upload',
		'angular-ui-tinymce': 'vendor/angular-ui-tinymce/src/tinymce',
		'tinymce': 'vendor/tinymce-dist/tinymce.min',
		'angular-idle': 'vendor/ng-idle/angular-idle',
		'angular-toastr': 'vendor/angular-toastr/dist/angular-toastr.tpls',
		'angular-animate': 'vendor/angular-animate/angular-animate.min',
		'angular-http-loader': 'vendor/angular-http-loader/app/package/js/angular-http-loader',
		'angular-sanitize': 'vendor/angular-sanitize/angular-sanitize.min',
		'angular-acl': 'vendor/angular-acl/angular-acl.min',
		'moment': 'vendor/moment/moment',
		//'DataTables' : 'vendor/DataTables/media/js/jquery.dataTables',
		'chosen':'vendor/chosen/chosen.jquery',
		'angular-chosen':'vendor/angular-chosen/dist/angular-chosen'
	},
	shim: {
		'angular': {
			exports: 'angular'
		},
		'angular-ui-router': {
			deps: ['angular'],
			exports: 'angular-route'
		},
		'angular-translate': {
			deps: ['angular'],
			exports: 'angular-translate'
		},
		'angular-dynamic-locale': {
			deps: ['angular'],
			exports: 'angular-dynamic-locale'
		},
		'bootstrap': {
			deps: ['jquery'],
			exports: 'bootstrap'
		},
		'uiBootstrap': {
			deps: ['angular', 'bootstrap'],
			exports: 'bootstrap'
		},
		'ng-file-upload': {
			deps: ['angular'],
			exports: 'ng-file-upload'
		},
		'angular-idle': {
			deps: ['angular'],
			exports: 'angular-idle'
		},
		'angular-toastr': {
			deps: ['angular'],
			exports: 'angular-toastr'
		},
		'angular-http-loader': {
			deps: ['angular'],
			exports: 'angular-http-loader'
		},
		'angular-animate': {
			deps: ['angular'],
			exports: 'angular-animate'
		},
		'angular-sanitize': {
			deps: ['angular'],
			exports: 'angular-sanitize'
		},
		'angular-acl':{
			deps:['angular'],
			exports: 'angular-acl'
		},
		/*'DataTables': {
            deps: ['jquery'],
            exports: 'DataTables'
        },*/
		'chosen': {
			deps: ['jquery'],
			exports: 'chosen'
		},
		'angular-chosen':{
			deps: ['angular','chosen'],
			exports: 'angular-chosen'
		}
	}
});

require(['domReady!', 'angular', 'app', 'routes', 'bootstrap', 'translate', 'angular-sanitize', 'angular-acl', 'angular-chosen', 'interceptor'], function (document, angular) {
	angular.bootstrap(document, ['app']);
});