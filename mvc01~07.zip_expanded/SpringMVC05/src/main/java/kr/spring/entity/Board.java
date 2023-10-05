package kr.spring.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Board {
	
	private int idx; // 번호
	private String memID; // 작성했을 당시 로그인 한 사람의 아이디
	private String title; // 제목
	private String content; // 내용
	private String writer; // 작성자
	private String indate; // 작성일
	private int count; // 조회수
	
	
}





