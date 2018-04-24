package com.zhengjunming.www.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * 数据库连接工具类
 * 
 * @author 郑俊铭
 *
 */
public class DbUtil {
	// 数据库地址
	private static String dbUrl = "jdbc:mysql://localhost:3306/user_commodity_trading_system";
	// 用户名
	private static String dbUserName = "root";
	// 密码
	private static String dbPassword = "970228";
	// 驱动名称
	private static String jdbcName = "com.mysql.jdbc.Driver";

	/**
	 * 获取数据库连接
	 * 
	 * @return
	 * @throws Exception
	 */
	public Connection getCon() throws Exception {
		try {
			Class.forName(jdbcName);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}

		Connection con = null;
		try {
			con = (Connection) DriverManager.getConnection(dbUrl, dbUserName,
					dbPassword);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	}

	/**
	 * 关闭连接
	 * 
	 * @param stmt
	 * @param con
	 * @throws Exception
	 */
	public void close(PreparedStatement pstmt, java.sql.Connection con)
			throws Exception {
		if (pstmt != null) {
			pstmt.close();
			if (con != null) {
				con.close();
			}
		}
	}
}
