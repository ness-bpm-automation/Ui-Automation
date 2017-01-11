'use strict';
angular.module('mswFrontendApp')

    .controller('HomePageCtrl', ['$scope', '$filter', '$rootScope', 'authService', '$http', '$location', function ($scope, $filter, $rootScope, authService, $http, $location) {
    	
    	console.log('Inside Home page controller:::');
    	
    	$scope.footerText = 'Copyright © 2016 Ness Technologies.All Rights Reserved.';
    	
    	 $scope.logOff = function () {
             return authService.logOff();
         }
    	 
    	 $rootScope.userModuleDisabled = false;
    	
    	 $scope.loginUser = self.userData;
    	 
    	 $http({
			  url: "/rest/report/getReportData",
			  method: "GET",
			  headers: {
				'Content-Type': 'application/json'
			}    
    	 	}).success(
				function (response) {
					$rootScope.pegaTestLocation=response.pegaSourceLocation;
	             	$rootScope.restTestLocation=response.restSourceLocation
					$rootScope.pegaReportDate=response.pegaReportGeneratedDate;
	             	$rootScope.restApiReportDate=response.restApiReportGeneratedDate;
	             	$rootScope.automationReportFileName=response.automationReportFileName;
	             	$rootScope.restApiReportFileName=response.restApiReportFileName;
				});	    	
    	
    	var userPermissions = $scope.loginUser.permissions;
    	var split_str = userPermissions.split(",");
    	if (split_str.indexOf("ADMIN") !== -1) {
    		console.log('Logged in User as Admin User:::');
    	}else{
    		console.log('Logged in User as Normal User:::');
    		$rootScope.userModuleDisabled = true;
    	}

        $scope.userAdmin = function () {
    	    $location.path('userAdmin');           
        }
       
       $scope.configAdmin = function () {
   	    	$location.path('configmanager');
       }
       
       $scope.toolsAdmin = function () {
    	   $location.path('toolsmanager');
       }
       
       $scope.executionAdmin = function () {
   	    	$location.path('executionmanager');
       }
       
       $scope.reportsAdmin = function () {
      	    $location.path('reportsmanager');
       }
       
       $scope.executeResults = function () {
     	    $location.path('executionResults');
      }
    
    }]);
