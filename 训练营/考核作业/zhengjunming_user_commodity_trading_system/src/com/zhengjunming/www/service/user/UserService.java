package com.zhengjunming.www.service.user;


import java.util.UUID;

import com.zhengjunming.www.dao.impl.UserDaoImpl;
import com.zhengjunming.www.po.User;
import com.zhengjunming.www.util.DigestUtils;
import com.zhengjunming.www.util.MailUtils;

/**
 * 用户逻辑判断
 * 
 * @author 郑俊铭
 *
 */
public class UserService {
	public final static int USERNAME_EXIST = 0; // 用户存在
	public final static int USERNAME_NOT_EXIST = 1; // 用户不存在
	public final static int EMAIL_EXIST = 0; // 邮箱存在
	public final static int EMAIL_NOT_EXIST = 1; // 邮箱不存在
	public final static int SUCCESS = 2; // 操作成功
	public final static int FAIL = 3; // 操作失败

	UserDaoImpl userDaoImpl = new UserDaoImpl();
	private static MailUtils mailUtils = new MailUtils();

	/**
	 * 注册用户，并判断是否符合注册条件
	 * 
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public int register(User user) throws Exception {
		if (userDaoImpl.usernameIsExist(user.getUsername())) {
			return USERNAME_EXIST; // 该用户已存在
		}
		if (userDaoImpl.emailIsExist(user.getEmail())) {
			return EMAIL_EXIST; // 邮箱已经存在
		}
		String code = UUID.randomUUID().toString().replace("-", ""); // 生成用户激活码
		user.setCode(code);
		user.setPassword(DigestUtils.md5(user.getPassword()));
		if (userDaoImpl.addUser(user) == 1) {
			sendActivationMail(user.getEmail(), code);
			return SUCCESS;
		} else {
			return FAIL;
		}

	}

	/**
	 * 用户激活
	 * 
	 * @param email
	 * @param code
	 * @throws Exception
	 */
	private void sendActivationMail(String email, String code) throws Exception {

		String Subject = "激活邮件";
		String setContent = "<h1>此邮件为激活邮件！请点击下面链接完成激活操作！</h1><h3><a href='http://localhost:8080//zhengjunming_user_commodity_trading_system/UserActiveServlet?code="
				+ code
				+ "'>http://localhost:8080/zhengjunming_user_commodity_trading_system/UserActiveServlet</a></h3>";
		String ContentType = "text/html;charset=UTF-8";
		mailUtils.sendMail(email, Subject, setContent, ContentType);
	}

	/**
	 * 用户登录，并判断是否信息正确
	 * 
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public int login(User user) throws Exception {
		int flag = FAIL;
		if (userDaoImpl.usernameIsExist(user.getUsername())) {
			if (userDaoImpl.login(user)) {
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
	public int passwordRight(User user, String oldPassword, String newPassword)
			throws Exception {
		int flag = FAIL;

		// 判断输入的密码是否正确，如果正确则修改密码
		if (oldPassword.equals(user.getPassword())) {
			if (userDaoImpl.update(user, newPassword)) {
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
	 * 判断用户是否激活成功
	 * 
	 * @param code
	 * @return
	 * @throws Exception
	 */
	public Boolean Active(String code) throws Exception {
		User user = userDaoImpl.queryUserByCode(code);
		if (user.getId() != 0) {
			userDaoImpl.setStatus(user.getId());
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 通过邮箱找回密码
	 * 
	 * @param email
	 * @throws Exception
	 */
	public void findPasswordByEmail(String email) throws Exception {
		String Subject = "找回密码邮件";
		String setContent = "<h1>此邮件为找回密码邮件！请点击下面链接完成操作！</h1>"
				+ "<h3><a href='http://localhost:8080/zhengjunming_user_commodity_trading_system/user/setNewPassword.jsp'"
				+ ">http://localhost:8080/zhengjunming_user_commodity_trading_system/user/setNewPassword.jsp</a></h3>";
		String ContentType = "text/html;charset=UTF-8";
		mailUtils.sendMail(email, Subject, setContent, ContentType);
	}

}
