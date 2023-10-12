package kr.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.spring.entity.Board;
import kr.spring.entity.Criteria;
import kr.spring.entity.PageMaker;
import kr.spring.service.BoardService;

@Controller
@RequestMapping("/board/*")
public class BoardController {
	
	@Autowired
	private BoardService service;
	
	@GetMapping("/reply")
	public String reply(@RequestParam("idx") int idx, Model model) {
		Board vo = service.get(idx);
		model.addAttribute("vo", vo);
		return "board/reply";
	}
	@PostMapping("/reply")
	public String reply(Board vo) {
		service.reply(vo);
		return "redirect:/board/list";
	}
	
	
	@GetMapping("/remove")
	public String remove(@RequestParam("idx") int idx) {
		service.remove(idx);
		return"redirect:/board/list";
	}

	
	@GetMapping("/modify")
	//location.href는 get방식임.
	public String modify(@RequestParam("idx") int idx, Model model) {
		//model에 idx를 담아서 board/modify로 이동함.
		Board vo = service.get(idx);
		model.addAttribute("vo", vo);
		return "board/modify";
	}
	
	@PostMapping("/modify")
	public String modify(Board vo) {
		service.modify(vo);
		return "redirect:/board/list";
	}
	
	@GetMapping("/get")
	// 게시글 상세보기
	// 하나만 받앙올 떄는 requestparam사용. idx를 받아와서 idx안에 넣겠다.
	public String get(@RequestParam("idx") int idx, Model model, @ModelAttribute("cri") Criteria cri) {
		// 받아오는 자료는 board자료형태이고 이름은 vo이다.
		Board vo =service.get(idx);
		model.addAttribute("vo", vo);
		return "board/get";
	}
	
	
	//단순히 페이지 이동만 하니까 getmapping임.
	@GetMapping("register")
	public String register() {
		return "board/register";
				
	}
	
	
	@PostMapping("/register")
	public String register(Board vo, RedirectAttributes rttr) {
		// System.out.println(vo.toString());
		service.register(vo);
		// System.out.println(vo.toString()); idx,boardGroup이 채워져서 print된다.
		                       //result라는 이름으로 
		rttr.addFlashAttribute("result", vo.getIdx());
		return "redirect:/board/list";
	}
	
	
	//(10/05)BoardService는 클래스가 아니라 인터페이스임. BoardServiceImpl가 BoardService 업캐스팅 된다.
	@GetMapping("/list")
	public String boardList(Model model, Criteria cri) {
		// 이제는 페이지 정보를 알고있는 criteria 객체를 service에게 전달
		// 페이징 처리에 필요한 PageMaker 객체도 생성
		List<Board> list = service.getList(cri);
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri); // pageMaker가 페이징 기법을 하기 위한 cri 객체 전달
		pageMaker.setTotalCount(service.totalCount()); // 페이징 기법을 하려면 전체 게시글 개수를 알려줘야함
		
		System.out.println("****pageMaker : ****" + pageMaker.toString());
		
		model.addAttribute("list",list);
		model.addAttribute("pageMaker", pageMaker);
		
		return "board/list";
	}
	
	
}
