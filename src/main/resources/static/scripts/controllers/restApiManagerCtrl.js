'use strict';
angular.module('mswFrontendApp')
    .controller('RestApiManagerCtrl', ['$scope','$rootScope','authService', '$http', '$location',  function ($scope, $rootScope, authService, $http, $location) {
    	
    	console.log('Inside Rest Api Manager Controller:::');
    	
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
        
        $scope.launchRestTest = function () {

    		console.log('Inside Launch Rest test in config manager Controller:::');
    		
    		$scope.isDisabled = true;
    		
    		$rootScope.loading = true;    		

    		$http({
    			 url: "/rest/config/launchRestApiTest",
    			 method: "POST",
    			headers: {
    				'Content-Type': 'application/json'
    			}    
    		}).success(
    				function (response) {
    					$rootScope.loading = false;
    					$location.path('restApiManagerSuccess');
    				});		
    	}
               
        $scope.homePage = function () {
     	   $location.path('home')
     	   console.log('Path Location in homepage::::'+$location.path());
        }
    }]
);