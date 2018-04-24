package com.zhengjunming.www.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.ResultSet;
import com.zhengjunming.www.dao.AdminDao;
import com.zhengjunming.www.po.Admin;
import com.zhengjunming.www.po.Store;
import com.zhengjunming.www.po.other.StoreStatus;
import com.zhengjunming.www.util.DbUtil;

/**
 * 管理员Dao层实现类
 * 
 * @author 郑俊铭
 *
 */
public class AdminDaoImpl implements AdminDao {
	private static DbUtil dbUtil = new DbUtil();

	@Override
	public boolean usernameIsExist(String adminUsername) throws Exception {
		Connection con = dbUtil.getCon();
		PreparedStatement pstmt = null;
		try {
			String sql = "select * from admin where admin_name=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, adminUsername);
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
	public boolean adminLogin(Admin admin) throws Exception {
		Connection con = dbUtil.getCon();
		PreparedStatement pstmt = null;
		try {
			String sql = "select * from admin where admin_name=? and admin_password=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, admin.getAdminName());
			pstmt.setString(2, admin.getAdminPassword());
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
	public Admin adminData(Admin admin) throws Exception {
		Connection con = dbUtil.getCon();
		PreparedStatement pstmt = null;
		try {
			String sql = "select * from admin where admin_name=? and admin_password=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, admin.getAdminName());
			pstmt.setString(2, admin.getAdminPassword());
			ResultSet resultSet = (ResultSet) pstmt.executeQuery();
			if (resultSet.next()) {
				admin.setId(resultSet.getInt(1));
				admin.setAdminName(resultSet.getString(2));
				admin.setAdminPassword(resultSet.getString(3));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbUtil.close(pstmt, con);
		}
		return admin;
	}

	@Override
	public List<Store> getAllStoreInReview() throws Exception {
		List<Store> stores = new ArrayList<Store>();
		Connection con = dbUtil.getCon();
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		try {
			String sql = "select * from store where store_status = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, StoreStatus.UNDER_REVIEW.getName());
			resultSet = (ResultSet) pstmt.executeQuery();
			while (resultSet.next()) {
				Store store = new Store();
				store.setId(resultSet.getInt(1));
				store.setStoreUsername(resultSet.getString(2));
				store.setStorePassword(resultSet.getString(3));
				store.setStoreOwnerName(resultSet.getString(4));
				store.setPhone(resultSet.getString(5));
				store.setEmail(resultSet.getString(6));
				store.setStoreName(resultSet.getString(7));
				store.setStoreDescription(resultSet.getString(8));
				store.setCreateTime(resultSet.getTimestamp(9));
				store.setStoreStatus(resultSet.getString(10));
				stores.add(store);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbUtil.close(pstmt, con);
		}
		return stores;
	}

	@Override
	public void agreeOpenStore(String email) throws Exception {
		Connection con = dbUtil.getCon();
		PreparedStatement pstmt = null;
		try {
			String sql = "update store set store_status = ? where email = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, StoreStatus.SUSEESSFULLY_OPENED.getName());
			pstmt.setString(2, email);
			;
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbUtil.close(pstmt, con);
		}

	}

	@Override
	public void rejectOpenStore(String email) throws Exception {
		Connection con = dbUtil.getCon();
		PreparedStatement pstmt = null;
		try {
			String sql = "update store set store_status = ? where email = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, StoreStatus.BE_REJECTED.getName());
			pstmt.setString(2, email);
			;
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbUtil.close(pstmt, con);
		}
	}

	@Override
	public List<Store> getAllHadOpenStore() throws Exception {
		List<Store> stores = new ArrayList<Store>();
		Connection con = dbUtil.getCon();
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		try {
			String sql = "select * from store where store_status = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, StoreStatus.SUSEESSFULLY_OPENED.getName());
			resultSet = (ResultSet) pstmt.executeQuery();
			while (resultSet.next()) {
				Store store = new Store();
				store.setId(resultSet.getInt(1));
				store.setStoreUsername(resultSet.getString(2));
				store.setStorePassword(resultSet.getString(3));
				store.setStoreOwnerName(resultSet.getString(4));
				store.setPhone(resultSet.getString(5));
				store.setEmail(resultSet.getString(6));
				store.setStoreName(resultSet.getString(7));
				store.setStoreDescription(resultSet.getString(8));
				store.setCreateTime(resultSet.getTimestamp(9));
				store.setStoreStatus(resultSet.getString(10));
				stores.add(store);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbUtil.close(pstmt, con);
		}
		return stores;
	}

	@Override
	public void closeStore(String email) throws Exception {
		Connection con = dbUtil.getCon();
		PreparedStatement pstmt = null;
		try {
			String sql = "update store set store_status = ? where email = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, StoreStatus.BE_CLOSE.getName());
			pstmt.setString(2, email);
			;
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbUtil.close(pstmt, con);
		}
	}

	@Override
	public List<Store> getAllStoreBeClose() throws Exception {
		List<Store> stores = new ArrayList<Store>();
		Connection con = dbUtil.getCon();
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		try {
			String sql = "select * from store where store_status = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, StoreStatus.BE_CLOSE.getName());
			resultSet = (ResultSet) pstmt.executeQuery();
			while (resultSet.next()) {
				Store store = new Store();
				store.setId(resultSet.getInt(1));
				store.setStoreUsername(resultSet.getString(2));
				store.setStorePassword(resultSet.getString(3));
				store.setStoreOwnerName(resultSet.getString(4));
				store.setPhone(resultSet.getString(5));
				store.setEmail(resultSet.getString(6));
				store.setStoreName(resultSet.getString(7));
				store.setStoreDescription(resultSet.getString(8));
				store.setCreateTime(resultSet.getTimestamp(9));
				store.setStoreStatus(resultSet.getString(10));
				stores.add(store);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbUtil.close(pstmt, con);
		}
		return stores;
	}

	@Override
	public List<Store> getAllStoreBeReject() throws Exception {
		List<Store> stores = new ArrayList<Store>();
		Connection con = dbUtil.getCon();
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		try {
			String sql = "select * from store where store_status = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, StoreStatus.BE_REJECTED.getName());
			resultSet = (ResultSet) pstmt.executeQuery();
			while (resultSet.next()) {
				Store store = new Store();
				store.setId(resultSet.getInt(1));
				store.setStoreUsername(resultSet.getString(2));
				store.setStorePassword(resultSet.getString(3));
				store.setStoreOwnerName(resultSet.getString(4));
				store.setPhone(resultSet.getString(5));
				store.setEmail(resultSet.getString(6));
				store.setStoreName(resultSet.getString(7));
				store.setStoreDescription(resultSet.getString(8));
				store.setCreateTime(resultSet.getTimestamp(9));
				store.setStoreStatus(resultSet.getString(10));
				stores.add(store);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbUtil.close(pstmt, con);
		}
		return stores;
	}
}
