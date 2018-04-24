package com.zhengjunming.www.service.other;

/**
 * 用户数据逻辑判断
 * 
 * @author 郑俊铭
 *
 */
public class DataService {
	public final static int PASSWORD_FORMAT_RIGHT = 1; // 密码格式正确
	public final static int PASSWORD_FORMAT_ERROR = 0; // 密码格式错误
	public final static int CHECK_PASSWORD_RIGHT = 1; // 两次密码一致
	public final static int CHECK_PASSWORD_ERROR = 0; // 两次密码不一致
	public final static int EMAIL_FORMAT_RIGHT = 1; // 邮箱格式正确
	public final static int EMAIL_FORMAT_ERROR = 0; // 邮箱格式错误
	public final static int USERNAME_FORMAT_RIGHT = 1; // 用户名格式正确
	public final static int USERNAME_FORMAT_ERROR = 0; // 用户名格式错误
	public final static int PHONE_FORMAT_RIGHT = 1; // 用户名格式正确
	public final static int PHONE_FORMAT_ERROR = 0; // 用户名格式错误

	/**
	 * 判断用户邮箱格式是否正确
	 * 
	 * @param email
	 * @return
	 */
	public int isRightEmail(String email) {
		if (email == null
				|| !email
						.matches("^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$")) {
			return EMAIL_FORMAT_ERROR;
		} else {
			return EMAIL_FORMAT_RIGHT;
		}
	}

	/**
	 * 判断用户名格式是否正确
	 * 
	 * @param userName
	 * @return
	 */
	public int isUserNameRight(String username) {
		if (username == null || !username.matches("^1[34578]\\d{9}$")) {
			return USERNAME_FORMAT_ERROR;
		} else {
			return USERNAME_FORMAT_RIGHT;
		}
	}

	/**
	 * 判断用户输入的密码格式正确
	 * 
	 * @param password
	 * @return
	 */
	public int isRightPassword(String password) {
		if (password == null || !password.matches("^[0-9a-zA-Z]{6,16}$")) {
			return PASSWORD_FORMAT_ERROR;
		} else {
			return PASSWORD_FORMAT_RIGHT;
		}
	}

	/**
	 * 判断两次输入的密码是否一致
	 * 
	 * @param password
	 * @param repassword
	 * @return
	 */
	public int isConfirmRight(String password, String repassword) {
		if (password.equals(repassword)) {
			return CHECK_PASSWORD_RIGHT;
		} else {
			return CHECK_PASSWORD_ERROR;
		}
	}

	/**
	 * 判断手机号码格式是否正确
	 * 
	 * @param phone
	 * @return
	 */
	public int isPhoneRight(String phone) {
		if (phone == null || !phone.matches("^1[34578]\\d{9}$")) {
			return PHONE_FORMAT_ERROR;
		} else {
			return PHONE_FORMAT_RIGHT;
		}
	}
}
