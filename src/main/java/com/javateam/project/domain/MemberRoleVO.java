package com.javateam.project.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberRoleVO extends MemberVO {
	
	private String role;

	@Override
	public String toString() {
		
		StringBuilder builder = new StringBuilder();
		builder.append("MemberRoleVO [")
			   .append(super.getId()).append(",")
			   .append(super.getPwd()).append(",")		
			   .append(super.getName()).append(",")
			   .append(super.getPwd()).append(",")
			   .append(super.getEmail()).append(",")
			   .append(super.getPhone()).append(",")
			   .append(super.getZipNum()).append(",")
			   .append(super.getAddress1()).append(",")
			   .append(super.getAddress2()).append(",")
			   .append(super.getUseyn()).append(",")
			   .append(super.getIndate()).append(",")
			   .append(role)
			   .append("]");
		return builder.toString();
	}

}
