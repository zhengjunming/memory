package com.zhengjunming.www.dao.impl;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.ResultSet;
import com.zhengjunming.www.dao.CartDao;
import com.zhengjunming.www.po.Cart;
import com.zhengjunming.www.po.CartItem;
import com.zhengjunming.www.po.Goods;
import com.zhengjunming.www.po.User;
import com.zhengjunming.www.util.DbUtil;

public class CartDaoImpl implements CartDao {
	private static DbUtil dbUtil = new DbUtil();

	@Override
	public boolean isUserhasCart(User user) throws Exception {
		Connection con = dbUtil.getCon();
		PreparedStatement pstmt = null;
		try {
			String sql = "select * from cart where user_id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, user.getId());
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
	public void addCart(User user) throws Exception {
		Connection con = dbUtil.getCon();
		PreparedStatement pstmt = null;
		try {
			String sql = "insert into cart (id, user_id) values (null, ?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, user.getId());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbUtil.close(pstmt, con);
		}
	}

	@Override
	public List<CartItem> getCartItem(User user) throws Exception {
		List<CartItem> cartItems = new ArrayList<CartItem>();
		Connection con = dbUtil.getCon();
		PreparedStatement pstmt = null;
		try {
			String sql = "select id from cart where user_id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, user.getId());
			ResultSet resultSet = (ResultSet) pstmt.executeQuery();
			if (resultSet.next()) {
				int id = resultSet.getInt(1);
				cartItems = getCartItemByCartId(id);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbUtil.close(pstmt, con);
		}
		return cartItems;
	}

	@Override
	public List<CartItem> getCartItemByCartId(int id) throws Exception {
		List<CartItem> cartItems = new ArrayList<CartItem>();
		Connection con = dbUtil.getCon();
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		GoodsDaoImpl daoImpl = new GoodsDaoImpl();
		try {
			String sql = "select * from cart_item where cart_id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, id);
			resultSet = (ResultSet) pstmt.executeQuery();
			while (resultSet.next()) {
				CartItem cartItem = new CartItem();
				cartItem.setId(resultSet.getInt(1));
				cartItem.setGoods(daoImpl.getGoodsById(resultSet.getInt(2)));
				cartItem.setGoodsAmount(resultSet.getInt(3));
				cartItem.setTotalPrice(resultSet.getString(4));
				cartItem.setCart(queryCartById(resultSet.getInt(5)));
				cartItems.add(cartItem);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbUtil.close(pstmt, con);
		}
		return cartItems;
	}

	@Override
	public Cart queryCartById(int id) throws Exception {
		Cart cart = new Cart();
		Connection con = dbUtil.getCon();
		PreparedStatement pstmt = null;
		try {
			String sql = "select * from cart where id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, id);
			ResultSet resultSet = (ResultSet) pstmt.executeQuery();
			if (resultSet.next()) {
				cart.setId(resultSet.getInt(1));
				int userId = resultSet.getInt(2);
				UserDaoImpl daoImpl = new UserDaoImpl();
				cart.setUser(daoImpl.getUserInfo(userId));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbUtil.close(pstmt, con);
		}
		return cart;
	}

	@Override
	public Cart getCartByUserId(int id) throws Exception {
		Cart cart = new Cart();
		Connection con = dbUtil.getCon();
		PreparedStatement pstmt = null;
		try {
			String sql = "select * from cart where user_id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, id);
			ResultSet resultSet = (ResultSet) pstmt.executeQuery();
			if (resultSet.next()) {
				cart.setId(resultSet.getInt(1));
				int userId = resultSet.getInt(2);
				UserDaoImpl daoImpl = new UserDaoImpl();
				cart.setUser(daoImpl.getUserInfo(userId));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbUtil.close(pstmt, con);
		}
		return cart;
	}

	@Override
	public void addCartItem(CartItem cartItem) throws Exception {
		Connection con = dbUtil.getCon();
		PreparedStatement pstmt = null;
		try {
			String sql = "insert into cart_item (goods_id, goods_amount, "
					+ "total_price, cart_id, store_id) values (?, ?, ?, ?, ?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, cartItem.getGoods().getId());
			pstmt.setInt(2, 1);
			pstmt.setString(3, cartItem.getGoods().getSellPrice());
			pstmt.setInt(4, cartItem.getCart().getId());
			pstmt.setInt(5, cartItem.getGoods().getStore().getId());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbUtil.close(pstmt, con);
		}
	}

	@Override
	public void increaseGoodsCount(CartItem cartItem, int cartId)
			throws Exception {
		Connection con = dbUtil.getCon();
		PreparedStatement pstmt = null;
		try {
			String sql = "update cart_item set goods_amount = ?, "
					+ "total_price = ? where goods_id = ? and cart_id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, cartItem.getGoodsAmount() + 1);
			pstmt.setString(
					2,
					new BigDecimal(cartItem.getTotalPrice()).add(
							new BigDecimal(cartItem.getGoods().getSellPrice()))
							.toString());
			pstmt.setInt(3, cartItem.getGoods().getId());
			pstmt.setInt(4, cartId);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbUtil.close(pstmt, con);
		}

	}

	@Override
	public void reduceGoodsCount(CartItem cartItem, int cartId)
			throws Exception {
		Connection con = dbUtil.getCon();
		PreparedStatement pstmt = null;
		try {
			String sql = "update cart_item set goods_amount = ?, "
					+ "total_price = ? where goods_id = ? and cart_id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, cartItem.getGoodsAmount() - 1);
			pstmt.setString(
					2,
					new BigDecimal(cartItem.getTotalPrice()).subtract(
							new BigDecimal(cartItem.getGoods().getSellPrice()))
							.toString());
			pstmt.setInt(3, cartItem.getGoods().getId());
			pstmt.setInt(4, cartId);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbUtil.close(pstmt, con);
		}

	}

	@Override
	public boolean goodsIsExist(Goods goods, int cartId) throws Exception {
		Connection con = dbUtil.getCon();
		PreparedStatement pstmt = null;
		try {
			String sql = "select * from cart_item where goods_id = ? and cart_id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, goods.getId());
			pstmt.setInt(2, cartId);
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
	public CartItem getCartItemByGoodsIdAndCartId(int goodsId, int cartId)
			throws Exception {
		Connection con = dbUtil.getCon();
		PreparedStatement pstmt = null;
		CartItem cartItem = new CartItem();
		GoodsDaoImpl daoImpl = new GoodsDaoImpl();
		try {
			String sql = "select * from cart_item where goods_id = ? and cart_id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, goodsId);
			pstmt.setInt(2, cartId);
			ResultSet resultSet = (ResultSet) pstmt.executeQuery();
			if (resultSet.next()) {
				cartItem.setId(resultSet.getInt(1));
				cartItem.setGoods(daoImpl.getGoodsById(resultSet.getInt(2)));
				cartItem.setGoodsAmount(resultSet.getInt(3));
				cartItem.setTotalPrice(resultSet.getString(4));
				cartItem.setCart(queryCartById(resultSet.getInt(5)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbUtil.close(pstmt, con);
		}
		return cartItem;
	}

	@Override
	public void deleteCartItem(int goodsId, int cartId) throws Exception {
		Connection con = dbUtil.getCon();
		PreparedStatement pstmt = null;
		try {
			String sql = "delete from cart_item where cart_id = ? and goods_id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, cartId);
			pstmt.setInt(2, goodsId);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbUtil.close(pstmt, con);
		}
	}

	@Override
	public void clearCart(int cartId) throws Exception {
		Connection con = dbUtil.getCon();
		PreparedStatement pstmt = null;
		try {
			String sql = "delete from cart_item where cart_id = ? ";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, cartId);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbUtil.close(pstmt, con);
		}

	}

	@Override
	public List<CartItem> getCartItemByStoreIdAndCartId(int storeId, int cartId)
			throws Exception {
		List<CartItem> cartItems = new ArrayList<CartItem>();
		Connection con = dbUtil.getCon();
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		GoodsDaoImpl goodsDaoImpl = new GoodsDaoImpl();
		StoreDaoImpl storeDaoImpl = new StoreDaoImpl();
		try {
			String sql = "select * from cart_item where store_id = ? and cart_id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, storeId);
			pstmt.setInt(2, cartId);
			resultSet = (ResultSet) pstmt.executeQuery();
			while (resultSet.next()) {
				CartItem cartItem = new CartItem();
				cartItem.setId(resultSet.getInt(1));
				cartItem.setGoods(goodsDaoImpl.getGoodsById(resultSet.getInt(2)));
				cartItem.setGoodsAmount(resultSet.getInt(3));
				cartItem.setTotalPrice(resultSet.getString(4));
				cartItem.setStore(storeDaoImpl.getStoreInfo(resultSet.getInt(5)));
				cartItem.setCart(queryCartById(resultSet.getInt(6)));
				cartItems.add(cartItem);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbUtil.close(pstmt, con);
		}
		return cartItems;
	}
}
