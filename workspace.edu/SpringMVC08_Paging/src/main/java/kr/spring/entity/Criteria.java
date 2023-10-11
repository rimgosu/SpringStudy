package kr.spring.entity;

import lombok.Data;

@Data
public class Criteria { // 기준이라는 뜻
	
	private int page; // 현재 페이지 번호를 저장할 변수
	private int perPageNum; // 한 페이지에 보여줄 게시글의 개수
	
	
}
