<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</head>
<body>
	<div class="container">
		<h2>나가주세요02</h2>
		<div class="panel panel-default">
			<div class="panel-heading">Board</div>
			<div class="panel-body">

				<table class="table table-bordered table-hover">

					<tr class="active">
						<td>번호</td>
						<td>제목</td>
						<td>작성자</td>
						<td>작성일</td>
						<td>조회수</td>
					</tr>
					
					<tbody id="#view">
					<!-- 비동기 방식으로 가져온 게시글 나오게할 부분 -->
					</tbody>
					


				</table>
				
				
			</div>
			<div class="panel-footer">스프링게시판 - 박병관</div>
		</div>
	</div>
	
	<script type="text/javascript">
	
		// 처음 실행될 때 자동 실행 => 생성자 느낌
		// html 다 로딩되고 아래 코드 실행
		$(document).ready(function() {
			loadList();
		});
	
		function loadList() {
			// 게시글 리스트 가져오기
			// ajax - 요청 url, 어떻게 데이터 받을지, 요청방식 등 .. => 객체{}로 넣기
			$.ajax({
				url : "boardList.do",
				type : "get",
				dataType : "json",
				success : makeView, // callback 함수
				error : function() {alert("error");}
			});
		}
		function makeView(data) {
			console.log(data);
		}
	
	
	</script>

</body>
</html>