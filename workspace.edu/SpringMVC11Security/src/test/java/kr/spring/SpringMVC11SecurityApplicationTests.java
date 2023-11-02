package kr.spring;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import kr.spring.entity.Member;
import kr.spring.entity.Role;
import kr.spring.repository.MemberRepository;

@SpringBootTest
class SpringMVC11SecurityApplicationTests {
	
	@Autowired
	private MemberRepository memberRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	

	@Test
	void contextLoads() {
		// 회원가입테스트
		Member m = new Member();
		m.setUsername("ddd");
		m.setPassword(passwordEncoder.encode("5555"));
		m.setName("서보경");
		m.setRole(Role.MEMBER);
		m.setEbled(true);
		
		memberRepository.save(m);
		
	}

}
