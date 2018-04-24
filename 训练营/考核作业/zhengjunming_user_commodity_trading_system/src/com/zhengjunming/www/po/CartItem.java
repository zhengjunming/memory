package com.zhengjunming.www.po;

/**
 * 购物车条目实体类
 * 
 * @author 郑俊铭
 *
 */
public class CartItem {
	private Integer id; // 主键
	private Goods goods;
	private Integer goodsAmount; // 商品数量
	private String totalPrice; // 总价格
	private Store store;
	private Cart cart; // 所属购物车

	public CartItem() {

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(String totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	public Goods getGoods() {
		return goods;
	}

	public void setGoods(Goods goods) {
		this.goods = goods;
	}

	public Integer getGoodsAmount() {
		return goodsAmount;
	}

	public void setGoodsAmount(Integer goodsAmount) {
		this.goodsAmount = goodsAmount;
	}

	public Store getStore() {
		return store;
	}

	public void setStore(Store store) {
		this.store = store;
	}

}
