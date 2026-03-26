package com.myspringboot.sajo.cart;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myspringboot.sajo.item.Item;

@Service
public class CartService {
	@Autowired
	private CartRepository cartRepo;
	
	// 회원에 따른 장바구니 리스트 조회
	public List<CartDto> getCartList(Integer memberNo) {
        List<Cart> carts = cartRepo.findAllByMemberNo(memberNo);
        
        // Entity 리스트를 DTO 리스트로 변환해서 반환
        return carts.stream()
                    .map(CartDto::new)
                    .collect(Collectors.toList());
    }
	
	// 장바구니에서 상품 삭제 (리턴값: 삭제했으면 true, 못했으면 false)
	public boolean deleteCartItem(Integer memberNo, Integer itemIdx) {
		List<Cart> listCartAll = cartRepo.findAll();
		for(Cart cart : listCartAll) {
			if(cart.getCartMemberNo().getMemberNo()==memberNo && cart.getCartItemIdx().getItemIdx()==itemIdx) {
				cartRepo.delete(cart);
				return true;
			}
		}
		return false;
	}
}
