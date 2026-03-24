package com.myspringboot.sajo.cart;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartService {
	@Autowired
	private CartRepository cartRepo;
	
	public List<CartDto> getCartList(Integer memberNo) {
        List<Cart> carts = cartRepo.findAllByMemberNo(memberNo);
        
        // Entity 리스트를 DTO 리스트로 변환해서 반환
        return carts.stream()
                    .map(CartDto::new)
                    .collect(Collectors.toList());
    }
}
