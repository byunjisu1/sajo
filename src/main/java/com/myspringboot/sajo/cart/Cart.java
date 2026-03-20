package com.myspringboot.sajo.cart;

import com.myspringboot.sajo.item.Item;
import com.myspringboot.sajo.member.Member;

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
public class Cart {
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seqCart")
	@SequenceGenerator(name="seqCart", sequenceName="seq_cart_idx", allocationSize=1)
	private Integer cartIdx;
	
	@ManyToOne
	@JoinColumn(name="member_no")
	private Member cartMemberNo;
	
	@ManyToOne
	@JoinColumn(name="item_idx")
	private Item cartItemIdx;
}
