package kr.spring.config;

import javax.servlet.Filter;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

// Web.xml의 기능을 담고 있는 클래스릉 상속받는다.
public class WebConfig extends AbstractAnnotationConfigDispatcherServletInitializer{

	@Override
	protected Class<?>[] getRootConfigClasses() {
		// DB설정 관련 RootConfig.java 파일을 가져온다
		// return 타입은 Class의 배열 형태
		// 나중에 설정파일이 여러 개 추가할 수 있기 때문.
		return new Class[] {RootConfig.class, SecurityConfig.class}; // 
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		// Servlet 설정 관련 있는 ServletConfig.java 파일을 가져온다.
		return new Class[] {ServletConfig.class};
	}

	@Override
	protected String[] getServletMappings() {
		// 시작점
		return new String[] {"/"};
	}

	@Override
	protected Filter[] getServletFilters() {
		CharacterEncodingFilter encodingFilter = new CharacterEncodingFilter();
		encodingFilter.setEncoding("utf-8");
		encodingFilter.setForceEncoding(true);
		return new Filter[] {encodingFilter};
	}

	
	
	
	
	

}
