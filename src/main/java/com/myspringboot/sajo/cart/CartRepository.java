package com.myspringboot.sajo.cart;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CartRepository extends JpaRepository<Cart, Integer> {
	// 특정 회원의 장바구니 목록 조회
	@Query("SELECT c FROM Cart c JOIN FETCH c.cartItemIdx WHERE c.cartMemberNo.memberNo = :memberNo ORDER BY c.cartIdx DESC")
    List<Cart> findAllByMemberNo(@Param("memberNo") Integer memberNo);
	
	// 회원별 장바구니 상품 유무 확인
	@Query(
		value = "SELECT count(*)"
				+ " FROM cart"
				+ " WHERE member_no=:memberNo AND item_idx=:itemIdx",
		nativeQuery = true
	)
	int checkExists(@Param("memberNo") Integer memberNo, @Param("itemIdx") Integer itemIdx);
}
