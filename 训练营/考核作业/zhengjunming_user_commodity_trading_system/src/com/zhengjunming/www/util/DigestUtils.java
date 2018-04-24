package com.zhengjunming.www.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 密码加密工具类
 * 
 * @author 郑俊铭
 *
 */
public class DigestUtils {

	/**
	 * 进行MD5加密
	 * 
	 * @param plainText
	 * @return
	 */
	public static String md5(String plainText) {
		byte[] secretBytes = null;
		try {
			secretBytes = MessageDigest.getInstance("md5").digest(
					plainText.getBytes());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return new BigInteger(1, secretBytes).toString(16); // 把加密后的数组用16进制表示
	}

}
