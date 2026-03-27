package com.myspringboot.sajo;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.myspringboot.sajo.member.Member;
import com.myspringboot.sajo.member.MemberRepository;
import com.myspringboot.sajo.search.Search;
import com.myspringboot.sajo.search.SearchRepository;

@SpringBootTest
public class SearchTests {
	@Autowired
	private MemberRepository memberRepo;
	@Autowired
	private SearchRepository searchRepo;
	
	// Search 테이블 더미데이터 추가
	@Test
	void TestInsertSearch() {
		Member m = memberRepo.findById(1).get();
		Search s = new Search();
		s.setSearchMemberNo(m);
		s.setSearchKeyword("신발");
		s.setSearchDate(LocalDateTime.now());
		searchRepo.save(s);
		
		m = memberRepo.findById(3).get();
		s = new Search();
		s.setSearchMemberNo(m);
		s.setSearchKeyword("프라다");
		s.setSearchDate(LocalDateTime.now());
		searchRepo.save(s);
		
		m = memberRepo.findById(2).get();
		s = new Search();
		s.setSearchMemberNo(m);
		s.setSearchKeyword("데이식스");
		s.setSearchDate(LocalDateTime.now());
		searchRepo.save(s);
		
		m = memberRepo.findById(1).get();
		s = new Search();
		s.setSearchMemberNo(m);
		s.setSearchKeyword("원필");
		s.setSearchDate(LocalDateTime.now());
		searchRepo.save(s);
		
		m = memberRepo.findById(3).get();
		s = new Search();
		s.setSearchMemberNo(m);
		s.setSearchKeyword("가방");
		s.setSearchDate(LocalDateTime.now());
		searchRepo.save(s);
		
		m = memberRepo.findById(2).get();
		s = new Search();
		s.setSearchMemberNo(m);
		s.setSearchKeyword("샤넬");
		s.setSearchDate(LocalDateTime.now());
		searchRepo.save(s);
	}
}
