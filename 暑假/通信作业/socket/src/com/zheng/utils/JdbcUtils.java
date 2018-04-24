package com.zheng.utils;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 数据库连接工具类
 * Created by 郑俊铭 on 2017/7/16.
 */
public class JdbcUtils {
    private static Connection conn;
    private static ComboPooledDataSource ds = new ComboPooledDataSource();

    /**
     * 获取数据库连接
     *
     * @return 一个数据库连接
     */
    public Connection getConnection() {
        try {
            conn = ds.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    /**
     * 关闭数据库
     * @param stmt
     * @param conn
     */
    public void close(Statement stmt, Connection conn) {
        try {
            if (stmt != null) {
                stmt.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
