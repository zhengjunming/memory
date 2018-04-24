package com.zhengjunming.www.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.ResultSet;
import com.zhengjunming.www.dao.GoodsDao;
import com.zhengjunming.www.po.Goods;
import com.zhengjunming.www.po.Store;
import com.zhengjunming.www.po.other.GoodsStatus;
import com.zhengjunming.www.util.DbUtil;

/**
 * 商品实现接口类
 * 
 * @author 郑俊铭
 *
 */
public class GoodsDaoImpl implements GoodsDao {
	private static DbUtil dbUtil = new DbUtil();

	@Override
	public List<Goods> ListGoods(int pagesize, int page) throws Exception {
		List<Goods> goodses = new ArrayList<Goods>();
		Connection con = dbUtil.getCon();
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		StoreDaoImpl daoImpl = new StoreDaoImpl();
		try {
			String sql = "select * from goods where status = ? order by sell_count desc limit "
					+ (page - 1) * pagesize + "," + pagesize;
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, GoodsStatus.HAS_BEEN_SHELVED.getName());
			resultSet = (ResultSet) pstmt.executeQuery();
			while (resultSet.next()) {
				Goods goods = new Goods();
				goods.setId(resultSet.getInt(1));
				goods.setGoodsName(resultSet.getString(2));
				goods.setGoodsDescription(resultSet.getString(3));
				goods.setCtreteTime(resultSet.getTimestamp(4));
				goods.setSellCount(resultSet.getInt(5));
				goods.setMarketPrice(resultSet.getString(6));
				goods.setSellPrice(resultSet.getString(7));
				goods.setGoodsDiscount(resultSet.getString(8));
				goods.setQuantity(resultSet.getInt(9));
				goods.setGoodsPicture(resultSet.getString(10));
				goods.setGoodsStatus(resultSet.getString(11));
				goods.setStore(daoImpl.getStoreInfo(resultSet.getInt(12)));
				goodses.add(goods);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbUtil.close(pstmt, con);
		}
		return goodses;
	}

