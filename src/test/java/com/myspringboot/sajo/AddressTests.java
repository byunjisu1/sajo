package com.myspringboot.sajo;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.myspringboot.sajo.member.Address;
import com.myspringboot.sajo.member.AddressRepository;
import com.myspringboot.sajo.member.AddressService;
import com.myspringboot.sajo.member.Member;
import com.myspringboot.sajo.member.MemberRepository;

@SpringBootTest
public class AddressTests {
	@Autowired
	private AddressRepository addressRepo;
	@Autowired
	private AddressService addrSvc;
	@Autowired
	private MemberRepository memberRepo;

	// Address 테이블 더미데이터 추가
	@Test
	void testInsertAddress() {
		Member m = memberRepo.findById(1).get();
		Address a = new Address();
		a.setAddressMemberNo(m);
		a.setAddress("문성로 144번길 아이티아파트 103동 803호");
		a.setPostCode("14563");
		addressRepo.save(a);
		
		m = memberRepo.findById(2).get();
		a = new Address();
		a.setAddressMemberNo(m);
		a.setAddress("신촌로 51-8 코리아아이티");
		a.setPostCode("84721");
		addressRepo.save(a);
		
		m = memberRepo.findById(3).get();
		a = new Address();
		a.setAddressMemberNo(m);
		a.setAddress("창천로 88번길 ");
		a.setPostCode("12345");
		addressRepo.save(a);
	}

	// addressIdx에 해당하는 배송지 삭제
	@Test
	@Transactional
	void testDeleteAddress() {
		//Given
		int addressIdx = 21;
		//When
		addressRepo.deleteById(addressIdx);
		//Then
	}
	
	// memberNo로 배송지 불러오기
	@Test
	void testGetAddress(){
		//Given
		int memberNo = 3;
		//When
		
		List<Address>listAddress = addrSvc.getAddressList(memberNo);
		//Then
		for(Address a : listAddress) {
			System.out.println(a.getAddress() + " : 배송지" + a.getPostCode() + " : 우편번호" );
		}
	}
}
