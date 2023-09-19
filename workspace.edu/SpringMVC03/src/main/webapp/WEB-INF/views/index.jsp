<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}"></c:set>
	
<!DOCTYPE html>
<html lang="en">
<head>
<title>Bootstrap Example</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</head>
<body>

	<div class="container">
		<jsp:include page="common/header.jsp"></jsp:include>
		<h3>홈페이지입니다</h3>

		<div class="panel panel-default">
			<div>
				이미지
			</div>
			<div class="panel-body">
				탭메뉴
			</div>
			<div class="panel-footer">
				스프링-박병관
			</div>

		</div>




		<p style="color: red; font-size: 120px;">나가주세요</p>
		<img alt=""
			src="https://westart.or.kr/wp-content/uploads/2016/05/AI7.png">
	</div>



	<!-- Modal -->
	<div class="modal fade" id="myMessage" role="dialog">
		<div class="modal-dialog">

			<!-- Modal content-->
			<div id="messageType" class="modal-content">
				<div class="modal-header panel-heading">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">${msgType}</h4>
				</div>
				<div class="modal-body">
					<p id="">${msg }</p>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
				</div>
			</div>

		</div>
	</div>


	<script type="text/javascript">
	  $(document).ready( function() {
		if(${not empty msgType}){
			if(${msgType eq "성공메시지"}){
				$("#messageType").attr("class", "modal-content panel-success");
				
			} else if(${msgType eq "로그인성공"}){
				$("#messageType").attr("class", "modal-content panel-success");
			}
			$("#myMessage").modal("show");
		}
	  });
	
	</script>

</body>
</html>