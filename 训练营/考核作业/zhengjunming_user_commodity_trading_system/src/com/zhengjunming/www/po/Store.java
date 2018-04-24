package com.zhengjunming.www.po;

import java.sql.Timestamp;

/**
 * 店家信息实体类
 * 
 * @author 郑俊铭
 *
 */
public class Store {
	private Integer id; // 店家id
	private String storeUsername; // 店家登录用户名
	private String storePassword; // 店家登录密码
	private String storeOwnerName; // 店主名字
	private String phone; // 店主电话
	private String email; // 店主邮箱
	private String storeName; // 店家名
	private String storeDescription; // 店铺描述
	private Timestamp createTime; // 创建时间
	private String storeStatus; // 店铺状态

	public Store() {

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getStoreUsername() {
		return storeUsername;
	}

	public void setStoreUsername(String storeUsername) {
		this.storeUsername = storeUsername;
	}

	public String getStorePassword() {
		return storePassword;
	}

	public void setStorePassword(String storePassword) {
		this.storePassword = storePassword;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getStoreDescription() {
		return storeDescription;
	}

	public void setStoreDescription(String storeDescription) {
		this.storeDescription = storeDescription;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public String getStoreStatus() {
		return storeStatus;
	}

	public void setStoreStatus(String storeStatus) {
		this.storeStatus = storeStatus;
	}

	public String getStoreOwnerName() {
		return storeOwnerName;
	}

	public void setStoreOwnerName(String storeOwnerName) {
		this.storeOwnerName = storeOwnerName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
