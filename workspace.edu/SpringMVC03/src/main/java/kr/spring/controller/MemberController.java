package kr.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.spring.entity.Member;
import kr.spring.mapper.MemberMapper;

@Controller
public class MemberController {
	
	@Autowired
	private MemberMapper mapper;
	

	@RequestMapping("/joinForm.do")
	public String joinForm() {
		return "member/joinForm";
	}
	

	@RequestMapping("/registerCheck.do")
	public @ResponseBody int registerCheck(@RequestParam("memID") String memID) {
	    Member m = mapper.registerCheck(memID);
	    // null이면 1 반환
	    if (m != null || memID.equals("")) {
	    	return 0;
	    } else {
	    	return 1;
	    }
	     
		
	}
	
}
