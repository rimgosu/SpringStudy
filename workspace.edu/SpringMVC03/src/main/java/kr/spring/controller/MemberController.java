package kr.spring.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	public @ResponseBody int registerCheck(@RequestParam("memId") String memId) {
	    Member m = mapper.registerCheck(memId);
	    // null이면 1 반환
	    if (m != null || memId.equals("")) {
	    	return 0;
	    } else {
	    	return 1;
	    }
	     
		
	}
	
	
	@RequestMapping("/join.do")
	public String join(Member member, RedirectAttributes rttr, HttpSession session) {
		System.out.println("회원가입 기능요청");
		System.out.println(member.toString());
		
		
		if(
			member.getMemId() == null || member.getMemId().equals("") || 
			member.getMemPassword() == null || member.getMemPassword().equals("") ||
			member.getMemName() == null || member.getMemName().equals("") ||
			member.getMemAge() == 0 ||
			member.getMemEmail() == null || member.getMemEmail().equals("") 
		){
			rttr.addFlashAttribute("msgType", "실패메세지");
			rttr.addFlashAttribute("msg", "모든 내용을 채워주세요.");
			
			return "redirect:/joinForm.do";
		}
		 else {
			mapper.join(member);
			
			rttr.addFlashAttribute("msgType", "성공메시지");
			rttr.addFlashAttribute("msg", "회원가입 성공!");
			
			// 회원가입 성공 시 로그인 처리까지 시키기
			session.setAttribute("member", member);
			
			return "redirect:/";
		}
			
			
		
		
	}
	
	
}
