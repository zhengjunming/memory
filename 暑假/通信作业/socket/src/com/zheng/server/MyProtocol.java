package com.zheng.server;

/**
 * Created by 郑俊铭 on 2017/7/14.
 */
public interface MyProtocol {
    int PROTOCOL = 2;
    String MSG_ROUND = "￥（"; // 信息标识符
    String USER_ROUND = "@）"; // 用户标识符
    String PRIVATE_ROUND = "【￥"; // 私聊标识符
    String SPLIT_SIGN = "乯蝦"; // 切割字符串标识符
}
