package kr.spring.entity;

import java.util.Date;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
import lombok.ToString;

@ToString
@Entity //Board VO가 Database Table로 만들 떄 설정
@Data
public class Board {  //vo가 orm을 통해서 table을 만들어줌.
	@Id //pk를 의미함. //1씩 증가하면서 넣기 -> auto_incremente와 같음
	@GeneratedValue(strategy = GenerationType.IDENTITY) //를 하면 자동으로 1씩 증가하게됨.
	private Long idx; //게시글 고유번호(호환을 long형으로 해준다.)
	
	private String title;
	
	@Column(length=2000) //길이 지정 : 길이지정 따로 안할 때 길이 255임.
	private String content;
	
	@Column(updatable = false) //수정할 때 작성자는 안 바꿔 주겠다.
	private String writer;
	
	@Column(insertable = false, updatable = false, columnDefinition = "datetime default now()")
	private Date indate;
	
	@Column(insertable = false, updatable = false, columnDefinition =  "int default 0")
	private Long count;
	
	
}
