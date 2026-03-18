package com.myspringboot.sajo;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.myspringboot.sajo.board.Board;
import com.myspringboot.sajo.board.BoardComments;
import com.myspringboot.sajo.board.BoardCommentsRepository;
import com.myspringboot.sajo.board.BoardFile;
import com.myspringboot.sajo.board.BoardFileRepository;
import com.myspringboot.sajo.board.BoardRepository;
import com.myspringboot.sajo.member.Member;
import com.myspringboot.sajo.member.MemberRepository;

@SpringBootTest
public class BoardTests {
	@Autowired
	private BoardRepository boardRepo;
	@Autowired
	private MemberRepository memberRepo;
	@Autowired 
	private BoardFileRepository boardFileRepo;
	@Autowired 
	private BoardCommentsRepository boardCommentsRepo;
	
	/**
	 * testInsertBoard : Board 테이블 더미데이터 추가 
	 */
	@Test
	void testInsertBoard() {
		Member m = memberRepo.findById(2).get();
		Board b = new Board();
		b.setWriter(m);
		b.setTitle("이 상품 구매 가능한가요?");
		b.setContent("이 신발 구매하고 싶은데 가능한가요?");
		b.setBoardDate(LocalDateTime.now());
		boardRepo.save(b);
		
		m = memberRepo.findById(2).get();
		b = new Board();
		b.setWriter(m);
		b.setTitle("구매 취소하고 싶어요?");
		b.setContent("2026/03/03에 주문한 상품을 취소하고 싶어요");
		b.setBoardDate(LocalDateTime.now());
		boardRepo.save(b);
		
		m = memberRepo.findById(3).get();
		b = new Board();
		b.setWriter(m);
		b.setTitle("가격이 안떠요");
		b.setContent("해당 포토카드 가격이 안 나와있는데, 알 수 있을까요?");
		b.setBoardDate(LocalDateTime.now());
		boardRepo.save(b);
		
		m = memberRepo.findById(1).get();
		b = new Board();
		b.setWriter(m);
		b.setTitle("여러개 구매 희망");
		b.setContent("같은 상품을 여러 개 하고 싶어요");
		b.setBoardDate(LocalDateTime.now());
		boardRepo.save(b);
		
		m = memberRepo.findById(3).get();
		b = new Board();
		b.setWriter(m);
		b.setTitle("구매했던 내역 보고 싶어요");
		b.setContent("이번 달 주문 내역을 볼 수 있나요?");
		b.setBoardDate(LocalDateTime.now());
		boardRepo.save(b);
	}
	
	/**
	 * testInsertBoardFile : BoardFile 테이블에 더미데이터 추가 
	 */
	@Test
	void testInsertBoardFile() {
		Board b = boardRepo.findById(1).get();
		BoardFile bf = new BoardFile();
		bf.setBoardIdx(b);
		bf.setFileUrl("https://www.bing.com/images/search?view=detailV2&ccid=0mhpc8JK&id=0FEF134DAB9D9A0863FA2D735248143C1A027FDA&thid=OIP.0mhpc8JKUMZMWy1IsHZb7AHaHa&mediaurl=https%3a%2f%2fcdn.imweb.me%2fthumbnail%2f20230331%2fe845c29359ca7.jpg&exph=750&expw=750&q=%ec%8b%a0%eb%b0%9c&FORM=IRPRST&ck=379CC4CF19E2875B1A5AABA6ECA63546&selectedIndex=16&itb=0");
		boardFileRepo.save(bf);
		
		b = boardRepo.findById(3).get();
		bf = new BoardFile();
		bf.setBoardIdx(b);
		bf.setFileUrl("https://www.bing.com/images/search?view=detailV2&ccid=jevhfNdu&id=1D629A3C49343D1AD0DAC289D1D7D2B5BE5DE602&thid=OIP.jevhfNduh_dq0gE_lSIUsgHaHa&mediaurl=https%3a%2f%2fth.bing.com%2fth%2fid%2fR.8debe17cd76e87f76ad2013f952214b2%3frik%3dAuZdvrXS19GJwg%26riu%3dhttp%253a%252f%252fhallyusuperstore.com%252fcdn%252fshop%252ffiles%252f361362261_1_1759929755_w856.webp%253fv%253d1759933177%26ehk%3dL%252bF3V4417QmkC5RQdKaZjGRkabPK76uJ4p2uI9edGbM%253d%26risl%3d%26pid%3dImgRaw%26r%3d0&exph=856&expw=856&q=%ec%9b%90%ed%95%84+%ed%8f%ac%ed%86%a0%ec%b9%b4%eb%93%9c&FORM=IRPRST&ck=96B00873BAC68ECBD6CFD186D445208E&selectedIndex=4&itb=0");
		boardFileRepo.save(bf);
		
		b = boardRepo.findById(3).get();
		bf = new BoardFile();
		bf.setBoardIdx(b);
		bf.setFileUrl("https://www.bing.com/images/search?view=detailV2&ccid=Vn0aIyZX&id=4FC790F1B6F7218377436A0C9BB70D840ED613A1&thid=OIP.Vn0aIyZXCW-CjZrENRpt3QHaHa&mediaurl=https%3a%2f%2fth.bing.com%2fth%2fid%2fR.567d1a232657096f828d9ac4351a6ddd%3frik%3doRPWDoQNt5sMag%26riu%3dhttp%253a%252f%252fwww.funiki.nl%252fcdn%252fshop%252ffiles%252f351956379_1_1756329425_w640.webp%253fv%253d1757600405%26ehk%3dI%252bORPmkh1O3qFZF7DwWux7XW3VpPUm7tze0Ne0P02%252fw%253d%26risl%3d%26pid%3dImgRaw%26r%3d0&exph=640&expw=640&q=%ec%9b%90%ed%95%84+%ed%8f%ac%ed%86%a0%ec%b9%b4%eb%93%9c&FORM=IRPRST&ck=B2067F6EB33B93715F2F5BBACE797691&selectedIndex=9&itb=0");
		boardFileRepo.save(bf);
	}
	
	/**
	 * testInsertBoardComments : BoardComments 테이블에 더미데이터 추가 
	 */
	@Test
	void testInsertBoardComments() {
		Board b = boardRepo.findById(1).get();
		BoardComments bc = new BoardComments();
		bc.setBoardIdx(b);
		bc.setComments("해당 상품은 현재 구매가 불가능한 상태입니다.");
		bc.setCommentDate(LocalDateTime.now());
		boardCommentsRepo.save(bc);
		
		b = boardRepo.findById(2).get();
		bc = new BoardComments();
		bc.setBoardIdx(b);
		bc.setComments("해당 주문 내역은 취소 불가능한 제품입니다.");
		bc.setCommentDate(LocalDateTime.now());
		boardCommentsRepo.save(bc);
		
		b = boardRepo.findById(3).get();
		bc = new BoardComments();
		bc.setBoardIdx(b);
		bc.setComments("해당 포토카드는 장 당 1,000원 입니다.");
		bc.setCommentDate(LocalDateTime.now());
		boardCommentsRepo.save(bc);
	}
}