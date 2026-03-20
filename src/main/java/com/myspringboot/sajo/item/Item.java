package com.myspringboot.sajo.item;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Item {
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seqItem")
	@SequenceGenerator(name="seqItem", sequenceName="seq_item_idx", allocationSize=1)
	private Integer itemIdx;
	
	@Column(columnDefinition="VARCHAR2(3000 BYTE)")
	private String itemName;
	
	@Column(columnDefinition="VARCHAR2(3000 BYTE)")
	private String itemDetail;
	
	private int itemPrice;
	
	@Column(columnDefinition="VARCHAR2(3000 BYTE)")
	private String itemImg;
}
