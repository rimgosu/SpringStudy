package kr.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.spring.entity.Board;
import kr.spring.entity.Member;
import kr.spring.mapper.BoardMapper;

@Service
public class BoardServiceImpl implements BoardService{
	
	@Autowired
	private BoardMapper mapper;

	@Override
	public List<Board> getList() {
		// 게시글 전체목록 가져오기 기능
		List<Board> list = mapper.getList();
		return list;
	}

	@Override
	public Member login(Member vo) {
		Member mvo = mapper.login(vo);
		return mvo;
	}

	@Override
	public void insertSelectKey(Board vo) {
		// TODO Auto-generated method stub
		mapper.insertSelectKey(vo);
	}

	@Override
	public Board get(int idx) {
		// TODO Auto-generated method stub
		Board vo = mapper.read(idx);
		return vo;
	}

	@Override
	public void modify(Board vo) {
		// TODO Auto-generated method stub
		mapper.update(vo);
	}

	@Override
	public void remove(int idx) {
		// TODO Auto-generated method stub
		mapper.delete(idx);
	}

	@Override
	public void reply(Board vo) {
		// vo : 댓글, parent : 원 글
		Board parent = mapper.read(vo.getIdx());
		System.out.println("parentBoardGroup : " + parent.getBoardGroup());
		vo.setBoardGroup(parent.getBoardGroup());
		vo.setBoardSequence(parent.getBoardSequence() +1);
		vo.setBoardLevel(parent.getBoardLevel() +1);
		
		// 현재 넣으려는 답글을 제외한 기존 같은 그룹의 댓글의 시퀀스 값을 1씩 올려줘야 한다.
		mapper.replySeqUpdate(parent);
		
		// 답변 저장
		mapper.replyInsert(vo);
	}
	
	
	
	

}









