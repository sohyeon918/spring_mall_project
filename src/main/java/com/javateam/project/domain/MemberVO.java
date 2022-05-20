package com.javateam.project.domain;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberVO {

	private String id;
	private String pwd;
	private String name;
	private String email;
	private String phone;
	private String zipNum;
	private String address1;
	private String address2;
	private String useyn;
	private Timestamp indate;
	
	public MemberVO(MemberDTO memberDTO) {
		
		this.id= memberDTO.getId();
		this.pwd= memberDTO.getPwd();
		this.name= memberDTO.getName();
		this.email= memberDTO.getEmail();
		this.phone= memberDTO.getPhone();
		this.zipNum= memberDTO.getZipNum();
		this.address1= memberDTO.getAddress1();
		this.address2= memberDTO.getAddress2();
		this.useyn= memberDTO.getUseyn();
		this.indate= memberDTO.getIndate();

	}
	
}