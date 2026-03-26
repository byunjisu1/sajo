package com.myspringboot.sajo.board;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BoardRepository extends JpaRepository<Board, Integer>{
	// 문의게시판 페이지별 목록보기
	@Query(
		value = "SELECT b2.*"
				+ " FROM ("
				+ "    SELECT rownum rnum, b.*"
				+ "    FROM ("
				+ "        SELECT * "
				+ "        FROM board "
				+ "        ORDER BY board_idx DESC"
				+ "    ) b"
				+ " ) b2"
				+ " WHERE b2.rnum>=:startRow AND b2.rnum<=:endRow",
		nativeQuery = true
	)
	List<Board> getListFromStartToEnd(@Param("startRow") int startRow, @Param("endRow") int endRow);
}
