# SpringStudy

### 9월 5일 (스프링 개요& 게시판 만들기-1)
1. ContextPath 변경하는 법 
server.xml, context 태그, path 변경

2. pom.xml 
- 스프링 3.1.1 => 5.0.2로 최신화
- 자바 1.6 => 1.8로 최신화
- 프로젝트 클릭 => Alt+F5 => Force update if snapshots

3. src/main/webapp/WEB-INF/web.xml
- <web-app version="2.5" xmlns="http://java.sun.com/xml/ns/javaee" =>
- <web-app version="2.5" xmlns="http://Java.sun.com/xml/ns/javaee"

4. @Controller
- @RequestMapping(value = "/", method = RequestMethod.GET)
- => HomeController.java에서 root 주소에서의 작업을 처리함

6. 부트스트랩 : 디자인 탬플릿 적용
- https://www.w3schools.com/bootstrap/tryit.asp?filename=trybs_panels_heading&stacked=h

7. Lombok :
- https://projectlombok.org/
- https://mvnrepository.com/artifact/org.projectlombok/lombok
- 자동으로 생성자, getter/setter 생성. dto 수정 시 빠르게 대응 할 수 있음.

8. 아파치 톰캣 등록 :
- 프로젝트 끌어서 Server 탭의 Tomcat에 드래그해서 끌어 당겨야함

---
### 9월 6일
1. web.xml => 스프링 정보 총괄
   - Root Spring
   - ContextLoaderListener
   - DispatcherServlet => servlet-context.xml (디스패쳐 서블릿이 생성될 때 이 파일 참조함)













