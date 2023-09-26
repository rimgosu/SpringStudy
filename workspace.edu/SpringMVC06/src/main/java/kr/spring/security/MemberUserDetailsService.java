package kr.spring.security;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import kr.spring.mapper.MemberMapper;

public class MemberUserDetailsService implements UserDetailsService{

	@Autowired
	private MemberMapper Mapper;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	// Spring Security에서 Mapper을 사용하기 위한 연결 클래스 => Service
	
	
}
