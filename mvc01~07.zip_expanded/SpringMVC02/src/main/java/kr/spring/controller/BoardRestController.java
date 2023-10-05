package kr.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import kr.spring.entity.Board;
import kr.spring.mapper.BoardMapper;



@RequestMapping("/board")
@RestController
public class BoardRestController {

	/*
	 * RestController 비동기 방식의 일만 처리하는 Controller Rest 전송 방식을 처리할 수 있다 - 요청 url +
	 * 전송방식(상태)을 묶어서 처리 가능 사용이유 : url의 통일성 및 단순화
	 */

	@Autowired
	private BoardMapper mapper;

	
	/*
	 * @ResponseBody 필요 없다.
	 * => 어차피 비동기 방식이기 때문!
	 */
	@GetMapping("/all")
	public List<Board> boardList() {
		System.out.println("게시글 전체보기 기능 수행");
		List<Board> list = mapper.getLists();
		return list;

	}

	@RequestMapping("/new")
	public void boardInsert(Board board) {
		System.out.println("게시글 작성 기능 수행");
		mapper.boardInsert(board);

	}
	
	@GetMapping("/{idx}")
	public Board boardContent(@PathVariable("idx") int idx) {
		System.out.println("게시글 정보 하나 보여주기 기능 수행");
		Board vo = mapper.boardContent(idx);
		return vo;
	}
	
	@DeleteMapping("/{idx}")
	public void boardDelete(@PathVariable int idx) {
		System.out.println("게시글 작성 기능 수행");
		mapper.boardDelete(idx);

	}

	@PutMapping("/update")
	public void boardUpdate(@RequestBody Board board) {
		System.out.println("게시글 수정 수행");
		mapper.boardUpdate(board);
	}

	@PutMapping("/count")
	public void boardCount(@RequestBody Board board) {
		System.out.println("조회수 1올리기 기능 수행");
		mapper.boardCount(board.getIdx());
	}

}
