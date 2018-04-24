package com.zhengjunming.www.po;

/**
 * 管理员实体类
 * 
 * @author 郑俊铭
 *
 */
public class Admin {
	private int id; // 管理员id
	private String adminName; // 管理员登录用户名
	private String adminPassword; // 管理员登录密码

	public Admin() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAdminName() {
		return adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	public String getAdminPassword() {
		return adminPassword;
	}

	public void setAdminPassword(String adminPassword) {
		this.adminPassword = adminPassword;
	}

}
