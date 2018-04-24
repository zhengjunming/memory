package com.zhengjunming.www.po.other;

/**
 * 订单状态枚举类
 * 
 * @author 郑俊铭
 *
 */
public enum OrderStatus {
	SHIPPED {
		public String getName() {
			return "已发货";
		}
	},
	RECEIVED {
		public String getName() {
			return "已收货";
		}
	},
	CANCEL_ORDER {
		public String getName() {
			return "取消订单";
		}
	},
	UNDER_REVIEW {
		public String getName() {
			return "审核中";
		}
	},
	EVALUATED {
		public String getName() {
			return "已评价";
		}
	};
	public abstract String getName();
}
