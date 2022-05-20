package com.javateam.project.domain;

public class RoleVO {
	
	private int roleSeq;
	private String id;
	private String role;
	
	public RoleVO() {}
	
	public RoleVO(String id, String role) {
		this.id = id;
		this.role = role;
	}
	
	public int getRoleSeq() {
		return roleSeq;
	}
	public void setRoleSeq(int roleSeq) {
		this.roleSeq = roleSeq;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
	@Override
	public String toString() {
		return "RoleVO [roleSeq=" + roleSeq + ", id=" + id + ", role=" + role + "]";
	}

}
