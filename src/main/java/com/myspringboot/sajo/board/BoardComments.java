package com.myspringboot.sajo.board;

import java.time.LocalDateTime;

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

public class BoardComments {
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seqBoardComment")
	@SequenceGenerator(name="seqBoardComment", sequenceName="seq_board_comment_idx", allocationSize=1)
	private Integer boardCommentIdx;
	
	@ManyToOne
	@JoinColumn(name="board_idx")
	Board boardIdx;
	
	@Column(columnDefinition="VARCHAR2(2000 BYTE)")
	private String comments;
	
	private LocalDateTime commentDate;	
	
}
