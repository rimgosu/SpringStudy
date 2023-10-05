package kr.spring.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Auth {

	private int no;
	private String memId;
	private String auth; // 회원 권한 (ROLE_USER, ROLE_MANAGER, ROLE_ADMIN)
	
}
