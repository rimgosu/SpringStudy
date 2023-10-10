package kr.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.spring.entity.Book;
import kr.spring.mapper.BookMapper;

@RequestMapping("/book")
@RestController
public class BookRestController {
	
	@Autowired
	private BookMapper mapper;
	
	@GetMapping("/bookListAjax")
    public List<Book> bookListAjax() {
        System.out.println("게시판 목록 보기 기능 수행 (비동기)");

        List<Book> list = mapper.getBookLists();
        return list;
    }
}
