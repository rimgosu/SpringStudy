package kr.spring.config;

import javax.servlet.Filter;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

// Web.xml의 기능을 담고 있는 클래스를 상속 받는다
public class WebConfig extends AbstractAnnotationConfigDispatcherServletInitializer {
	// web.xml을 대체할 java class
	
	
	@Override
	protected Class<?>[] getRootConfigClasses() {
		// DB 설정관련 RootConfig.java 파일을 가져온다
		// 리턴타입은 Class의 배열 형태이다
		// 왜냐하면 나중에 설정파일이 여러개 추가할 수 있기 때문에
		return new Class[] {RootConfig.class, SecurityConfig.class};
	}

	@Override
	protected Filter[] getServletFilters() {
		// 인코딩 설정하는 부분
		CharacterEncodingFilter encodingFilter = new CharacterEncodingFilter();
		encodingFilter.setEncoding("UTF-8");
		encodingFilter.setForceEncoding(true);
		return new Filter[] {encodingFilter};
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		// Servlet 설정 관련 있는 ServletConfig.java 파일을 가져온다
		return new Class[] {ServletConfig.class};
	}

	@Override
	protected String[] getServletMappings() {
		// TODO Auto-generated method stub
		return new String[] {"/"};
	}
	
	
}





