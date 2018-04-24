package com.zhengjunming.www.dao;

import java.util.List;

import com.zhengjunming.www.po.Goods;
import com.zhengjunming.www.po.Store;

/**
 * 店家DAO层接口
 * 
 * @author 郑俊铭
 *
 */
public interface StoreDao {

	/**
	 * 店家注册
	 * 
	 * @param store 店家实体类
	 * @throws Exception
	 */
	public void addStore(Store store) throws Exception;

	/**
	 * 判断用户名是否存在
	 * 
	 * @param storeUsername 店家用户名
	 * @return 存在--true，不存在--false
	 * @throws Exception
	 */
	public boolean usernameIsExist(String storeUsername) throws Exception;

	/**
	 * 判断邮箱是否存在
	 * 
	 * @param email 邮箱
	 * @return 存在--true，不存在--false
	 * @throws Exception
	 */
	public boolean emailIsExist(String email) throws Exception;

	/**
	 * 店主登录
	 * 
	 * @param store 店家实体类
	 * @return 成功--true，失败--false
	 * @throws Exception
	 */
	public boolean storeLogin(Store store) throws Exception;

	/**
	 * 店主修改密码
	 * 
	 * @param store 店家实体类
	 * @param newPassword 新密码
	 * @return 成功--true，失败--false
	 * @throws Exception
	 */
	public boolean updatePassword(Store store, String newPassword)
			throws Exception;

	/**
	 * 获得一个店家实体类信息
	 * 
	 * @param store 店家实体类
	 * @return 店家实体类
	 * @throws Exception
	 */
	public Store storeData(Store store) throws Exception;

	/**
	 * 添加商品
	 * 
	 * @param goods 商品实体类
	 * @param store 店家实体类
	 * @throws Exception
	 */
	public void addGoods(Goods goods, Store store) throws Exception;

	/**
	 * 修改商品图片
	 * 
	 * @param goods 商品实体类
	 * @throws Exception
	 */
	public void modifyGoodsPicture(Goods goods) throws Exception;

	/**
	 * 修改商品信息
	 * 
	 * @param goods 商品实体类
	 * @throws Exception
	 */
	public void modifyGoodsInfo(Goods goods) throws Exception;

	/**
	 * 下架商品
	 * 
	 * @param id 商品id
	 * @throws Exception
	 */
	public void underShelvedGoods(int id) throws Exception;

	/**
	 * 查找除了当前商家之外的所有商家的邮箱
	 * 
	 * @param store 店家实体
	 * @return 邮箱集合
	 * @throws Exception
	 */
	public List<String> queryEmail(Store store) throws Exception;

	/**
	 * 查找除了当前商家之外的所有商家的手机号码
	 * 
	 * @param store 店家实体
	 * @return 手机集合
	 * @throws Exception
	 */
	public List<String> queryPhone(Store store) throws Exception;

	/**
	 * 完善/修改店铺信息
	 * 
	 * @param store 店家实体
	 * @param storeOwnerName
	 * @param phone
	 * @param email
	 * @param storeName
	 * @param storeDescription
	 * @throws Exception
	 */
	public void improveStoreInfo(Store store, String storeOwnerName,
			String phone, String email, String storeName,
			String storeDescription) throws Exception;

	/**
	 * 根据id获取店家信息
	 * 
	 * @param id 店家id
	 * @return
	 * @throws Exception
	 */
	public Store getStoreInfo(int id) throws Exception;

	/**
	 * 根据关键字搜索店铺的总量
	 * 
	 * @param pageSize 一页的数量
	 * @param page 第几页
	 * @param key 关键字
	 * @return 数量
	 * @throws Exception
	 */
	public int getStoreCountByKey(int pageSize, int page, String key)
			throws Exception;

	/**
	 * 根据关键字查找店家
	 * 
	 * @param pageSize 一页的数量
	 * @param page 第几页
	 * @param key 关键字
	 * @return 店家集合
	 * @throws Exception
	 */
	public List<Store> queryStoreByKey(int pageSize, int page, String key)
			throws Exception;

}
