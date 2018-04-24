package com.gdutjunming.www.dao;

import com.gdutjunming.www.model.Admin;
import org.apache.ibatis.annotations.Param;

/**
 * @author 郑俊铭
 * Date: 2017/11/19
 * Time: 17:38
 * No struggle, talent how to match the willfulness.
 * Description: 管理员登录的DAO层
 */
public interface LoginDao {
    /**
     * 根据用户名查找管理员用户，可判断是否存在该用户
     *
     * @param username 管理员用户名
     * @return Admin（一个管理员实体类）
     */
    Admin findAdminByUsername(@Param("username") String username);
}
