'use strict';
angular.module('mswFrontendApp')
    .controller('ExecutionManagerCtrl', ['$scope','$rootScope','authService', '$http', '$location',  function ($scope, $rootScope, authService, $http,$location) {
    	
    	console.log('Inside Execution Manager Controller:::');
    	
    	$scope.footerText = 'Copyright © 2016 Ness Technologies.All Rights Reserved.';
    	 
    	console.log('User logged in First name in Execution Manager:::::'+userData.username);
    	
    	$scope.isDisabled = false;
    	
    	$scope.loginUser = self.userData;
    	
    	$rootScope.userLoggedIn=userData.username;
    	
    	$http({
            url: "/rest/execution/getLatestSessionQueue",
            method: "GET",
         }).success(
               function (response) {
                   $scope.testSessionQueue = response;
                   	console.log('build*******'+$scope.testSessionQueue.testBuildNumber);
                   if($scope.testSessionQueue == ""){
                   	//$rootScope.buildNumber = "build"+buildNo;
                   	$scope.buildNumber = 1.0;
                   }else{
                   	$scope.val = $scope.testSessionQueue.testBuildNumber;
                   	console.log('In else root scope Queue build number::'+ $scope.val++); 
                   	$scope.buildNumber = $scope.val++;  
                   	console.log('In else root scope Queue build number::'+ $scope.buildNumber);  
                   }
                   if($scope.testSessionQueue == ""){
                   	//$rootScope.releaseNumber = "release" +releaseNo;
                   	$scope.releaseNumber = 1.0;
                       console.log('In if loop root scope Queue release number::'+$scope.releaseNumber); 
                   }else {
                   	$scope.newVal = $scope.testSessionQueue.testReleaseName;
                   	console.log('In else root scope Queue release number::'+ $scope.newVal++); 
                   	$scope.releaseNumber = $scope.newVal++;  
                   	console.log('In else root scope Queue Release number::'+ $scope.releaseNumber); 
                   }
           })       
    	
    	$scope.executeTestForSelParams = function () {    		
    		console.log('Inside Launch Testng in execution manager Controller:::');
    		
    		$scope.execution.testNgBuildNumber = $scope.buildNumber;
    		
    		$scope.execution.testNgReleaseNumber = $scope.releaseNumber;
    		
    		$scope.execution.testNgUserName = $rootScope.userLoggedIn;
    		
    		$scope.isDisabled = true;
    		
            var addTestNgParams = {};
            
            addTestNgParams = $scope.execution;
            
            $rootScope.loading = true;
            
            $http({
                url: "/rest/execution/saveTestSessionQueue",
                method: "POST",
                data: JSON.stringify(addTestNgParams),
                headers: {
                    'Content-Type': 'application/json'
                }    
             }).success(
                   function (response) {
                	   $rootScope.loading = false;
                	   $location.path('executionManagerLaunch');                  	   
               });             
        }
    	
    	$scope.username = userData.username;
    	
    	$scope.initialize = function () {
    		console.log('Inside Execution Manager Initialize:::::');
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
    }]
);