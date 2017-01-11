'use strict';
angular.module('mswFrontendApp')
    .controller('ToolsManagerCtrl', ['$scope','$timeout','$rootScope','authService', '$http', '$location',  function ($scope, $timeout, $rootScope, authService, $http, $location) {
    	console.log('User logged in First name:::::'+userData.username);
    	
    	$scope.username = userData.username;
    	
    	$scope.loginUser = self.userData;
    	
        $scope.isAuth = function () {
            return authService.isAuthenticated();
        }

        $scope.logOff = function () {
            return authService.logOff();
        }
        
       /* $scope.testNgXmlAdmin = function(){
            $scope.delay = true;
            $http({
                url: "/rest/executionqueue/populatequeue",
                method: "POST",
                //data: JSON.stringify(""),
                headers: {
                    'Content-Type': 'application/json'
                }    
             }).success(
                   function (response) {
                	   console.log('Testng Execution queue created succesfully');
               });
            $timeout(function(){            	
        	    $location.path('generateTestngXml');  
                $scope.delay = false;
            }, 65000);
        };*/       
        $scope.testNgXmlAdmin = function () {
        	 $rootScope.loading = true;
        	$http({
                url: "/rest/executionqueue/populatequeue",
                method: "POST",
                //data: JSON.stringify(""),
                headers: {
                    'Content-Type': 'application/json'
                }    
             }).success(
            		   function (response) {            			   
            			$rootScope.loading = false;
                    	$location.path('generateTestngXml');    
            		    console.log('Testng Execution queue created succesfully');                	   
               });
    	              
        }
        
        authService.checkLogin();
        console.log('Inside tools Manager Controller::::');
        
        $scope.homePage = function ($event) {
     	   console.log('Inside back to home page:::::');
     	   $event.preventDefault();
    	   $event.stopPropagation();
     	   $location.path('home');
     	   console.log('Path Location in homepage::::'+$location.path());
        }
    }]
);