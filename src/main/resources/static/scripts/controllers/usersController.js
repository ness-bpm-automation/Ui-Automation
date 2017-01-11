'use strict';
angular.module('mswFrontendApp')
    .controller('UsersCtrl', ['$scope', '$cookies', '$filter', '$route', '$uibModal','$rootScope', 'authService', '$http','$location','$window','$timeout', function LoginCtrl($scope, $cookies, $filter, $route, $uibModal, $rootScope, authService, $http, $location, $window, $timeout) {
    	
    	console.log('User logged in First name:::::'+userData.username);
    	
    	$scope.footerText = 'Copyright © 2016 Ness Technologies.All Rights Reserved.';
    	
    	$scope.username = userData.username;
    	
    	$scope.loginUser = self.userData;
    	
    	$cookies.userName = userData.username;
    	
    	$scope.platformCookie = $cookies.userName;
    	
    	 $scope.userPermissonmodel = [];
    	 
         $scope.userPermissondata = [
             {id: "USER", label: "USER"},
             {id: "ADMIN", label: "ADMIN"},
             {id: "REPORTER", label: "REPORTER"}];
         
         $scope.userPermissonsettings = {
             smartButtonMaxItems: 3,
             //scrollable: true,
             smartButtonTextConverter: function(itemText, originalItem) {
                 return itemText;
             }
         };
         
         $scope.isAuth = function () {
            return authService.isAuthenticated();
        }

        $scope.logOff = function ($route) {
            return authService.logOff();
        }
        
        $scope.contacts = [];
        $scope.selectedcontact = {};
        $scope.newcontact = {};
        $scope.showEditContact = false;
        $scope.showNewContact = false;


        $scope.initialize = function () {
            $scope.loadData();
        }

        $scope.loadData = function () {
        	console.log('Inside load users data::::');
            {
            $http({
                url: "/rest/users/getUsers",
                method: "GET",
             }).success(
                   function (response) {
                       $scope.contacts = response;
                       console.log('Under user load:::::'+ response);
                       $scope.selectedcontact = [];
                       $scope.newcontact = [];
               })
            }
        }

        $scope.edit = function(id) {
    	   console.log('Inside Show Modal dialog Selected user id::'+ id);
           for (var i = 0; i < $scope.contacts.length; i++) {
           	if(id == $scope.contacts[i].id ){
                 var contact = $scope.contacts[i];
                 console.log('Selected User to edit:::::::'+contact.id);                 
                 $scope.selectedcontact =  $scope.contacts[i]; 
           	}
           }
    	   var modalInstance = $uibModal.open({
    		      templateUrl: 'views/user-details.html',
    		      controller: ModalInstanceCtrl,
    		      backdrop: true,
        	      backdropClick: true,
        	      dialogFade: false,
        	      keyboard: true,
    		      resolve: {
    		        userObj: function () {
    		        	console.log('Selected User Image:::'+$scope.selectedcontact.userImage);
    		        	return $scope.selectedcontact;
    		        }
    		      }
    		    });    	    
    	    };        
    	    
    	     var ModalInstanceCtrl = function ($scope, $modalInstance, userObj) {
    	    	 
    	    	 console.log('User existing permissions::'+userObj.permissions);
    	         
    	         var splits = userObj.permissions.split(",");
    	         
    	         $scope.userPermissonmodel = [];
    	    	 
    	    	 if(splits[0] !== undefined && splits[1] !== undefined && splits[2] !== undefined){
    	         	$scope.userPermissonmodel.push({id: splits[0], label: splits[0]},{id: splits[1], label: splits[1]},{id: splits[2], label: splits[2]});
    	    	 }else if(splits[0] !== undefined && splits[1] !== undefined){
    	    		 $scope.userPermissonmodel.push({id: splits[0], label: splits[0]},{id: splits[1], label: splits[1]}); 
    	    	 }else if(splits[0] !== undefined){    	
    	    		 $scope.userPermissonmodel.push({id: splits[0], label: splits[0]});     	    		 
    	    	 }
    	    	 
    	    	 console.log('After push permissions::'+$scope.userPermissonmodel);
    	    	 
    	         $scope.userPermissondata = [
    	             {id: "USER", label: "USER"},
    	             {id: "ADMIN", label: "ADMIN"},
    	             {id: "REPORTER", label: "REPORTER"}];
    	         
    	         $scope.userPermissonsettings = {
    	             smartButtonMaxItems: 3,
    	             smartButtonTextConverter: function(itemText, originalItem) {
    	                 return itemText;
    	             }
    	         };
    	         
    	          $scope.selectedUser = userObj;
    	         
    	       	  $scope.save = function () {
    	    		  
    	    	  var updatedPermissions = [];
    	             
    	          angular.forEach($scope.userPermissonmodel, function (value, index) {
    	        	  updatedPermissions.push(value.id);            	
    	           }); 
    	          
    	          $scope.selectedUser.permissions = updatedPermissions.toString();
    	          
    	          console.log('Under save model selected Contact firstname:::'+userObj.firstName + '  Permissions::: ' + userObj.permissions);
    	          
    	       	   var newContact = {};
    	       	   
    	       	   newContact = userObj;
    	              $http({
    	                  url: "/rest/users/updateUser",
    	                  method: "PUT",
    	                  data: JSON.stringify(newContact),
    	                  headers: {
    	                      'Content-Type': 'application/json'
    	                  }     	                   
    	                }).success(
    	                      function (response) {
    	                   	   console.log('updated the user succesfully:::');    	                   	  
    	                  });
    	              $modalInstance.close();
    	    	  };

    	    	  $scope.cancel = function () {
    	    	    $modalInstance.dismiss('cancel');
    	    	  };
    	    	};    
       
        $scope.delete = function (userId) {        	
        	console.log('Inside Show delete Modal dialog Selected user id::'+ userId );  
        	for (var i = 0; i < $scope.contacts.length; i++) {
               	if(userId == $scope.contacts[i].id ){
                     var contact = $scope.contacts[i];
                     console.log('Selected User to Delete:::::::'+contact.id);                 
                     $scope.selectedcontact =  $scope.contacts[i]; 
               	}
               }           
    	   var modalInstance = $uibModal.open({
    		      templateUrl: 'views/user-delete.html',
    		      controller: DeleteUserInstanceCtrl,
    		      backdrop: true,
        	      backdropClick: true,
        	      dialogFade: false,
        	      keyboard: true,
    		      resolve: {
    		    	  userObj: function () {
        		          return $scope.selectedcontact;
        		       }
    		      }
    		    }); 
    	   
    	   modalInstance.result.then(function(condition) {
    		   $scope.loadData();
   	    	});
        }        
        
        var DeleteUserInstanceCtrl = function ($scope, $modalInstance, userObj) {
        	  $scope.userNameSelected = userObj.username;
        	  var userId = userObj.id;
	       	  $scope.deleteUser = function () {
	    	  console.log('Under delete model selected userId:::'+userId);
	       	       $http({
	       	    	url: "/rest/users/deleteUser/" +userId,
	                method: "DELETE",
	                params: {userId: userId},
	                headers: {
	                    'Content-Type': 'application/json'
	                }                   
	                }).success(
	                      function (response) {
	                   	   console.log('User deleted succesfully:::');  
	                   	   $modalInstance.close();	
	                  });	    	     	   
	    	  };

	    	  $scope.cancel = function () {
	    	    $modalInstance.dismiss('cancel');
	    	  };	  
	    	 
	    	};    

       $scope.newContact = function () {
            $scope.showNewContact = true;            
        }
       
       $scope.imageDispay = [];
       
       $scope.setFile = function(element) {
           $scope.$apply(function($scope) {
        	   $scope.theFile = element.files[0];
               var fileReader = new FileReader();
               fileReader.readAsDataURL($scope.theFile); // convert the image to data url. 
               fileReader.onload = function(e) {
                    $scope.imageDispay.dataUrl = e.target.result; // Retrieve the image. 
                    console.log('Adding the file::::'+$scope.theFile.name + '   URL::::' +$scope.imageDispay.dataUrl);                  
                 }
              
           });
       };
       
        $scope.addUser = function () {
    	   
            var addNewuser = {};
            
            var permissionsToAdd = [];
            
            angular.forEach($scope.userPermissonmodel, function (value, index) {
            	permissionsToAdd.push(value.id);            	
            });        
                             
            var byteString = atob($scope.imageDispay.dataUrl.split(',')[1]);

            // separate out the mime component
            var mimeString = $scope.imageDispay.dataUrl.split(',')[0].split(':')[1].split(';')[0]

            // write the bytes of the string to an ArrayBuffer
            var ab = new ArrayBuffer(byteString.length);
            
            var ia = new Uint8Array(ab);
            
             for (var i = 0; i < byteString.length; i++) {
                ia[i] = byteString.charCodeAt(i);
            }

            // write the ArrayBuffer to a blob, and you're done
            var userImageToUpload = new Blob([ab], {type: mimeString});
            //return blob;
            
            console.log('User userImageToUpload::::'+ userImageToUpload);
                        
           // $scope.newuser.userImage =  byteString;
            var fileToUpload = $scope.theFile;
            //console.log('User Image blob::::'+ blob );
            console.log('User Image File Name::::'+ $scope.theFile);
           // $scope.newuser.userImage= byteString;
            $scope.newuser.permissions = permissionsToAdd.toString();
            
            addNewuser = $scope.newuser;
           // console.log('Adding new permissions ::::'+ addNewuser.userImage);
            $http({
                //url: "/rest/users/createUser" +fileToUpload,
                url: "/rest/users/createUser",
                method: "POST",
                data: JSON.stringify(addNewuser),
               // params: {fileToUpload: fileToUpload},
                headers: {
                    'Content-Type': 'application/json'
                }    
             }).success(
                   function (response) {
                	   console.log('User created succesfully');
                       $scope.reloadContacts(response);
               });
            $scope.showNewContact = false;            
            $scope.newuser = {};
        }

       $scope.cancel = function () {
            $scope.showEditContact = false;
            $scope.showNewContact = false;
        }


       $scope.reloadContacts = function (contacts) {
            $scope.contacts = contacts;
            $scope.selectedcontact = [];
            $scope.newcontact = [];
       }
       
       var history = [];

       $rootScope.$on('$routeChangeSuccess', function() {
           history.push($location.$path);
       });
       
       $scope.homePage = function ($event) {
    	   console.log('Inside back to home page:::::');
    	   //$window.history.back();    	   
    	   $location.path('/home').replace();
    	   $route.reload();
    	/*   $window.location.replace('/home');*/

    	  /* $location.path('/home').replace();*/
    	  /* $location.replace();
    	   $window.location.href = $window.location.href;*/
    	    // $window.history.back(-1);
    	   //window.location = '#/home/' + pageName;
    	   //$window.location.reload();        	
    	   console.log('Path Location in homepage::::'+$location.path());
       }
                    
       $rootScope.back = function () {
    	   console.log('Inside back function:::::');
           var prevUrl = history.length > 1 ? history.splice(-1)[0] : "/";
           $location.path(prevUrl);
       };


        $scope.initialize();
    }]);

