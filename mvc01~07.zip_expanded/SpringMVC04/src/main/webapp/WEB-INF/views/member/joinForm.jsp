<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
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
		<jsp:include page="../common/header.jsp"></jsp:include>
		<h2>Spring MVC03</h2>
		<div class="panel panel-default">
			<div class="panel-heading">Board</div>
			<div class="panel-body">
				<form action="${contextPath }/join.do" method="post">
				
					<!-- 보안 토큰 설정 -->
					<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">

					<!-- 이 친구가 넘어갈 예정임. -->
					<input type="hidden" name="memPassword" id="memPassword" value="">

					<table style="text-align: center; border: 1px solid #dddddd"
						class="table table-borded">
						<tr>
							<td style="width: 110px; vertical-align: middle;">아이디</td>
							<td><input type="text" name="memId" id="memId"
								class="form-control" maxlength="20" placeholder="아이디를 입력하세요."></td>
							<td style="width: 110px;"><button type="button"
									onclick="registerCheck()" class="btn btn-sm btn-primary">중복확인</button></td>
							<!-- 버튼이 submit으로 작동못 하게 type="button"  -->
						</tr>
						<tr>
							<td style="width: 110px; vertical-align: middle;">비밀번호</td>
							<td colspan="2"><input type="password"
								onkeyup="passwordCheck()" name="memPassword1" id="memPassword1"
								class="form-control" maxlength="20" placeholder="비밀번호를 입력하세요."></td>
						</tr>
						<tr>
							<td style="width: 110px; vertical-align: middle;">비밀번호 확인</td>
							<td colspan="2"><input type="password"
								onkeyup="passwordCheck()" name="memPassword2" id="memPassword2"
								class="form-control" maxlength="20" placeholder="비밀번호를 확인하세요."></td>
						</tr>
						<tr>
							<td style="width: 110px; vertical-align: middle;">사용자 이름</td>
							<td colspan="2"><input type="text" id="memName"
								name="memName" class="form-control" maxlength="20"
								placeholder="이름을 입력하세요."></td>
						</tr>
						<tr>
							<td style="width: 110px; vertical-align: middle;">나이</td>
							<td colspan="2"><input type="number" name="memAge"
								id="memAge" class="form-control" maxlength="20"
								placeholder="나이를 입력하세요."></td>
						</tr>
						<tr>
							<td style="width: 110px; vertical-align: middle;">성별</td>
							<td colspan="2">
								<div class="form-group"
									style="text-align: center; margin: 0 auto;">
									<div class="btn-group" data-toggle="buttons">
										<!-- 라디오를 버튼식으로  -->
										<label class="btn btn-primary active "> <input
											type="radio" id="memGender" name="memGender"
											autocomplete="off" value="남자" checked="checked"> 남자
										</label> <label class="btn btn-primary"> <input type="radio"
											id="memGender" name="memGender" autocomplete="off" value="여자">
											여자
										</label>
									</div>
								</div>

							</td>
						</tr>
						<!-- 성별 끝 -->
						<tr>
							<td style="width: 110px; vertical-align: middle;">이메일</td>
							<td colspan="2"><input required type="email" name="memEmail"
								id="memEmail" class="form-control" maxlength="50"
								placeholder="이메일을 입력하세요."></td>
						</tr>
						<tr>
							<td colspan="3"><span id="passMessage" style="color: red;"></span>

								<input type="submit" class="btn btn-primary btn-sm pull-right"
								value="등록"> <input type="reset"
								class="btn btn-warning btn-sm pull-right" value="취소"></td>
						</tr>

					</table>
				</form>
			</div>

			<div class="panel-footer">스프링게시판-뇽뇽이</div>
		</div>
	</div>








	<!-- Modal -->
	<div class="modal fade" id="myModal" role="dialog">
		<div class="modal-dialog">

			<!-- Modal content-->
			<div id="checkType" class="modal-content">
				<div class="modal-header panel-heading">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">메세지 확인</h4>
				</div>
				<div class="modal-body">
					<p id="checkMessage"></p>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
				</div>
			</div>

		</div>
	</div>

	<!-- 회원가입 실패시 띄워줄 Modal -->
	<div class="modal fade" id="myMessage" role="dialog">
		<div class="modal-dialog">

			<!-- Modal content-->
			<div id="messageType" class="modal-content">
				<div class="modal-header panel-heading">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">${msgType }</h4>
				</div>
				<div class="modal-body">
					<p>${msg }</p>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
				</div>
			</div>

		</div>
	</div>


	<script type="text/javascript">
      function registerCheck(){
         var memId = $("#memId").val();
         console.log(memId);
         $.ajax({
            url:"${contextPath}/registerCheck.do",
            type:"get",
            data:{"memId":memId},
            success:function(data){
               // 중복여부 확인( data:1 -> 사용 가능, data:0 -> 사용 불가능)
               if (data == 1) {
            	   $("#checkMessage").text("사용할 수 있는 아이디입니다.");
            	   $("#checkType").attr("class", "modal-content panel-success");
               } else {
            	   $("#checkMessage").text("사용할 수 없는 아이디입니다.");
            	   $("#checkType").attr("class", "modal-content panel-warning");
               }
               
               $("#myModal").modal("show");
               
            },
            error: function(){
               alert("error");
            }
         });
      }
      
      function passwordCheck(){
    	  
    	  var memPassword1 = $("#memPassword1").val()
    	  var memPassword2 = $("#memPassword2").val()
    	  
    	  if(memPassword1 != memPassword2) {
    		  $("#passMessage").html("비밀번호가 서로 일치하지 않습니다.");
    		  $("#passMessage").attr("style", "color: red;");
    	  } else {
    		  $("#memPassword").val(memPassword1);
    		  console.log($("#memPassword").val(memPassword1));
    		  $("#passMessage").html("비밀번호 일치");
    		  $("#passMessage").attr("style", "color: green;");
    	  }
    	  
    	  
      }
      
      $(document).ready( function() {
		if(${not empty msgType}){
			if(${msgType eq "실패메세지"}){
				$("#messageType").attr("class", "modal-content panel-warning");
				
			}
			$("#myMessage").modal("show");
		}
	  });
      
      
   </script>

</body>
</html>