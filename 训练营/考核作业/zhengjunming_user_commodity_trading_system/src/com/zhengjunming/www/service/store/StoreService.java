package com.zhengjunming.www.service.store;

import com.zhengjunming.www.dao.impl.StoreDaoImpl;
import com.zhengjunming.www.po.Store;
import com.zhengjunming.www.util.DigestUtils;

/**
 * 店家逻辑判断
 * 
 * @author 郑俊铭
 *
 */
public class StoreService {
	public final static int USERNAME_EXIST = 0; // 用户存在
	public final static int USERNAME_NOT_EXIST = 1; // 用户不存在
	public final static int EMAIL_EXIST = 0; // 邮箱存在
	public final static int EMAIL_NOT_EXIST = 1; // 邮箱不存在
	public final static int SUCCESS = 2; // 操作成功
	public final static int FAIL = 3; // 操作失败
	public final static int INFO_EMPTY = 0; // 信息为空
	public final static int INFO_NOT_EMPTY = 1; // 信息不为空
	public final static int IMAGE_FORMAT_IS_RIGHT = 1; // 图片格式正确
	public final static int IMAGE_FORMAT_IS_ERROR = 0; // 图片格式不正确

	StoreDaoImpl storeDaoImpl = new StoreDaoImpl();

	/**
	 * 注册店家，并判断是否符合注册条件
	 * 
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public int register(Store store) throws Exception {
		if (storeDaoImpl.usernameIsExist(store.getStoreUsername())) {
			return USERNAME_EXIST; // 该用户已存在
		}
		if (storeDaoImpl.emailIsExist(store.getEmail())) {
			return EMAIL_EXIST; // 邮箱已经存在
		}
		store.setStorePassword(DigestUtils.md5(store.getStorePassword()));
		storeDaoImpl.addStore(store); // 添加该用户
		return SUCCESS;
	}

	/**
	 * 用户登录，并判断是否信息正确
	 * 
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public int login(Store store) throws Exception {
		int flag = FAIL;
		if (storeDaoImpl.usernameIsExist(store.getStoreUsername())) {
			if (storeDaoImpl.storeLogin(store)) {
				flag = SUCCESS; // 登录成功
			} else {
				flag = FAIL; // 密码错误
			}
		} else {
			flag = USERNAME_NOT_EXIST; // 该用户不存在
		}
		return flag;
	}

	/**
	 * 用户修改密码
	 * 
	 * @param user
	 * @param oldPassword
	 * @param newPassword
	 * @return
	 * @throws Exception
	 */
	public int passwordRight(Store store, String oldPassword, String newPassword)
			throws Exception {
		int flag = FAIL;

		// 判断输入的密码是否正确，如果正确则修改密码
		if (oldPassword.equals(store.getStorePassword())) {
			if (storeDaoImpl.updatePassword(store, newPassword)) {
				flag = SUCCESS;
			} else {
				flag = FAIL;
			}
		} else {
			flag = FAIL;
		}

		return flag;
	}


	/**
	 * 判断图片格式是否正确
	 * 
	 * @param filename
	 * @return
	 * @throws Exception
	 */
	public int imageFormatIsRight(String filename) throws Exception {
		if (filename.endsWith(".jpg") || filename.endsWith(".png")
				|| filename.endsWith(".gif") || filename.endsWith(".jpeg")
				|| filename.endsWith(".JPG") || filename.endsWith(".PNG")
				|| filename.endsWith(".GIF") || filename.endsWith(".JPEG")) {
			return IMAGE_FORMAT_IS_RIGHT;
		} else {
			return IMAGE_FORMAT_IS_ERROR;
		}
	}
}
