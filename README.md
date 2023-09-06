# SpringStudy

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
   - 프로젝트 끌어서 Server 탭의 Tomcat에 드래그해서 끌어 당겨야함
  
4. 오류메시지 지우기 :
   - src/main/webapp/WEB-INF/web.xml
   - <web-app version="2.5" xmlns="http://java.sun.com/xml/ns/javaee" =>
   - <web-app version="2.5" xmlns="http://Java.sun.com/xml/ns/javaee"







### DB 세팅
1. MYBATIS 개요
   - ![image](https://github.com/rimgosu/SpringStudy/assets/120752098/9c4fcc99-fb49-4935-9429-e7db4a1c42a1)
   - ![image](https://github.com/rimgosu/SpringStudy/assets/120752098/3de06ba0-2e9c-4ea3-8feb-567ce1db1ce8)
   - ![image](https://github.com/rimgosu/SpringStudy/assets/120752098/a4c043ea-c2cc-4a22-a80c-0dd33cf236ed)

   - https://mybatis.org/mybatis-3/getting-started.html
   - JDBC 쉽게 API로!
   - hikariCP, JDBC, SPRING JDBC, MYBATIS, MYBATIS-SPRING 연결.
   - 

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













### mysql 세팅
1. C++ 다운로드
   - mysql 실행 안되는 경우 :
   - C:\eGovFrame-4.0.0\bin\mysql-5.7.32\startup.bat 눌렀는데, Version: '5.7.32-log'  socket: ''  port: 3306  MySQL Community Server (GPL)라고 뜨면 잘 되는 것임.
   - https://www.microsoft.com/ko-KR/download/details.aspx?id=40784
2. kr.spring.mapper 
   - Type : MySql_5.1 ver









### 9월 5일 (스프링 개요& 게시판 만들기-1)
1. ContextPath 변경하는 법 
   - server.xml, context 태그, path 변경

2. @Controller
   - @RequestMapping(value = "/", method = RequestMethod.GET)
   - => HomeController.java에서 root 주소에서의 작업을 처리함










### 9월 6일
1. web.xml => 스프링 정보 총괄
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












