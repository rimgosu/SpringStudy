package kr.spring.mapper;

import org.apache.ibatis.annotations.Mapper;

import kr.spring.entity.Auth;
import kr.spring.entity.Member;

@Mapper
public interface MemberMapper {

	public Member registerCheck(String memID);

	public void join(Member member);

	public Member login(Member member);

	public int update(Member member);

	public void profileUpdate(Member mvo);

	public Member getMember(String memId);

	public void authInsert(Auth saveVO);

	public void authDelete(String memId);

}
