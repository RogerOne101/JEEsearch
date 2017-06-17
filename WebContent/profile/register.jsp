<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<div class="header">
        	<h3>Rejestracja</h3>
</div>
<br/>
<form action="Register" method="POST">
Login:<input type="text"     name="login"  value=""><br>
Hasło:<input type="password" name="passwd" value=""><br>
Email:<input type="text" name="email" value=""><br>
<input type="submit" value="Rejestruj się">
</form>

<br/>
<a href="../index.jsp">Wróć do strony głównej.</a>

</body>
</html>