'use strict';
angular.module('mswFrontendApp')
    .controller('LoginCtrl', ['$scope', 'authService', '$http','$location', function LoginCtrl($scope, authService, $http, $location) {

    	$scope.footerText = 'Copyright © 2016 Ness Technologies.All Rights Reserved.';
    	
    	$scope.loginfailed = false;
        $scope.user = null;

        $scope.login = function (user) {
            $http({
                method: 'POST',
                url: '/login',
                data: "username=" + encodeURIComponent(user.username) + "&password=" + encodeURIComponent(user.password),
                headers: {'Content-Type': 'application/x-www-form-urlencoded'}
            }).success(function (data) {
                authService.checkLogin().success(function(){
                $scope.user = data;
                $location.path('home');
                }).error(function (){
                $scope.loginfailed = true;
                });

            }).error(function () {
                $scope.checkLogin();
            });
        };

    }])


;
