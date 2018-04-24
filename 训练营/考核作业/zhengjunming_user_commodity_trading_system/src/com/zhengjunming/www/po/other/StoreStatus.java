package com.zhengjunming.www.po.other;

/**
 * 店铺状态枚举类
 * 
 * @author 郑俊铭
 *
 */
public enum StoreStatus {
	UNDER_REVIEW {
		public String getName() {
			return "审核中";
		}
	},
	BE_REJECTED {
		public String getName() {
			return "被拒绝开店";
		}
	},
	SUSEESSFULLY_OPENED {
		public String getName() {
			return "成功开店";
		}
	},
	BE_CLOSE {
		public String getName() {
			return "被管理员强制关闭";
		}
	};
	public abstract String getName();
}
