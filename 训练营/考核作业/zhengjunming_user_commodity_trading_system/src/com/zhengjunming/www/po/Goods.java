package com.zhengjunming.www.po;

import java.sql.Timestamp;

/**
 * 商品信息实体类
 * 
 * @author 郑俊铭
 *
 */
public class Goods {
	private Integer id; // 商品id
	private String goodsName; // 商品名称
	private String goodsDescription; // 商品描述
	private Timestamp ctreteTime; // 商品创建时间
	private Integer sellCount; // 销售量
	private String marketPrice; // 市场价格
	private String sellPrice; // 销售价格
	private String goodsDiscount; // 商品的折扣率
	private Integer quantity; // 库存量
	private Store store; // 所属店家
	private String goodsPicture; // 商品图片
	private String goodsStatus; // 商品状态

	public Goods() {

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public String getGoodsDescription() {
		return goodsDescription;
	}

	public void setGoodsDescription(String goodsDescription) {
		this.goodsDescription = goodsDescription;
	}

	public Timestamp getCtreteTime() {
		return ctreteTime;
	}

	public void setCtreteTime(Timestamp ctreteTime) {
		this.ctreteTime = ctreteTime;
	}

	public Integer getSellCount() {
		return sellCount;
	}

	public void setSellCount(Integer sellCount) {
		this.sellCount = sellCount;
	}

	public String getMarketPrice() {
		return marketPrice;
	}

	public void setMarketPrice(String marketPrice) {
		this.marketPrice = marketPrice;
	}

	public String getSellPrice() {
		return sellPrice;
	}

	public void setSellPrice(String sellPrice) {
		this.sellPrice = sellPrice;
	}

	public String getGoodsDiscount() {
		return goodsDiscount;
	}

	public void setGoodsDiscount(String goodsDiscount) {
		this.goodsDiscount = goodsDiscount;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Store getStore() {
		return store;
	}

	public void setStore(Store store) {
		this.store = store;
	}

	public String getGoodsPicture() {
		return goodsPicture;
	}

	public void setGoodsPicture(String goodsPicture) {
		this.goodsPicture = goodsPicture;
	}

	public String getGoodsStatus() {
		return goodsStatus;
	}

	public void setGoodsStatus(String goodsStatus) {
		this.goodsStatus = goodsStatus;
	}

}
