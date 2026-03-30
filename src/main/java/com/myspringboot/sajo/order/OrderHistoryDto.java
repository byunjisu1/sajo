package com.myspringboot.sajo.order;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class OrderHistoryDto {
	private String orderDate;
	private String orderNo;
	private String orderStatus;
	private int qty;
	private int itemPrice;
	private String itemImg;
	private String itemName;
}
