package com.myspringboot.sajo.order;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrdersService {
	
	@Autowired
	OrdersRepository ordersRepo;
	
	/*
	 * getOrderList : memberNo에 해당하는 주문내역 가져오기 
	 * input: memberNo
	 * output: orderHistoryList
	 */
	public List<OrderHistoryDto> getOrderList(Integer memberNo){
		List<Object[]> newOrderList = ordersRepo.findOrderHistory(memberNo); // Repo에서 만든 Object배열을 새로운 Object배열에 담 
		List<OrderHistoryDto> orderHistoryList = new ArrayList<>(); // OrderHistoryDto 의 새로운 객체 생성 
		
		
		for(Object[] ob : newOrderList) {
			OrderHistoryDto dto = new OrderHistoryDto();
			
			dto.setOrderDate(String.valueOf(ob[0])); // os.order_date
			dto.setOrderNo(String.valueOf(ob[1])); // os.order_no
			dto.setOrderStatus(String.valueOf(ob[2])); // os.order_status
			dto.setQty(Integer.parseInt(String.valueOf(ob[3]))); // od.qty
			dto.setItemPrice(Integer.parseInt(String.valueOf(ob[4]))); // it.item_price
			dto.setItemImg(String.valueOf(ob[5])); // it.item_img
			dto.setItemName(String.valueOf(ob[6])); // it.item_name
			
			orderHistoryList.add(dto);
		}
		return orderHistoryList;
	}
}
