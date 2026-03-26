package com.myspringboot.sajo.member;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Integer>{
	List<Address> findByAddressMemberNo_memberNo(Integer memberNo);
	
}
