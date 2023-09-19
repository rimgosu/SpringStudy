package kr.spring.controller;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Mapper;
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
	
	
	
	@RequestMapping("/logout.do")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
	
	
	@RequestMapping("/loginForm.do")
	public String loginForm() {
		return "member/loginForm";
	}
	
	
	@RequestMapping("/login.do")
	public String login(HttpSession session, Member member, RedirectAttributes rttr) {
		// 문제,
		// mapper에 login이라는 매소드 이름으로 로그인 기능을 수행하시오
		// 로그인 성공 시 => index.jsp 이동 후 로그인에 성공했습니다(modal)
		// 로그인 실패 시 => login.jsp 이동 후 로그인에 실패했습니다(modal)
		System.out.println("로그인 기능 수행");
		System.out.println(member.toString());
		
		
		Member m = mapper.login(member);
		if (m != null) {
			System.out.println("로그인 성공");
			rttr.addFlashAttribute("msgType", "로그인성공");
			rttr.addFlashAttribute("msg", "로그인에 성공했습니다");
			session.setAttribute("member", m);
			System.out.println(session.getAttribute("member"));
			return "redirect:/";
		} else {
			System.out.println("로그인 실패");
			rttr.addFlashAttribute("msgType", "로그인실패");
			rttr.addFlashAttribute("msg", "로그인에 실패했습니다");
			return "redirect:/loginForm.do";
		}
		
	}
	
	
	@RequestMapping("/updateForm.do")
	public String updateForm() {
		return "member/updateForm";
	}
	
	
	
	@RequestMapping("/update.do")
	public String update(Member member, RedirectAttributes rttr) {
		System.out.println(member.toString());
		
		member.setMemProfile("");
		
		if (
				member.getMemId() == null || member.getMemId().equals("") || 
				member.getMemPassword() == null || member.getMemPassword().equals("") ||
				member.getMemName() == null || member.getMemName().equals("") ||
				member.getMemAge() == 0 ||
				member.getMemEmail() == null || member.getMemEmail().equals("") 
		) {
			rttr.addFlashAttribute("msgType", "공란");
			rttr.addFlashAttribute("msg", "모든 내용을 채워주세요.");
			
			return "redirect:/updateForm.do";
		} else {
			int cnt = mapper.update(member);
			
			if (cnt != 0) {
				rttr.addFlashAttribute("msgType", "회원정보수정성공");
				rttr.addFlashAttribute("msg", "회원정보 수정에 성공했습니다.");
				
				return "redirect:/";
			} else {
				rttr.addFlashAttribute("msgType", "정보수정실패");
				rttr.addFlashAttribute("msg", "회원정보 수정에 실패했습니다.");
				
				return "redirect:/updateForm.do";
			}
			
			
		}
	}
	
	
	
}
