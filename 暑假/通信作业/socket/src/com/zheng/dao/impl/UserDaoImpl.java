package com.zheng.dao.impl;

import com.zheng.dao.UserDao;
import com.zheng.model.User;
import com.zheng.utils.JdbcUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 用户DAO层实现类
 * Created by 郑俊铭 on 2017/7/16.
 */
public class UserDaoImpl implements UserDao {
    private static JdbcUtils jdbcUtils = new JdbcUtils();

    @Override
    public boolean addUser(User user) {
        Connection conn = jdbcUtils.getConnection();
        PreparedStatement pstmt = null;
        try {
            String sql = "insert into user(username, password) values(?, ?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, user.getPassword());
            if (pstmt.executeUpdate() != 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            jdbcUtils.close(pstmt, conn);
        }
        return false;
    }

    @Override
    public User login(User user) {
        Connection conn = jdbcUtils.getConnection();
        PreparedStatement pstmt = null;
        ResultSet resultSet = null;
        User user1 = new User();
        try {
            String sql = "select * from user where username=? and password=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, user.getPassword());
            resultSet = pstmt.executeQuery();
            if (resultSet.next()) {
                user1.setId(resultSet.getInt(1));
                user1.setUsername(resultSet.getString(2));
                user1.setPassword(resultSet.getString(3));
                return user1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            jdbcUtils.close(pstmt, conn);
        }
        return null;
    }

    @Override
    public boolean usernameIsExist(String username) {
        Connection conn = jdbcUtils.getConnection();
        PreparedStatement pstmt = null;
        try {
            String sql = "select * from user where username=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, username);
            if (pstmt.executeQuery().next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            jdbcUtils.close(pstmt, conn);
        }
        return false;
    }

}
