package kr.spring.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/* lombok => 생성자 만들어주는 api
 @Data => getter/setter
 @AllArgsConstructor => 전체 생성자
 @NoArgsConstructor => 빈 생성자
 @ToString => toString */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Board {

	private int idx;
	private String memId;
	private String title;
	private String content;
	private String writer;
	private String indate;
	private int count;
	
}
