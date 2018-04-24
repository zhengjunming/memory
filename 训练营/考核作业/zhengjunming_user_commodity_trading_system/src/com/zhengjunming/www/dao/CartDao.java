package com.zhengjunming.www.dao;

import java.util.List;

import com.zhengjunming.www.po.Cart;
import com.zhengjunming.www.po.CartItem;
import com.zhengjunming.www.po.Goods;
import com.zhengjunming.www.po.User;

/**
 * 购物车DAO层
 * 
 * @author 郑俊铭
 *
 */
public interface CartDao {

	/**
	 * 判断用户是否存在购物车
	 * 
	 * @param user 用户实体类
	 * @return 没有购物车则返回false，有则返回true
	 * @throws Exception
	 */
	public boolean isUserhasCart(User user) throws Exception;

	/**
	 * 为用户添加购物车
	 * 
	 * @param user 用户实体类
	 * @throws Exception
	 */
	public void addCart(User user) throws Exception;

	/**
	 * 获得购物车详细信息
	 * 
	 * @param user 用户实体类
	 * @return 一个购物车订单集合
	 * @throws Exception
	 */
	public List<CartItem> getCartItem(User user) throws Exception;

	/**
	 * 根据购物车id获取购物条目
	 * 
	 * @param id 购物车ID
	 * @return 一个购物车订单集合
	 * @throws Exception
	 */
	public List<CartItem> getCartItemByCartId(int id) throws Exception;

	/**
	 * 根据id查找购物车
	 * 
	 * @param id 购物车ID
	 * @return 购物车实体类
	 * @throws Exception
	 */
	public Cart queryCartById(int id) throws Exception;

	/**
	 * 根据用户id获得购物车
	 * 
	 * @param id 用户ID
	 * @return 购物车实体类
	 * @throws Exception
	 */
	public Cart getCartByUserId(int id) throws Exception;

	/**
	 * 添加购物车条目
	 * 
	 * @param cartItem 购物车条目实体类
	 * @throws Exception
	 */
	public void addCartItem(CartItem cartItem) throws Exception;

	/**
	 * 增加购物车中某一商品的数量
	 * 
	 * @param cartItem 购物车条目实体类
	 * @param cartId 购物车ID
	 * @throws Exception
	 */
	public void increaseGoodsCount(CartItem cartItem, int cartId)
			throws Exception;

	/**
	 * 减少购物车某一商品的数量
	 * 
	 * @param cartItem 购物车条目实体类 
	 * @param cartId 购物车ID
	 * @throws Exception
	 */
	public void reduceGoodsCount(CartItem cartItem, int cartId)
			throws Exception;

	/**
	 * 判断购物车是否存在该商品
	 * 
	 * @param goods 商品实体类
	 * @param cartId 购物车ID
	 * @return 存在--true，不存在--false
	 * @throws Exception
	 */
	public boolean goodsIsExist(Goods goods, int cartId) throws Exception;

	/**
	 * 根据商品id和购物车id获得相应购物车条目
	 * 
	 * @param goodsId 商品ID
	 * @param cartId 购物车ID
	 * @return 购物车条目
	 * @throws Exception
	 */
	public CartItem getCartItemByGoodsIdAndCartId(int goodsId, int cartId)
			throws Exception;

	/**
	 * 删除购物车的某条条目
	 * 
	 * @param goodsId 商品id
	 * @param cartId 购物车ID
	 * @throws Exception
	 */
	public void deleteCartItem(int goodsId, int cartId) throws Exception;

	/**
	 * 清空购物车
	 * 
	 * @param cartId 购物车ID
	 * @throws Exception
	 */
	public void clearCart(int cartId) throws Exception;

	/**
	 * 根据店家id和购物车id得到清单
	 * 
	 * @param storeId 店家ID
	 * @param cartId 购物车ID
	 * @return 购物车条目集合
	 * @throws Exception
	 */
	public List<CartItem> getCartItemByStoreIdAndCartId(int storeId, int cartId)
			throws Exception;
}
