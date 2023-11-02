package kr.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kr.spring.entity.Board;

@Repository //메모리로 올리기 위한 어노테이션(생략해도 가능함.)  //<테이블명, pk의 데이터 타입>
public interface BoardRepository extends JpaRepository<Board, Long> {
	
}
