package com.zhengjunming.www.dao;

import java.util.List;

import com.zhengjunming.www.po.Admin;
import com.zhengjunming.www.po.Store;

/**
 * 管理员接口类
 * 
 * @author 郑俊铭
 *
 */
public interface AdminDao {

	/**
	 * 判断用户名是否存在
	 * 
	 * @param adminUsername
	 *            管理员用户名
	 * @return 存在返回true，不存在返回false
	 * @throws Exception
	 */
	public boolean usernameIsExist(String adminUsername) throws Exception;

	/**
	 * 管理员登录
	 * 
	 * @param admin
	 *            管理员实体类
	 * @return 登录成功--true，失败--false
	 * @throws Exception
	 */
	public boolean adminLogin(Admin admin) throws Exception;

	/**
	 * 获得管理员的所有信息
	 * 
	 * @param admin
	 *            管理员实体类
	 * @return 管理员实体类admin
	 * @throws Exception
	 */
	public Admin adminData(Admin admin) throws Exception;

	/**
	 * 获取提交审核的所有店家信息
	 * 
	 * @return 返回一个店家集合
	 * @throws Exception
	 */
	public List<Store> getAllStoreInReview() throws Exception;

	/**
	 * 同意店家开店
	 * 
	 * @param email
	 *            店家邮箱
	 * @throws Exception
	 */
	public void agreeOpenStore(String email) throws Exception;

	/**
	 * 拒绝店家开店
	 * 
	 * @param email
	 *            店家邮箱
	 * @throws Exception
	 */
	public void rejectOpenStore(String email) throws Exception;

	/**
	 * 获得所有已经开张的店铺
	 * 
	 * @return 返回一个店家集合--已经开张
	 * @throws Exception
	 */
	public List<Store> getAllHadOpenStore() throws Exception;

	/**
	 * 关闭店铺
	 * 
	 * @param email
	 *            店家邮箱
	 * @throws Exception
	 */
	public void closeStore(String email) throws Exception;

	/**
	 * 获得所有被强制关闭的店铺
	 * 
	 * @return 一个被强制关闭的店家集合
	 * @throws Exception
	 */
	public List<Store> getAllStoreBeClose() throws Exception;

	/**
	 * 获得所有被拒绝的店铺
	 * 
	 * @return 一个被拒绝开店的店家集合
	 * @throws Exception
	 */
	public List<Store> getAllStoreBeReject() throws Exception;
}
