package com.myspringboot.sajo.order;

import com.myspringboot.sajo.item.Item;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class OrderDetail {
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seqOrderDetail")
	@SequenceGenerator(name="seqOrderDetail", sequenceName="seq_order_detail_idx", allocationSize=1)
	private Integer orderDetailIdx;
	
	@ManyToOne
	@JoinColumn(name="order_no")
	private Orders orderNo;
	
	@ManyToOne
	@JoinColumn(name="item_idx")
	private Item itemIdx;
	
	private int qty;
	
	private int orderPrice;
}
