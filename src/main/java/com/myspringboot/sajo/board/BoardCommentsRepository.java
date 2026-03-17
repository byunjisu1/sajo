package com.myspringboot.sajo.board;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardCommentsRepository extends JpaRepository<BoardComments, Integer>{
	
}