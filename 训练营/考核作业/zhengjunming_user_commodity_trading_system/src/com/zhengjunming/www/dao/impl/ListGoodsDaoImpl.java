package com.zhengjunming.www.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.ResultSet;
import com.zhengjunming.www.dao.ListGoodsDao;
import com.zhengjunming.www.po.Goods;
import com.zhengjunming.www.po.Store;
import com.zhengjunming.www.po.other.GoodsStatus;
import com.zhengjunming.www.util.DbUtil;

/**
 * 列举商品实现接口类
 * 
 * @author 郑俊铭
 *
 */
public class ListGoodsDaoImpl implements ListGoodsDao {
	private static DbUtil dbUtil = new DbUtil();

	@Override
	public List<Goods> ListGoods(int pagesize, int page) throws Exception {
		List<Goods> goodses = new ArrayList<Goods>();
		Connection con = dbUtil.getCon();
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		try {
			String sql = "select * from goods order by sell_count desc limit "
					+ (page - 1) * pagesize + "," + pagesize;
			pstmt = con.prepareStatement(sql);
			resultSet = (ResultSet) pstmt.executeQuery();
			while (resultSet.next()) {
				int id = resultSet.getInt(1);
				String goodsName = resultSet.getString(2);
				String goodsDescription = resultSet.getString(3);
				Timestamp ctreteTime = resultSet.getTimestamp(4);
				Integer sellCount = resultSet.getInt(5);
				String marketPrice = resultSet.getString(6);
				String sellPrice = resultSet.getString(7);
				String goodsDiscount = resultSet.getString(8);
				Integer quantity = resultSet.getInt(9);
				String goodsPicture = resultSet.getString(10);
				Goods goods = new Goods(id, goodsName, goodsDescription,
						ctreteTime, sellCount, marketPrice, sellPrice,
						goodsDiscount, quantity, goodsPicture);
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
			String sql = "select * from goods";
			pstmt = con.prepareStatement(sql);
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
	public List<Goods> ListStoreGoods(int pageSize, int page, Store store) throws Exception {
		List<Goods> goodses = new ArrayList<Goods>();
		Connection con = dbUtil.getCon();
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		try {
			String sql = "select * from goods where store_id = ? order by sell_count desc limit "
					+ (page - 1) * pageSize + "," + pageSize;
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, store.getId());
			resultSet = (ResultSet) pstmt.executeQuery();
			while (resultSet.next()) {
				int id = resultSet.getInt(1);
				String goodsName = resultSet.getString(2);
				String goodsDescription = resultSet.getString(3);
				Timestamp ctreteTime = resultSet.getTimestamp(4);
				Integer sellCount = resultSet.getInt(5);
				String marketPrice = resultSet.getString(6);
				String sellPrice = resultSet.getString(7);
				String goodsDiscount = resultSet.getString(8);
				Integer quantity = resultSet.getInt(9);
				String goodsPicture = resultSet.getString(10);
				String goodsStatus = resultSet.getString(11);
				Goods goods = new Goods(id, goodsName, goodsDescription,
						ctreteTime, sellCount, marketPrice, sellPrice,
						goodsDiscount, quantity, goodsPicture);
				goods.setGoodsStatus(goodsStatus);
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
	public List<Goods> ListHasShelvedGoods(int pageSize, int page, Store store)
			throws Exception {
		List<Goods> goodses = new ArrayList<Goods>();
		Connection con = dbUtil.getCon();
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		try {
			String sql = "select * from goods where store_id = ? and status = ? order by sell_count desc limit "
					+ (page - 1) * pageSize + "," + pageSize;
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, store.getId());
			pstmt.setString(2, GoodsStatus.HAS_BEEN_SHELVED.getName());
			resultSet = (ResultSet) pstmt.executeQuery();
			while (resultSet.next()) {
				int id = resultSet.getInt(1);
				String goodsName = resultSet.getString(2);
				String goodsDescription = resultSet.getString(3);
				Timestamp ctreteTime = resultSet.getTimestamp(4);
				Integer sellCount = resultSet.getInt(5);
				String marketPrice = resultSet.getString(6);
				String sellPrice = resultSet.getString(7);
				String goodsDiscount = resultSet.getString(8);
				Integer quantity = resultSet.getInt(9);
				String goodsPicture = resultSet.getString(10);
				String goodsStatus = resultSet.getString(11);
				Goods goods = new Goods(id, goodsName, goodsDescription,
						ctreteTime, sellCount, marketPrice, sellPrice,
						goodsDiscount, quantity, goodsPicture);
				goods.setGoodsStatus(goodsStatus);
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
	public int GetHasShelvedGoodsCount(Store store) throws Exception {
		Connection con = dbUtil.getCon();
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		int count = 0;
		try {
			String sql = "select * from goods where store_id = ? and status = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, store.getId());
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
	public List<Goods> ListHasUnderShelvedGoods(int pageSize, int page,
			Store store) throws Exception {
		List<Goods> goodses = new ArrayList<Goods>();
		Connection con = dbUtil.getCon();
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		try {
			String sql = "select * from goods where store_id = ? and status = ? order by sell_count desc limit "
					+ (page - 1) * pageSize + "," + pageSize;
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, store.getId());
			pstmt.setString(2, GoodsStatus.HAS_BEEN_UNDER_SHELVED.getName());
			resultSet = (ResultSet) pstmt.executeQuery();
			while (resultSet.next()) {
				int id = resultSet.getInt(1);
				String goodsName = resultSet.getString(2);
				String goodsDescription = resultSet.getString(3);
				Timestamp ctreteTime = resultSet.getTimestamp(4);
				Integer sellCount = resultSet.getInt(5);
				String marketPrice = resultSet.getString(6);
				String sellPrice = resultSet.getString(7);
				String goodsDiscount = resultSet.getString(8);
				Integer quantity = resultSet.getInt(9);
				String goodsPicture = resultSet.getString(10);
				String goodsStatus = resultSet.getString(11);
				Goods goods = new Goods(id, goodsName, goodsDescription,
						ctreteTime, sellCount, marketPrice, sellPrice,
						goodsDiscount, quantity, goodsPicture);
				goods.setGoodsStatus(goodsStatus);
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
	public List<Goods> ListHasOutGoods(int pageSize, int page, Store store)
			throws Exception {
		List<Goods> goodses = new ArrayList<Goods>();
		Connection con = dbUtil.getCon();
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		try {
			String sql = "select * from goods where store_id = ? and status = ? order by sell_count desc limit "
					+ (page - 1) * pageSize + "," + pageSize;
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, store.getId());
			pstmt.setString(2, GoodsStatus.OUT_OF_STOCK.getName());
			resultSet = (ResultSet) pstmt.executeQuery();
			while (resultSet.next()) {
				int id = resultSet.getInt(1);
				String goodsName = resultSet.getString(2);
				String goodsDescription = resultSet.getString(3);
				Timestamp ctreteTime = resultSet.getTimestamp(4);
				Integer sellCount = resultSet.getInt(5);
				String marketPrice = resultSet.getString(6);
				String sellPrice = resultSet.getString(7);
				String goodsDiscount = resultSet.getString(8);
				Integer quantity = resultSet.getInt(9);
				String goodsPicture = resultSet.getString(10);
				String goodsStatus = resultSet.getString(11);
				Goods goods = new Goods(id, goodsName, goodsDescription,
						ctreteTime, sellCount, marketPrice, sellPrice,
						goodsDiscount, quantity, goodsPicture);
				goods.setGoodsStatus(goodsStatus);
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
	public int GetHasUnderShelvedGoodsCount(Store store) throws Exception {
		Connection con = dbUtil.getCon();
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		int count = 0;
		try {
			String sql = "select * from goods where store_id = ? and status = ?";
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
	public int GetHasOutGoodsCount(Store store) throws Exception {
		Connection con = dbUtil.getCon();
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		int count = 0;
		try {
			String sql = "select * from goods where store_id = ? and status = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, store.getId());
			pstmt.setString(2, GoodsStatus.OUT_OF_STOCK.getName());
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
		try {
			String sql = "select * from goods where store_id = ? and status <> ? order by sell_count desc limit "
					+ (page - 1) * pageSize + "," + pageSize;
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, store.getId());
			pstmt.setString(2, GoodsStatus.HAS_BEEN_UNDER_SHELVED.getName());
			resultSet = (ResultSet) pstmt.executeQuery();
			while (resultSet.next()) {
				int id = resultSet.getInt(1);
				String goodsName = resultSet.getString(2);
				String goodsDescription = resultSet.getString(3);
				Timestamp ctreteTime = resultSet.getTimestamp(4);
				Integer sellCount = resultSet.getInt(5);
				String marketPrice = resultSet.getString(6);
				String sellPrice = resultSet.getString(7);
				String goodsDiscount = resultSet.getString(8);
				Integer quantity = resultSet.getInt(9);
				String goodsPicture = resultSet.getString(10);
				String goodsStatus = resultSet.getString(11);
				Goods goods = new Goods(id, goodsName, goodsDescription,
						ctreteTime, sellCount, marketPrice, sellPrice,
						goodsDiscount, quantity, goodsPicture);
				goods.setGoodsStatus(goodsStatus);
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

}
