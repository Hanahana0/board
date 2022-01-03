<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<html>
<head>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

<title>게시판</title>
<style type="text/css">
	li {list-style: none; float: left; padding: 6px;}
</style>
</head>
<body> 
	<div id="root">
		<header>
			<h1>게시판</h1>
		</header>
		<hr>
		<div>
			<jsp:include page="nav.jsp"/>
		</div>
		<hr />
		<section id="container">
			<form role="form" method="get">
				<table>
					<tr>
						<th>번호</th>
						<th>제목</th>
						<th>작성자</th>
						<th>등록일</th>
					</tr>
						<c:if test="${fn:length(list) != 0 }">
							<c:forEach items="${list}" var="list">
								<tr onclick="javascript:location.href='readView?bno=${list.bno}'" style="cursor: pointer;">
									<td><c:out value="${list.bno}" /></td>
									<td><c:out value="${list.title}" /></td>
									<td><c:out value="${list.writer}" /></td>
									<td><c:out value="${fn:substring(list.regdate, 0, 10) }"/></td>
								</tr>
							</c:forEach>
						</c:if>
						<c:if test="${fn:length(list) == 0 }">
							<script>
								alert("조회결과가 없습니다.");
								location.href="list";
							</script>
						</c:if>
				</table>
				<div class="search">
					<select name="searchType">
						<option value="t" <c:out value="${scri.searchType eq 't' ? 'selected' : ''}"/>>제목</option>
						<option value="c" <c:out value="${scri.searchType eq 'c' ? 'selected' : ''}"/>>내용</option>
						<option value="w" <c:out value="${scri.searchType eq 'w' ? 'selected' : ''}"/>>작성자</option>
						<option value="tc" <c:out value="${scri.searchType eq 'tc' ? 'selected' : ''}"/>>제목+내용</option>
					</select> 
					
					<input type="text" name="keyword" id="keywordInput" value="${scri.keyword}"/>
					
					<button id="searchBtn" type="button">검색</button>
					<script>
						$(function() {
							$('#searchBtn').click(function() {
								/* if ($("#keywordInput").val().trim().length <= 0) {
									alert("검색어를 입력해주세요.");
									return;
								}; */
								location.href = "list" + '${pageMaker.makeQuery(1)}' 
									+ "&searchType=" + $("select option:selected").val()
									+ "&keyword=" + encodeURIComponent($('#keywordInput').val());
								});	
						});
					</script>
				</div>
				<div>
					<ul>
						<c:if test="${pageMaker.prev}">
							<li><a href="list${pageMaker.makeSearch(pageMaker.startPage - 1)}">이전</a></li>
						</c:if>
						<c:forEach begin="${pageMaker.startPage}" end="${pageMaker.endPage}" var="idx">
							<li><a href="list${pageMaker.makeSearch(idx)}">${idx}</a></li>
						</c:forEach>
						<c:if test="${pageMaker.next && pageMaker.endPage > 0}">
							<li><a href="list${pageMaker.makeSearch(pageMaker.endPage + 1)}">다음</a></li>
						</c:if>
					</ul>
				</div>
			</form>
		</section>
		<hr />
	</div>
</body>
</html>