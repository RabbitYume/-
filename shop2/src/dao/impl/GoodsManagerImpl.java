package dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.IGoodsManager;
import entity.Goods;
import utils.JDBCUtil;

/*商品信息接口操作实现类*/
public class GoodsManagerImpl implements IGoodsManager{
	PreparedStatement ps = null;
	ResultSet rs = null;
	// 添加
	@Override
	public boolean insert(Goods g) {
		int n = 0;
		String sql = "insert into goods values(?,?,?,?)";
		try {
			ps = JDBCUtil.getConn().prepareStatement(sql);
			ps.setInt(1, g.getG_id());
			ps.setString(2, g.getG_name());
			ps.setString(3, g.getG_model());
			ps.setDouble(4, g.getG_price());
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

	// 删除
	@Override
	public int delete(int g_id) {
		String sql = "delete from goods where g_id=?";
		int n = 0;
		try {
			ps = JDBCUtil.getConn().prepareStatement(sql);
			ps.setInt(1, g_id);

			n = ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCUtil.release(null, ps, null);
		}

		return n;
	}

	// 更新
	@Override
	public int update(Goods g) {
		int n = 0;
		String sql = "update goods set g_id=?,g_name=?,g_model=?,g_price=? where g_id = ?";
		try {
			ps = JDBCUtil.getConn().prepareStatement(sql);
			ps.setInt(1, g.getG_id());
			ps.setString(2, g.getG_name());
			ps.setString(3, g.getG_model());
			ps.setDouble(4, g.getG_price());
			ps.setInt(5, g.getG_id());

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
	public Goods getByGId(int g_id) {
		Goods goods = null;
		String sql = "select * from goods g,sum_stock ss,supplier sp where g.g_id = ss.gg_id and sp.s_id = ss.ss_id and g.g_id = ?";
		try {
			ps = JDBCUtil.getConn().prepareStatement(sql);
			ps.setInt(1, g_id);
			rs = ps.executeQuery();
			if (rs.next()) {
				goods = new Goods();
				goods.setG_id(rs.getInt("g_id"));
				goods.setG_name(rs.getString("g_name"));
				goods.setG_brand(rs.getString("s_name"));
				goods.setG_model(rs.getString("g_model"));
				goods.setG_price(rs.getDouble("g_price"));
				goods.setStock(rs.getInt("stock"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCUtil.release(null, ps, rs);
		}
		return goods;
	}

	// 查询所有
	@Override
	public List<Goods> getGoods() {
		String sql = "select * from goods g,sum_stock ss,supplier sp where g.g_id = ss.gg_id and sp.s_id = ss.ss_id";
		List<Goods> goods = new ArrayList<Goods>();
		try {
			ps = JDBCUtil.getConn().prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				Goods good = new Goods();
				good.setG_id(rs.getInt("g_id"));
				good.setG_name(rs.getString("g_name"));
				good.setG_brand(rs.getString("s_name"));
				good.setG_model(rs.getString("g_model"));
				good.setG_price(rs.getDouble("g_price"));
				good.setStock(rs.getInt("stock"));
				goods.add(good);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCUtil.release(null, ps, rs);
		}
		return goods;
	}
	
	//查询价格
	public double getByPrice(int g_id) {
		Goods goods = null;
		double price = 0;
		String sql = "select g_price from goods where g_id = ?";
		try {
			ps = JDBCUtil.getConn().prepareStatement(sql);
			ps.setInt(1, g_id);
			rs = ps.executeQuery();
			if (rs.next()) {
				goods = new Goods(rs.getDouble("g_price"));
				price = goods.getG_price();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCUtil.release(null, ps, rs);
		}
		return price;
	}
}
