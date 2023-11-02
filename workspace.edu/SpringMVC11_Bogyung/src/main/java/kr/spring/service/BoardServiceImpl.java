package kr.spring.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.spring.entity.Board;
import kr.spring.repository.BoardRepository;

@Service
public class BoardServiceImpl implements BoardService { // BoardService를 가져다 쓰겠다.
	
	@Autowired
	private BoardRepository boardRepository ;
	
	@Override
	public List<Board> getList() { //여러개의 게시물을 가져오니까 board데이터의 list형태
		List<Board> list = boardRepository.findAll(); 
		return list;
	}

	@Override
	public void register(Board vo) {
		boardRepository.save(vo);
	}

	//상세보기
	@Override
	public Board get(Long idx) {
		Optional<Board> vo = boardRepository.findById(idx);  //board형태가 아니라 optional형태임.
		return vo.get(); //이렇게 하면 optional안에 있는걸 꺼내주는거임.
	}

	@Override
	public void delete(Long idx) {
		boardRepository.deleteById(idx);
	}

	@Override
	public void update(Board vo) {
		//JPA의 SAVE메서드는 새로운pk값 또는 없는 값이 들어오면 insert기능을
		// 기존에 존재하는 pk값이 들어오면 update기능을 함.
		boardRepository.save(vo);
		
	}
	
}
