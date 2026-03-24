package com.myspringboot.sajo.likes;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikesRepository extends JpaRepository<Likes, Integer>{
	List<Likes> findByMemberNo_MemberNo(Integer memberNo, Sort sort);	// Likes 엔티티의 memberNo 필드(객체) 내부의 memberNo를 찾음
}
