<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%@ include file="/WEB-INF/include/config.jsp"%>
</head>
<body>
	<div class="fix main_content_area">
		<div class="fix wrapper main_content">
			<div class="inner_page">
				<h1>"${board.no} 번 게시물"</h1>
				<form action="update.do?pageNo=${page}&whatSearch=${whatSearch}&wantSearch=${wantSearch}" method="post">
		<table class="w3-table-all">
			<tr>
				<td>번호</td>
				<td>${board.no}</td>
				<td>${board.reg_date}</td>
			</tr>
			<tr>
				<td>제목</td>
				<td><input type="text" name="title" value="${board.title}"></td>
				<td>${like.count} </td>
			</tr>
			<tr>
				<td>글쓴이</td>
				<td colspan="2">${board.id }</td>
			</tr>
			<tr>
				<td>내용</td>
				<td colspan="2"><textarea name="content" cols="30" rows="4" style="resize: none;">${board.content}</textarea></td>
				
			</tr>
		</table>
		<input type="hidden" name="no" value="${board.no}">
		<input type="submit" value="수정완료"/>
	</form>
	<a href="boardList.do?pageNo=${page}&whatSearch=${whatSearch}&wantSearch=${wantSearch}">목록</a>
			</div>
		</div>
	</div>




</body>
</html>