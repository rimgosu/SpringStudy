package kr.spring.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.spring.entity.Member;
import kr.spring.service.BoardService;

@Controller
@RequestMapping("/login/*")
public class LoginController {
	
	@Autowired
	private BoardService service;
	
	@RequestMapping("/loginProcess")
	public String login(Member vo, HttpSession session) {
		System.out.println("**********로그인 기능 수행***********");
		System.out.println(vo.toString());
		
		Member mvo = service.login(vo);
		
		if(mvo != null) {
			session.setAttribute("mvo", mvo);
		}
		
		System.out.println(mvo.toString());
		
		return "redirect:/board/list";
	}
	
	@RequestMapping("/logoutProcess")
	public String logout(HttpSession session) {
		System.out.println("***********로그아웃 기능 실행*************");
		
		session.invalidate();
		return "redirect:/board/list";
		
	}
	
}
