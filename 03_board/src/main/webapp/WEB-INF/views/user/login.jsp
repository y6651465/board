<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
</head>
<script> 
		function doLogin() {
			if(frm.username.value == "") {
				alert("아이디를 입력해주세요.");
				return;
			}
			if(frm.password.value == "") {
				alert("패스워드를 입력해주세요.");
				return;
			}
			
			frm.submit();
		}
		
	</script>
<link rel="stylesheet" href="/board/resources/css/login.css">
<body>
	<form id="login" action="loginForm.do" method="post">
		<h1>Log In</h1>
		<fieldset id="inputs">
			<input id="username" name="id" type="text" placeholder="Username">
			<input id="password" name="password" type="password"
				placeholder="Password">
		</fieldset>
		<fieldset id="actions">
			<input type="submit" id="submit" onclick="doLogin()" value="Login">
			<a href="register.do" id="register">Register</a>
		</fieldset>
		<h3 id="ok"></h3>
	</form>
	<div id="dialog"></div>

	<script>
	$("#ok").html("${msg}")


</script>
</body>
</html>