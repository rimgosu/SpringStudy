package kr.spring.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Entity // Board VO가 Database Table로 만들 때 설정
@Data
public class Board { // VO <---- ORM ----> TABLE

	@Id // PK의 의미
	private Long idx;
	
	
}
