'use strict';
angular.module('mswFrontendApp')
    .controller('GenerateTestngManagerCtrl', ['$scope','$uibModal','$rootScope','authService', '$http', '$filter','$location',  function ($scope, $uibModal, $rootScope, authService, $http, $filter, $location) {
    	
    	$scope.username = userData.username;
    	
    	$scope.loginUser = self.userData;
    	
    	var checked_priorities = [];
    	
    	//$rootScope.loadTestNGExecution = false;
    	
    	$scope.selected_testqueues = [];
    	
    	$scope.selected_priorities = [];
    	
    	$scope.isAuth = function () {
            return authService.isAuthenticated();
        }

        $scope.logOff = function () {
            return authService.logOff();
        }
             
        authService.checkLogin();        
      
        $scope.executionFeaturemodel = [];
   	 
        $scope.executionFeaturedata = [];
        
        $scope.model = {};
        
        $scope.model.allItemsSelected = false;
        
        $scope.executionFeaturesettings = {
            smartButtonMaxItems: 3,
            smartButtonTextConverter: function(itemText, originalItem) {
                return itemText;
            }
        };
        
        {
       // $rootScope.loadTestNGExecution = true;
        $http({
            url: "/rest/executionqueue/getPriorities",
            method: "GET",
         }).success(
               function (response) {
                   $scope.priorities = response;
           })
           
           
           $http({
               url: "/rest/executionqueue/getAllQueueCustomList",
               method: "GET",
            }).success(
                  function (response) {
                	   $scope.customQueueList  = response;
                	   $scope.totalItems = $scope.customQueueList.length;
                       $scope.currentPage = 1;
                       $scope.numPerPage = 5;
                      // $rootScope.loadTestNGExecution = false;
                       
                       $scope.paginate = function(value) {
                           var begin, end, index;
                           begin = ($scope.currentPage - 1) * $scope.numPerPage;
                           end = begin + $scope.numPerPage;
                           index = $scope.customQueueList.indexOf(value);
                           return (begin <= index && index < end);
                         };
                         
                         $scope.change = function(list, active){
                        	    if (active)
                        	    	$scope.selected_testqueues.push(list);
                        	    else
                        	    	$scope.selected_testqueues.splice($scope.selected_testqueues.indexOf(list), 1);
                        	    console.log('Final Selected single entity  queue object:::'+$scope.selected_testqueues);
                        	};
                            	        
              	        // This executes when checkbox in table header is checked
              	      $scope.selectAll = function () {
           	        	// Loop through all the entities and set their isChecked property
           	        	for (var i = 0; i < $scope.customQueueList.length; i++) {
           	        		    $scope.customQueueList[i].isChecked = $scope.model.allItemsSelected 
           	        			$scope.selected_testqueues.push($scope.customQueueList[i]); 
           	        	} 
           	        	$scope.selectObjectType(); 
           	        	console.log('Final Selected All custom queue object :::'+$scope.selected_testqueues);
           	        };
           	        
           	  // Fired when an object type in the list is checked
           	     $scope.selectObjectType = function () {
           	          $scope.model.allItemsSelected = true;
           	         for (var i = 0; i < $scope.customQueueList.length; i++) {
           	             if (!$scope.customQueueList[i].isChecked) { 
           	                  $scope.selected_testqueues.splice($scope.customQueueList[i].value);
           	                  $scope.model.allItemsSelected = false;
           	             } 
           	             else {
           	            	 if($scope.selected_testqueues.indexOf($scope.customQueueList[i].value) == -1) {
           	            		$scope.selected_testqueues.push($scope.customQueueList[i].value);
           	            		}
           	             }
           	             
           	         }
           	     };
             	        angular.forEach($scope.customQueueList, function(filterObj , filterIndex) {
             	        	angular.forEach(filterObj, function(value , key) {
             	        		//console.log('Object Values:::::'+value+"--"+key);                	                           	                  	            
             	        	})
             	        })                       
              })
              
              $http({
                  url: "/rest/executionqueue/getAllQueueFeatureList",
                  method: "GET",
               }).success(
                     function (response) {
                         $scope.featuresList  = response;            
                         $scope.selected = {
                                 priorities: []
                          };
                         angular.forEach($scope.featuresList, function (value, index) {
                             $scope.executionFeaturedata.push({ id: value, label: value });
                         });
                 })      
        }   
        
        $scope.filter = {};
        $scope.filter.feature = "";
        
        $scope.customFilter = function (data) {
            if ((!$scope.filter.feature === $scope.filter.feature) ) {
              return true;
            } else {
              return false;
            }
          };
        
        $scope.sort = function(keyname){
            $scope.sortKey = keyname;   //set the sortKey to the param passed
            $scope.reverse = !$scope.reverse; //if true make it false and vice versa
        }    
        
                       
     // remove Queue
        $scope.removeQueue = function(index) {
        	console.log("Under delete queue row index:::"+ index);
        	$scope.customQueueList.splice(index, 1);
        	
        	$scope.totalItems = $scope.customQueueList.length;
            $scope.currentPage = 1;
            $scope.numPerPage = 3;
            
            $scope.paginate = function(value) {
                var begin, end, index;
                begin = ($scope.currentPage - 1) * $scope.numPerPage;
                end = begin + $scope.numPerPage;
                index = $scope.customQueueList.indexOf(value);
                return (begin <= index && index < end);
              };
        };
                       
        $scope.homePage = function ($event) {
     	   console.log('Inside back to home page:::::');
     	   $event.preventDefault();
     	   $event.stopPropagation();
     	   $location.path('home');
     	   //$window.history.back();
     	   console.log('Path Location in homepage::::'+$location.path());
        }
        
        $scope.homePage = function () {
     	   console.log('Inside back to home page:::::');
     	   $location.path('home')
     	   console.log('Path Location in homepage::::'+$location.path());
        }
                
         $scope.addPriority = function(priority) {        	 
        	 if (checked_priorities.indexOf(priority) === -1) {
                 checked_priorities.push(priority);
             } else {
                 checked_priorities.splice(checked_priorities.indexOf(priority), 1);
             }   
            $scope.selected_priorities = checked_priorities;            
          };   
          
          $scope.checkboxModel = {value1 : ''};
          
          $scope.launchTestNg = function () {
        	  console.log('Selected Test Queues array::::::'+$scope.selected_testqueues);
        	  var tempdata = [];
        	  angular.forEach($scope.selected_testqueues, function(filterObj , filterIndex) {
                	        angular.forEach(filterObj, function(value , key) {
                	            console.log('Object Values:::::'+key+"--"+value);  
                	            tempdata.push({key : value});  
                	        })
                 })      
              
              if($scope.checkboxModel.value1 == true){
            	  $scope.checkBoxAllChecked = 'All';
              }else{
            	  $scope.checkBoxAllChecked = '';
              }        
        	  console.log('Temp Data:::::'+tempdata);
        	  
              console.log('after adding Checkbox Value:::::'+$scope.checkBoxAllChecked);
              
              console.log('Selected Priorities array::::::'+$scope.selected_priorities); 
        	      	  
        	  console.log('Test suite file name:::'+$scope.testSuiteFileName);
        	  
        	  if($scope.checkBoxAllChecked == null || $scope.checkBoxAllChecked =='' ){
        		  $scope.checkBoxAllChecked =null;
        	  }
        	  
        	  if($scope.selected_priorities == null || $scope.selected_priorities == ''){
        		  $scope.selected_priorities =[null];
        	  }
        	  
        	  if($scope.selected_testqueues == '' || $scope.selected_testqueues.length == 0){
        		  console.log('Inside test queue settings:::');
        		  $scope.selected_testqueues = [null];
        	  }
        	   
              var testNgObj = {
                      priorities: $scope.selected_priorities,
                      allcheckbox:$scope.checkBoxAllChecked,
                      TestExecutionQueue:$scope.selected_testqueues,
                      filename:$scope.testSuiteFileName
                  }; 
             $http({
                  //url: "/rest/executionqueue/saveTestNgXml/" + $scope.testSuiteFileName + "/" + $scope.checkBoxAllChecked + "/" +$scope.selected_testqueues + "/"+$scope.selected_priorities,
                  url: "/rest/executionqueue/saveTestNgXml/"  +$scope.testSuiteFileName + "/" + $scope.checkBoxAllChecked,
                  method: "POST",
                  //data: JSON.stringify(testNgObj), 
                  headers: {
                      'Content-Type': 'application/json'
                  }    
               }).success(
                     function (response) {
                  	   console.log('Testng xml created succesfully');
                 });            
               $scope.checkBoxAllChecked ='';
          	   $location.path('generateTestngXmlForSelParams');           
          }        
        	        
    }]
);