package kr.spring.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.spring.entity.Board;
import kr.spring.mapper.BoardMapper;

@Controller // 현재클래스를 핸들러맵핑이 찾기위해 컨트롤러로 등록하는 부분
public class BoardController {

	@Autowired
	private BoardMapper mapper; // MyBatis한테 JDBC 실행하게 요청하는 객체

	
	@RequestMapping("/boardMain.do") // 요청 url로 들어왔을때 아래 기능을 수행하겠다
	public String home() {
		System.out.println("게시판 페이지 이동 기능수행");
		return "board/main";
	}
	


}