	@Override
	public int GetGoodsCount() throws Exception {
		Connection con = dbUtil.getCon();
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		int count = 0;
		try {
			String sql = "select * from goods where status = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, GoodsStatus.HAS_BEEN_SHELVED.getName());
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
	public List<Goods> ListStoreGoods(int pageSize, int page, Store store)
			throws Exception {
		List<Goods> goodses = new ArrayList<Goods>();
		Connection con = dbUtil.getCon();
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		StoreDaoImpl daoImpl = new StoreDaoImpl();
		try {
			String sql = "select * from goods where store_id = ? order by sell_count desc limit "
					+ (page - 1) * pageSize + "," + pageSize;
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, store.getId());
			resultSet = (ResultSet) pstmt.executeQuery();
			while (resultSet.next()) {
				Goods goods = new Goods();
				goods.setId(resultSet.getInt(1));
				goods.setGoodsName(resultSet.getString(2));
				goods.setGoodsDescription(resultSet.getString(3));
				goods.setCtreteTime(resultSet.getTimestamp(4));
				goods.setSellCount(resultSet.getInt(5));
				goods.setMarketPrice(resultSet.getString(6));
				goods.setSellPrice(resultSet.getString(7));
				goods.setGoodsDiscount(resultSet.getString(8));
				goods.setQuantity(resultSet.getInt(9));
				goods.setGoodsPicture(resultSet.getString(10));
				goods.setGoodsStatus(resultSet.getString(11));
				goods.setStore(daoImpl.getStoreInfo(resultSet.getInt(12)));
				goodses.add(goods);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbUtil.close(pstmt, con);
		}
		return goodses;
	}

	@Override
	public int GetStoreGoodsCount(Store store) throws Exception {
		Connection con = dbUtil.getCon();
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		int count = 0;
		try {
			String sql = "select * from goods where store_id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, store.getId());
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
	public List<Goods> ListNotUnderShelvedGoods(int pageSize, int page,
			Store store) throws Exception {
		List<Goods> goodses = new ArrayList<Goods>();
		Connection con = dbUtil.getCon();
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		StoreDaoImpl daoImpl = new StoreDaoImpl();
		try {
			String sql = "select * from goods where store_id = ? and status <> ? order by sell_count desc limit "
					+ (page - 1) * pageSize + "," + pageSize;
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, store.getId());
			pstmt.setString(2, GoodsStatus.HAS_BEEN_UNDER_SHELVED.getName());
			resultSet = (ResultSet) pstmt.executeQuery();
			while (resultSet.next()) {
				Goods goods = new Goods();
				goods.setId(resultSet.getInt(1));
				goods.setGoodsName(resultSet.getString(2));
				goods.setGoodsDescription(resultSet.getString(3));
				goods.setCtreteTime(resultSet.getTimestamp(4));
				goods.setSellCount(resultSet.getInt(5));
				goods.setMarketPrice(resultSet.getString(6));
				goods.setSellPrice(resultSet.getString(7));
				goods.setGoodsDiscount(resultSet.getString(8));
				goods.setQuantity(resultSet.getInt(9));
				goods.setGoodsPicture(resultSet.getString(10));
				goods.setGoodsStatus(resultSet.getString(11));
				goods.setStore(daoImpl.getStoreInfo(resultSet.getInt(12)));
				goodses.add(goods);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbUtil.close(pstmt, con);
		}
		return goodses;
	}

	@Override
	public int GetNotUnderShelvedGoodsCount(Store store) throws Exception {
		Connection con = dbUtil.getCon();
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		int count = 0;
		try {
			String sql = "select * from goods where store_id = ? and status <> ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, store.getId());
			pstmt.setString(2, GoodsStatus.HAS_BEEN_UNDER_SHELVED.getName());
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
	public Goods getGoodsById(int id) throws Exception {
		Goods goods = new Goods();
		Connection con = dbUtil.getCon();
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		StoreDaoImpl daoImpl = new StoreDaoImpl();
		try {
			String sql = "select * from goods where id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, id);
			resultSet = (ResultSet) pstmt.executeQuery();
			if (resultSet.next()) {
				goods.setId(resultSet.getInt(1));
				goods.setGoodsName(resultSet.getString(2));
				goods.setGoodsDescription(resultSet.getString(3));
				goods.setCtreteTime(resultSet.getTimestamp(4));
				goods.setSellCount(resultSet.getInt(5));
				goods.setMarketPrice(resultSet.getString(6));
				goods.setSellPrice(resultSet.getString(7));
				goods.setGoodsDiscount(resultSet.getString(8));
				goods.setQuantity(resultSet.getInt(9));
				goods.setGoodsPicture(resultSet.getString(10));
				goods.setGoodsStatus(resultSet.getString(11));
				goods.setStore(daoImpl.getStoreInfo(resultSet.getInt(12)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbUtil.close(pstmt, con);
		}
		return goods;
	}

	@Override
	public void updateSellCountAndQuantity(int count, Goods goods)
			throws Exception {
		Connection con = dbUtil.getCon();
		PreparedStatement pstmt = null;
		try {
			String sql = "update goods set sell_count = ?, quantity = ? where id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, goods.getSellCount() + count);
			pstmt.setInt(2, goods.getQuantity() - count);
			pstmt.setInt(3, goods.getId());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbUtil.close(pstmt, con);
		}

	}

	@Override
	public List<Goods> queryGoodsByKey(int pageSize, int page, String key)
			throws Exception {
		List<Goods> goodses = new ArrayList<Goods>();
		Connection con = dbUtil.getCon();
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		StoreDaoImpl daoImpl = new StoreDaoImpl();
		try {
			String sql = "select * from goods where goods_name LIKE ? and status = ? order by sell_count desc limit "
					+ (page - 1) * pageSize + "," + pageSize;
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "%" + key + "%");
			pstmt.setString(2, GoodsStatus.HAS_BEEN_SHELVED.getName());
			resultSet = (ResultSet) pstmt.executeQuery();
			while (resultSet.next()) {
				Goods goods = new Goods();
				goods.setId(resultSet.getInt(1));
				goods.setGoodsName(resultSet.getString(2));
				goods.setGoodsDescription(resultSet.getString(3));
				goods.setCtreteTime(resultSet.getTimestamp(4));
				goods.setSellCount(resultSet.getInt(5));
				goods.setMarketPrice(resultSet.getString(6));
				goods.setSellPrice(resultSet.getString(7));
				goods.setGoodsDiscount(resultSet.getString(8));
				goods.setQuantity(resultSet.getInt(9));
				goods.setGoodsPicture(resultSet.getString(10));
				goods.setGoodsStatus(resultSet.getString(11));
				goods.setStore(daoImpl.getStoreInfo(resultSet.getInt(12)));
				goodses.add(goods);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbUtil.close(pstmt, con);
		}
		return goodses;
	}

	@Override
	public int GetGoodsCountByKey(String key) throws Exception {
		Connection con = dbUtil.getCon();
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		int count = 0;
		try {
			String sql = "select * from goods where goods_name LIKE ? and status = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "%" + key + "%");
			pstmt.setString(2, GoodsStatus.HAS_BEEN_SHELVED.getName());
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
	public List<Goods> queryGoodsByKeyAndStoreId(int pageSize, int page,
			String key, int storeId) throws Exception {
		List<Goods> goodses = new ArrayList<Goods>();
		Connection con = dbUtil.getCon();
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		StoreDaoImpl daoImpl = new StoreDaoImpl();
		try {
			String sql = "select * from goods where goods_name LIKE ? and status = ? and store_id = ? order by sell_count desc limit "
					+ (page - 1) * pageSize + "," + pageSize;
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "%" + key + "%");
			pstmt.setString(2, GoodsStatus.HAS_BEEN_SHELVED.getName());
			pstmt.setInt(3, storeId);
			resultSet = (ResultSet) pstmt.executeQuery();
			while (resultSet.next()) {
				Goods goods = new Goods();
				goods.setId(resultSet.getInt(1));
				goods.setGoodsName(resultSet.getString(2));
				goods.setGoodsDescription(resultSet.getString(3));
				goods.setCtreteTime(resultSet.getTimestamp(4));
				goods.setSellCount(resultSet.getInt(5));
				goods.setMarketPrice(resultSet.getString(6));
				goods.setSellPrice(resultSet.getString(7));
				goods.setGoodsDiscount(resultSet.getString(8));
				goods.setQuantity(resultSet.getInt(9));
				goods.setGoodsPicture(resultSet.getString(10));
				goods.setGoodsStatus(resultSet.getString(11));
				goods.setStore(daoImpl.getStoreInfo(resultSet.getInt(12)));
				goodses.add(goods);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbUtil.close(pstmt, con);
		}
		return goodses;
	}

	@Override
	public int GetGoodsCountByKeyAndStoreId(String key, int storeId)
			throws Exception {
		Connection con = dbUtil.getCon();
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		int count = 0;
		try {
			String sql = "select * from goods where goods_name LIKE ? and status = ? and store_id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "%" + key + "%");
			pstmt.setString(2, GoodsStatus.HAS_BEEN_SHELVED.getName());
			pstmt.setInt(3, storeId);
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
	public void updateGoodsStatus(String status, int id) throws Exception {
		Connection con = dbUtil.getCon();
		PreparedStatement pstmt = null;
		try {
			String sql = "update goods set status = ? where id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, status);
			pstmt.setInt(2, id);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbUtil.close(pstmt, con);
		}
	}

	@Override
	public List<Goods> storeQueryGoodsByKeyAndStoreId(int pageSize, int page,
			String key, int storeId) throws Exception {
		List<Goods> goodses = new ArrayList<Goods>();
		Connection con = dbUtil.getCon();
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		StoreDaoImpl daoImpl = new StoreDaoImpl();
		try {
			String sql = "select * from goods where goods_name LIKE ? and store_id = ? order by sell_count desc limit "
					+ (page - 1) * pageSize + "," + pageSize;
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "%" + key + "%");
			pstmt.setInt(2, storeId);
			resultSet = (ResultSet) pstmt.executeQuery();
			while (resultSet.next()) {
				Goods goods = new Goods();
				goods.setId(resultSet.getInt(1));
				goods.setGoodsName(resultSet.getString(2));
				goods.setGoodsDescription(resultSet.getString(3));
				goods.setCtreteTime(resultSet.getTimestamp(4));
				goods.setSellCount(resultSet.getInt(5));
				goods.setMarketPrice(resultSet.getString(6));
				goods.setSellPrice(resultSet.getString(7));
				goods.setGoodsDiscount(resultSet.getString(8));
				goods.setQuantity(resultSet.getInt(9));
				goods.setGoodsPicture(resultSet.getString(10));
				goods.setGoodsStatus(resultSet.getString(11));
				goods.setStore(daoImpl.getStoreInfo(resultSet.getInt(12)));
				goodses.add(goods);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbUtil.close(pstmt, con);
		}
		return goodses;
	}

	@Override
	public int storeGetGoodsCountByKeyAndStoreId(String key, int storeId)
			throws Exception {
		Connection con = dbUtil.getCon();
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		int count = 0;
		try {
			String sql = "select * from goods where goods_name LIKE ? and store_id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "%" + key + "%");
			pstmt.setInt(2, storeId);
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
	public int getGoodsCountByStatusAndStoreId(String status, int id)
			throws Exception {
		Connection con = dbUtil.getCon();
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		int count = 0;
		try {
			String sql = "select * from goods where store_id = ? and status = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, id);
			pstmt.setString(2, status);
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
	public List<Goods> ListGoodsByStatusAndStoreId(int pageSize, int page,
			String status, int id) throws Exception {
		List<Goods> goodses = new ArrayList<Goods>();
		Connection con = dbUtil.getCon();
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		StoreDaoImpl daoImpl = new StoreDaoImpl();
		try {
			String sql = "select * from goods where store_id = ? and status = ? order by sell_count desc limit "
					+ (page - 1) * pageSize + "," + pageSize;
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, id);
			pstmt.setString(2, status);
			resultSet = (ResultSet) pstmt.executeQuery();
			while (resultSet.next()) {
				Goods goods = new Goods();
				goods.setId(resultSet.getInt(1));
				goods.setGoodsName(resultSet.getString(2));
				goods.setGoodsDescription(resultSet.getString(3));
				goods.setCtreteTime(resultSet.getTimestamp(4));
				goods.setSellCount(resultSet.getInt(5));
				goods.setMarketPrice(resultSet.getString(6));
				goods.setSellPrice(resultSet.getString(7));
				goods.setGoodsDiscount(resultSet.getString(8));
				goods.setQuantity(resultSet.getInt(9));
				goods.setGoodsPicture(resultSet.getString(10));
				goods.setGoodsStatus(resultSet.getString(11));
				goods.setStore(daoImpl.getStoreInfo(resultSet.getInt(12)));
				goodses.add(goods);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbUtil.close(pstmt, con);
		}
		return goodses;
	}

}
