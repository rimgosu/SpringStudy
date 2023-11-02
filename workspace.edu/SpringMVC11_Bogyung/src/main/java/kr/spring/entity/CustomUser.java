package kr.spring.entity;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

import lombok.Data;

@Data
public class CustomUser extends User{
	
	private Member member;
	

	public CustomUser(Member member) {
		super(member.getUsername(), member.getPassword(), AuthorityUtils.createAuthorityList("ROLE_"+member.getRole().toString()));
		
		this.member = member;
		
	}
	// 우리가 만든 회원정보 -> Member
	//Spring Context holder에 저장하기 위해서는
	//User형태로 변환하여서 넣어줘야한다.
	//그걸 해주는 클래스가 바로 CustomUser 클래스임.
	
	
	
	
	
	
}
