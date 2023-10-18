package kr.spring.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {
	
	@Autowired
	private UserDetailsServiceImpl userService;
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		
		http.csrf().disable(); // CSRF 인증 토큰 비활성화
		
		http.authorizeHttpRequests()
			.antMatchers("/", "/member/**").permitAll()
			// "/", "member" 하위에 모든 접근을 허용하겠다.
			.antMatchers("/board/**").authenticated()
			// "/board/**" 접근하는 모든 경우는 인증된(로그인한) 사용자만 허용한다
			.and()
			.formLogin() // 별도의 로그인 폼을 사용하겠다
			.loginPage("/member/login") // 로그인 페이지는 member안에 login에서 하겠다
			.defaultSuccessUrl("/board/list") // 로그인 성공 시 board list로 이동하겠다
			.and()
			.logout() // Spring Security에서 제공하는 기본 로그아웃을 사용하겠다.
			.logoutUrl("/member/logout") // 로그아웃 실행하고싶다면 member/logout으로 요청하겠다
			.logoutSuccessUrl("/"); // 로그아웃하고 /로 이동
		
		http.userDetailsService(userService);
		
		
		return http.build();
	}
	
}
