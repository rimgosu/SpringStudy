package kr.spring.entity;

import javax.management.relation.Role;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;
import lombok.ToString;

@Entity
@Data
@ToString
public class Member {

	// 시큐리티에선 반드시 username, password라고 지정해줘야함.
	// 반드시 role도 필요하다.
	@Id
	private String username; // Spring Security에서는 id가 아닌 username으로 지정
	
	private String password; // Spring Security에서는 pw가 아닌 password로 지정
	
	private Role role;
	
	private String name;
	
	private boolean ebled; // 계정 활성화/비활성화
	
}
