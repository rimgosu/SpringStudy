package kr.spring.entity;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import lombok.Data;

@Data
public class MemberUser extends User{
	// Spring Security에 Member 객체를 담을 수 있게 해주는 클래스
	private Member member;
	
	public MemberUser(String username, String password, Collection<? extends GrantedAuthority> authorities) {
		// User 메소드 Default : id, pw, 권한
		super(username, password, authorities);
		
	}
	
	// 실제로 우리가 사용할 생성자
	public MemberUser(Member mvo) {
		// User 클래스의 생성자를 사용해서 구현한다\
		// 생성자 (아이디, 비밀번호 권한)을 넣어준다.
		super(mvo.getMemID(), mvo.getMemPassword(),
				/* User 클래스의 생성자의 사용하는 권한 정보는 */
				/* Collection<SimpleGrantedAuthority>로 변경해서 넣어야함 */
				mvo.getAuthList().stream() /* 바이트로 변경 */
				.map(auth -> new SimpleGrantedAuthority(auth.getAuth()))
				/* List<Auth> -> Collection 안에 들어갈 수 있게 변경 */
				.collect(Collectors.toList())
				/* 최종 콜렉션 리스트로 변경 */
				);
		this.member = mvo;
		
		
	}
	
	
}
