package com.zheng.dao;

import com.zheng.model.User;

/**
 * 用户DAO层
 * Created by 郑俊铭 on 2017/7/16.
 */
public interface UserDao {

    /**
     * 添加用户
     *
     * @param user 用户
     * @return 成功返回true，失败返回false
     */
    boolean addUser(User user);

    /**
     * 用户登录
     *
     * @param user 用户
     * @return 用户信息
     */
    User login(User user);

    /**
     * 判断用户名是否已经存在
     *
     * @param username 用户名
     * @return 存在返回true，失败返回false
     */
    boolean usernameIsExist(String username);
}
