'use strict';
angular.module('mswFrontendApp')
    .controller('MainCtrl', ['$scope','$rootScope','authService',  function ($scope, $rootScope, authService) {
        authService.checkLogin();
        
        $scope.logOff = function () {
            return authService.logOff();
            $window.location.reload(); 
        }
        
        $scope.homePage = function ($event) {
     	   console.log('Inside back to home page:::::');
     	   $event.preventDefault();
     	   $location.path('/home');     	   
     	   console.log('homepage path location::::'+$location.path());
        }
    }]
);
