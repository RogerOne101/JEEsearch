$(document).ready(function() {
        $(function() {
                $("#search").autocomplete({     
                source : function(request, response) {
                $.ajax({
                        url : "SearchController",
                        type : "GET",
                        data : {
                                term : request.term
                        },
                        dataType : "json",
                        success : function(data) {
                                response(data);
                        }
                });
        },
        select: function(e, ui) {       	
        	
        	$.ajax({
                url : "ProfileController",
                type : "GET",
                data : {
                        term : ui.item.value
                },
                dataType : "json",
                success : function(data) {
                	document.getElementById("profile_id").innerHTML=data.id;
                	document.getElementById("profile_login").innerHTML=data.login; 
                	document.getElementById("profile_email").innerHTML=data.email; 
                }
        	});
        }
});
});
});