package kr.spring.service;

import java.util.List;

import kr.spring.entity.Board;
import kr.spring.entity.Member;

// Service 클래스에서 사용할 기능의 이름을 정의하는 인터페이스
public interface BoardService {
	
	// 게시글 전체목록 보기 기능
	public List<Board> getList();
	
	public Member login(Member vo);
	
	public void insertSelectKey(Board vo);

	public Board read(int idx);

}
