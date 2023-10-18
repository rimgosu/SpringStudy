package kr.spring.repository;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kr.spring.entity.Board;

@Repository // @Mapper 랑 같음 (생략 가능)
public interface BoardRepository extends JpaRepository<Board, Long>{

	
	
}
