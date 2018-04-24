package com.zhengjunming.www.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.ResultSet;
import com.zhengjunming.www.dao.StoreDao;
import com.zhengjunming.www.po.Goods;
import com.zhengjunming.www.po.Store;
import com.zhengjunming.www.po.other.GoodsStatus;
import com.zhengjunming.www.po.other.StoreStatus;
import com.zhengjunming.www.util.DbUtil;
import com.zhengjunming.www.util.DigestUtils;

/**
 * 店家DAO层实现类
 * 
 * @author 郑俊铭
 *
 */
public class StoreDaoImpl implements StoreDao {
	private static DbUtil dbUtil = new DbUtil();

	@Override
	public void addStore(Store store) throws Exception {
		Connection con = dbUtil.getCon();
		PreparedStatement pstmt = null;
		try {
			String sql = "insert into store(store_username, store_password, store_owner_name, phone, email, store_name, store_description, create_time, store_status) values (?,?,?,?,?,?,?,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, store.getStoreUsername());
			pstmt.setString(2, store.getStorePassword());
			pstmt.setString(3, store.getStoreOwnerName());
			pstmt.setString(4, store.getPhone());
			pstmt.setString(5, store.getEmail());
			pstmt.setString(6, store.getStoreName());
			pstmt.setString(7, store.getStoreDescription());
			pstmt.setTimestamp(8, new Timestamp(System.currentTimeMillis()));
			pstmt.setString(9, StoreStatus.UNDER_REVIEW.getName());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbUtil.close(pstmt, con);
		}

	}

