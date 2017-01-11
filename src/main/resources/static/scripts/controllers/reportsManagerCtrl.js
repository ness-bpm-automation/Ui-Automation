'use strict';
angular.module('mswFrontendApp')
    .controller('ReportManagerCtrl', ['$scope','$window','$rootScope','authService', '$http', '$location',  function ($scope, $window, 	$rootScope, authService, $http, $location) {
    	
    	$scope.username = userData.username;
    	
    	$scope.loginUser = self.userData;
    	
    	$rootScope.userLoggedIn=userData.username;
    	
    	$scope.isAuth = function () {
            return authService.isAuthenticated();
        }

        $scope.logOff = function () {
            return authService.logOff();
        }
        authService.checkLogin();
        
        console.log('Inside Report Manager Controller:::');
        
        $scope.getTestNgReport = function () {
            return 'views/TestNgDefault.html';
        }
        
        $scope.homePage = function () {
     	   $location.path('/home');
     	   console.log('Path Location in homepage::::'+$location.path());
        }
    }]
);