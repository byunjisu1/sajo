package com.myspringboot.sajo.board;

import java.util.List;
import java.util.stream.Collectors;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BoardListDto {
	private String title;
	private String nickname;
	private String boardDate;
	private String content;
	private int boardIdx;
	private List<BoardFile> fileList;
	private int memberNo;
	
	public BoardListDto(Board board) {
		this.title = board.getTitle();
		this.nickname = board.getWriter().getNickname();
		this.boardDate = board.getBoardDate().toString();
		boardDate = boardDate.substring(0, boardDate.indexOf("T")+6).replace("T", " ");
		this.content = board.getContent();
		this.boardIdx = board.getBoardIdx();
		this.fileList = board.getFileList();
		this.memberNo = board.getWriter().getMemberNo();
	}
}
