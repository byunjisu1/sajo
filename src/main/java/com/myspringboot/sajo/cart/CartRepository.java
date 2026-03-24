package com.myspringboot.sajo.cart;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CartRepository extends JpaRepository<Cart, Integer> {
	// 특정 회원의 장바구니 목록을 가져오는 메서드
    // 성능 최적화를 위해 fetch join을 사용하여 Item 정보까지 한 번에 가져옵니다.
	@Query("SELECT c FROM Cart c JOIN FETCH c.cartItemIdx WHERE c.cartMemberNo.memberNo = :memberNo ORDER BY c.cartIdx DESC")
    List<Cart> findAllByMemberNo(@Param("memberNo") Integer memberNo);
}
