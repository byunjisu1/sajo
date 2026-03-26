package com.myspringboot.sajo.member;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

public class AddressDto {
	private Integer addressIdx;
	private Integer addressMemberNo;
	private String address;
	private String postCode;
}
