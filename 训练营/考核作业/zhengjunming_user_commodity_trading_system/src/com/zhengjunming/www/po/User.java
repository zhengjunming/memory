package com.zhengjunming.www.po;

/**
 * 用户实体类
 * 
 * @author 郑俊铭
 *
 */
public class User {
	private int id; // 用户id
	private String username; // 用户名
	private String password; // 用户密码
	private String email; // 用户邮箱
	private String securityQuestion; // 密保问题
	private String classifiedAnswer; // 密保答案
	private String phone; // 用户电话
	private String address; // 用户住址
	private String code; // 用户激活码
	private boolean status; // 用户状态

	public User() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSecurityQuestion() {
		return securityQuestion;
	}

	public void setSecurityQuestion(String securityQuestion) {
		this.securityQuestion = securityQuestion;
	}

	public String getClassifiedAnswer() {
		return classifiedAnswer;
	}

	public void setClassifiedAnswer(String classifiedAnswer) {
		this.classifiedAnswer = classifiedAnswer;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}
}
