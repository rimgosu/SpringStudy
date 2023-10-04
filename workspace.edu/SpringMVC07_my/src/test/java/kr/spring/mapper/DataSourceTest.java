/*
 * package kr.spring.mapper;
 * 
 * import java.sql.Connection;
 * 
 * import org.junit.Test; import org.junit.runner.RunWith; import
 * org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.test.context.ContextConfiguration; import
 * org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
 * 
 * import lombok.extern.log4j.Log4j;
 * 
 * @Log4j // 테스트 실행결과를 콘솔창에 나오게하기 위함
 * 
 * @RunWith(SpringJUnit4ClassRunner.class) // 실행하기 위해 스프링컨테이너에 올리는 코드
 * 
 * @ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
 * public class DataSourceTest { // root-context.xml이 이상 없는지 test하는 클래스
 * 
 * // Connection이 잘 되는지 테스트
 * 
 * @Autowired // root-context.xml에 있는 id가 dataSource인 녀석을 사용하겠다 private
 * DataSourceTest dataSource;
 * 
 * @Test public void testConnection() { try ( Connection conn =
 * dataSource.getConnection() ) { log.info(conn); } catch(Exception e) {
 * e.printStackTrace(); } }
 * 
 * }
 */

package kr.spring.mapper;

import java.sql.Connection;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.extern.log4j.Log4j;

@Log4j // 테스트 실행결과를 콘솔창에 나오게 하기위함
@RunWith(SpringJUnit4ClassRunner.class) // 실행하기위해 스프링컨테이너에 올리는 코드
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml") 
// root-context.xml 경로를 잡아주는과정
public class DataSourceTest {
   // root-context.xml이 이상없는지 
   // test하는 클래스
   
   // Connection이 잘되는지 테스트
   @Autowired // root-context.xml에 있는 id가 dataSource인 녀석을 사용하겠다
   private DataSource dataSource;
   
   @Test
   public void testConnection() {
      
      try( Connection conn = dataSource.getConnection() ){
         log.info(conn);
      }catch(Exception e) {
         e.printStackTrace();
      }
      
   }
   
   
   
   
   
   
}








