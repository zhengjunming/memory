package com.zhengjunming.www.dao;

import java.util.List;

import com.zhengjunming.www.po.Goods;
import com.zhengjunming.www.po.Store;

public interface ListGoodsDao {

	/**
	 * 罗列所有商品
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<Goods> ListGoods(int pagesize, int page) throws Exception;

	/**
	 * 获得商品数量
	 * 
	 * @return
	 * @throws Exception
	 */
	public int GetGoodsCount() throws Exception;

	/**
	 * 罗列一个店家的所有商品
	 * 
	 * @param pageSize
	 * @param page
	 * @return
	 * @throws Exception
	 */
	public List<Goods> ListStoreGoods(int pageSize, int page, Store store)
			throws Exception;

	/**
	 * 获得一个店铺的商品总量
	 * 
	 * @param store
	 * @return
	 * @throws Exception
	 */
	public int GetStoreGoodsCount(Store store) throws Exception;

	/**
	 * 罗列店家已上架的商品
	 * 
	 * @param pageSize
	 * @param page
	 * @param store
	 * @return
	 * @throws Exception
	 */
	public List<Goods> ListHasShelvedGoods(int pageSize, int page, Store store)
			throws Exception;

	/**
	 * 获得一个店铺已经上架的商品总量
	 * 
	 * @param store
	 * @return
	 * @throws Exception
	 */
	public int GetHasShelvedGoodsCount(Store store) throws Exception;

	/**
	 * 罗列店家已下架的商品
	 * 
	 * @param pageSize
	 * @param page
	 * @param store
	 * @return
	 * @throws Exception
	 */
	public List<Goods> ListHasUnderShelvedGoods(int pageSize, int page,
			Store store) throws Exception;

	/**
	 * 罗列店家已断货的商品
	 * 
	 * @param pageSize
	 * @param page
	 * @param store
	 * @return
	 * @throws Exception
	 */
	public List<Goods> ListHasOutGoods(int pageSize, int page, Store store)
			throws Exception;

	/**
	 * 获得一个店铺已经下架的商品总量
	 * 
	 * @param store
	 * @return
	 * @throws Exception
	 */
	public int GetHasUnderShelvedGoodsCount(Store store) throws Exception;

	/**
	 * 获得一个店铺已经断货的商品总量
	 * 
	 * @param store
	 * @return
	 * @throws Exception
	 */
	public int GetHasOutGoodsCount(Store store) throws Exception;

	/**
	 * 获得一个商店除下架以外的所有商品
	 * 
	 * @param pageSize
	 * @param page
	 * @param store
	 * @return
	 * @throws Exception
	 */
	public List<Goods> ListNotUnderShelvedGoods(int pageSize, int page,
			Store store) throws Exception;

	/**
	 * 获得一个商店除下架以外的商品数量
	 * 
	 * @param store
	 * @return
	 * @throws Exception
	 */
	public int GetNotUnderShelvedGoodsCount(Store store) throws Exception;
}