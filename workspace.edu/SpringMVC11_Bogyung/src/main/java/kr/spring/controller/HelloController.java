package kr.spring.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController //비동기방식으로 작동하려면 이게 꼭 있어야함.
public class HelloController {
	@RequestMapping("/hello")  //hello라고 요청이 들어오면
	public String hello() {
		return "Hello! spring Boot!";
	}
}
