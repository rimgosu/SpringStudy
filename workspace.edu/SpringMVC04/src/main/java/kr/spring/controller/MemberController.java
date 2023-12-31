package kr.spring.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

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
	
	
	@RequestMapping("/imageForm.do")
	public String imageForm() {
		return "member/imageForm";
	}
	
	
	
	
	@RequestMapping("/imageUpdate.do")
	public String imageUpdate(HttpServletRequest request, HttpSession session, RedirectAttributes rttr) {
		System.out.println("파일 업로드!");
		
		// 파일 업로드를 할 수 있게 도와주는 객체 (cos.jar)
		// 파일 업로드를 할 수 있게 도와주는 MultipartRequest 객체를 생성하기 위해서는
		// 5개의 정보가 필요하다
		// 요청 데이터, 저장경로, 최대 크기, 인코딩, 파일명 중복제거
		MultipartRequest multi = null;
		
		String savePath = request.getRealPath("resources/upload");
		int fileMaxSize = 10 * 1024 * 1024;
		
		String memId = ((Member) session.getAttribute("member")).getMemId();
		// getMember
		String oldImg = mapper.getMember(memId).getMemProfile();
		System.out.println(oldImg);
		// 파일 삭제
		File oldFile = new File(savePath + "/" + oldImg);
		if(oldFile.exists()) {
			oldFile.delete();
		}
		
		
		try {
			multi = new MultipartRequest(request, savePath, fileMaxSize, "utf-8", new DefaultFileRenamePolicy());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// 내가 업로드한 파일 가져오기
		File file = multi.getFile("memProfile");
		
		if (file != null) { // 업로드가 된 상태
			String ext = file.getName().substring(file.getName().lastIndexOf(".") + 1);
			ext = ext.toUpperCase();
			
			if (!(ext.equals("PNG") || ext.equals("GIF") || ext.equals("JPG") || ext.equals("JPEG"))) {
				if(file.exists()) {
					file.delete();
					rttr.addFlashAttribute("msgType", "실패메세지");
					rttr.addFlashAttribute("msg", "이미지 파일만 가능합니다.(png, jpg, gif)");
					
					return "redirect:/imageForm.do";
				}
			}
			
		}
		
		
		String newProfile = multi.getFilesystemName("memProfile");
		
		System.out.println(memId +"/" + newProfile);
		
		Member mvo = new Member();
		mvo = (Member) session.getAttribute("member");
		mvo.setMemId(memId);
		mvo.setMemProfile(newProfile);
		
		mapper.profileUpdate(mvo);
			
		rttr.addFlashAttribute("msgType", "성공메세지");
		rttr.addFlashAttribute("msg", "이미지 변경을 성공했습니다.");
		session.setAttribute("member", mvo);
		
		return "redirect:/";
	}
	
	
	
	
}
