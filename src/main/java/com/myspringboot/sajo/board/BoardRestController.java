package com.myspringboot.sajo.board;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class BoardRestController {
	@Autowired
	private BoardService bSvc;
	
	// 게시판 페이지별 목록보기
//	@GetMapping("/board/list/{pageNum}")
//	public List<BoardListDto> boardListByPage(@PathVariable("pageNum") Integer pageNum) {
//		if(pageNum==null) pageNum=1;
//		List<BoardListDto> boardList = bSvc.getBoardListAll(pageNum);
//		
//		return boardList;
//	}
	@GetMapping("/board/list/{pageNum}")
	public Map<String, Object> boardListByPage(@PathVariable("pageNum") Integer pageNum) {
		if(pageNum==null) pageNum=1;
		List<BoardListDto> boardList = bSvc.getBoardListAll(pageNum);
		int lastPageNumber = bSvc.getLastPageNumber();
		
		Map<String, Object> mapRet = new HashMap<>();
		mapRet.put("list", boardList);
		mapRet.put("lastPageNumber", lastPageNumber);
		return mapRet;
	}

	
	// 게시판 상세보기
	@GetMapping("/board/{boardIdx}")
	public BoardListDto boardDetail(@PathVariable("boardIdx") Integer boardIdx) {
		return bSvc.getBoardByIdx(boardIdx);
	}
	
	// 문의게시판 작성
	@PostMapping("/board/write")
	public void boardWrite(
			@RequestParam("title") String title, 
			@RequestParam("content") String content, 
			@RequestParam("writer") int writer, 
			@RequestParam(value="files", required=false) List<MultipartFile> files) {
		bSvc.write(title, content, writer, files);
	}
	
	// 문의게시판 수정
	@PutMapping("/board/edit")
	public void boardEdit(
			@RequestParam("title") String title, 
			@RequestParam("content") String content, 
			@RequestParam(value="files", required=false) List<MultipartFile> files) {
	}
}
