package com.zhengjunming.www.dao;

import java.util.List;

import com.zhengjunming.www.po.Goods;
import com.zhengjunming.www.po.Store;

public interface GoodsDao {

	/**
	 * 罗列所有商品
	 * 
	 * @return 商品集合
	 * @throws Exception
	 */
	public List<Goods> ListGoods(int pagesize, int page) throws Exception;

	/**
	 * 获得商品数量
	 * 
	 * @return 商品数量
	 * @throws Exception
	 */
	public int GetGoodsCount() throws Exception;

	/**
	 * 罗列一个店家的所有商品
	 * 
	 * @param pageSize 一页的数量
	 * @param page 第几页
	 * @param store 商家实体类
	 * @return 商品集合
	 * @throws Exception
	 */
	public List<Goods> ListStoreGoods(int pageSize, int page, Store store)
			throws Exception;

	/**
	 * 获得一个店铺的商品总量
	 * 
	 * @param store 商家实体类
	 * @return 店铺商品数量
	 * @throws Exception
	 */
	public int GetStoreGoodsCount(Store store) throws Exception;

	/**
	 * 获得一个店铺某种状态的商品总量
	 *
	 * @param status 商品状态
	 * @param id 店家id
	 * @return 商品总量
	 * @throws Exception
	 */
	public int getGoodsCountByStatusAndStoreId(String status, int id)
			throws Exception;

	/**
	 * 罗列店家某种状态的商品
	 *
	 * @param pageSize 一页的数量
	 * @param page 第几页
	 * @param status 商品状态
	 * @param id 店家id
	 * @return 商品集合
	 * @throws Exception
	 */
	public List<Goods> ListGoodsByStatusAndStoreId(int pageSize, int page,
			String status, int id) throws Exception;

	/**
	 * 获得一个商店除下架以外的所有商品
	 * 
	 * @param pageSize 一页的数量
	 * @param page 第几页
	 * @param store 店家实体类
	 * @return 商品集合
	 * @throws Exception
	 */
	public List<Goods> ListNotUnderShelvedGoods(int pageSize, int page,
			Store store) throws Exception;

	/**
	 * 获得一个商店除下架以外的商品数量
	 * 
	 * @param store 店家实体类
	 * @return 商品数量
	 * @throws Exception
	 */
	public int GetNotUnderShelvedGoodsCount(Store store) throws Exception;

	/**
	 * 根据id获得商品
	 * 
	 * @param id 商品ID
	 * @return 商品实体类
	 * @throws Exception
	 */
	public Goods getGoodsById(int id) throws Exception;

	/**
	 * 修改销售量和库存量
	 * 
	 * @param count 数量
	 * @param goods 商品实体类
	 * @throws Exception
	 */
	public void updateSellCountAndQuantity(int count, Goods goods)
			throws Exception;

	/**
	 * 根据关键字搜索商品
	 * 
	 * @param pageSize 一页的数量
	 * @param page 第几页
	 * @param key 关键字，可进行模糊搜索
	 * @return 商品集合
	 * @throws Exception
	 */
	public List<Goods> queryGoodsByKey(int pageSize, int page, String key)
			throws Exception;

	/**
	 * 得到符合条件的商品数量
	 * 
	 * @param key 关键字
	 * @return 商品数量
	 * @throws Exception
	 */
	public int GetGoodsCountByKey(String key) throws Exception;

	/**
	 * 根据关键字搜索商品
	 * 
	 * @param pageSize 一页的数量
	 * @param page 第几页
	 * @param key 关键字
	 * @param storeId 商家id
	 * @return 一个商家的商品集合
	 * @throws Exception
	 */
	public List<Goods> queryGoodsByKeyAndStoreId(int pageSize, int page,
			String key, int storeId) throws Exception;

	/**
	 * 得到符合条件的商品数量
	 * 
	 * @param key 关键字
	 * @param storeId 店家id
	 * @return 一个店家符合条件的商品数量
	 * @throws Exception
	 */
	public int GetGoodsCountByKeyAndStoreId(String key, int storeId)
			throws Exception;

	/**
	 * 店家根据关键字搜索商品
	 * 
	 * @param pageSize 一页的数量
	 * @param page 第几页
	 * @param key 关键字
	 * @param storeId 店家id
	 * @return 符合条件的商品集合
	 * @throws Exception
	 */
	public List<Goods> storeQueryGoodsByKeyAndStoreId(int pageSize, int page,
			String key, int storeId) throws Exception;

	/**
	 * 得到符合条件的商品数量
	 * 
	 * @param key 关键字
	 * @param storeId 商家id
	 * @return 符合条件的商品数量
	 * @throws Exception
	 */
	public int storeGetGoodsCountByKeyAndStoreId(String key, int storeId)
			throws Exception;

	/**
	 * 改变商品状态
	 * 
	 * @param status 商品状态
	 * @param id 商品id
	 * @throws Exception
	 */
	public void updateGoodsStatus(String status, int id) throws Exception;

}