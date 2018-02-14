/**
 * 
 */
app.factory('ClientService',function($http){
	var clientService={}
	var BASE_URL="http://localhost:8090/PostalWeb"
	
	clientService.login=function(client)
	{
	return $http.post(BASE_URL+"/servlet/login",client)
	}
	clientService.logout=function()
	{
	return $http.get(BASE_URL+"/servlet/logout")
	}
	clientService.getlist=function(){
		return	$http.get(BASE_URL + "/servlet/getlist")
		}
	clientService.getatelist=function(){
		return	$http.get(BASE_URL + "/servlet/getatelist")
		}
	clientService.complete=function(){
		return	$http.get(BASE_URL + "/servlet/complete")
		}
	clientService.update=function(client)
	{
	return $http.put(BASE_URL+"/servlet/update",client)
	}
	clientService.bill=function(clienta)
	{
	return $http.get(BASE_URL+"/servlet/bill",clienta)
	}
	clientService.getusername=function(client)
	{
	return $http.get(BASE_URL+"/servlet/username",client)
	}
	
	return clientService;
	
})