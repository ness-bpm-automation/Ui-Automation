'use strict';
angular.module('mswFrontendApp')
    .controller('JenkinsJobExecManagerSuccessCtrl', ['$scope','$rootScope','authService', '$http', '$location',  function ($scope, $rootScope, authService, $http,$location) {
    	
    	console.log('Inside Jenkins JobExecution Manager successController:::');
    	
    	$scope.footerText = 'Copyright © 2016 Ness Technologies.All Rights Reserved.';
    	
    	$scope.loginUser = self.userData;
    	 
    	$scope.username = userData.username;
    	
    	$scope.initialize = function () {
    		$scope.loadData();
        }
    	
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
     	   console.log('Path Location in homepage::::'+$location.path());
        }
        
        $scope.backPage = function () {
      	   $window.history.back();
         }
    }]
);