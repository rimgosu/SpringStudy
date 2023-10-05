package kr.spring.mapper;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.mysql.jdbc.PreparedStatement;

import kr.spring.entity.Auth;
import kr.spring.entity.Board;
import kr.spring.entity.Member;

@Mapper // MyBatis가 interface를 찾기위해 달아주는 부분
public interface MemberMapper {

	public Member registerCheck(String memID);

	public int join(Member m);

	public Member login(String username);

	public int update(Member m);

	public void profileUpdate(Member mvo);

	public Member getMember(String memID);

	public void authInsert(Auth saveVO);

	public void authDelete(String memID);

}












