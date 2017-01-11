'use strict';
angular.module('mswFrontendApp')
    .controller('DropDownMultiSelCtrl', ['$scope','$rootScope','authService','$location',  function ($scope, $rootScope, authService, $location) {
    	
    	console.log('Inside dropdown multiselect Controller:::');
    	
    	$scope.username = userData.username;
    	
        $scope.isAuth = function () {
            return authService.isAuthenticated();
        }

        $scope.logOff = function () {
            return authService.logOff();
        }
        authService.checkLogin();
                
        $scope.homePage = function () {
     	   $location.path('home')
     	   console.log('Path Location in homepage::::'+$location.path());
        }        
      
    }]
);