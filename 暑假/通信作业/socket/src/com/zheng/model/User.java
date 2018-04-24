package com.zheng.model;

import java.util.List;

/**
 * Created by 郑俊铭 on 2017/7/16.
 */
public class User {
    private int id; // 用户id
    private String username; // 用户名称
    private String password; // 用户密码

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
