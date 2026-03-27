package com.myspringboot.sajo;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.myspringboot.sajo.bell.Bell;
import com.myspringboot.sajo.bell.BellMsg;
import com.myspringboot.sajo.bell.BellMsgRepository;
import com.myspringboot.sajo.bell.BellRepository;
import com.myspringboot.sajo.board.Board;
import com.myspringboot.sajo.board.BoardRepository;
import com.myspringboot.sajo.likes.Likes;
import com.myspringboot.sajo.likes.LikesRepository;
import com.myspringboot.sajo.member.Member;
import com.myspringboot.sajo.member.MemberRepository;

@SpringBootTest
public class BellTests {
	@Autowired
	private MemberRepository memberRepo;
	@Autowired
	private BellRepository bellRepo;
	@Autowired
	private BellMsgRepository bellMsgRepo;
	@Autowired
	private LikesRepository likesRepo;
	@Autowired
	private BoardRepository boardRepo;
	
	// bell_msg 테이블 더미데이터 추가
	@Test
	void testInsertBellMsg() {
		BellMsg bm = new BellMsg();
		bm.setBellType("D");
		bm.setBellMsg("찜한 !상품의 가격이 하락되었습니다.");
		bellMsgRepo.save(bm);
		
		bm = new BellMsg();
		bm.setBellType("Z");
		bm.setBellMsg("찜한 !상품이 품절되었습니다.");
		bellMsgRepo.save(bm);
		
		bm = new BellMsg();
		bm.setBellType("C");
		bm.setBellMsg("문의하신 게시글에 답변이 등록되었습니다.");
		bellMsgRepo.save(bm);
	}
	
	// Bell 테이블 더미데이터 추가
	@Test
	void testInsertBell() {
		Member m = memberRepo.findById(1).get();
		BellMsg bm = bellMsgRepo.findById("D").get();
		Likes l = likesRepo.findById(5).get();
		Bell b = new Bell();
		b.setBellMemberNo(m);
		b.setBellType(bm);
		b.setLikeIdx(l);
		b.setBellTime(LocalDateTime.now());
		bellRepo.save(b);
		
		m = memberRepo.findById(2).get();
		bm = bellMsgRepo.findById("C").get();
		Board bd = boardRepo.findById(1).get();
		b = new Bell();
		b.setBellMemberNo(m);
		b.setBellType(bm);
		b.setBellBoardIdx(bd);
		b.setBellTime(LocalDateTime.now());
		bellRepo.save(b);
		
		m = memberRepo.findById(3).get();
		bm = bellMsgRepo.findById("C").get();
		bd = boardRepo.findById(3).get();
		b = new Bell();
		b.setBellMemberNo(m);
		b.setBellType(bm);
		b.setBellBoardIdx(bd);
		b.setBellTime(LocalDateTime.now());
		bellRepo.save(b);
		
		m = memberRepo.findById(1).get();
		bm = bellMsgRepo.findById("Z").get();
		l = likesRepo.findById(1).get();
		b = new Bell();
		b.setBellMemberNo(m);
		b.setBellType(bm);
		b.setLikeIdx(l);
		b.setBellTime(LocalDateTime.now());
		bellRepo.save(b);
		
		m = memberRepo.findById(3).get();
		bm = bellMsgRepo.findById("D").get();
		l = likesRepo.findById(3).get();
		b = new Bell();
		b.setBellMemberNo(m);
		b.setBellType(bm);
		b.setLikeIdx(l);
		b.setBellTime(LocalDateTime.now());
		bellRepo.save(b);
	}
}
