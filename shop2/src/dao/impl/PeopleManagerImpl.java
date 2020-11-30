package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.IPeopleManager;
import entity.People;
import utils.JDBCUtil;

/*员工接口操作实现类*/
public class PeopleManagerImpl implements IPeopleManager {

	PreparedStatement ps = null;
	ResultSet rs = null;
	// 添加
	@Override
	public boolean insert(People p) {
		int n = 0;
		String sql = "insert into people values(?,?,?,?,?,?)";
		try {
			ps = JDBCUtil.getConn().prepareStatement(sql);
			ps.setInt(1, p.getP_id());
			ps.setString(2, p.getP_name());
			ps.setString(3, p.getP_gender());
			ps.setInt(4, p.getP_wage());
			ps.setString(5, p.getP_position());
			ps.setString(6, p.getP_pwd());
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
	public boolean insertPd (People p) {
		int n = 0;
		String sql = "insert into people_data values(?,?,?,?,?)";
		try {
			JDBCUtil.getConn().setAutoCommit(false);// 关闭自动提交
			ps = JDBCUtil.getConn().prepareStatement(sql);
			ps.setInt(1, p.getP_id());
			ps.setString(2, p.getP_name());
			ps.setString(3, p.getP_dang());
			ps.setString(4, p.getP_resume());
			ps.setString(5, p.getP_photo());
			n = ps.executeUpdate();
			JDBCUtil.getConn().commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				// 回滚
				JDBCUtil.getConn().rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}finally {
			JDBCUtil.release(null, ps, null);
		}
		// TODO Auto-generated method stub
		return n > 0;
	}

	// 删除
	@Override
	public int deleteP(int p_id) {
		String sql = "delete from people where p_id = ?";
		int n = 0;
		try {
			ps = JDBCUtil.getConn().prepareStatement(sql);
			ps.setInt(1, p_id);

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
	public int update(People p) {
		int n = 0;
		String sql = "update people set p_id=?,p_name=?,p_gender=?,p_wage=?,p_position=?,p_pwd=? where p_id = ?";
		try {
			ps = JDBCUtil.getConn().prepareStatement(sql);
			ps.setInt(1, p.getP_id());
			ps.setString(2, p.getP_name());
			ps.setString(3, p.getP_gender());
			ps.setInt(4, p.getP_wage());
			ps.setString(5, p.getP_position());
			ps.setString(6, p.getP_pwd());
			ps.setInt(7, p.getP_id());

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
	public int updatePd (People p) {
		int n = 0;
		String sql = "update people_data set p_id=?,p_name=?,p_dang=?,p_resume=?,p_photo=? where p_id = ?";
		try {
			ps = JDBCUtil.getConn().prepareStatement(sql);
			ps.setInt(1, p.getP_id());
			ps.setString(2, p.getP_name());
			ps.setString(3, p.getP_dang());
			ps.setString(4, p.getP_resume());
			ps.setString(5, p.getP_photo());
			ps.setInt(6, p.getP_id());

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
	public People getByPId(int p_id) {
		People people = null;
		String sql = "select * from people where p_id = ?";
		try {
			ps = JDBCUtil.getConn().prepareStatement(sql);
			ps.setInt(1, p_id);
			rs = ps.executeQuery();
			if (rs.next()) {
				people = new People();
				people.setP_id(rs.getInt("p_id"));
				people.setP_name(rs.getString("p_name"));
				people.setP_gender(rs.getString("p_gender"));
				people.setP_wage(rs.getInt("p_wage"));
				people.setP_position(rs.getString("p_position"));
				people.setP_pwd(rs.getString("p_pwd"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCUtil.release(null, ps, rs);
		}
		return people;
	}
	public People getByPdId(int p_id) {
		People people = null;
		String sql = "select * from people p,people_data pd where p.p_id = pd.p_id and p.p_id = ?";
		try {
			ps = JDBCUtil.getConn().prepareStatement(sql);
			ps.setInt(1, p_id);
			rs = ps.executeQuery();
			if (rs.next()) {
				people = new People();
				people.setP_id(rs.getInt("p_id"));
				people.setP_name(rs.getString("p_name"));
				people.setP_gender(rs.getString("p_gender"));
				people.setP_wage(rs.getInt("p_wage"));
				people.setP_position(rs.getString("p_position"));
				people.setP_pwd(rs.getString("p_pwd"));
				people.setP_dang(rs.getString("p_dang"));
				people.setP_resume(rs.getString("p_resume"));
				people.setP_photo(rs.getString("p_photo"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCUtil.release(null, ps, rs);
		}
		return people;
	}

	// 查询所有
	@Override
	public List<People> getPeople() {
		String sql = "select * from people p,people_data pd where p.p_id = pd.p_id and p.p_name = pd.p_name order by p.p_id desc";
		List<People> peoples = new ArrayList<People>();
		try {
			ps = JDBCUtil.getConn().prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				People people = new People();
				people.setP_id(rs.getInt("p_id"));
				people.setP_name(rs.getString("p_name"));
				people.setP_gender(rs.getString("p_gender"));
				people.setP_wage(rs.getInt("p_wage"));
				people.setP_position(rs.getString("p_position"));
				people.setP_pwd(rs.getString("p_pwd"));
				people.setP_dang(rs.getString("p_dang"));
				people.setP_resume(rs.getString("p_resume"));
				people.setP_photo(rs.getString("p_photo"));
				peoples.add(people);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			if (ps!=null && rs!=null) {
				JDBCUtil.release(null, ps, rs);
			}
		}
		return peoples;
	}

	// 登录
	@Override
	public People loginByPId(int p_id, String p_pwd) {
		People people = null;
		String sql = "select * from people where p_id=? and p_pwd=?";
		try {
			ps = JDBCUtil.getConn().prepareStatement(sql);
			ps.setInt(1, p_id);
			ps.setString(2, p_pwd);
			rs = ps.executeQuery();
			if (rs.next()) {
				people = new People(rs.getInt("p_id"),rs.getString("p_pwd"));
			}
			return people;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCUtil.release(null, ps, rs);
		}
		return people;
	}
	
	//权限
	public int quanxian(int p_id) {
		String sql = "select p_position from people where p_id = ?";
		People people = null;
		int q = 0;
		try {
			ps = JDBCUtil.getConn().prepareStatement(sql);
			ps.setInt(1, p_id);
			rs = ps.executeQuery();
			if (rs.next()) {
				people = new People(rs.getString("p_position"));
				if (people.getP_position().equals("经理")) {
					q = 1;
				}else if (people.getP_position().equals("仓库管理")) {
					q = 2;
				}else if (people.getP_position().equals("销售")) {
					q = 3;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCUtil.release(null, ps, rs);
		}
		return q;
	}
}
