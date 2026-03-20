package com.myspringboot.sajo.likes;

import java.time.LocalDateTime;

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
public class Likes {
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seqLike")
	@SequenceGenerator(name="seqLike", sequenceName="seq_like_idx", allocationSize=1)
	private Integer likeIdx;
	
	@ManyToOne
	@JoinColumn(name="member_no")
	private Member likeMemberNo;
	
	@ManyToOne
	@JoinColumn(name="item_idx")
	private Item likeItemIdx;
	
	private LocalDateTime like_date;
	
	private int likePrice;
}
