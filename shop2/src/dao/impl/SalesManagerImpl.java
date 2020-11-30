package dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.ISalesManager;
import entity.Sales;
import utils.JDBCUtil;

public class SalesManagerImpl implements ISalesManager{
	PreparedStatement ps = null;
	ResultSet rs = null;

	//添加
	@Override
	public boolean insert(Sales s) {
		int n = 0;
		String sql = "insert into sales values(null,?,?,?,?,?)";
		try {
			ps = JDBCUtil.getConn().prepareStatement(sql);
			ps.setInt(1, s.getDanhao());
			ps.setInt(2, s.getGs_id());
			ps.setInt(3, s.getNum());
			ps.setDouble(4, s.getPrice());
			ps.setString(5, s.getSelldate());
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
	public boolean insertP(Sales s) {
		int n = 0;
		String sql = "insert into sales_people values(null,?,?)";
		try {
			ps = JDBCUtil.getConn().prepareStatement(sql);
			ps.setInt(1, s.getDanhao());
			ps.setInt(2, s.getPs_id());
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
	public int delete(int danhao) {
		String sql = "delete from sales where danhao=?";
		int n = 0;
		try {
			ps = JDBCUtil.getConn().prepareStatement(sql);
			ps.setInt(1, danhao);

			n = ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCUtil.release(null, ps, null);
		}

		return n;
	}
	public int deleteG(int gs_id) {
		String sql = "delete from sales where gs_id=?";
		int n = 0;
		try {
			ps = JDBCUtil.getConn().prepareStatement(sql);
			ps.setInt(1, gs_id);

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
	public int update(Sales s) {
		int n = 0;
		String sql = "update sales set danhao=?,gs_id=?,num=?,price=?,selldate=? where danhao = ? and gs_id = ?";
		try {
			ps = JDBCUtil.getConn().prepareStatement(sql);
			ps.setInt(1, s.getDanhao());
			ps.setInt(2, s.getGs_id());
			ps.setInt(3, s.getNum());
			ps.setDouble(4, s.getPrice());
			ps.setString(5, s.getSelldate());
			ps.setInt(6, s.getDanhao());
			ps.setInt(7, s.getGs_id());

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
	public int updateP(Sales s) {
		int n = 0;
		String sql = "update sales_people set dp_id=?,pd_id=? where dp_id = ?";
		try {
			ps = JDBCUtil.getConn().prepareStatement(sql);
			ps.setInt(1, s.getDanhao());
			ps.setInt(2, s.getPs_id());
			ps.setInt(3, s.getDanhao());

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
	public List<Sales> getBySId(int danhao) {
		List<Sales> sales = new ArrayList<Sales>();
		String sql = "select * from sales s,sales_people sp where s.danhao = sp.dp_id and danhao = ?";
		try {
			ps = JDBCUtil.getConn().prepareStatement(sql);
			ps.setInt(1, danhao);
			rs = ps.executeQuery();
			while (rs.next()) {
				Sales sale = new Sales();
				sale.setDanhao(rs.getInt("danhao"));
				sale.setGs_id(rs.getInt("gs_id"));
				sale.setNum(rs.getInt("num"));
				sale.setPrice(rs.getDouble("price"));
				sale.setSelldate(rs.getString("selldate"));
				sale.setPs_id(rs.getInt("pd_id"));
				sales.add(sale);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCUtil.release(null, ps, rs);
		}
		return sales;
	}
	public Sales getByGId(int gs_id) {
		Sales sales = null;
		String sql = "select * from sales s,sales_people sp where s.danhao = sp.dp_id and s.gs_id = ?";
		try {
			ps = JDBCUtil.getConn().prepareStatement(sql);
			ps.setInt(1, gs_id);
			rs = ps.executeQuery();
			if (rs.next()) {
				sales = new Sales();
				sales.setDanhao(rs.getInt("danhao"));
				sales.setGs_id(rs.getInt("gs_id"));
				sales.setNum(rs.getInt("num"));
				sales.setPrice(rs.getDouble("price"));
				sales.setSelldate(rs.getString("selldate"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCUtil.release(null, ps, rs);
		}
		return sales;
	}
	public Sales getBySPId(int danhao) {
		Sales sales = null;
		String sql = "select * from sales_people where dp_id = ?";
		try {
			ps = JDBCUtil.getConn().prepareStatement(sql);
			ps.setInt(1, danhao);
			rs = ps.executeQuery();
			if (rs.next()) {
				sales = new Sales();
				sales.setDanhao(rs.getInt("dp_id"));
				sales.setGs_id(rs.getInt("pd_id"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCUtil.release(null, ps, rs);
		}
		return sales;
	}

	// 查询所有
	@Override
	public List<Sales> getSales() {
		String sql = "select * from sales s, sales_people sp where s.danhao = sp.dp_id order by selldate";
		List<Sales> sales = new ArrayList<Sales>();
		try {
			ps = JDBCUtil.getConn().prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				Sales sale = new Sales();
				sale.setDanhao(rs.getInt("danhao"));
				sale.setGs_id(rs.getInt("gs_id"));
				sale.setNum(rs.getInt("num"));
				sale.setPrice(rs.getDouble("price"));
				sale.setSelldate(rs.getString("selldate"));
				sale.setPs_id(rs.getInt("pd_id"));
				sales.add(sale);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCUtil.release(null, ps, rs);
		}
		return sales;
	}

	//销售统计
	public Sales getSum(String selldate) {
		Sales sales = null;
		double price = 0;
		String sql = "select selldate,price from sales where selldate = ?";
		try {
			ps = JDBCUtil.getConn().prepareStatement(sql);
			ps.setString(1, selldate);
			rs = ps.executeQuery();
			while (rs.next()) {
				sales = new Sales();
				sales.setSelldate(rs.getString("selldate"));
				price += rs.getDouble("price");
				sales.setPrice(price);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCUtil.release(null, ps, rs);
		}
		return sales;
	}
}
