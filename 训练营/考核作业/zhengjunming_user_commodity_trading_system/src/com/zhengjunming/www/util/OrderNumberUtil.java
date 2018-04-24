package com.zhengjunming.www.util;

import java.util.UUID;

/**
 * 生成订单编号工具类
 * 
 * @author 郑俊铭
 *
 */
public class OrderNumberUtil {
	public static String getOrderIdByUUId() {
		int machineId = 1;
		int hashCodeV = UUID.randomUUID().toString().hashCode();
		if (hashCodeV < 0) {// 有可能是负数
			hashCodeV = -hashCodeV;
		}
		return machineId + String.format("%015d", hashCodeV);
	}
}
