'use strict';
angular.module('mswFrontendApp')
    .controller('GenerateTestngForSelParamsCtrl', ['$scope','$rootScope','authService', '$http', '$location',  function ($scope, $rootScope, authService, $http, $location) {
    	
    	$scope.username = userData.username;
    	
        $scope.isAuth = function () {
            return authService.isAuthenticated();
        }

        $scope.logOff = function () {
            return authService.logOff();
        }
        authService.checkLogin();
         
        $scope.homePage = function ($event) {
     	   $event.preventDefault();
     	   $event.stopPropagation();
     	   $location.path('home');
     	   //$window.history.back();
     	   console.log('Path Location in homepage::::'+$location.path());
        }
        
        $scope.homePage = function () {
     	   $location.path('home')
     	   console.log('Path Location in homepage::::'+$location.path());
        }
    }]
);