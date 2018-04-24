package com.zhengjunming.www.po;

/**
 * 商品类别实体类
 * 
 * @author 郑俊铭
 *
 */
public class GoodsType {
	private Integer id; // 商品类别id
	private String typeName; // 商品类型名
	private String typeDescription; // 商品类型描述

	public GoodsType() {

	}

	public GoodsType(Integer id, String typeName, String typeDescription) {
		this.id = id;
		this.typeName = typeName;
		this.typeDescription = typeDescription;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getTypeDescription() {
		return typeDescription;
	}

	public void setTypeDescription(String typeDescription) {
		this.typeDescription = typeDescription;
	}

	@Override
	public String toString() {
		return "GoodsType [id=" + id + ", typeName=" + typeName
				+ ", typeDescription=" + typeDescription + "]";
	}

}
