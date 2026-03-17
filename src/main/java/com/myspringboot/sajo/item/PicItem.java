package com.myspringboot.sajo.item;

import com.myspringboot.sajo.member.Member;

import jakarta.persistence.Column;
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
public class PicItem {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seqPic")
	@SequenceGenerator(name="seqPic", sequenceName="seq_pic_idx", allocationSize=1)
	private Integer pic_idx;
	
	@ManyToOne
	@JoinColumn(name="member_no")
	private Member memberNo;
	
	@Column(columnDefinition="VARCHAR2(3000 BYTE)")
	private String picItemUrl;
}
