define(['app'], function (app) {
	app.controller('MasterCtrl', ['$scope', '$translate', 'toastr', 'AclService', 'CrudService', 'uibDatepickerPopupConfig', 'tmhDynamicLocale', 'Idle', '$location', '$uibModal', '$uibModalStack',
		function ($scope, $translate, toastr, AclService, CrudService, uibDatepickerPopupConfig, tmhDynamicLocale, Idle, $location, $uibModal, $uibModalStack) {



			$scope.currentUser = undefined;
			$scope.username = undefined;


			$scope.hasUser = function() {
				try {
					var result = sessionStorage.user !== "undefined" && sessionStorage.user !== undefined;
					if (result) {
						$scope.username = JSON.parse(sessionStorage.user).name;
					}
					return result;
				} catch(err) {
					return false;
				}

			};


			$scope.hasRole = function(role) {
				var result = sessionStorage.user !== "undefined" && sessionStorage.user !== undefined;
				if (result) {
					var roles = JSON.parse(sessionStorage.user).roles;
					for(var i = 0; i < roles.length; i++){
						var roleName = roles[i].name;
						if (roleName == role){
							return true;
						}
					}
				}
				return false;
			};

			$scope.loadButton = function(id, loading, loadingText, normalText) {
				if (loading) {
					$(id).attr('disabled', 'disabled');
					$(id).text(loadingText);
				} else {
					$(id).removeAttr('disabled');
					$(id).text(normalText);
				}
			};

			$scope.download = function(data, filename) {
				var a = document.createElement("a");
				document.body.appendChild(a);
				a.setAttribute("style", "display: none");
				var url = "data:application/octet-stream;charset=utf-16le;base64," + data;
				a.setAttribute("href", url);
				a.download = filename;
				a.click();
				window.URL.revokeObjectURL(url);
			};

			$(function () {
				var url = window.location.href.split("/")[3];
				url = url.replace("#", ""), $("#sidebar li a").map(function () {
					$(this).attr("href") === url && ($(this).addClass("active").closest(".sidebar-submenu").collapse("show"), $(this).closest(".sidebar-dropdown").find(".sidebar-link").addClass("active"));
				});
				var sidebarLinks = $("#sidebar a.sidebar-link, #sidebar .sidebar-submenu-link, .header-logo"), sidebarLinksClass = $("#sidebar .sidebar-link, #sidebar .sidebar-submenu-link");
				sidebarLinks.click(function () {
					sidebarLinksClass.removeClass("active"), $(this).addClass("active").closest(".sidebar-submenu").collapse("show"), $(this).closest(".sidebar-dropdown").find(".sidebar-link").addClass("active")
				}),
					$(document).ready(function () {
						$(".sidebar-overlay").click(function () {
							$(".sidebar-overlay").fadeOut(), $("#sidebar").removeClass("active"), $(".navbar-toggle").removeClass("active"), $("body").removeClass("sidebar-collapse-active")
						}),
						$(".btn-sidebar-collapse").click(function (s) {
							s.preventDefault(), $(this).toggleClass("active"), $("body").toggleClass("sidebar-collapse-active"), $("#sidebar").toggleClass("active"), $(".sidebar-overlay").fadeToggle()
						})
					});

				$("#sidebar").mouseover(function () {
					$(this).hasClass("active") ? $(this).addClass("sidebar-collapse-hover") : $(this).removeClass("sidebar-collapse-hover")
				}).mouseout(function () {
					$(this).removeClass("sidebar-collapse-hover");
				});

				$('ul[id*="sidebar-"]').on('show.bs.collapse', function () {
					$('ul[id*="sidebar-"][id!="' + $(this).attr('id') + '"]').collapse('hide');
				});
				$('.header-logo').click(function () {
					$('ul[id*="sidebar-"][id!="' + $(this).attr('id') + '"]').collapse('hide');
				});

				$(".background-svg").css("height", document.getElementsByTagName('body')[0].scrollHeight + "px");

				if ($(window).width() < 1024) {
					$('a.sidebar-submenu-link, .unique-item').click(function () {
						$('.btn-sidebar-collapse').click();
					});
				};
			});

		}]);
});