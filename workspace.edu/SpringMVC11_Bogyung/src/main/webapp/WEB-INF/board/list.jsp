<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix ="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix ="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!-- Spring Security관련 태그 라이브러리-->
<!--  로그인한 사람들의 정보 가져오기 -->
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<c:set var="cpath" value="${pageContext.request.contextPath}" />

<!-- 로그인한 계정정보 -->
<c:set var="user" value="${SPRING_SECURITY_CONTEXT.authentication.principal}" />
<!-- 권한정보 -->
<c:set var="auth" value="${SPRING_SECURITY_CONTEXT.authentication.authorities}" />
	
<!DOCTYPE html>
<html lang="en">
<head>
  <title>Bootstrap Example</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
  <script src="https://cdn.jsdelivr.net/npm/jquery@3.6.4/dist/jquery.slim.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
</head>
<body>
 
  
  <div class="card">
    <div class="card-header">
	    <div class="jumbotron jumbotron-fluid">
		  <div class="container">
		    <h1>Spring MVC11</h1>
		    <p>Java ➜ HTML ➜CSS ➜ JavaScript ➜ Jsp&Servlet ➜ Spring F/w ➜ Spring Boot </p>
		  </div>
		</div>
	</div>
    <div class="card-body">
    	<div class="row"> <!--비율의 총합은 12이여야한다.  -->
    		<div class="col-lg-2">
    			<div class="card" style="min-height: 500px; max-height: 1000px; ">
    				<div class="card-body"> <!-- 내용을 넣을 떄는 card-body를 사용함 -->
    					<h4 class="card-title">
    						<sec:authentication property="principal.member.name"/>
    					</h4>
    					<p class="card-text">회원님! welcome </p>
    					<form action="${cpath}/member/logout">
    						<button type="submit" class="form-control btn btn-sm btn-primary">로그아웃</button>
    					</form>
    					<br>
    					현재 권한 : <sec:authentication property="principal.member.role"/>
    					<!-- 권한에 따른 화면구성 다르ㅔ 보여주기.  -->
    				<sec:authorize access="hasRole('ADMIN')">
    						<button class="btn btn-info form-control">관리자 페이지</button>
    						<br>
    						<br>
    						<button class="btn btn-success form-control">회원목록</button>
    						<br>
    						<br>
    						<button class="btn btn-warning form-control">개인 정보 수정</button>
    					</sec:authorize>
    					
    					<sec:authorize access="hasRole('MANAGER')">
    						<button class="btn btn-success form-control">회원목록</button>
    						<br>
    						<br>
    						<button class="btn btn-warning form-control">개인 정보 수정</button>
    
    					</sec:authorize>
    					
    					<sec:authorize access="hasRole('MEMBER')">
    						<button class="btn btn-warning form-control">개인 정보 수정</button>
    					</sec:authorize>
    					
    					
    					
    				</div>
    			</div>
    		</div>
    		
    		<div class="col-lg-5">
    			<div class="card" style="min-height: 500px; max-height: 1000px; ">
    				<div class="card-body">
    					<table class="table table-bordered table-hover">
    						<thead>
								<th>번호</th>
    							<th>제목</th>
    							<th>작성일</th>
    							<th>조회수</th>
    						</thead>
    						<tbody>
    							<c:forEach var="vo" items="${list}" varStatus="i">
    								<tr>
    								 	<td>${i.count}</td>
    								 	<td><a href="${vo.idx}">${vo.title}</a></td>
    								 	<td><fmt:formatDate value="${vo.indate}" pattern="yyyy-MM-dd"/></td>
    								 	<td>${vo.count}</td>
    								</tr>
    							</c:forEach>
    						</tbody>
    					</table>
    				</div>
    			</div>
    		</div>
    		
    		<div class="col-lg-5">
    			<div class="card" style="min-height: 500px; max-height: 1000px; ">
    				<div class="card-body">
    					<form id="regForm" action="${cpath}/board/register" method="post">
    					
    						<input type="hidden" id="idx" name="idx" value="">
    						
    						<div class="form-group">
    							<label for="title">제목</label>
    							<input type="text" class="form-control" id="title" name="title" placeholder="Enter Title">
    						</div>
    						<div class="form-group">
    							<label for="content">내용</label>
    							<textarea id="content" name="content" class="form-control" placeholder="Enter Content" rows="7" cols=""></textarea>
    						</div>
    						<div class="form-group">
    							<label for="writer">작성자</label>
    							<input type="text" readonly="readonly" value="<sec:authentication property='principal.member.name'/>" class="form-control" id="writer" name="writer" placeholder="Enter writer">
    						</div>
    						<div id="regDiv"> 
	    						<button type="button" data-oper="register" class="btn btn-sm btn-primary">등록</button>
	    						<button type="button" data-oper="reset" class=" btn btn-sm btn-warning">취소</button>
    						</div>
    						<div id="updateDiv" style="display: none;">
    							<button class="btn btn-sm btn-primary" data-oper="list" type="button">목록</button>
    							<span id="update">
    							<button class="btn btn-sm btn-warning" data-oper="updateForm" type="button">수정</button>
    							</span>
    							<button class="btn btn-sm btn-success" data-oper="remove" type="button">삭제</button>
    						</div>
    					</form>
    				</div>
    			</div>
    		</div>
    	</div>
    </div> 
    <div class="card-footer">스프링-뇽뇽이</div>
  </div>

	<script type="text/javascript">
		$(document).ready(function(){
			
			var regForm=$("#regForm");
			
			$("button").on("click",function(){//버튼의 모든 클릭을 감지하겠다.
				var oper = $(this).data("oper");
				if(oper=="register"){
					regForm.submit();
				}else if(oper=="reset"){
					regForm[0].reset();
				}else if(oper=="list"){
					location.href="${cpath}/board/list";	
				} else if(oper=="remove"){
					var idx = regForm.find("#idx").val();
					location.href="${cpath}/board/remove?idx="+idx;
				} else if(oper=="updateForm"){
					regForm.find("#title").attr("readonly",false);
					regForm.find("#content").attr("readonly",false);
					
					var upBtn="<button onclick='goUpdate()' class='btn btn-sm btn-info' type='button'>수정완료</button>";
					$("#update").html(upBtn);
					
				}
			}); 
			
			//상세보기
			$("a").on("click", function(e){  //e : 감지한 요소 자체를 넣어준다.
				
				e.preventDefault();
				var idx= $(this).attr("href");
		        
				$.ajax({
					url:"${cpath}/board/get",
					type: "get",
					data:{"idx":idx},
					dataType:"json", //어떤식으로 받아올지
					success: printBoard, //성공하면 printBoard함수를 실행시킴.콜백함수는 이름만 넣음.
					error:function(){
						alert("error");
					}
				});
			});
			 
			
			
		});
		
		function printBoard(vo){
			var regForm = $("#regForm");
			regForm.find("#title").val(vo.title);//title을 찾아서 value을 넣을 것이다
			regForm.find("#content").val(vo.content);//title을 찾아서 value을 넣을 것이다
			regForm.find("#writer").val(vo.writer);//title을 찾아서 value을 넣을 것이다
			
			regForm.find("input").attr("readonly", true);
			regForm.find("textarea").attr("readonly", true);
			//상세보기를 누르면 목록,수정,삭제가 나옴.
			$("#regDiv").css("display", "none");
			$("#updateDiv").css("display", "block");
			
			regForm.find("#idx").val(vo.idx);
			
			if("${user.member.name}" == vo.writer){
				$("button[data-oper='updateForm']").attr("disabled",false);
				$("button[data-oper='remove']").attr("disabled",false);
			}else{
				$("button[data-oper='updateForm']").attr("disabled",true);
				$("button[data-oper='remove']").attr("disabled",true);
			}
		}
	
		function goUpdate(){
			var regForm =$("#regForm");
			regForm.attr("action","${cpath}/board/modify");
			regForm.submit();
		}
	</script>


</body>
</html>
    