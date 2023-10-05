package kr.spring.entity;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor // 기본생성자, 이거 있어야 lombok 제대로 작동함
@ToString
public class Member {
	
	private int memIdx;
	private String memId;
	private String memPassword;
	private String memName;
	private int memAge;
	private String memGender;
	private String memEmail;
	private String memProfile; // 파일 경로
	
	// 자신의 권한 정보를 저장할 변수
	// 권한은 여러개가 될 수 있음.
	private List<Auth> authList;
	
	
}
