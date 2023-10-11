package kr.spring.mapper;

import java.sql.Connection;
import java.util.List;

import javax.sql.DataSource;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import kr.spring.entity.Board;
import kr.spring.service.BoardServiceImpl;
import lombok.extern.log4j.Log4j;

@Log4j // 테스트 실행결과를 콘솔창에 나오게 하기위함
@RunWith(SpringJUnit4ClassRunner.class) // 실행하기위해 스프링컨테이너에 올리는 코드
@ContextConfiguration({"file:src/main/webapp/WEB-INF/spring/root-context.xml",
	"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"}) 
// root-context.xml 경로를 잡아주는과정
@WebAppConfiguration // 10/05 servlet 컨테이너를 사용하기 위한 어노테이션
public class DataSourceTest {
   // root-context.xml이 이상없는지 
   // test하는 클래스
   
   // Connection이 잘되는지 테스트
   @Autowired // root-context.xml에 있는 id가 dataSource인 녀석을 사용하겠다
   private DataSource dataSource;
   
   @Autowired
   private BoardMapper mapper;
   
   @Autowired
   private BoardServiceImpl service;
   
   @Autowired
   private WebApplicationContext ctx; // 10/05 Spring Container라는 메모리 공간 객체임. 
   
   private MockMvc mockMvc; // 10/05 가상의 mvc환경을 만들어주는 객체, 뷰, 핸들러, 맵빙 등등을 실행해줌.
   
   @Before  //10/05 test실행하기 전에 먼저 실행하는 부분
   public void setup() {
	   this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
   }
   
  
   
   
//   @Test
//   public void testInsert() {
//	   Board vo = new Board();
//	   vo.setMemID("와플");
//	   vo.setTitle("합병조건");
//	   vo.setContent("일반집합 연산자");
//	   vo.setWriter("관계대수");
//	   mapper.insertSelectKey(vo);
//   }
   
	
	  @Test 
	  public void testController() throws Exception{ 
		  
		  log.info(
				   mockMvc.perform(MockMvcRequestBuilders.get("/board/modify?idx")) //perform - 요청하다
				   .andReturn() // return값을 받아오겠다. 
				   .getModelAndView() //controller의 model값과iew경로를 다 받아오겠다. 
				   );  // 콘솔창에 출력하는거임. }
	 
				  
	  }
	 
   
   
//   @Test
//   //10/05
//   public void testGetList() {
//	   List<Board> list = service.getList();
//	   for(Board vo: list) {
//		   System.out.println(vo.toString());
//	   }
//   }  
//   
//   @Test  //mapper 작동 ㄷ잘되는지 테스트         
//   public void testGetList() {
//	   List<Board> list  = mapper.getList();
//	   for(Board vo : list) {
//		   System.out.println(vo.toString());
//	   }
//   }
     
   //junit을 통해서 jsp없이도 테스트 가능하다. 모듈별로 테스트 가능하다.
//   @Test
//   public void testConnection() {
//      
//      try( Connection conn = dataSource.getConnection() ){
//         log.info(conn);
//      }catch(Exception e) {
//         e.printStackTrace();
//      }
//      
//   }
   
   

   
}








