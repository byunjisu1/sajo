package com.myspringboot.sajo.payment;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.myspringboot.sajo.order.OrderDetailItemDto;

import lombok.Data;

@Data
public class PaymentRequestDto {
    // ORDERS 및 PAYMENT 공통 정보
    private Integer memberNo;
    private int totalAmount;
    @JsonProperty("merchantUid")
    private String orderNo; 
    private String impUid; 

    // ORDER_DETAIL용 리스트
    private List<OrderDetailItemDto> items; 
}
