package com.myspringboot.sajo.cart;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myspringboot.sajo.item.Item;
import com.myspringboot.sajo.item.ItemRepository;
import com.myspringboot.sajo.member.Member;
import com.myspringboot.sajo.member.MemberRepository;

@Service
public class CartService {
	@Autowired
	private CartRepository cartRepo;
	@Autowired
	private MemberRepository memberRepo;
	@Autowired
	private ItemRepository itemRepo;
	
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
	
	//장바구니 넣기
	public boolean addCart(Integer memberIdx, Integer itemIdx) {
		int count = cartRepo.checkExists(memberIdx, itemIdx);
		if(count>0) {
			return false;
		} else {
			Optional<Member> om = memberRepo.findById(memberIdx);
			Optional<Item> oi = itemRepo.findById(itemIdx);
			
			Cart carts = new Cart();
			carts.setCartItemIdx(oi.get());
			carts.setCartMemberNo(om.get());
			
			cartRepo.save(carts);
			return true;
		}
	}
}
