package com.zhengjunming.www.po;

/**
 * 购物车实体类
 * 
 * @author 郑俊铭
 *
 */
public class Cart {
	private Integer id; // 主键
	private User user; // 所属用户

	public Cart() {

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
