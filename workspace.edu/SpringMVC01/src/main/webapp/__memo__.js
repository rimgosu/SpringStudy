/**
	1. ContextPath 변경하는 법 : 
	server.xml, context 태그, path 변경
	
	2. pom.xml :
	스프링 3.1.1 => 5.0.2로 최신화
	자바 1.6 => 1.8로 최신화
	프로젝트 클릭 => Alt+F5 => Force update if snapshots
	
	3. src/main/webapp/WEB-INF/web.xml
	<web-app version="2.5" xmlns="http://java.sun.com/xml/ns/javaee" =>
	<web-app version="2.5" xmlns="http://Java.sun.com/xml/ns/javaee"
	
	4. @Controller
	   @RequestMapping(value = "/", method = RequestMethod.GET)
	   => HomeController.java에서 root 주소에서의 작업을 처리함

	5. 부트스트랩 : 디자인 탬플릿 적용
		https://www.w3schools.com/bootstrap/tryit.asp?filename=trybs_panels_heading&stacked=h
	
 */