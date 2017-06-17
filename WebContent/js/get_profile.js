
function getProfile(login)
{
	$.ajax({
		url : "ProfileController",
	    type : "GET",
	    data : {
	    	term : login
	    },
	    dataType : "json",
	                success : function(data) {
	                	document.getElementById("profile_id").innerHTML=data.id;
	                	document.getElementById("profile_login").innerHTML=data.login; 
	                	document.getElementById("profile_email").innerHTML=data.email; 
	                }
	        	});

}
