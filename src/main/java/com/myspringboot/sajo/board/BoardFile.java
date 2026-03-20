package com.myspringboot.sajo.board;

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
public class BoardFile {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seqBoardFile")
	@SequenceGenerator(name="seqBoardFile", sequenceName="seq_board_file_idx", allocationSize=1)
	private Integer boardFileIdx;
	
	@ManyToOne
	@JoinColumn(name="board_idx")
	Board boardIdx;
	
	@Column(columnDefinition="VARCHAR2(3000 BYTE)")
	private String fileUrl;
	
	
	
}
