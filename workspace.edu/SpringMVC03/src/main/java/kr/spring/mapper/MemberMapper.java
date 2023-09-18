package kr.spring.mapper;

import org.apache.ibatis.annotations.Mapper;

import kr.spring.entity.Member;

@Mapper
public interface MemberMapper {

	public Member registerCheck(String memID);

	public void join(Member member);

	public String login(Member member);

}
