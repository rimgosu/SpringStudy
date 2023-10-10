package kr.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.spring.entity.Book;
import kr.spring.mapper.BookMapper;

@Controller
public class BookController {

	@Autowired
	private BookMapper mapper;
	
	@RequestMapping("/") // 요청 url로 들어왔을 때 아래 기능을 수행하겠다 
	public String home() {
		System.out.println("****홈 기능 수행****"); 
		return "main";
	}
	
	@RequestMapping("/bookList.do") 
	public String bookList(Model model) {
		System.out.println("게시판 목록 보기 기능 수행");
		
		// 게시글 정보 가져오기
		// 한 개의 게시글은 - 번호, 제목, 내용, 작성자, 작성일, 조회수
		// JDBC => MYBATIS
		List<Book> list = mapper.getBookLists();
		
		// list를 jsp로 전달해줘야할 때 model에 저장해서 씀
		// model은 request 객체로 들어감.
		// 객체바인딩 => 동적바인딩
		model.addAttribute("list", list);
		
		System.out.println(list.toString());
		// 이거 forward 방식임
		return "main";
	}
	
	
	@RequestMapping("bookForm.do")
	public String bookForm() {
		System.out.println("*****책 페이지 이동*****");
		return "bookForm";
	}
	
	
	
	
	@RequestMapping("/bookInsert.do")
	public String bookInsert(Book book) {
		System.out.println("*****책 등록 기능 수행*******");
		
		mapper.bookInsert(book);
		return "redirect:/bookList.do";
	}
	
	
	@RequestMapping("/bookDelete.do")
	public String bookDelete(@RequestParam("num") int num) {
		System.out.println("*******책정보 삭제********");
		mapper.bookDelete(num);
		return "redirect:/bookList.do";
	}
	
	
	@RequestMapping("/bookUpdateForm.do/{num}")
	public String bookUpdateForm(@PathVariable("num") int num, Model model) {
		System.out.println("**********책정보수정페이지이동*************");
		Book vo = mapper.bookContent(num);
		System.out.println(vo.toString());
		model.addAttribute("vo", vo);
		return "bookUpdateForm";
	}
	
	@RequestMapping("/bookUpdate.do")
	public String bookUpdate(Book book) {
		System.out.println("******책업데이트기능실행*********");
		mapper.bookUpdate(book);
		return "redirect:/bookList.do";
	}
	
	
	
}
