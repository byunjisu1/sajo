package com.myspringboot.sajo.order;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderRestController {
	@Autowired
	OrdersService orderSvc;
	
	@GetMapping("/getOrderList/{memberNo}")
	public List<OrderHistoryDto> getOrderHistoryList(@PathVariable("memberNo") Integer memberNo){
		List<OrderHistoryDto> orderList = orderSvc.getOrderList(memberNo);
		return orderList;
	}
}
