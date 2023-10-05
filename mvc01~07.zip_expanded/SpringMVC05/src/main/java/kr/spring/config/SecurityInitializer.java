package kr.spring.config;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;
// Spring Security를 사용하기위한 클래스
public class SecurityInitializer extends AbstractSecurityWebApplicationInitializer{
	// AbstractSecurityWebApplicationInitializer
	// 를 상속만 받으면 자동으로 보안관련 객체들이 생성되어서
	// 스프링 컨테이너(메모리공간)으로 올라간다
	
}
