package kr.spring.controller;

import java.lang.ProcessBuilder.Redirect;
import java.util.List;

import javax.jws.WebParam.Mode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.spring.entity.Board;
import kr.spring.service.BoardService;

@Controller
@RequestMapping("/board/*")
public class BoardController {
	
	@Autowired
	private BoardService service;
	// BoardService -> interface
	// BoardServiceImpl -> BoardService로 업캐스팅 된다
	
	@PostMapping("/reply")
	public String reply(Board vo) {
		service.reply(vo);
		return "redirect:/board/list";
	}
	
	@GetMapping("/reply")
	public String reply(@RequestParam("idx") int idx, Model model) {
		Board vo = service.get(idx);
		model.addAttribute("vo", vo);
		return "board/reply";
	}
	
	@GetMapping("/remove")
	public String remove(@RequestParam("idx") int idx) {
		System.out.println("**********삭제 기능 실행***********");
		service.remove(idx);
		return "redirect:/board/list";
	}
	
	@PostMapping("/modify")
	public String modify(Board vo) {
		service.modify(vo);
		return "redirect:/board/list";
	}
	
	
	@GetMapping("/modify")
	public String modify(@RequestParam("idx") int idx, Model model) {
		Board vo = service.get(idx);
		
		model.addAttribute("vo", vo);
		return "board/modify";
	}
	
	@GetMapping("/get")
	public String get(@RequestParam("idx") int idx, Model model) {
		Board vo = service.get(idx);
		model.addAttribute("vo", vo);
		return "board/get";
	}
	
	@GetMapping("register")
	public String register() {
		return "board/register";
	}
	
	@PostMapping("register")
	public String registerPost(Board vo, RedirectAttributes rttr) {
		System.out.println("**********post방식 게시글 등록 기능 실행***********");
		service.insertSelectKey(vo);
		
		rttr.addFlashAttribute("result", vo.getIdx());
		return "redirect:/board/list";
	}
	

	@GetMapping("/list")
	public String boardList(Model model) {
		List<Board> list = service.getList();
		model.addAttribute("list", list);
		return "board/list";
	}
	
}



