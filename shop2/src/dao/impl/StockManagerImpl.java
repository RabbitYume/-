package dao.impl;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.IStockManager;
import entity.Stock;
import utils.JDBCUtil;

/*库存操作接口*/
public class StockManagerImpl implements IStockManager{
	PreparedStatement ps = null;
	ResultSet rs = null;
	//添加
	@Override
	public boolean insert(Stock s) {
		int n = 0;
		String sql = "insert into stock values(?,?,?,?,?,null)";
		try {
			ps = JDBCUtil.getConn().prepareStatement(sql);
			ps.setInt(1, s.getG_id());
			ps.setInt(2, s.getS_id());
			ps.setString(3, s.getBuyDate());
			ps.setDouble(4, s.getBuyprice());
			ps.setInt(5, s.getStock());
			n = ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCUtil.release(null, ps, null);
		}
		// TODO Auto-generated method stub
		return n > 0;
	}

	//删除
	@Override
	public int delete(int id) {
		String sql = "delete from stock where id=?";
		int n = 0;
		try {
			ps = JDBCUtil.getConn().prepareStatement(sql);
			ps.setInt(1, id);

			n = ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCUtil.release(null, ps, null);
		}

		return n;
	}

	//更新
	@Override
	public int update(Stock s) {
		int n = 0;
		String sql = "update stock set g_id=?,s_id=?,buydate=?,buyprice=?,stock=? where id = ?";
		try {
			ps = JDBCUtil.getConn().prepareStatement(sql);
			ps.setInt(1, s.getG_id());
			ps.setInt(2, s.getS_id());
			ps.setString(3, s.getBuyDate());
			ps.setDouble(4, s.getBuyprice());
			ps.setDouble(5, s.getStock());
			ps.setInt(6, s.getId());

			n = ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCUtil.release(null, ps, null);
		}
		// TODO Auto-generated method stub
		return n;
	}
	
	//出入库
	public int updateSell(Stock s) {
		int n = 0;
		String sql = "update stock set stock = ? where g_id = ?";
		try {
			ps = JDBCUtil.getConn().prepareStatement(sql);
			ps.setDouble(1, s.getStock());
			ps.setInt(2, s.getG_id());

			n = ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCUtil.release(null, ps, null);
		}
		// TODO Auto-generated method stub
		return n;
	}

	// 根据ID查询
	@Override
	public List<Stock> getByGId(int g_id) {
		List<Stock> stocks = new ArrayList<Stock>();
		String sql = "select * from stock where g_id = ?";
		try {
			ps = JDBCUtil.getConn().prepareStatement(sql);
			ps.setInt(1, g_id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Stock stock = new Stock();
				stock.setId(rs.getInt("id"));
				stock.setG_id(rs.getInt("g_id"));
				stock.setS_id(rs.getInt("s_id"));
				stock.setBuyDate(rs.getString("buydate"));
				stock.setBuyprice(rs.getDouble("buyprice"));
				stock.setStock(rs.getInt("stock"));
				stocks.add(stock);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCUtil.release(null, ps, rs);
		}
		return stocks;
	}
	
	@Override
	public List<Stock> getBySId(int s_id) {
		String sql = "select * from stock where s_id = ?";
		List<Stock> stocks = new ArrayList<Stock>();
		try {
			ps = JDBCUtil.getConn().prepareStatement(sql);
			ps.setInt(1, s_id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Stock stock = new Stock();
				stock.setId(rs.getInt("id"));
				stock.setG_id(rs.getInt("g_id"));
				stock.setS_id(rs.getInt("s_id"));
				stock.setBuyDate(rs.getString("buydate"));
				stock.setBuyprice(rs.getDouble("buyprice"));
				stock.setStock(rs.getInt("stock"));
				stocks.add(stock);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCUtil.release(null, ps, rs);
		}
		return stocks;
	}

	// 查询所有
	@Override
	public List<Stock> getStock() {
		String sql = "select * from stock";
		List<Stock> stocks = new ArrayList<Stock>();
		try {
			ps = JDBCUtil.getConn().prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Stock stock = new Stock();
				stock.setId(rs.getInt("id"));
				stock.setG_id(rs.getInt("g_id"));
				stock.setS_id(rs.getInt("s_id"));
				stock.setBuyDate(rs.getString("buydate"));
				stock.setBuyprice(rs.getDouble("buyprice"));
				stock.setStock(rs.getInt("stock"));
				stocks.add(stock);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCUtil.release(null, ps, rs);
		}
		return stocks;
	}

}
