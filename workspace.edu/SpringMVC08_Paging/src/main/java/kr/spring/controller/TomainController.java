package kr.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TomainController {
	
	@RequestMapping("/")
	public String goHome() {
		return "redirect:/board/list";
	}

}
