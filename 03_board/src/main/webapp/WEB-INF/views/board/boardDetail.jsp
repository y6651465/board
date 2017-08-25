<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%@ include file="/WEB-INF/include/config.jsp"%>
</head>
<body>
<%
       request.setCharacterEncoding("UTF-8");
     %>

	<div class="fix main_content_area">
		<div class="fix wrapper main_content">
			<div class="inner_page">
				<h1>"${board.no} 번 게시물"</h1>
				<table class="w3-table-all">
					<tr>
						<td>번호</td>
						<td>${board.no}</td>
						<td>좋아요 : <span id="likeCount">${board.like_count }</span>  <input type="button" id="lBtn" value="좋아요"/></td>
					</tr>
					<tr>
						<td>날짜</td>
						<td colspan="2"><input type="text" readonly="readonly" value="${board.reg_date}"/></td>
					</tr>
				
					<tr>
						<td>제목</td>
						<td colspan="2"><input type="text" readonly="readonly" value="${board.title }"/></td>
					</tr>
					<tr>
						<td>파일</td>
						<c:if test="${empty file}">
						<td colspan="2">파일이 없습니다.</td>
						</c:if>
						<c:if test="${not empty file }">
						<td colspan="2">첨부 파일 : <a href="download.do?no=${board.no }">${file.ori_name }</a></td>
						</c:if>
					</tr>
					<tr>
						<td>글쓴이</td>
						<td colspan="2"><input type="text" readonly="readonly" value="${board.id}"/></td>
					</tr>
					<tr>
						<td>내용</td>
						<td colspan="2"><textarea cols="30" rows="4" readonly="readonly" style="resize: none;">${board.content}</textarea></td>
					</tr>
				</table>
				<br>
				<br>
				<br>
				<div class="contact_form">
					<form id="rForm" action="#" method="post">
						<span>아이디</span> <input type="text" id="id" name="id" value="${userId}" readonly />
						<input type="submit" value="등록" />
						<div>내용</div>
						<textarea rows="5" cols="50" name="comment_content" style="resize: none;"></textarea>
						<br>
						<br>
					</form>
				</div>

				<table id="comment" class="w3-table-all">
				</table>
				<br><br>
				<a href="boardList.do?pageNo=${page}&whatSearch=${whatSearch}&wantSearch=${wantSearch}">목록</a>
				<c:if test="${userId eq board.id}">
				<a href="boardUpdateForm.do?no=${board.no}&pageNo=${page}&whatSearch=${whatSearch}&wantSearch=${wantSearch}" id="update">수정</a>
				<a href="delete.do?no=${board.no}" id="delete">삭제</a><br>
				</c:if>
				<br>
			</div>
		</div>
	</div>
	<input type="hidden" id="bno" value="${board.no}">
	<input type="hidden" id="bid" value="${board.id}">
	<input type="hidden" id="userId" value="${userId}">
	<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
	<script src="/board/resources/js/comment.js"></script>
	<script>
	$("#lBtn").click(function () {
		$.ajax({
			url: "like.do",
			data: {
				no: $("#bno").val(),
				id: $("#userId").val()
			}
		}).done(function (result) {	$("#likeCount").html(result); console.log(result)	});
	});
	</script>
</body>
</html>