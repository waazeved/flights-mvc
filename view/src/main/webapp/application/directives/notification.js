define(['./module'], function (directives) {
    directives.directive('notification', function() {
    	return {
    		restrict: 'A',
    		scope: {
    			listNotifications: '=',
    			templateNotifications: '='
    		},
    		link: function (scope, el, attrs) {
    			scope.notifications = function(scope) {
                	var notificationsEl = "";
        			var notificationsRev = scope.listNotifications.slice().reverse();
        			for (var i = 0; i < notificationsRev.length; i++) {
        				var notif = notificationsRev[i];
        				notificationsEl += scope.templateNotifications.replace('{{description}}', notif.description);
        			}
        			return notificationsEl;
                };
                $(el).click(function() {
                	$(el).popover({
                        trigger: 'click',
                        html: true,
                        title: 'Notificações',
                        content: scope.notifications(scope),
                        placement: attrs.popoverPlacement
                    });
                	$(el).popover('toggle');
                });

            }
    	};
    });
});