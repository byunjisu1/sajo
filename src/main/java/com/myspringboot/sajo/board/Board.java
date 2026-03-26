package com.myspringboot.sajo.board;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.myspringboot.sajo.member.Member;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Board {
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seqBoard")
	@SequenceGenerator(name="seqBoard", sequenceName="seq_board_idx", allocationSize=1)
	private Integer boardIdx;
	
	@ManyToOne
	@JoinColumn(name="member_no")
	Member writer;
	
	@Column(columnDefinition="VARCHAR2(1000 BYTE)")
	private String title;
	
	@Column(columnDefinition="VARCHAR2(3000 BYTE)")
	private String content;
	
	private LocalDateTime boardDate;
	
	@OneToMany(mappedBy="board", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	private List<BoardFile> fileList = new ArrayList<>();
	
	// 연관관계 편의 메서드
	public void addFile(BoardFile file) {
		this.fileList.add(file);
		file.setBoard(this);
	}
}
