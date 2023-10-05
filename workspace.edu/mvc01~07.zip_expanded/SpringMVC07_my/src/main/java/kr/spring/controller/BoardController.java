package kr.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BoardController {
	
	@GetMapping("/boardList.do")
	public String boardList() {
		return "board/boardList";
	}
	
}
