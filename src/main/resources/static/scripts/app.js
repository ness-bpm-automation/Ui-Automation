'use strict';
/**
 *
 * Main module of the application.
 */
var automationApp=angular.module('mswFrontendApp', ['ui.bootstrap','angularjs-dropdown-multiselect',
    'ngAnimate',
    'ngCookies',
    'ngResource',
    'ngRoute',
    'ngSanitize',
    'ngTouch'
  ]);

automationApp.config(function ($routeProvider) {
    $routeProvider
      .when('/', {
        templateUrl: 'views/login.html',
        controller: 'LoginCtrl'
      })
      .when('/home', {
        templateUrl: 'views/home.html',
        controller: 'HomePageCtrl'
      })
      .when('/userAdmin', {
        templateUrl: 'views/users.html',
        controller: 'UsersCtrl'
      })
      .when('/configmanager', {
        templateUrl: 'views/configManager.html',
        controller: 'ConfigManagerCtrl'
      })
      .when('/configManagerSuccess', {
        templateUrl: 'views/configManagerSuccess.html',
        controller: 'ConfigManagerSuccessCtrl'
      })
      .when('/restApiManager', {
        templateUrl: 'views/restApiManager.html',
        controller: 'RestApiManagerCtrl'
      })
      .when('/restApiManagerSuccess', {
        templateUrl: 'views/restApiManagerSuccess.html',
        controller: 'RestApiManagerSuccessCtrl'
      })
      .when('/toolsmanager', {
        templateUrl: 'views/toolsManager.html',
        controller: 'ToolsManagerCtrl'
      })
      .when('/generateTestngXml', {
        templateUrl: 'views/testNgManager.html',
        controller: 'GenerateTestngManagerCtrl'
      })
       .when('/generateTestngXmlForSelParams', {
        templateUrl: 'views/testNgSelectedParmas.html',
        controller: 'GenerateTestngForSelParamsCtrl'
      })
      .when('/reportsmanager', {
        templateUrl: 'views/reports.html',
        controller: 'ReportManagerCtrl'
      })
      .when('/executionResults', {
        templateUrl: 'views/automationResults.html',
        controller: 'ResultsManagerCtrl'
      })      
      .when('/executionmanager', {
        templateUrl: 'views/executionManagerMain.html',
        controller: 'ExecutionManagerMainCtrl'
      })
      .when('/invokeExecutionManager', {
        templateUrl: 'views/executionManager.html',
        controller: 'ExecutionManagerCtrl'
      })
       .when('/executionManagerLaunch', {
        templateUrl: 'views/executionManagerLaunch.html',
        controller: 'ExecutionManagerLaunchCtrl'
      })
       .when('/executionManagerSuccess', {
        templateUrl: 'views/executionManagerSuccess.html',
        controller: 'ExecutionManagerSuccessCtrl'
      })
       .when('/jenkinsJobexecManagerSuccess', {
        templateUrl: 'views/jenkinsJobExecManagerSuccess.html',
        controller: 'JenkinsJobExecManagerSuccessCtrl'
      })
      .otherwise({
        redirectTo: '/'
      });
  });


automationApp.factory('modalService', ['$uibModal', function($uibModal) {
	  return {
	    openMenuModal: function(templateLink, windowAnimation, inputJobName) {	 
	        var modalObj = $uibModal.open({
	            templateUrl: templateLink,
	            backdrop: 'static',
	            windowClass: windowAnimation,
	            size: 'lg',
	            controller: function($scope,$modalInstance){
	            $modalInstance.jobSelected = inputJobName;
	            $scope.jobSelected = inputJobName;
	              $scope.ok = function(id){
	                //Process OK Button Click
	                 $modalInstance.close(); 
	              },
	               $scope.cancel = function(){
	                $modalInstance.dismiss('cancel');
	              }
	            },
	            size: 'md',
	            keyboard: true,
	            resolve: {
	              someData: function () {	            	 
	                return 'Return some Data';
	              }
	          }
	        });

	    }
	};
	}])