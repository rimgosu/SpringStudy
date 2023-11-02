package kr.spring.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

import lombok.Data;
import lombok.ToString;

@Entity
@Data
@ToString
public class Member {
	
	@Id
	private String username; //spring Security에서는 id가 아닌 username로 지정함.
	
	private String password; //spring Security에서는 pw가 아닌 password로 지정함.
	
	@Enumerated(EnumType.STRING) //@Enumerated -> 열거형(권한이 여러개임)
	private Role role; // 권한
	// ----------------여기까지는 반드시 필수로 넣어줘야한다.
	
	private String name; //이름
	private boolean ebled; // 계정활성화, 비활성화 부분
	
	
	
	
	
}
