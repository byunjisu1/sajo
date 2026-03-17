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
public class PicItem {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seqPic")
	@SequenceGenerator(name="seqPic", sequenceName="seq_pic_idx", allocationSize=1)
	private Integer pic_idx;
	
	private int memberNo;
	
	@Column(columnDefinition="VARCHAR2(500 BYTE)")
	private String picItemUrl;
}