	@Override
	public boolean usernameIsExist(String storeUsername) throws Exception {
		Connection con = dbUtil.getCon();
		PreparedStatement pstmt = null;

		try {
			String sql = "select * from store where store_username=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, storeUsername);
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
	public boolean emailIsExist(String email) throws Exception {
		Connection con = dbUtil.getCon();
		PreparedStatement pstmt = null;

		try {
			String sql = "select * from store where email=?";
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
	public boolean storeLogin(Store store) throws Exception {
		Connection con = dbUtil.getCon();
		PreparedStatement pstmt = null;
		store.setStorePassword(DigestUtils.md5(store.getStorePassword()));
		try {
			String sql = "select * from store where store_username=? and store_password=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, store.getStoreUsername());
			pstmt.setString(2, store.getStorePassword());
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
	public boolean updatePassword(Store store, String newPassword)
			throws Exception {
		Connection con = dbUtil.getCon();
		PreparedStatement pstmt = null;
		int result = 0;

		try {
			String sql = "update store set store_password=? where id=?"; // 修改密码
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, newPassword);
			pstmt.setInt(2, store.getId());
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
	public Store storeData(Store store) throws Exception {
		Connection con = dbUtil.getCon();
		PreparedStatement pstmt = null;
		try {
			String sql = "select * from store where store_username=? and store_password=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, store.getStoreUsername());
			pstmt.setString(2, store.getStorePassword());
			ResultSet resultSet = (ResultSet) pstmt.executeQuery();
			if (resultSet.next()) {
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
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbUtil.close(pstmt, con);
		}
		return store;
	}

	@Override
	public void addGoods(Goods goods, Store store) throws Exception {
		Connection con = dbUtil.getCon();
		PreparedStatement pstmt = null;
		try {
			String sql = "insert into goods(goods_name, goods_description, "
					+ "create_time, sell_count, market_price, sell_price, "
					+ "goods_discount, quantity, picture,status, store_id) "
					+ "values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, goods.getGoodsName());
			pstmt.setString(2, goods.getGoodsDescription());
			pstmt.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
			pstmt.setInt(4, 0);
			pstmt.setString(5, goods.getMarketPrice());
			pstmt.setString(6, goods.getSellPrice());
			pstmt.setString(7, goods.getGoodsDiscount());
			pstmt.setInt(8, goods.getQuantity());
			pstmt.setString(9, goods.getGoodsPicture());
			pstmt.setString(10, GoodsStatus.HAS_BEEN_SHELVED.getName());
			pstmt.setInt(11, store.getId());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbUtil.close(pstmt, con);
		}

	}

	@Override
	public void modifyGoodsPicture(Goods goods) throws Exception {
		Connection con = dbUtil.getCon();
		PreparedStatement pstmt = null;
		try {
			String sql = "update goods set picture = ? where id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, goods.getGoodsPicture());
			pstmt.setInt(2, goods.getId());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbUtil.close(pstmt, con);
		}
	}

	@Override
	public void modifyGoodsInfo(Goods goods) throws Exception {
		Connection con = dbUtil.getCon();
		PreparedStatement pstmt = null;
		try {
			String sql = "update goods set goods_name = ?, goods_description = ?, "
					+ "market_price = ?, sell_price = ?, "
					+ "goods_discount = ?, quantity = ?, picture = ? where id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, goods.getGoodsName());
			pstmt.setString(2, goods.getGoodsDescription());
			pstmt.setString(3, goods.getMarketPrice());
			pstmt.setString(4, goods.getSellPrice());
			pstmt.setString(5, goods.getGoodsDiscount());
			pstmt.setInt(6, goods.getQuantity());
			pstmt.setString(7, goods.getGoodsPicture());
			pstmt.setInt(8, goods.getId());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbUtil.close(pstmt, con);
		}
	}

	@Override
	public void underShelvedGoods(int id) throws Exception {
		Connection con = dbUtil.getCon();
		PreparedStatement pstmt = null;
		try {
			String sql = "update goods set status = ? where id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, GoodsStatus.HAS_BEEN_UNDER_SHELVED.getName());
			pstmt.setInt(2, id);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbUtil.close(pstmt, con);
		}

	}

	@Override
	public List<String> queryEmail(Store store) throws Exception {
		List<String> list = new ArrayList<String>();
		Connection con = dbUtil.getCon();
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		try {
			String sql = "select email from store where id <> ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, store.getId());
			resultSet = (ResultSet) pstmt.executeQuery();
			while (resultSet.next()) {
				list.add(resultSet.getString(1));
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbUtil.close(pstmt, con);
		}
		return null;
	}

	@Override
	public List<String> queryPhone(Store store) throws Exception {
		List<String> list = new ArrayList<String>();
		Connection con = dbUtil.getCon();
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		try {
			String sql = "select phone from store where id <> ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, store.getId());
			resultSet = (ResultSet) pstmt.executeQuery();
			while (resultSet.next()) {
				list.add(resultSet.getString(1));
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbUtil.close(pstmt, con);
		}
		return null;
	}

	@Override
	public void improveStoreInfo(Store store, String storeOwnerName,
			String phone, String email, String storeName,
			String storeDescription) throws Exception {
		Connection con = dbUtil.getCon();
		PreparedStatement pstmt = null;
		try {
			String sql = "update store set store_owner_name = ?, "
					+ "phone = ?, email = ?, store_name = ?, "
					+ "store_description = ? where id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, storeOwnerName);
			pstmt.setString(2, phone);
			pstmt.setString(3, email);
			pstmt.setString(4, storeName);
			pstmt.setString(5, storeDescription);
			pstmt.setInt(6, store.getId());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbUtil.close(pstmt, con);
		}

	}

	@Override
	public Store getStoreInfo(int id) throws Exception {
		Connection con = dbUtil.getCon();
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		try {
			String sql = "select * from store where id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, id);
			resultSet = (ResultSet) pstmt.executeQuery();
			if (resultSet.next()) {
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
				return store;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbUtil.close(pstmt, con);
		}
		return null;
	}

	@Override
	public int getStoreCountByKey(int pageSize, int page, String key)
			throws Exception {
		Connection con = dbUtil.getCon();
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		int count = 0;
		try {
			String sql = "select * from store where store_name LIKE ? and store_status = ? order by id desc limit "
					+ (page - 1) * pageSize + "," + pageSize;
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "%" + key + "%");
			pstmt.setString(2, StoreStatus.SUSEESSFULLY_OPENED.getName());
			resultSet = (ResultSet) pstmt.executeQuery();
			while (resultSet.next()) {
				count++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbUtil.close(pstmt, con);
		}
		return count;
	}

	@Override
	public List<Store> queryStoreByKey(int pageSize, int page, String key)
			throws Exception {
		Connection con = dbUtil.getCon();
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		List<Store> stores = new ArrayList<Store>();
		try {
			String sql = "select * from store where store_name LIKE ? and store_status = ? order by id desc limit "
					+ (page - 1) * pageSize + "," + pageSize;
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "%" + key + "%");
			pstmt.setString(2, StoreStatus.SUSEESSFULLY_OPENED.getName());
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
