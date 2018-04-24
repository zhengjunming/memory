package com.zhengjunming.www.po.other;

/**
 * 商品状态枚举类
 * 
 * @author 郑俊铭
 *
 */
public enum GoodsStatus {
	OUT_OF_STOCK {
		public String getName() {
			return "断货中";
		}
	},
	HAS_BEEN_SHELVED {
		public String getName() {
			return "已上架";
		}
	},
	HAS_BEEN_UNDER_SHELVED {
		public String getName() {
			return "已下架";
		}
	};
	public abstract String getName();
}
