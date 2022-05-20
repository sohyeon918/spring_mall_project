package com.javateam.project.domain;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberDTO {

	private String id;
	
	@Pattern(regexp="\\s{1,20}|(?=.*[a-zA-Z])((?=.*\\d)(?=.*\\W)).{8,20}", 
			 message="패쓰워드는 영문/숫자 및 특수문자 최소 1자의 조합으로 8~20사이로 입력하십시오")
	private String pwd;
	
	@Pattern(regexp="\\s{1,20}|(?=.*[a-zA-Z])((?=.*\\d)(?=.*\\W)).{8,20}", 
			 message="패쓰워드는 영문/숫자 및 특수문자 최소 1자의 조합으로 8~20사이로 입력하십시오")
	private String pwdCheck;
	
	private String name;
	
	@Email(message="올바른 이메일 형식이 아닙니다")
	private String email;
	
	@Pattern(regexp="01\\d{1}-\\d{3,4}-\\d{4}",
			 message="연락처(휴대폰)는 010-1234-5678 형식으로 입력하십시오")
	private String phone;
	
	private String zipNum;
	private String address1;
	private String address2;
	private String useyn;
	private Timestamp indate;
	
}