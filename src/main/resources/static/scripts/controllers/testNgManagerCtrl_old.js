'use strict';
angular.module('mswFrontendApp')
    .controller('GenerateTestngManagerCtrl', ['$scope','$rootScope','authService', '$http', '$filter','$location',  function ($scope, $rootScope, authService, $http, $filter, $location) {
    	console.log('User logged in First name:::::'+userData.username);
    	
    	$scope.username = userData.username;
    	
        $scope.isAuth = function () {
            return authService.isAuthenticated();
        }

        $scope.logOff = function () {
            return authService.logOff();
        }
        authService.checkLogin();
        
      
        $scope.filteredQueues = []
        ,$scope.currentPage = 1
        ,$scope.numPerPage = 3
        ,$scope.maxSize = 5;
        
        $scope.executionFeaturemodel = [];
   	 
        $scope.executionFeaturedata = [];
        
        $scope.executionFeaturesettings = {
            smartButtonMaxItems: 3,
            smartButtonTextConverter: function(itemText, originalItem) {
                return itemText;
            }
        };
        
        $scope.person = [];
        
        {
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
                	   angular.forEach($scope.customQueueList, function(filterObj , filterIndex) {
                	        angular.forEach(filterObj, function(value , key) {
                	            console.log('Object Values:::::'+value+"--"+key);
                	            $scope.person.push({key : value});
                	           /* $scope.person.push({
                	            	testCaseId: value.testCaseId,
                	            	testCaseClass: value.testCaseClass,
                	            	testCaseMethod:value.testCaseMethod
                	            });      */          	            
                	        })
                	    })                       
              }) 
              
              $http({
                  url: "/rest/executionqueue/getAllQueueFeatureList",
                  method: "GET",
               }).success(
                     function (response) {
                         $scope.featuresList  = response;                         
                         angular.forEach($scope.featuresList, function (value, index) {
                             $scope.executionFeaturedata.push({ id: value, label: value });
                         });
                 })      
        }      
        
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
        
        $scope.testNgForSelParams = function () {
    	    $location.path('generateTestngXmlForSelParams');           
        }
        
        	var sortingOrder = 'testCaseClass';
        	
        	// init
            $scope.sortingOrder = sortingOrder;
            $scope.reverse = false;
            $scope.filteredItems = [];
            $scope.groupedItems = [];
            $scope.itemsPerPage = 3;
            $scope.pagedItems = [];
            $scope.currentPage = 0;
            //console.log('Queue Items:::'+$scope.items);
            console.log('Custom Person queue::::'+$scope.person);
            $scope.items = [
                {"testCaseId":"1","testCaseClass":"com.nessq.pega.login.LoginTest9","testCaseMethod":"validLogin","testCaseGroup":"Group1","testCaseFeature":"feature1","testCaseCreatedBy":"P5109824","Action":""}, 
                {"testCaseId":"2","testCaseClass":"com.nessq.pega.login.LoginTest12","testCaseMethod":"invalidLogin","testCaseGroup":"Group1","testCaseFeature":"loginTest","testCaseCreatedBy":"P5109824","Action":""}, 
                {"testCaseId":"3","testCaseClass":"com.nessq.pega.login.DealOnboardingTest","testCaseMethod":"checkDatabase","testCaseGroup":"Fun Group","testCaseFeature":"feature2","testCaseCreatedBy":"P5103824","Action":""}, 
                {"testCaseId":"4","testCaseClass":"com.nessq.pega.login.LoginTest2","testCaseMethod":"createAccount","testCaseGroup":"Group3","testCaseFeature":"feature5","testCaseCreatedBy":"P5112824","Action":""}               
            ];

            var searchMatch = function (haystack, needle) {
                if (!needle) {
                    return true;
                }
                return haystack.toLowerCase().indexOf(needle.toLowerCase()) !== -1;
            };

            // init the filtered items
            $scope.search = function () {
                $scope.filteredItems = $filter('filter')($scope.items, function (item) {
                    for(var attr in item) {
                        if (searchMatch(item[attr], $scope.query))
                            return true;
                    }
                    return false;
                });
                // take care of the sorting order
                if ($scope.sortingOrder !== '') {
                    $scope.filteredItems = $filter('orderBy')($scope.filteredItems, $scope.sortingOrder, $scope.reverse);
                }
                $scope.currentPage = 0;
                // now group by pages
                $scope.groupToPages();
            };
            
            // calculate page in place
            $scope.groupToPages = function () {
                $scope.pagedItems = [];
                
                for (var i = 0; i < $scope.filteredItems.length; i++) {
                    if (i % $scope.itemsPerPage === 0) {
                        $scope.pagedItems[Math.floor(i / $scope.itemsPerPage)] = [ $scope.filteredItems[i] ];
                    } else {
                        $scope.pagedItems[Math.floor(i / $scope.itemsPerPage)].push($scope.filteredItems[i]);
                    }
                }
            };
            
            $scope.range = function (start, end) {
                var ret = [];
                if (!end) {
                    end = start;
                    start = 0;
                }
                for (var i = start; i < end; i++) {
                    ret.push(i);
                }
                return ret;
            };
            
            $scope.prevPage = function () {
                if ($scope.currentPage > 0) {
                    $scope.currentPage--;
                }
            };
            
            $scope.nextPage = function () {
                if ($scope.currentPage < $scope.pagedItems.length - 1) {
                    $scope.currentPage++;
                }
            };
            
            $scope.setPage = function () {
                $scope.currentPage = this.n;
            };
            
            $scope.pageChanged = function() {
                $log.log('Page changed to: ' + $scope.currentPage);
              };

            // functions have been describe process the data for display
            $scope.search();

            // change sorting order
            $scope.sort_by = function(newSortingOrder) {
                if ($scope.sortingOrder == newSortingOrder)
                    $scope.reverse = !$scope.reverse;

                $scope.sortingOrder = newSortingOrder;
            };
        
    }]
);