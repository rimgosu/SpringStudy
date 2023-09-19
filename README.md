![image](https://github.com/rimgosu/SpringStudy/assets/120752098/dbe4a854-f60f-4e27-9a7c-9c411d1662ad)



# SpringStudy

## Settings

### 필수세팅
1. 전자정부프레임워크
   - 표준프레임워크 개발자 교육 교재 및 실습 for Win (V4.0.0)
   - https://www.egovframe.go.kr/home/ntt/nttRead.do?pagerOffset=0&searchKey=&searchValue=&menuNo=65&bbsId=4&nttId=1743
   - 다운로드 받고, 반드시 C:\ 경로에 저장할 것
  
2. 스프링, 자바 업데이트
   -pom.xml 
   - 스프링 3.1.1 => 5.0.2로 최신화
   - 자바 1.6 => 1.8로 최신화
   - 프로젝트 클릭 => Alt+F5 => Force update if snapshots
  
3. 아파치 톰캣 등록 :
   > ![image](https://github.com/rimgosu/SpringStudy/assets/120752098/f1983a92-1bb5-4866-a69c-0ff0da62a847)

   - 프로젝트 끌어서 Server 탭의 Tomcat에 드래그해서 끌어 당겨야함
  
4. 오류메시지 지우기 :
   - src/main/webapp/WEB-INF/web.xml
   - <web-app version="2.5" xmlns="http://java.sun.com/xml/ns/javaee" =>
   - <web-app version="2.5" xmlns="http://Java.sun.com/xml/ns/javaee"

5. 한글 깨짐 해결 :
   - 한글깨짐방지.txt => web.xml로 붙여넣기







### DB 세팅


![image](https://github.com/rimgosu/SpringStudy/assets/120752098/9c4fcc99-fb49-4935-9429-e7db4a1c42a1)
![image](https://github.com/rimgosu/SpringStudy/assets/120752098/3de06ba0-2e9c-4ea3-8feb-567ce1db1ce8)
![image](https://github.com/rimgosu/SpringStudy/assets/120752098/a4c043ea-c2cc-4a22-a80c-0dd33cf236ed)

1. MYBATIS 개요
   - https://mybatis.org/mybatis-3/getting-started.html
   - JDBC 쉽게 API로!
   - hikariCP, JDBC, SPRING JDBC, MYBATIS, MYBATIS-SPRING 연결.

2. mybatis : https://mvnrepository.com/artifact/org.mybatis/mybatis/3.4.6
3. hikariCP : https://mvnrepository.com/artifact/com.zaxxer/HikariCP/3.4.1
4. MySQL Connector Java : https://mvnrepository.com/artifact/mysql/mysql-connector-java/5.1.42
5. Spring JDBC (스프링 버전과 맞춰줘야함) : https://mvnrepository.com/artifact/org.springframework/spring-jdbc/5.0.2.RELEASE
6. mybatis spring : https://mvnrepository.com/artifact/org.mybatis/mybatis-spring/1.3.2
7. 루트컨텍스트 설정.txt : => root-context.xml 붙여 넣기
      






  
### 세부 세팅 (라이브러리)
1. 부트스트랩 : 디자인 탬플릿 적용
   - https://www.w3schools.com/bootstrap/tryit.asp?filename=trybs_panels_heading&stacked=h

2. Lombok
   - https://projectlombok.org/
   - https://mvnrepository.com/artifact/org.projectlombok/lombok
   - 자동으로 생성자, getter/setter 생성. dto 수정 시 빠르게 대응 할 수 있음.
  
```
/* lombok => 생성자 만들어주는 api
 @Data => getter/setter
 @AllArgsConstructor => 전체 생성자
 @NoArgsConstructor => 빈 생성자
 @ToString => toString */
```













### mysql 세팅
1. C++ 다운로드
   - mysql 실행 안되는 경우 :
   - C:\eGovFrame-4.0.0\bin\mysql-5.7.32\startup.bat 눌렀는데, Version: '5.7.32-log'  socket: ''  port: 3306  MySQL Community Server (GPL)라고 뜨면 잘 되는 것임.
   - https://www.microsoft.com/ko-KR/download/details.aspx?id=40784
2. kr.spring.mapper 
   - Type : MySql_5.1 ver







## Lecture Note

### 9월 5일 (스프링 개요& 게시판 만들기-1)
1. ContextPath 변경하는 법 
   - server.xml, context 태그, path 변경
```
<Context docBase="SpringMVC03" path="/controller" reloadable="true" source="org.eclipse.jst.jee.server:SpringMVC03"/></Host>
```

2. @Controller
   - @RequestMapping(value = "/", method = RequestMethod.GET)
   - => HomeController.java에서 root 주소에서의 작업을 처리함










### 9월 6일
1. web.xml => 스프링 정보 총괄, 톰캣이 읽을 때 가장 먼저 읽는 파일
   - Root Spring => root-context.xml (여기에 jdbc 설정 해줘야함 - 루트컨텍스트 설정.txt)
   - ContextLoaderListener
   - DispatcherServlet => servlet-context.xml (디스패쳐 서블릿이 생성될 때 이 파일 참조함)

2. servlet-context.xml 
   - <context:component-scan base-package="kr.spring.controller" />
   - 여기 기본 context 매핑되어있음.

3. root-context.xml
   - 루트컨텍스트 설정.txt => root-context.xml 붙여 넣기
   - hikariConfig : db url, id, pw 정보 담고 있음
   - dataSource : getConnection() [hikariConfig를 참조]
   - SqlSessionFactoryBean : psmt, getClose() [dataSource를 참조]
   - mybatis-spring:scan : kr.spring.mapper 패키지에 있는 인터페이스 찾을거임.

4. BoardMapper.java (인터페이스)
```
@Mapper // MyBatis 인터페이스를 찾기위해 달아주는 부분
public interface BoardMapper {
	
	@Select("SELECT * FROM BOARD")
	public List<Board> getLists();

}
```






### 9월 7일
1. 리다이렉트 방식
   - return "redirect:주소";
   - return "redirect:boardList.do";
     
2. 간편한 파라미터 받기 : 
   - 1. 기본생성자
   - 2. getter/setter
   - 3. dto의 속성 이름 (name="DTO의 속성 이름")
   - 이 셋 조건을 충족하면 request.getParameter 할 필요 없이 dto자체로 받을 수 있다.

3. MyBatis XML Mapper
   - 1. Mapper 이름 동일
   - 2. &lt;select id="Mapper 매서드 이름과 동일"&gt;

   
```
<select id="getLists" resultType="kr.spring.entity.Board">
	SELECT * FROM BOARD ORDER BY INDATE DESC
</select>

<insert id="boardInsert">
	INSERT INTO BOARD (TITLE,	CONTENT,	WRITER)
	VALUES(#{title}, #{content}, #{writer})
</insert>
```

4. @RequestParam("idx") int idx :
   - request.getParameter과 동일한 역할.
  
5. 줄바꿈 :
   - <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
   - &lt;body&gt; <% pageContext.setAttribute("newLineChar", "\n"); %> &lt;/body&gt;
   - ${fn:replace( vo.content, newLineChar, "&lt;br&gt;") }
   - ${fn:split( vo.indate, " ")[0] }






### 9월 11일 (PathVariable, 비동기 통신)
1. @RequestParam("idx") int idx => int idx
   - 로 짧게 써도 파라미터 가져올 수 있다.
   - ※단, 파라미터의 name과 같은 변수명을 써야

2. &lt;input type="hidden" name="idx" value="${vo.idx }"&gt;
   - type="hidden" 옵션으로 데이터 더 보낼 수 있음.

3. PathVariable
   > ![image](https://github.com/rimgosu/SpringStudy/assets/120752098/8e51c709-9e0f-4819-9095-9d7ec77702d4)

   - get 방식에서, key값을 설정을 안하고 값을 넘겨줄 수 있다.
   - 1. boardContent.do?idx=${dto.idx } => boardContent.do/${dto.idx }
   - 2. /boardContent.do => /boardContent.do/{idx}
   - 3. @RequestParam("idx") => @RequestParam("idx")
   - 4. ../ : 경로 안으로 들어온 처리가 되어서, 경로 밖으로 나가줘야함.

4. jakson
   - json 응답 라이브러리
```
<!-- json 방식으로 응답하기 위한 API -->
<!-- https://mvnrepository.com/artifact/com.fasterxml.jackson.core/jackson-databind -->
<dependency>
	<groupId>com.fasterxml.jackson.core</groupId>
	<artifactId>jackson-databind</artifactId>
	<version>2.9.8</version>
</dependency>
```

5. @ResponseBody
   - 비동기임을 알려주는 어노테이션
```
public @ResponseBody List<Board> boardList() {}
```

6. Ajax
```
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
```

### 9월 12일 (비동기 통신-2)

1. jquery 반복문 활용
```
$.each(data, function(index, obj) {
				listHtml += "<tr>";
				listHtml += "<td>" + index + "</td>";
				listHtml += "<td>" + obj.title + "</td>";
				listHtml += "<td>" + obj.writer + "</td>";
				listHtml += "<td>" + obj.indate + "</td>";
				listHtml += "<td>" + obj.count + "</td>";
				listHtml += "</tr>";
			});
			
			$("#view").html(listHtml);
```

2. \&nbsp;
   * 줄바꿈
  
3. javascript $().serialize() 
   ``` fData = $("#frm").serialize(); ```
   * 1) {"키" : 값, "키" : 값}
   * 2) serialData = "title=" + title + "&content=" + content;
  


### 9월 13일 (REST-1)

1. Rest 전송 방식
   > URL에 통일성, 요청하는 URL+전송방식 묶어서 요청 가능!
   * URL의 통일성, 단순화

2. @RestController
```
@RestController
public class BoardRestController {
	@Autowired
	private BoardMapper mapper;
}
```

3. BoardRestController => 비동기 방식
   *  @ResponseBody 필요 없다. <br>
   => 어차피 비동기 방식이기 때문!

4. 매핑
   * @RequestMapping("/주소")
```
@RequestMapping("/board")
@RestController
public class BoardRestController {
	@RequestMapping("/all")
	public List<Board> boardList() {...}
}
```

5. 매핑 주소 사용 방법
   * $.ajax({url : "board/주소"})
```
$.ajax({url : "board/all"})
```

6. Mapping Type:
   1. @RequestMapping : get, post
   2. @GetMapping : get
   3. @PostMapping : post
   4. @DeleteMapping : delete
   5. @PutMapping : update 




### 9월 14일 (REST-2)

1. update / put 매서드
```
$.ajax({
	url : "board/update",
	type : "put",
	contentType : "application/json;charset=utf-8",
	data : JSON.stringify({
		"idx" : idx,
		"title" : title,
		"content" : content,
		"writer" : writer
	}),
	...
})
```
```
@PutMapping
@RequestMapping("/update")
public void boardUpdate(@RequestBody Board board) {
	System.out.println("게시글 수정 수행");
	mapper.boardUpdate(board);
}
```
> @RequestBody : <br>
> 자바 객체로 변환해주는 것임. <br>
> data를 JSON.stirngify()로 json => stirng으로 변환하므로 자바 객체로 변환해줄 필요가 있다.

2. 네비게이션 바 BOOTSTRAP
   - <https://www.w3schools.com/bootstrap/bootstrap_navbar.asp>

3. contextPath 전역변수로 지정
```<!-- context-path 값을 내장 객체 변수로 저장 -->
<c:set var="contextPath" value="${pageContext.request.contextPath}"></c:set>
```



### 9월 15일
1. modal :
   - [modal bootstrap](https://www.w3schools.com/bootstrap/tryit.asp?filename=trybs_modal&stacked=h)
   - 사용자의 이목을 집중시키기 위한 그래픽 인터페이스 창


```
$("#checkMessage").text("사용할 수 있는 아이디입니다.");
$("#myModal").modal("show");
```



### 9월 18일
1. RedirectAttributes
   - 리다이렉트 방식으로 이동할 때 보낼 데이터를 저장하는 객체
   - 리다이렉트할 때 보내고 사라짐
   - (↔ forward model)
  
```
// 매개변수 받기
public String join(RedirectAttributes rttr);

// 사용하기
rttr.addFlashAttribute("msgType", "실패메세지");
rttr.addFlashAttribute("msg", "모든 내용을 채워주세요.");
```
```
// 모델 사용 복습 (포워드 방식)
public String boardlist(Model model)

model.addAttribute("list", list);
```

2. $(document).ready()
   - 모든 문서가 로드 되었을 때 쓰는 자바스크립트 함수
```
<script>
$(document).ready(function() {})
</script>
```

3. required
   - input란이 공백이면 제출할 수 없음
```
<input required>
```

4. HttpSession
   - 스프링의 세션사용
  
```
public String join(HttpSession session)

session.setAttribute("member", member);
```

#### 프로젝트 임포트
5. 프로젝트 임포트하는법
   1. 이름이 같은 프로젝트 Delete, 실제 경로로 들어가서 그 프로젝트 있는지 확인
   2. 같은 경로로 .zip 파일 붙여넣기
   3. import-import-next-archaive-.zip파일 선택
   4. build path-JRE System Library - Workspace default JRE
   5. Server Runtime 연동 안되어있으면, Add Library-Server Runtime-Finish
   6. 서버 등록













### 9월 19일
1. webapp/resources
   - 이미지 등 저장공간
   - servlet-context.xml에 매핑되어있음.
```
<resources mapping="/resources/**" location="/resources/" />
```
  
2. bootstrap tabs
> <https://www.w3schools.com/bootstrap/tryit.asp?filename=trybs_tabs_dynamic&stacked=h>

![image](https://github.com/rimgosu/SpringStudy/assets/120752098/138d73df-57fe-4f06-b7bb-6c6db051c037)

3. c:if - 라디오 버튼
```
<c:if test="${member.memGender eq '남자'}"></c:if>
<c:if test="${member.memGender eq '여자'}"></c:if>
```

4. insert, update : cnt
   - update, insert 성공적으로 수행했으면 cnt=1
```
int cnt = mapper.update(member);
```

5. 부트스트랩 아이콘
   > <https://www.w3schools.com/bootstrap/bootstrap_ref_comp_glyphs.asp>

```
<span class="glyphicon glyphicon-log-in">로그인</span>
<span class="glyphicon glyphicon-log-out">로그아웃</span>
```






















