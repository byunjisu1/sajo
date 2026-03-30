package com.myspringboot.sajo.order;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailItemDto {
	private String orderNo;
	private int itemIdx;
	private int qty;
	private int orderPrice;
}
