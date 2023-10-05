package kr.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.web.filter.CharacterEncodingFilter;

@Configuration // WebConfig.java에서 SecurityConfig를 읽어오기위한 어노테이션
@EnableWebSecurity // web에서 Sercurity를 쓰겠다
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	// Spring Security 환경설정하는 클래스
	// WebSecurityConfigurerAdapter
	// 요청에 대한 보안 설정을 해주는 클래스
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// 요청에 대한 보안 설정하는 곳
		CharacterEncodingFilter filter = new CharacterEncodingFilter();
		filter.setEncoding("UTF-8");
		filter.setForceEncoding(true);
		http.addFilterBefore(filter, CsrfFilter.class);
	}
	
	@Bean // 패스워드 인코딩 기능을 메모리에 올리기
	public PasswordEncoder passwordEncoder() {
		// 비밀번호 암호화 메소드
		return new BCryptPasswordEncoder();
	}
	
	
	
	
}




