package kr.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kr.spring.entity.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, String>{

}
