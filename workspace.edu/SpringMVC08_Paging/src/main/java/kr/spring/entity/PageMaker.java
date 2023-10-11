package kr.spring.entity;

import lombok.Data;

@Data
public class PageMaker { // 페이징 처리 클래스
	
	private Criteria cri; // 페이징 기준(설정) 불러오기
	private int totalCount; // 총 게시글의 수
	private int startPage; // 접속시 페이지 번호 default: 1
	private int endPage; // 끝페이지 번호
	private boolean prev; // 이전버튼
	private boolean next; // 다음버튼
	private int displayPageNum = 10; // 보여줄 페이지 수 1 2 3 4 5 6 7 8 9 10
	
	// 총 게시글의 수를 구하는 메소드
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
		makePagein();
	}
	
	public void makePagein() {
		// 1. 화면에 보여질 마지막 페이지 번호
		// 현재 하단에 보여줄 페이지 개수는? 10개
		// 질문. 내가 현재 7페이지를 보고 있다. 그렇다면 우측 끝에 있는 마지막 페이지 번호는? 10
		// 질문. 내가 현재 13페이지를 보고 있따. 그렇다면 우측 끝에 있는 마지막 페이지 번호는? 20
		// 7/ 10.0 => 소수 발생 시 올림 => 1 * 10 => 10
		// 13/ 10.0 => 2* 10 => 20
		endPage = (int)(Math.ceil((cri.getPage() / (double)displayPageNum) * displayPageNum));
		
		// 2. 화면에 보여질 시작 페이지 번호
		startPage = endPage - displayPageNum + 1;
		
		if(startPage <= 0) {
			startPage = 1; // 혹시라도 startPage가 0보다 작거나 같다면 1부터 시작할 수 있게
		}
		
	}
	
	
}
