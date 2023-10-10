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
		<h2>Spring Book</h2>
		<div class="panel panel-default">
			<div class="panel-heading">Book</div>
			<div class="panel-body">

				<table class="table table-bordered table-hover">

					<tr class="active">
						<td>번호</td>
						<td>제목</td>
						<td>작가</td>
						<td>출판사</td>
						<td>isbn</td>
						<td>보유도서수</td>
						<td>삭제</td>
						<td>수정</td>
					</tr>
					<c:forEach items="${list}" var="dto" varStatus="i">
						<tr>
							<td>${dto.num} </td>
							<td>${dto.title} </td>
							<td>${dto.author} </td>
							<td>${dto.company} </td>
							<td>${dto.isbn} </td>
							<td>${dto.count} </td>
							<td> <a href="bookDelete.do?num=${dto.num}" class="btn btn-primary btn-sm" type="submit">삭제</a> </td>
							<td> <a href="bookUpdateForm.do/${dto.num}" class="btn btn-warning btn-sm" type="submit">수정</a> </td>
						</tr>

					</c:forEach>
					
					<tbody></tbody>


				</table>
				
				
				<a href="bookForm.do" class="btn btn-primary btn-sm">도서등록</a>

			</div>
			<div class="panel-footer">스프링도서관-박병관</div>
		</div>
	</div>


</body>
</html>