<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>회원가입</title>
<%@ include file="/WEB-INF/include/config.jsp"%>
<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
</head>
<body>
	<div class="fix main_content_area">
		<div class="fix wrapper main_content">
			<div class="inner_page">
				<h1>회원가입</h1>
				<div class="w3-container">
				<form action="registerForm.do">
					아이디 : <input type="text" name="id" placeholder="id"/><br><br>
					비밀번호 : <input type="password" name="password" placeholder="pass"/><br><br>
					
					이메일 : <input type="email" name="email" placeholder="email"/><br><br>
					<input type="submit" value="회원 가입"> <button type="button" id="login">가입취소</button>
				</form>
				</div>
			</div>
		</div>
	</div>
	<script>
		$("#login").click(function () {
			location.href="login.do";
		});
	
	</script>
  
  
</body>
</html>