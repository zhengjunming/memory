package com.zhengjunming.www.service.admin;

import com.zhengjunming.www.dao.impl.AdminDaoImpl;
import com.zhengjunming.www.po.Admin;

/**
 * 管理员逻辑判断
 * 
 * @author 郑俊铭
 *
 */
public class AdminService {
	public final static int USERNAME_EXIST = 0; // 用户存在
	public final static int USERNAME_NOT_EXIST = 1; // 用户不存在
	public final static int SUCCESS = 2; // 操作成功
	public final static int FAIL = 3; // 操作失败

	AdminDaoImpl adminDaoImpl = new AdminDaoImpl();

	/**
	 * 用户登录，并判断是否信息正确
	 * 
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public int login(Admin admin) throws Exception {
		int flag = FAIL;
		if (adminDaoImpl.usernameIsExist(admin.getAdminName())) {
			if (adminDaoImpl.adminLogin(admin)) {
				flag = SUCCESS; // 登录成功
			} else {
				flag = FAIL; // 密码错误
			}
		} else {
			flag = USERNAME_NOT_EXIST; // 该用户不存在
		}
		return flag;
	}
}
