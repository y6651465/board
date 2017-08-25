<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%@ include file="/WEB-INF/include/config.jsp"%>
<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
</head>
<body>
	<c:import url="/WEB-INF/include/topMenu.jsp"></c:import>
	<div class="fix main_content_area">
		<div class="fix wrapper main_content">
			<div class="inner_page">
				<h1>게시물 목록</h1>

				<div class="w3-container">
					<table class="w3-table-all">
						<thead>
							<tr class="w3-red">
								<th>번호</th>
								<th>제목</th>
								<th>조회수</th>
								<th>댓글수</th>
								<th>좋아요</th>
								<th>날자</th>
							</tr>
						</thead>
						<tbody>
							<c:if test="${empty list}">
								<tr>
									<td colspan="4">게시물 내용이 없습니다.</td>
								</tr>
							</c:if>
							<c:forEach items="${list}" var="board">
							
							<input type="hidden" value="${board.no}" />
								<tr>
									<td>${board.no}</td>
									<td><a href="boardDetail.do?no=${board.no}&pageNo=${pageResult.pageNo}&whatSearch=${whatSearch}&wantSearch=${wantSearch}">${board.title}</a></td>
									<td>${board.view_count}</td>
									<td>${board.count}</td>
									<td>${board.like_count}</td>
									<td>${board.reg_date}</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					<br>
					<a href="writeForm.do" class="center">글쓰기</a><br>
				</div>
				<br> <br>
				<!-- 페이징 처리 영역임... -->
				<c:if test="${pageResult.count != 0}">
					<ul class="pagination">
						<li
							class="<c:if test="${pageResult.prev eq false}">disabled</c:if>">
							<a
							href="<c:if test="${pageResult.prev eq true}">javascript:goPage(${pageResult.beginPage - 1})</c:if>">
								<span>&laquo;</span>
						</a>
						</li>

						<c:forEach var="i" begin="${pageResult.beginPage}"
							end="${pageResult.endPage}">
							<c:choose>
								<c:when test="${pageResult.pageNo eq i}">
									<li class="active"><a href="#1">${i}</a></li>
								</c:when>
								<c:otherwise>
									<li><a href="javascript:goPage(${i})">${i}</a></li>
								</c:otherwise>
							</c:choose>
						</c:forEach>

						<li
							class="<c:if test="${pageResult.next eq false}">disabled</c:if>">
							<a
							href="<c:if test="${pageResult.next eq true}">javascript:goPage(${pageResult.endPage + 1})</c:if>">
								<span>&raquo;</span>
						</a>
						</li>
					</ul>
				</c:if>
			</div>
		</div>
	</div>
	<script>
		function goPage(pageNo) {
			location.href = "boardList.do?pageNo=" + pageNo + "&whatSearch=${whatSearch}&wantSearch=${wantSearch}";
		}
		$("ul.pagination > li > a").click(function() {
			var no = $(this).data("page");
			if (no) {
				location.href = "boardList.do?pageNo=" + no;
			}
		});
		
		
			
			
	</script>
</body>
</html>