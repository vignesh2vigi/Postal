/**
 * 
 */

app.factory('DashService',function($http){
	
	var dashService={}
	var BASE_URL="http://localhost:8090/PostalWeb"
	
		dashService.newlead=function(client)
	{
	return $http.get(BASE_URL+"/servlet/count",client)
	}
	dashService.leadprocess=function(client1)
	{
	return $http.get(BASE_URL+"/servlet/process",client1)
	}
	dashService.leadnotverify=function(client2)
	{
	return $http.get(BASE_URL+"/servlet/notverify",client2)
	}
	dashService.leadverify=function(client3)
	{
	return $http.get(BASE_URL+"/servlet/verify",client3)
	}
		return dashService;
	
})