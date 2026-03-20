package com.myspringboot.sajo.search;

import java.time.LocalDateTime;

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
public class Search {
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seqSearch")
	@SequenceGenerator(name="seqSearch", sequenceName="seq_search_idx", allocationSize=1)
	private Integer searchIdx;
	
	@ManyToOne
	@JoinColumn(name="member_no")
	private Member searchMemberNo;
	
	@Column(columnDefinition="VARCHAR2(1000 BYTE)")
	private String searchKeyword;
	
	private LocalDateTime searchDate;
}
