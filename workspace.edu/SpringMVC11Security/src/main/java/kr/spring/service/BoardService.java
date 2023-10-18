package kr.spring.service;

import java.util.List;

import kr.spring.entity.Board;

public interface BoardService {
	
	public List<Board> getList(); // 게시글 전체조회
	
	public void register(Board vo); // 게시글 등록

	public Board get(Long idx);

	public void delete(Long idx);

	public void update(Board vo);
	
	
	
}
