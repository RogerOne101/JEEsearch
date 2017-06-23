
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
	Boolean loggedIn = (Boolean) session.getAttribute("LOGGED-IN");
	if (loggedIn == null || !loggedIn.booleanValue()) {
		response.sendRedirect("profile/loginform.jsp");
		return;
	}
%>


<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Ten widok jest chroniony</title>

<script src="//code.jquery.com/jquery-1.10.2.js"></script>
<script src="//code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
<script src="js/autocompleter.js"></script>
<script src="js/get_profile.js"></script>
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css">

<link rel="stylesheet" type="text/css" href="css/profile_table.css">

</head>

<body>

	<br />

	<c:choose>
		<c:when test="<%=(Boolean) session.getAttribute("IS-ADMIN")%>">

			<div class="header">
				<h3>Panel administratora</h3>
			</div>
			<br />
			<br />

		Znajdź użytkownika:
		<div class="search-container">
				<div class="ui-widget">
					<input type="text" id="search" name="search" class="search" />
				</div>
			</div>

		</c:when>
		<c:otherwise>

			<script>
			window.onload = function() {
				getProfile('<%=session.getAttribute("LOGIN")%>');
				};
			</script>
		
        Jeżeli to oglądasz, to znaczy, że jesteś zalogowanym użytkownikiem o
		nazwie: <div><%=session.getAttribute("LOGIN")%></div>
			<br />
			<br />
		Informacje o moim profilu:
		
    </c:otherwise>
	</c:choose>


	<table>
		<tr>
			<th>Id</th>
			<th>Login</th>
			<th>Email</th>
		</tr>
		<tr>
			<th><div id="profile_id"></div></th>
			<th><div id="profile_login"></div></th>
			<th><div id="profile_email"></div></th>
		</tr>

	</table>

	<br />
	<a href="profile/Logout">Wyloguj się</a>

</body>
</html>