package com.myspringboot.sajo.cart;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartDto {
	private Integer cartIdx;
	private int itemIdx;
	private String itemName;
	private int itemPrice;
	private String itemImg;
	
	public CartDto(Cart cart) {
		this.cartIdx = cart.getCartIdx();
		this.itemIdx = cart.getCartItemIdx().getItemIdx();
		this.itemName = cart.getCartItemIdx().getItemName();
		this.itemPrice = cart.getCartItemIdx().getItemPrice();
		this.itemImg = cart.getCartItemIdx().getItemImg();
	}
}
