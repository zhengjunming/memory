package com.zhengjunming.www.dao;

import com.zhengjunming.www.po.User;

/**
 * 用户接口
 * 
 * @author 郑俊铭
 *
 */
public interface UserDao {

	/**
	 * 添加用户
	 * 
	 * @param user 用户实体
	 * @return
	 * @throws Exception
	 */
	public int addUser(User user) throws Exception;

	/**
	 * 判断用户名是否存在
	 * 
	 * @param username 用户名
	 * @return
	 * @throws Exception
	 */
	public boolean usernameIsExist(String username) throws Exception;

	/**
	 * 判断邮箱是否存在
	 * 
	 * @param email 邮箱
	 * @return
	 * @throws Exception
	 */
	public boolean emailIsExist(String email) throws Exception;

	/**
	 * 用户修改密码
	 * 
	 * @param user 用户实体
	 * @param newPassword 新密码
	 * @return
	 * @throws Exception
	 */
	public boolean update(User user, String newPassword) throws Exception;

	/**
	 * 用户登录
	 * 
	 * @param user 用户实体
	 * @return
	 * @throws Exception
	 */
	public boolean login(User user) throws Exception;

	/**
	 * 获得一个User实体类的所有信息
	 * 
	 * @param user 用户实体
	 * @return
	 * @throws Exception
	 */
	public User UserData(User user) throws Exception;

	/**
	 * 完善用户信息
	 * 
	 * @param user 用户实体
	 * @throws Exception
	 */
	public void improveUserInfo(User user) throws Exception;

	/**
	 * 根据id获取用户信息
	 * 
	 * @param id 用户ID
	 * @return
	 * @throws Exception
	 */
	public User getUserInfo(int id) throws Exception;

	/**
	 * 根据激活码查找用户
	 * 
	 * @param code 激活码
	 * @return
	 * @throws Exception
	 */
	public User queryUserByCode(String code) throws Exception;

	/**
	 * 将用户状态设为可用
	 * 
	 * @param id 用户id
	 * @throws Exception
	 */
	public void setStatus(int id) throws Exception;

	/**
	 * 根据用户名得到一个user
	 * 
	 * @param username 用户名
	 * @return 
	 * @throws Exception
	 */
	public User getUserByUsername(String username) throws Exception;
}
