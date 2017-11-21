var BASE_PATH = '/flights-view/';

define(['./module'], function (services) {
    services.service('CrudService', ['RestService', function (rest) {
            return {
            	flight: {
            		search: function(search, index, limit) {
            			return rest.get(BASE_PATH + 'flight/search/' + search + '/' + index + "/" + limit);
            		},
            		findAll: function(index, limit) {
            			return rest.get(BASE_PATH + 'flight/findAll/' + index + "/" + limit);
            		},
            		save: function(flight) {
            			return rest.post(BASE_PATH + 'flight/save',flight);
            		}
            	},
            	pilot: {
            		save: function(pilot) {
            			return rest.post(BASE_PATH + 'pilot/save',pilot);
            		},
            		findAll: function() {
            			return rest.get(BASE_PATH + 'pilot/findAll');
            		},
            	},
            	airplane: {
            		save: function(airplane) {
            			return rest.post(BASE_PATH + 'airplane/save',airplane);
            		},
            		findAll: function() {
            			return rest.get(BASE_PATH + 'airplane/findAll');
            		},
            	},

            };
        }]);
});