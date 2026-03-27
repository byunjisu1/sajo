package com.myspringboot.sajo.member;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AddressService {
	
	@Autowired
	private AddressRepository addrRepo;
	
	/*
	 * insertAddress : address테이블에 배송지 추가 
	 * input : 
	 * output : 
	 */
	public Address insertAddress(AddressDto dto) {
		
		Address address = new Address();
		
		address.setAddress(dto.getAddress());
		address.setPostCode(dto.getPostCode());
		
		Member m1 = new Member();
		m1.setMemberNo(dto.getAddressMemberNo());
		
		address.setAddressMemberNo(m1);
		
		return addrRepo.save(address);
		
	}
	/*
	 * getAddressList : 저장된 배송지 불러오기 
	 * input : memberNo
	 * output : memberNo에 해당하는 모든 주소록 
	 */
	@Transactional
	public List<Address> getAddressList(Integer memberNo){
		return addrRepo.findByAddressMemberNo_memberNo(memberNo);
		
	}
	/*
	 * deleteAddressFromAddressIdx : addressIdx 값으로 배송지 삭제 
	 * input: addressIdx
	 * output: 
	 */
	@Transactional
	public void deleteAddressFromAddressIdx(Integer addressIdx) {
		addrRepo.deleteById(addressIdx);
	}
}
