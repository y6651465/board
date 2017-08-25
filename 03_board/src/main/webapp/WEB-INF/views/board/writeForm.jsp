<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%@ include file="/WEB-INF/include/config.jsp"%>
<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
</head>
<body>
	<div class="fix main_content_area">
		<div class="fix wrapper main_content">
			<div class="inner_page">
				<h1>글쓰기</h1>

				<div class="w3-container">
					<form action="write.do" method="post" enctype="multipart/form-data">
						글쓴이<input type="text" name="id" value="${userId }"><br> <br>
						제목 <input type="text" name="title"><br><br>
						<input type="file" id="attachFile" name="attachFile"><br><br>
						내용<textarea cols="40" rows="7" name="content" style="resize: none;"></textarea>
						<br> <input type="submit" value="등록" />
						<button class="button" type="button" id ="cansle">취소</button>
					</form>
				</div>
			</div>
		</div>
	</div>
	<script>
		$("#cansle").click(function () {
			location.href="boardList.do";
		});
	</script>
</body>
</html>




