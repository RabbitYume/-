package dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.ISumStockManager;
import entity.Goods;
import entity.SumStock;
import utils.JDBCUtil;

public class SumStockManagerImpl implements ISumStockManager {
	PreparedStatement ps = null;
	ResultSet rs = null;

	// 添加
	/*@Override
	public boolean insert(SumStock ss) {
		int n = 0;
		String sql = "insert into sum_stock values(?,?,?)";
		try {
			ps = JDBCUtil.getConn().prepareStatement(sql);
			ps.setInt(1, ss.getGg_id());
			ps.setInt(2, ss.getSs_id());
			ps.setInt(3, ss.getStock());
			n = ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCUtil.release(null, ps, null);
		}
		// TODO Auto-generated method stub
		return n > 0;
	}*/
	public boolean insert(int gg_id, int s_id, int i) {
		int n = 0;
		String sql = "insert into sum_stock values(?,?,?)";
		try {
			ps = JDBCUtil.getConn().prepareStatement(sql);
			ps.setInt(1, gg_id);
			ps.setInt(2, s_id);
			ps.setInt(3, i);
			n = ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCUtil.release(null, ps, null);
		}
		// TODO Auto-generated method stub
		return n > 0;
	}

	// 修改
	@Override
	public int update(SumStock ss) {
		int n = 0;
		String sql = "update sum_stock set stock=? where gg_id = ?";
		try {
			ps = JDBCUtil.getConn().prepareStatement(sql);
			ps.setInt(1, ss.getStock());
			ps.setInt(2, ss.getGg_id());

			n = ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCUtil.release(null, ps, null);
		}
		// TODO Auto-generated method stub
		return n;
	}

	// 根据ID查询库存数量
	@Override
	public int getByGId(int gg_id) {
		SumStock sumStock = null;
		int stock = 0;
		String sql = "select * from sum_stock where gg_id = ?";
		try {
			ps = JDBCUtil.getConn().prepareStatement(sql);
			ps.setInt(1, gg_id);
			rs = ps.executeQuery();
			if (rs.next()) {
				sumStock = new SumStock(rs.getInt("stock"));
				stock = sumStock.getStock();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCUtil.release(null, ps, rs);
		}
		return stock;
	}

	// 查询所有
	@Override
	public List<SumStock> getGoods() {
		String sql = "select * from sum_stock";
		List<SumStock> sumStocks = new ArrayList<SumStock>();
		try {
			ps = JDBCUtil.getConn().prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				SumStock sumStock = new SumStock();
				sumStock.setGg_id(rs.getInt("gg_id"));
				sumStock.setSs_id(rs.getInt("ss_id"));
				sumStock.setStock(rs.getInt("stock"));
				sumStocks.add(sumStock);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCUtil.release(null, ps, rs);
		}
		return sumStocks;
	}

}
