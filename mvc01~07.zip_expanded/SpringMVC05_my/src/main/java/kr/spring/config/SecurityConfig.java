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

@Configuration // WebConfig.java에서 SecurityConfig를 읽어오기 위한 어노테이션
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// 요청에 대한 보안 설정하는 곳
		CharacterEncodingFilter filter = new CharacterEncodingFilter();
		filter.setEncoding("utf-8");
		filter.setForceEncoding(true);
		http.addFilterBefore(filter, CsrfFilter.class);
		
	}
	
	@Bean // password 인코딩 기능을 메모리에 올리기
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
		
	}
	
	
	
	
	
	
	
}
