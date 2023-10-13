package kr.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.spring.entity.Board;
import kr.spring.entity.Criteria;
import kr.spring.entity.Member;
import kr.spring.mapper.BoardMapper;

@Service  //여기가 실질적으로 일하는 service임. 
public class BoardServiceImpl implements BoardService {

	@Autowired
	private BoardMapper mapper;
	
	//10/05 인터페이스의 메서드 구현 하는 거임.
	@Override
	public List<Board> getList(Criteria cri) {
		// 게시글 전체목록 가져오기 기능 = mapper가 하는 일임.controller > service > mapper.
		List<Board> list = mapper.getList(cri);
		return list;
	}

	@Override
	public Member login(Member vo) {
		Member mvo = mapper.login(vo);
		return mvo;
	}

	@Override
	public void register(Board vo) {
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
		mapper.update(vo);
	}
	@Override
	public void remove(int idx) {
		mapper.delete(idx);
	}

	@Override
	public void reply(Board vo) {
	    // 답글 만들기
		// vo :부모글의 번호, 로그인한 아이디, 제목, 답글, 작성자 이름
		// 부모글의 정보를 가져오기
		Board parent = mapper.read(vo.getIdx());
		// 부모글의 boardGroup값을 답긆 vo에 저장하기.
		vo.setBoardGroup(parent.getBoardGroup());
		//sequence와 레벨은 부모글에서 +1
		vo.setBoardSequence(parent.getBoardSequence()+1);
		vo.setBoardLevel(parent.getBoardLevel()+1);
		
		//현재 추가하려는 답글을 제외한 기존에 같은 그룹의 댓글 시퀀스 값을 1씩 올려줘야한다,
		//왜냐면 최신순으로 답글이 출력되기 때문에.
		mapper.replySeqUpdate(parent);
		
		//답변 저장기능
		mapper.replyInsert(vo);
	}

	@Override
	public int totalCount(Criteria cri) {
		// TODO Auto-generated method stub
		
		int count = mapper.totalCount(cri);
		return count;
	}
}
