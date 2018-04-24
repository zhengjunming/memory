package com.zhengjunming.www.util;

import java.util.UUID;

/**
 * 文件上传工具类
 * 
 * @author 郑俊铭
 */
public class UploadUtils {

	/**
	 * 切掉fileName路径
	 * 
	 * @param fileName
	 * @return
	 */
	public static String subFileName(String fileName) {
		if (fileName.contains("\\")) {
			return fileName.substring(fileName.lastIndexOf("\\"));
		}
		return fileName;
	}

	/**
	 * 生成唯一文件名
	 * 
	 * @param fileName
	 * @return
	 */
	public static String generateUUIDName(String fileName) {
		String ext = fileName.substring(fileName.lastIndexOf("."));
		return UUID.randomUUID().toString() + ext;
	}

	/**
	 * 生成随机目录
	 * 
	 * @param uuidName
	 * @return
	 */
	public static String generateRandomDir(String uuidName) {
		int hashCode = uuidName.hashCode();
		int d1 = hashCode & 0xf;
		int d2 = (hashCode >> 4) & 0xf;
		return "/" + d1 + "/" + d2;
	}
}
