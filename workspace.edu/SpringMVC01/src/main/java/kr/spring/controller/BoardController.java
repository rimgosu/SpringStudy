package kr.spring.controller;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.spring.entity.Board;

@Controller // 현재 클래스를 핸들러 매핑이 찾기 위해 컨트롤러로 등록하는 부분
public class BoardController {

	/*
	 * @RequestMapping("/") // 요청 url로 들어왔을 때 아래 기능을 수행하겠다 public String home() {
	 * System.out.println("홈 기능 수행"); return "boardList"; }
	 */
	
	@RequestMapping("/boardList.do") 
	public String boardlist(Model model) {
		System.out.println("게시판 목록 보기 기능 수행");
		
		// 게시글 정보 가져오기
		// 한 개의 게시글은 - 번호, 제목, 내용, 작성자, 작성일, 조회수
		Board b0 = new Board(1, "오늘 점심 추천 받는다","짜장면 말고","중국집매니아","2023-09-05",5);
		Board b1 = new Board(2, "ㅎㅇ","짜장면 말고2","중국집매니아1","2023-09-06",15);
		Board b2 = new Board(3, "ㅂㅇ","짜장면 말고3","중국집매니아2","2023-09-07",22);
		Board b3 = new Board(4, "ㅎㅎㅎ","짜장면 말고4","중국집매니아3","2023-09-08",101);
		Board b4 = new Board(5, "5번째게시글","짜장면 말고5","중국집매니아4","2023-09-09",3);
		
		ArrayList<Board> list = new ArrayList<Board>();
		list.add(b0);
		list.add(b1);
		list.add(b2);
		list.add(b3);
		list.add(b4);
		
		// list를 jsp로 전달해줘야할 때 model에 저장해서 씀
		model.addAttribute("list", list);
		
		return "boardList";
	}
}
