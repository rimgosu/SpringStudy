package kr.spring.entity;

import lombok.Data;

@Data
public class Criteria { // 기준이라는 뜻
	
	private int page; // 현재 페이지 번호를 저장할 변수
	private int perPageNum; // 한 페이지에 보여줄 게시글의 개수
	
	// Criteria 기본 셋팅 생성자를 통해서 하기
	public Criteria() {
		this.page = 1;
		this.perPageNum = 5;
		
	}
	
	// 현재 페이지의 게시글의 시작번호를 구하는 메소드
	// 1page => 0~9 2page => 10~19 3page => 20~29
	// 오라클 : 1~10, mysql : 0~9
	public int getPageStart() {
		return (page - 1) * perPageNum;
	}
	
}
