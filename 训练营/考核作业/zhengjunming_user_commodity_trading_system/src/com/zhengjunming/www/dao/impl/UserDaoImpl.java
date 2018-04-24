package com.zhengjunming.www.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.mysql.jdbc.ResultSet;
import com.zhengjunming.www.dao.UserDao;
import com.zhengjunming.www.po.User;
import com.zhengjunming.www.util.DbUtil;
import com.zhengjunming.www.util.DigestUtils;

/**
 * 用户DAO层实现类
 * 
 * @author 郑俊铭
 *
 */
public class UserDaoImpl implements UserDao {
	private static DbUtil dbUtil = new DbUtil();

	@Override
	public int addUser(User user) throws Exception {
		Connection con = dbUtil.getCon();
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			String sql = "insert into user(username, password, email, security_question, "
					+ "classified_answer, code, status) values(?, ?, ?, ?, ?, ?, ?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, user.getUsername());
			pstmt.setString(2, user.getPassword());
			pstmt.setString(3, user.getEmail());
			pstmt.setString(4, user.getSecurityQuestion());
			pstmt.setString(5, user.getClassifiedAnswer());
			pstmt.setString(6, user.getCode());
			pstmt.setInt(7, 0);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbUtil.close(pstmt, con);
		}
		return result;
	}

	@Override
	public boolean usernameIsExist(String username) throws Exception {
		Connection con = dbUtil.getCon();
		PreparedStatement pstmt = null;

		try {
			String sql = "select * from user where username=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, username);
			// 判断是否存在该用户
			if (!pstmt.executeQuery().next()) {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbUtil.close(pstmt, con);
		}
		return true;
	}

	@Override
	public boolean emailIsExist(String email) throws Exception {
		Connection con = dbUtil.getCon();
		PreparedStatement pstmt = null;

		try {
			String sql = "select * from user where email=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, email);
			// 判断是否存在该用户
			if (pstmt.executeQuery().next()) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbUtil.close(pstmt, con);
		}
		return false;
	}

	@Override
	public boolean update(User user, String newPassword) throws Exception {
		Connection con = dbUtil.getCon();
		PreparedStatement pstmt = null;
		int result = 0;

		try {
			String sql = "update user set password=? where id=?"; // 修改密码
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, newPassword);
			pstmt.setInt(2, user.getId());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbUtil.close(pstmt, con);
		}

		if (result != 0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean login(User user) throws Exception {
		Connection con = dbUtil.getCon();
		PreparedStatement pstmt = null;
		user.setPassword(DigestUtils.md5(user.getPassword()));
		try {
			String sql = "select * from user where username=? and password=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, user.getUsername());
			pstmt.setString(2, user.getPassword());
			if (pstmt.executeQuery().next()) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbUtil.close(pstmt, con);
		}
		return false;
	}

	@Override
	public User UserData(User user) throws Exception {
		Connection con = dbUtil.getCon();
		PreparedStatement pstmt = null;
		try {
			String sql = "select * from user where username=? and password=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, user.getUsername());
			pstmt.setString(2, user.getPassword());
			ResultSet resultSet = (ResultSet) pstmt.executeQuery();
			if (resultSet.next()) {
				user.setId(resultSet.getInt(1));
				user.setUsername(resultSet.getString(2));
				user.setPassword(resultSet.getString(3));
				user.setEmail(resultSet.getString(4));
				user.setSecurityQuestion(resultSet.getString(5));
				user.setClassifiedAnswer(resultSet.getString(6));
				user.setPhone(resultSet.getString(7));
				user.setAddress(resultSet.getString(8));
				user.setCode(resultSet.getString(9));
				user.setStatus(resultSet.getBoolean(10));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbUtil.close(pstmt, con);
		}
		return user;
	}

	@Override
	public void improveUserInfo(User user) throws Exception {
		Connection con = dbUtil.getCon();
		PreparedStatement pstmt = null;
		try {
			String sql = "update user set email=?,security_question=?,classified_answer=?,phone=?,address=? where id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, user.getEmail());
			pstmt.setString(2, user.getSecurityQuestion());
			pstmt.setString(3, user.getClassifiedAnswer());
			pstmt.setString(4, user.getPhone());
			pstmt.setString(5, user.getAddress());
			pstmt.setInt(6, user.getId());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbUtil.close(pstmt, con);
		}
	}

	@Override
	public User getUserInfo(int id) throws Exception {
		Connection con = dbUtil.getCon();
		PreparedStatement pstmt = null;
		User user = new User();
		try {
			String sql = "select * from user where id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, id);
			ResultSet resultSet = (ResultSet) pstmt.executeQuery();
			if (resultSet.next()) {
				user.setId(resultSet.getInt(1));
				user.setUsername(resultSet.getString(2));
				user.setPassword(resultSet.getString(3));
				user.setEmail(resultSet.getString(4));
				user.setSecurityQuestion(resultSet.getString(5));
				user.setClassifiedAnswer(resultSet.getString(6));
				user.setPhone(resultSet.getString(7));
				user.setAddress(resultSet.getString(8));
				user.setCode(resultSet.getString(9));
				user.setStatus(resultSet.getBoolean(10));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbUtil.close(pstmt, con);
		}
		return user;
	}

	@Override
	public User queryUserByCode(String code) throws Exception {
		User user = new User();
		Connection con = dbUtil.getCon();
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		try {
			String sql = "select * from user where code = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, code);
			resultSet = (ResultSet) pstmt.executeQuery();
			if (resultSet.next()) {
				user.setUsername(resultSet.getString("username"));
				user.setId(resultSet.getInt(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbUtil.close(pstmt, con);
		}
		return user;
	}

	@Override
	public void setStatus(int id) throws Exception {
		Connection con = dbUtil.getCon();
		PreparedStatement pstmt = null;
		try {
			String sql = "update user set status = ? where id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, 1);
			pstmt.setInt(2, id);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbUtil.close(pstmt, con);
		}
	}

	@Override
	public User getUserByUsername(String username) throws Exception {
		User user = new User();
		Connection con = dbUtil.getCon();
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		try {
			String sql = "select * from user where username = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, username);
			resultSet = (ResultSet) pstmt.executeQuery();
			if (resultSet.next()) {
				user.setId(resultSet.getInt(1));
				user.setUsername(resultSet.getString(2));
				user.setPassword(resultSet.getString(3));
				user.setEmail(resultSet.getString(4));
				user.setSecurityQuestion(resultSet.getString(5));
				user.setClassifiedAnswer(resultSet.getString(6));
				user.setPhone(resultSet.getString(7));
				user.setAddress(resultSet.getString(8));
				user.setCode(resultSet.getString(9));
				user.setStatus(resultSet.getBoolean(10));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbUtil.close(pstmt, con);
		}
		return user;
	}

}
