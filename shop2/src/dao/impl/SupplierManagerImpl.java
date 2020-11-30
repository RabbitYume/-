package dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.ISupplierManager;
import entity.Supplier;
import utils.JDBCUtil;

public class SupplierManagerImpl implements ISupplierManager{
	PreparedStatement ps = null;
	ResultSet rs = null;
	//添加
	@Override
	public boolean insert(Supplier s) {
		int n = 0;
		String sql = "insert into supplier values(?,?,?,?)";
		try {
			ps = JDBCUtil.getConn().prepareStatement(sql);
			ps.setInt(1, s.getS_id());
			ps.setString(2, s.getS_name());
			ps.setString(3, s.getS_address());
			ps.setInt(4, s.getS_phone());
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
	public int delete(int s_id) {
		String sql = "delete from supplier where s_id=?";
		int n = 0;
		try {
			ps = JDBCUtil.getConn().prepareStatement(sql);
			ps.setInt(1, s_id);

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
	public int update(Supplier s) {
		int n = 0;
		String sql = "update supplier set s_id=?,s_name=?,s_address=?,s_phone=? where s_id = ?";
		try {
			ps = JDBCUtil.getConn().prepareStatement(sql);
			ps.setInt(1, s.getS_id());
			ps.setString(2, s.getS_name());
			ps.setString(3, s.getS_address());
			ps.setInt(4, s.getS_phone());
			ps.setInt(5, s.getS_id());

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
	public Supplier getBySId(int s_id) {
		Supplier supplier = null;
		String sql = "select * from supplier where s_id = ?";
		try {
			ps = JDBCUtil.getConn().prepareStatement(sql);
			ps.setInt(1, s_id);
			rs = ps.executeQuery();
			if (rs.next()) {
				supplier = new Supplier();
				supplier.setS_id(rs.getInt("s_id"));
				supplier.setS_name(rs.getString("s_name"));
				supplier.setS_address(rs.getString("s_address"));
				supplier.setS_phone(rs.getInt("s_phone"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCUtil.release(null, ps, rs);
		}
		return supplier;
	}
	
	// 根据名称查询ID
		@Override
		public int getBySName(String s_name) {
			int s_id = 0;
			String sql = "select s_id from supplier where s_name = ?";
			try {
				ps = JDBCUtil.getConn().prepareStatement(sql);
				ps.setString(1, s_name);
				rs = ps.executeQuery();
				if (rs.next()) {
					Supplier supplier = new Supplier(rs.getInt("s_id"));
					s_id = supplier.getS_id();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				JDBCUtil.release(null, ps, rs);
			}
			return s_id;
		}

	// 查询所有
	@Override
	public List<Supplier> getSupplier() {
		String sql = "select * from supplier";
		List<Supplier> suppliers = new ArrayList<Supplier>();
		try {
			ps = JDBCUtil.getConn().prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				Supplier supplier = new Supplier();
				supplier.setS_id(rs.getInt("s_id"));
				supplier.setS_name(rs.getString("s_name"));
				supplier.setS_address(rs.getString("s_address"));
				supplier.setS_phone(rs.getInt("s_phone"));
				suppliers.add(supplier);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCUtil.release(null, ps, rs);
		}
		return suppliers;
	}

}
