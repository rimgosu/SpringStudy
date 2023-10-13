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
	//login정보는 member에 있음.
	//로그인 하고 나서는 session에 저장,
	public String login(Member vo, HttpSession session) {
		
		//로그인 성공하면 member타입으로 받아옴.
		Member mvo =service.login(vo);
		
		if(mvo != null) {
			session.setAttribute("mvo", mvo);
		}
		return "redirect:/board/list";
		
	}
	@RequestMapping("/logoutProcess")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/board/list";
	}
}
