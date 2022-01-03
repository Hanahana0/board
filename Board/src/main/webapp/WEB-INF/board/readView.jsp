<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
<script
	src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js">
</script>
<title>게시판</title>
</head>
<script type="text/javascript">
	$(document).ready(function() {
		var formObj = $("form"); 
		// 수정
		/* $(".update_btn").on("click", function() {
			formObj.attr("action", "/board/updateView");
			formObj.attr("method", "get");
			formObj.submit();
		}) */
		// 삭제
		$(".delete_btn").on("click", function() {
			var val = confirm("삭제하시겠습니까?")
			if(val == true){
				formObj.attr("action", "/board/delete");
				formObj.attr("method", "post");
				formObj.submit();
			}
		})
		// 취소
		$(".list_btn").on("click", function() {
			location.href = "/board/list";
		})
	})
</script>
<body>
	<div id="root">
		<header>
			<h1>게시판</h1>
		</header>
		<hr />
		<div>
			<jsp:include page="nav.jsp"/>
		</div>
		<hr />
		<section id="container">
			<form role="form" method="post">
				<table>
					<tbody>
						<tr>
							<td><label for="bno">글 번호</label><input type="text" id="bno"
								name="bno" value="${read.bno}" /></td>
						</tr>
						<tr>
							<td><label for="title">제목</label><input type="text"
								id="title" name="title" value="${read.title}" /></td>
						</tr>
						<tr>
							<td><label for="content">내용</label> <textarea id="content"
									name="content"><c:out value="${read.content}" /></textarea></td>
						</tr>
						<tr>
							<td><label for="writer">작성자</label><input type="text"
								id="writer" name="writer" value="${read.writer}" /></td>
						</tr>
						<tr>
							<td><label for="regdate">작성날짜</label> <c:out
									value="${fn:substring(read.regdate, 0, 10) }" /></td>
						</tr>
					</tbody>
				</table>
			</form>
			<div>
				<button type="submit" class="update_btn" onclick="javascript:location.href='updateView?bno=${read.bno}'">수정</button>
				<button type="submit" class="delete_btn">삭제</button>
				<button type="submit" class="list_btn">목록</button>
			</div>
		</section>
		<hr />
	</div>
</body>
</html>