/**
 * 
 */

app.controller('DashController',function($scope,DashService,$location){

    function newlead (){
		
		DashService.newlead().then(function(response){
			console.log(response.data)
			console.log(response.status)
			$scope.client=response.data
			console.log($scope.client)
			
		},function(response){
			
			console.log(response.status)
			console.log(response.data)
			if(response.status==401){
				$scope.error=response.data
				$location.path('/login')
			}
				})
		}
    function leadprocess (){
		
		DashService.leadprocess().then(function(response){
			console.log(response.data)
			console.log(response.status)
			$scope.client1=response.data
			console.log($scope.client1)
			
		},function(response){
			
			console.log(response.status)
			console.log(response.data)
				$location.path('/login')
				})
		}
    function leadnotverify (){
		
		DashService.leadnotverify().then(function(response){
			console.log(response.data)
			console.log(response.status)
			$scope.client2=response.data
			console.log($scope.client2)
		
		},function(response){
			
			console.log(response.status)
			console.log(response.data)
				$location.path('/login')
				})
		}
    function leadverify (){
		
		DashService.leadverify().then(function(response){
			console.log(response.data)
			console.log(response.status)
			$scope.client3=response.data
			console.log($scope.client3)
		
		},function(response){
			
			console.log(response.status)
			console.log(response.data)
				$location.path('/login')
				})
		}
    newlead ()
     leadprocess ()
      leadnotverify ()
       leadverify ()
})