<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
	<div class="fix header_area">
		<div class="fix wrapper header">
			<div class="fix logo floatleft">
				<h1>
					<span>게시판</span>
				</h1>
				<button id="logout">로그아웃</button>
			</div>
		</div>
	</div>
	<div class="fix search_area">
		<div class="fix wrapper search_box">
			<form action="boardList.do" id="sForm">
				<select name="whatSearch" id="ops">
					<option value="0" selected="selected">검색</option>
					<option value="1">글쓴이</option>
					<option value="2">제목</option>
					<option value="3">내용</option>
				</select>
				<p>
					<input name="wantSearch" class="search" type="search" placeholder="게시물 검색"> 
					<input id="searchBoard" class="search_submit" type="submit" value="Search">
				</p>
			</form>
		</div>
	</div>
	<script>
		$("#sForm").submit(function () {
			if ($("#ops").val() == 0 || $("input[name=wantSearch]").val() == ""){
				alert("검색 옵션"); return false;
				
			}				
		});
		$("#logout").click(function () {
			location.href="${pageContext.request.contextPath}/user/logout.do";
		})
	</script>