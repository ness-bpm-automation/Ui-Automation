'use strict';
angular.module('mswFrontendApp')
    .controller('menuCtrl', ['$scope', 'authService', '$http', '$window', function LoginCtrl($scope, authService, $http, $window) {
        $scope.isAuth = function () {
            return authService.isAuthenticated();
        }

        $scope.logOff = function () {
            return authService.logOff();
            $window.location.reload(); 
        }
    }]);
