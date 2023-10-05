package kr.spring.entity;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Board {
	
	private int idx;
	private String memID;
	private String title;
	private String content;
	private String writer;
	private Date indate;
	private int count;
	
	private int boardGroup;
	private int boardSequence;
	private int boardLevel;
	private int boardAvailable;
	
}




