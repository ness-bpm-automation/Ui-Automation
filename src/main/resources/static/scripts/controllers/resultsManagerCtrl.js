'use strict';
angular.module('mswFrontendApp')
    .controller('ResultsManagerCtrl', ['$scope','$rootScope','authService', '$location',  function ($scope, $rootScope, authService, $location) {
    	
    	console.log('User logged in First name:::::'+userData.username);
    	
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
        console.log('Inside testNg results Manager Controller:::');
        
        $scope.homePage = function () {
     	   $location.path('home')
     	   console.log('Path Location in homepage::::'+$location.path());
        }
    }]
);