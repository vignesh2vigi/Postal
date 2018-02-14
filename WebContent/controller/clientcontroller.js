/**
 * 
 */
app.controller('ClientController',function($scope,ClientService,$location,$rootScope,$cookieStore){
	
	$scope.login=function(){
		console.log($scope.client)
	ClientService.login($scope.client).then(function(response){
		
		console.log(response.data)
		console.log(response.status)
		$rootScope.currentClient=response.data//username
		$cookieStore.put('currentClient',response.data)
		$location.path('/home')
		
	},function(response){
	
		$scope.error=response.data.message
	
	$location.path('/login')
	})
	}
	if($rootScope.currentClient!=undefined){
		ClientService.getusername($scope.client).then(function(response){
			$scope.client=response.data
		},function(response){
			console.log(response.status)
			if(response.status==401){
				delete $rootScope.currentClient;
				$cookieStore.remove('currentClient')
				$location.path('/login')

			}
				
			})
		}
	
	function getlist(){
		ClientService.getlist().then(function(response){
             console.log(response.data)
			console.log(response.status)
			
			$scope.newlead=response.data
            },function(response){
            
			$location.path('/login')
		})
		
	}
	function getatelist(){
		ClientService.getatelist().then(function(response){
             console.log(response.data)
			console.log(response.status)			
			$scope.ate=response.data
            },function(response){
			
			$location.path('/login')
		})
		
	}
	$scope.update=function(){
		ClientService.update($scope.client).then(function(response){
			alert('Updated successfully')
			$location.path('/home')
		},function(response){
			
			
				$location.path('/billup')
				})
		}
	function bill(){
		ClientService.bill().then(function(response){
			console.log(response.data)
			console.log(response.status)
			$scope.clienta=response.data
			console.log($scope.clienta)
		
		},function(response){
			
			console.log(response.data)
				$location.path('/home')
				})
		}
	
	
	 $scope.exportData = function () {
		 /*var myJsonString = JSON.stringify($scope.exportData);*/

	        var blob = new Blob([document.getElementById('exportable').innerHTML], {
	            type: "application/vnd.ms-excel;charset=charset=utf-8"
	        });
	      saveAs(blob, "Report.xls");
	    };

    
	function complete(){
		ClientService.complete().then(function(response){
             console.log(response.data)
			console.log(response.status)
			$scope.complete=response.data
            },function(response){
			
			$location.path('/login')
		})
		
	}
	
	
bill()
	getlist()
	getatelist()
	complete()
})