'use strict';
angular.module('mswFrontendApp')
    .controller('ConfigManagerSuccessCtrl', ['$scope','$rootScope','authService', '$http', '$location',  function ($scope, $rootScope, authService, $http,$location) {
    	
    	console.log('Inside Config Manager successController:::');
    	
    	$scope.footerText = 'Copyright © 2016 Ness Technologies.All Rights Reserved.';
    	
    	$scope.loginUser = self.userData;
    	 
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
        }
        
        $scope.backPage = function () {
      	   $window.history.back();
         }
    }]
);