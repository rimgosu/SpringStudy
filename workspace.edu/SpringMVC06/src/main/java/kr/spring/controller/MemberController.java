package kr.spring.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import kr.spring.entity.Auth;
import kr.spring.entity.Member;
import kr.spring.mapper.MemberMapper;

@Controller
public class MemberController {

	@Autowired
	private MemberMapper mapper;

	@Autowired // 내가 만들어 놓은 비밀번호 암호화 객체를 주입받아 사용하겠다
	private PasswordEncoder pwEncoder;
	
	@GetMapping("/access-denied")
	public String showAccessDenied() {
		return "access-denied";
	}

	@RequestMapping("/joinForm.do")
	public String joinForm() {
		return "member/joinForm";
	}

	@RequestMapping("/registerCheck.do")
	public @ResponseBody int registerCheck(@RequestParam("memID") String memID) {

		Member m = mapper.registerCheck(memID);
		// m == null -> 아이디 사용가능
		// m != null -> 아이디 사용 불가능
		if (m != null || memID.equals("")) {
			return 0;
		} else {
			return 1;
		}

	}

	@RequestMapping("/join.do")
	public String join(Member m, RedirectAttributes rttr, HttpSession session) {
		System.out.println("회원가입 기능요청");

		// 유효성 검사
		if (m.getMemID() == null || m.getMemID().equals("") || m.getMemPassword() == null
				|| m.getMemPassword().equals("") || m.getMemName() == null || m.getMemName().equals("")
				|| m.getMemAge() == 0 || m.getMemEmail() == null || m.getMemEmail().equals("")
				|| m.getAuthList().size() == 0) {
			// 회원가입을 할 수 없다 하나라도 누락되어 있기 때문에

			// 실패시 joinForm.do로 msgType과 msg 내용을 보내야함
			// msgType : 실패메세지, msg : 모든 내용을 입력하세요
			// RedirectAttributes - 리다이렉트 방식으로 이동할때 보낼 데이터를 저장하는 객체

			rttr.addFlashAttribute("msgType", "실패메세지");
			rttr.addFlashAttribute("msg", "모든 내용을 입력하세요.");

			return "redirect:/joinForm.do";

		} else {
			// 회원가입을 시도할 수 있는 부분
			m.setMemProfile("");
			// 추가 : 비밀번호를 암호화하기
			String encyPw = pwEncoder.encode(m.getMemPassword());
			m.setMemPassword(encyPw);

			int cnt = mapper.join(m);

			// 추가 : 권한테이블에 회원의 권한을 저장하기
			List<Auth> list = m.getAuthList();
			for (Auth auth : list) {
				if (auth.getAuth() != null) {
					// 권한 값이 있을때만 권한테이블에 값 넣기
					Auth saveVO = new Auth();
					saveVO.setMemID(m.getMemID()); // 회원아이디 넣기
					saveVO.setAuth(auth.getAuth()); // 권한 넣기
					// 권한 저장
					mapper.authInsert(saveVO);
				}
			}

			if (cnt == 1) {
				System.out.println("회원가입 성공!");
				rttr.addFlashAttribute("msgType", "성공메세지");
				rttr.addFlashAttribute("msg", "회원가입에 성공했습니다.");
				// 회원가입 성공 시 로그인 처리까지 시키기
				// 회원가입 성공 시 회원정보 + 권한정보까지 가져오기
				Member mvo = mapper.getMember(m.getMemID());
				session.setAttribute("mvo", mvo);
				return "redirect:/";
			} else {
				System.out.println("회원가입 실패...");
				rttr.addFlashAttribute("msgType", "실패메세지");
				rttr.addFlashAttribute("msg", "회원가입에 실패했습니다.");
				return "redirect:/joinForm.do";
			}

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

	/*
	 * @RequestMapping("/login.do") public String login(Member m, HttpSession
	 * session, RedirectAttributes rttr) {
	 * 
	 * Member mvo = mapper.login(m); // 추가 비밀번호 일치여부 체크 if (mvo != null &&
	 * pwEncoder.matches(m.getMemPassword(), mvo.getMemPassword())) {
	 * 
	 * rttr.addFlashAttribute("msgType", "성공메세지"); rttr.addFlashAttribute("msg",
	 * "로그인에 성공했습니다."); session.setAttribute("mvo", mvo); return "redirect:/";
	 * 
	 * } else { rttr.addFlashAttribute("msgType", "실패메세지");
	 * rttr.addFlashAttribute("msg", "로그인에 실패했습니다."); return
	 * "redirect:/loginForm.do"; }
	 * 
	 * }
	 */

	@RequestMapping("/updateForm.do")
	public String updateForm() {
		return "member/updateForm";
	}

	@RequestMapping("/update.do")
	public String update(Member m, RedirectAttributes rttr, HttpSession session) {

		if (m.getMemID() == null || m.getMemID().equals("") || m.getMemPassword() == null
				|| m.getMemPassword().equals("") || m.getMemName() == null || m.getMemName().equals("")
				|| m.getMemAge() == 0 || m.getMemEmail() == null || m.getMemEmail().equals("")
				|| m.getAuthList().size() == 0) {

			rttr.addFlashAttribute("msgType", "실패메세지");
			rttr.addFlashAttribute("msg", "모든 내용을 입력하세요.");

			return "redirect:/updateForm.do";

		} else {

			Member mvo = (Member) session.getAttribute("mvo");
			m.setMemProfile(mvo.getMemProfile());

			// 비밀번호 암호화
			String encyPw = pwEncoder.encode(m.getMemPassword());
			m.setMemPassword(encyPw);

			// 권한 삭제
			mapper.authDelete(m.getMemID());
			// 권한 입력
			// 추가 : 권한테이블에 회원의 권한을 저장하기
			List<Auth> list = m.getAuthList();
			for (Auth auth : list) {
				if (auth.getAuth() != null) {
					// 권한 값이 있을때만 권한테이블에 값 넣기
					Auth saveVO = new Auth();
					saveVO.setMemID(m.getMemID()); // 회원아이디 넣기
					saveVO.setAuth(auth.getAuth()); // 권한 넣기
					// 권한 저장
					mapper.authInsert(saveVO);
				}
			}

			int cnt = mapper.update(m);

			if (cnt == 1) {
				rttr.addFlashAttribute("msgType", "성공메세지");
				rttr.addFlashAttribute("msg", "회원정보수정에 성공했습니다.");
				
				Member info = mapper.getMember(m.getMemID());
				
				session.setAttribute("mvo", info);
				return "redirect:/";
			} else {
				rttr.addFlashAttribute("msgType", "실패메세지");
				rttr.addFlashAttribute("msg", "회원정보수정에 실패했습니다.");

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

		// 파일업로드를 할 수 있게 도와주는 객체 (cos.jar)
		// 파일업로드를 할 수 있게 도와주는 MultipartRequest 객체를 생성하기 위해서는
		// 5개의 정보가 필요하다
		// 요청데이터, 저장경로, 최대크기, 인코딩, 파일명 중복제거
		MultipartRequest multi = null;
		// 저장경로
		String savePath = request.getRealPath("resources/upload");
		// 이미지 최대크기
		int fileMaxSize = 10 * 1024 * 1024 * 10;

		// 기존 해당 프로필 이미지 삭제
		// - 로그인 한 사람의 프로필 값을 가져와야함
		String memID = ((Member) session.getAttribute("mvo")).getMemID();

		// getMember 메소드는 memID와 일치하는 회원의 정보 (Member)를 가져온다
		String oldImg = mapper.getMember(memID).getMemProfile();

		// 기존의 프로필 사진 삭제
		File oldFile = new File(savePath + "/" + oldImg);
		if (oldFile.exists()) {
			oldFile.delete();
		}

		try {
			multi = new MultipartRequest(request, savePath, fileMaxSize, "UTF-8", new DefaultFileRenamePolicy());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// 내가 업로드한 파일 가져오기
		File file = multi.getFile("memProfile");

		if (file != null) { // 업로드가 된 상태
			// System.out.println(file.getName());
			String ext = file.getName().substring(file.getName().lastIndexOf(".") + 1);
			ext = ext.toUpperCase();

			if (!(ext.equals("PNG") || ext.equals("GIF") || ext.equals("JPG"))) {

				if (file.exists()) {
					file.delete();
					rttr.addFlashAttribute("msgType", "실패메세지");
					rttr.addFlashAttribute("msg", "이미지 파일만 가능합니다.(PNG, JPG, GIF)");
					return "redirect:/imageForm.do";
				}
			}
		}
		// 업로드한 파일의 이름을 가져오는 코드
		String newProfile = multi.getFilesystemName("memProfile");

		Member mvo = new Member();
		mvo.setMemID(memID);
		mvo.setMemProfile(newProfile);

		mapper.profileUpdate(mvo);

		// 사진 업데이트 후 수정된 회원정보를 다시 가져와서 세션에 담기
		Member m = mapper.getMember(memID);
		session.setAttribute("mvo", m);

		rttr.addFlashAttribute("msgType", "성공메세지");
		rttr.addFlashAttribute("msg", "이미지 변경이 성공했습니다.");
		return "redirect:/";

	}

}
