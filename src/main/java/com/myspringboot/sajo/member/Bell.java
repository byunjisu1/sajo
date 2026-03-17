package com.myspringboot.sajo.member;

import java.time.LocalDateTime;

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
public class Bell {
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seqBell")
	@SequenceGenerator(name="seqBell", sequenceName="seq_bell_idx", allocationSize=1)
	private Integer bellIdx;
	
	@ManyToOne
	@JoinColumn(name="member_no")
	Member bellMemberNo;
	
	@ManyToOne
	@JoinColumn(name="bell_type")
	Member bellType;
	
	@ManyToOne
	@JoinColumn(name="like_idx")
	Member likeIdx;
	
	@ManyToOne
	@JoinColumn(name="board_idx")
	Member bellBoardIdx;
	
	private LocalDateTime bellTime;
}
