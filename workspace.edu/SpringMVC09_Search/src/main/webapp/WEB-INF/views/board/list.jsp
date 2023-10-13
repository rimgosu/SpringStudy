<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="cpath" value="${pageContext.request.contextPath}" />
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
		<h2>Spring MVC09</h2>
		<div class="panel panel-default">
			<div class="panel-heading">

				<c:if test="${empty mvo}">

					<form class="form-inline" action="${cpath}/login/loginProcess"
						method="post">
						<div class="form-group">
							<!-- 라벨태그를 사용하면 글자를 클릭하면 활성화됨, -->
							<label for="id">ID: </label> <input type="text"
								class="form-control" id="id" name="memID">
						</div>
						<div class="form-group">
							<!-- 라벨태그를 사용하면 글자를 클릭하면 활성화됨, -->
							<label for="pwd">Password: </label> <input type="password"
								class="form-control" id="pwd" name="memPwd">
						</div>
						<button type="submit" class="btn btn-default">로그인</button>
					</form>

				</c:if>
				<c:if test="${not empty mvo}">
					<!-- login: loginController로 감. 그리고 logoutProcess 는 @requestmapping으로 함. -->
					<form class="form-inline" action="${cpath}/login/logoutProcess"
						method="post">
						<div class="form-group">
							<lable> ${mvo.memName}님 방문을 환영합니다. </lable>

						</div>
						<button type="submit" class="btn btn-default">로그아웃</button>
					</form>

				</c:if>

			</div>
			<div class="panel-body">
				<table class="table table-bordered table-hover">
					<thead>
						<!-- 10/05 그냥 영역을 나누는 태그임. -->
						<tr>
							<th>번호</th>
							<th>제목</th>
							<th>작성자</th>
							<th>작성일</th>
							<th>조회수</th>
						</tr>
					</thead>
					<tbody>
						<!-- 10/05 꺼내서 vo라는 이름에 담겠다. -->
						<c:forEach items="${list}" var="vo" varStatus="i">
							<tr>
								<td>${i.count}</td>
								<!-- 댓글 표현 하는 곳 -->
								<!-- 10/11 삭제 글/댓글 표현 (xml에도 update로 변경함.-->
								<td><c:if test="${vo.boardAvailable ==0}">
										<a href="javascript:alert('삭제된 게시글 입니다. ')"> <c:if
												test="${vo.boardLevel > 0 }">
												<c:forEach begin="0" end="${vo.boardLevel}" step="1">
													<span style="padding-left: 15px"></span>
												</c:forEach>
										ㄴ[RE]
									</c:if> 삭제된 게시물 입니다.
										</a>
									</c:if> <c:if test="${vo.boardAvailable > 0}">
										<a class="move" href="${vo.idx}"> <c:if
												test="${vo.boardLevel > 0 }">
												<c:forEach begin="0" end="${vo.boardLevel}" step="1">
													<span style="padding-left: 15px"></span>
												</c:forEach>
										ㄴ[RE]
									</c:if> <c:out value="${vo.title}"></c:out>
										</a>
									</c:if></td>
								<td>${vo.writer}</td>
								<td><fmt:formatDate value="${vo.indate}"
										pattern="yyyy-MM-dd" /></td>
								<td>${vo.count}</td>
							</tr>
						</c:forEach>

					</tbody>

					<c:if test="${not empty mvo}">
						<tr>
							<td colspan="5">
								<button id="regBtn" class="btn btn-xs btn-info pull-right">글쓰기</button>
							</td>
						</tr>
					</c:if>

				</table>
				
				<!-- 검색메뉴 -->
				<div style="text-align: center;">
					<form class="form-inline" action="${cpath}/board/list" method="post">
						<div class="form-group">
							<select name="type" class="form-control">
								<option value="writer">작성자</option>
								<option value="title">제목</option>
								<option value="content">내용</option>
							</select>
						</div>
						
						<div class="form-group">
							<input type="text" class="form-control" name="keyword">
						</div>
						<button type="submit" class="btn btn-success">검색</button>
						
					</form>
				</div>

				<div style="text-align: center;">
					<ul class="pagination">

						<!-- 이전버튼 처리 -->
						<c:if test="${pageMaker.prev}">
							<li class="paginate_button previous"><a
								href="${pageMaker.startPage - 1}">◀</a></li>
						</c:if>

						<!-- 페이지번호 처리 -->
						<c:forEach var="pageNum" begin="${pageMaker.startPage}"
							end="${pageMaker.endPage}">
							<c:if test="${pageMaker.cri.page == pageNum}">
								<li class="paginate_button active"><a href="${pageNum}">${pageNum}</a></li>
							</c:if>
							<c:if test="${pageMaker.cri.page != pageNum}">
								<li class="paginate_button"><a href="${pageNum}">${pageNum}</a></li>
							</c:if>
						</c:forEach>

						<!-- 다음버튼 처리 -->
						<c:if test="${pageMaker.next}">
							<li class="paginate_button next"><a
								href="${pageMaker.endPage + 1}">▶</a></li>
						</c:if>
					</ul>

					<!-- a태그를 버튼으로 -->
					<form action="${cpath}/board/list" id="pageFrm">
						<input type="hidden" id="page" name="page"
							value="${pageMaker.cri.page}"> <input type="hidden"
							id="perPageNum" name="perPageNum"
							value="${pageMaker.cri.perPageNum}">
						
					</form>

				</div>


			</div>
			<div class="panel-footer">스프링게시판-뇽뇽이</div>
		</div>
	</div>



	<!-- 모달 창!  -->
	<div class="modal fade" id="myMessage" role="dialog">
		<div class="modal-dialog">

			<!-- Modal content-->
			<div id="messageType" class="modal-content">
				<div class="modal-header panel-heading">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">Modal Header</h4>
				</div>
				<div class="modal-body"></div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
				</div>
			</div>

		</div>
	</div>

	<!-- 모달 자바스크립트 -->
	<script type="text/javascript">
		$(document).ready(function() {
			
			var pageFrm = $("#pageFrm");
			// li태그 안에 a태그 값 가져와서 form태그에 적용시켜 페이지이동

			$(".paginate_button a").on("click", function(e) {
				// e -> 현재 클릭한 a태크 요소 자체
				e.preventDefault(); // a태그의 href속성 작동 막기
				var page = $(this).attr("href"); // 클릭한 a태그의 href값 가져오기
				pageFrm.find("#page").val(page);
				pageFrm.submit();
			});
			
			$(".move").on("click", function(e) {
				e.preventDefault();
				var idx = $(this).attr("href");
				var tag = "<input type='hidden' name='idx' value='"+idx+"'>";
				pageFrm.append(tag);
				pageFrm.attr("action", "${cpath}/board/get");
				pageFrm.submit();
			});
			
			
			var result = "${result}";
			checkModal(result);

			$("#regBtn").click(function() {
				//버튼을 눌렀을 때, 함수를 작동시키겠다.
				//location.href는 페이지를 이동시킴.
				location.href = "${cpath}/board/register";
			});
		});

		function checkModal(result) {
			if (result == '') {
				return;
			}
			if (parseInt(result) > 0) {
				$(".modal-body").text("게시글 " + result + "번이 등록되었습니다.");
				$("#myMessage").modal("show");
			}
		}
	</script>

	<!-- a태그 => form태그로 처리 (유지보수 용이) -->
	<script type="text/javascript">
	
	</script>

</body>
</html>