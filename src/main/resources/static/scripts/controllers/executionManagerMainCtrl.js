'use strict';
angular.module('mswFrontendApp')
    .controller('ExecutionManagerMainCtrl', ['$scope','$timeout','$rootScope','authService', '$http', '$location',  function ($scope, $timeout, $rootScope, authService, $http, $location) {
    	console.log('User logged in First name:::::'+userData.username);
    	
    	$scope.username = userData.username;
    	
    	$scope.loginUser = self.userData;
    	
        $scope.isAuth = function () {
            return authService.isAuthenticated();
        }

        $scope.logOff = function () {
            return authService.logOff();
        }
        
        $scope.invokeUIExecutionManager = function () {
        	$location.path('invokeExecutionManager');    
        	console.log('Navigating to execution Manager');
        }
        
        $scope.invokeRestAPIExecutionManager = function () {
        	$location.path('restApiManager');    
        	console.log('Navigating to rest api Manager');
        }
        
        authService.checkLogin();
                
        $scope.homePage = function ($event) {
     	   $event.preventDefault();
    	   $event.stopPropagation();
     	   $location.path('home');
     	   console.log('Path Location in homepage::::'+$location.path());
        }
    }]
);