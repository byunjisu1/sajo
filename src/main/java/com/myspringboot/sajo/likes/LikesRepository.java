package com.myspringboot.sajo.likes;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface LikesRepository extends JpaRepository<Likes, Integer>{
	List<Likes> findByMemberNo_MemberNo(Integer memberNo, Sort sort);	// Likes 엔티티의 memberNo 필드(객체) 내부의 memberNo를 찾음
	
	@Query(
		value = "SELECT count(*)"
				+ " FROM likes"
				+ " WHERE member_no=:memberNo AND item_idx=:itemIdx",
		nativeQuery = true
	)
	int checkExists(@Param("memberNo") Integer memberNo, @Param("itemIdx") Integer itemIdx);
}
