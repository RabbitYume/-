package dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.IShopCarManager;
import entity.Goods;
import entity.ShopCar;
import utils.JDBCUtil;

/*购物车接口操作实现类*/
public class ShopCarManagerImpl implements IShopCarManager{
	PreparedStatement ps = null;
	ResultSet rs = null;
	// 添加
	@Override
	public boolean insert(ShopCar sc) {
		int n = 0;
		String sql = "insert into shopcar values(null,?,?,?,?)";
		try {
			ps = JDBCUtil.getConn().prepareStatement(sql);
			ps.setInt(1, sc.getGc_id());
			ps.setInt(2, sc.getNum());
			ps.setDouble(3, sc.getPrice());
			ps.setInt(4, sc.getPc_id());
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
	public int delete(int gc_id,int pc_id) {
		String sql = "delete from shopcar where gc_id=? and pc_id=?";
		int n = 0;
		try {
			ps = JDBCUtil.getConn().prepareStatement(sql);
			ps.setInt(1, gc_id);
			ps.setInt(2, pc_id);
			n = ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCUtil.release(null, ps, null);
		}

		return n;
	}
	
	@Override
	public int delall(int pc_id) {
		String sql = "delete from shopcar where pc_id=?";
		int n = 0;
		try {
			ps = JDBCUtil.getConn().prepareStatement(sql);
			ps.setInt(1, pc_id);
			n = ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCUtil.release(null, ps, null);
		}

		return n;
	}

	//修改
	@Override
	public int update(ShopCar sc) {
		int n = 0;
		String sql = "update shopcar set num=?,price=? where gc_id = ?";
		try {
			ps = JDBCUtil.getConn().prepareStatement(sql);
			ps.setInt(1, sc.getNum());
			ps.setDouble(2, sc.getPrice());
			ps.setInt(3, sc.getGc_id());

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

	//查询
	public ShopCar getByGId(int gc_id,int pc_id) {
		ShopCar shopCar = new ShopCar();
		String sql = "select * from shopcar sc,goods g where g.g_id = sc.gc_id and gc_id=? and pc_id=?";
		try {
			ps = JDBCUtil.getConn().prepareStatement(sql);
			ps.setInt(1, gc_id);
			ps.setInt(2, pc_id);
			rs = ps.executeQuery();
			if (rs.next()) {
				shopCar.setGc_id(rs.getInt("gc_id"));
				shopCar.setG_name(rs.getString("g_name"));
				shopCar.setNum(rs.getInt("num"));
				shopCar.setPrice(rs.getDouble("price"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCUtil.release(null, ps, rs);
		}
		return shopCar;
	}
	
	
	//查询数量
	public int getNum(int gc_id) {
		int num = 0;
		ShopCar shopCar = null;
		String sql = "select num from shopcar where gc_id=?";
		try {
			ps = JDBCUtil.getConn().prepareStatement(sql);
			ps.setInt(1, gc_id);
			rs = ps.executeQuery();
			if (rs.next()) {
				shopCar = new ShopCar(rs.getInt("num"));
				num = shopCar.getNum();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCUtil.release(null, ps, rs);
		}
		return num;
	}
	
	//查询所有
	@Override
	public List<ShopCar> getShopCars(int pc_id) {
		String sql = "select * from shopcar sc,goods g where g.g_id = sc.gc_id and pc_id=?";
		List<ShopCar> shopCars = new ArrayList<ShopCar>();
		try {
			ps = JDBCUtil.getConn().prepareStatement(sql);
			ps.setInt(1, pc_id);
			rs = ps.executeQuery();
			while (rs.next()) {
				ShopCar shopCar = new ShopCar();
				shopCar.setGc_id(rs.getInt("g_id"));
				shopCar.setG_name(rs.getString("g_name"));
				shopCar.setNum(rs.getInt("num"));
				shopCar.setPrice(rs.getDouble("price"));
				shopCars.add(shopCar);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCUtil.release(null, ps, rs);
		}
		return shopCars;
	}

}
