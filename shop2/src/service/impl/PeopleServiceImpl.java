package service.impl;

import java.sql.SQLException;

import dao.IPeopleManager;
import dao.impl.PeopleManagerImpl;
import entity.Msg;
import entity.People;
import enumType.Statu;
import service.IPeopleService;
import utils.JDBCUtil;

public class PeopleServiceImpl implements IPeopleService{
	private IPeopleManager peopleManager = new PeopleManagerImpl();
	Msg msg = new Msg();
	/*@Override
	public Msg insert(People p) {
		if (p == null) {
			return new Msg(Statu.INPUTERROR);
		}
		People people = peopleManager.getByPId(p.getP_id());
		if (people != null) {
			return new Msg(Statu.INPUTERROR);
		}
		try {
			JDBCUtil.getConn().setAutoCommit(false);
			peopleManager.insert(p);
			JDBCUtil.getConn().commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return new Msg(Statu.SUCCESS);
	}
	public Msg insertPd(People p) {
		if (p == null) {
			return new Msg(Statu.INPUTERROR);
		}
		People people = peopleManager.getByPId(p.getP_id());
		if (people != null) {
			try {
				JDBCUtil.getConn().setAutoCommit(false);
				peopleManager.insertPd(p);
				JDBCUtil.getConn().commit();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		}
		return new Msg(Statu.SUCCESS);
	}*/
	
	//添加
	public Msg add(People p,People p1) {
		//判断是否为空
		if (p == null) {
			return new Msg(Statu.INPUTERROR);
		}
		//判断编号是否存在
		People people = peopleManager.getByPId(p.getP_id());
		if (people != null) {
			return new Msg(Statu.INPUTERROR);
		}else {
			try {
				//开启事务
				JDBCUtil.getConn().setAutoCommit(false);
				//添加员工
				peopleManager.insert(p);
				peopleManager.insertPd(p1);
				//提交
				JDBCUtil.getConn().commit();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return new Msg(Statu.SUCCESS);
		}
	}

	@Override
	public Msg deleteP(int p_id) {
		if (p_id <= 0) {
			return new Msg(Statu.INPUTERROR);
		}
		People peopleById = peopleManager.getByPId(p_id);
		if (peopleById == null) {
			return new Msg(Statu.ISNULL);
		}else {
			try {
				JDBCUtil.getConn().setAutoCommit(false);
				peopleManager.deleteP(p_id);
				JDBCUtil.getConn().commit();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return new Msg(Statu.SUCCESS);
		}
	}

	/*@Override
	public Msg update(People p) {
		if (p.getP_id() <= 0) {
			return new Msg(Statu.INPUTERROR);
		}
		People peopleById = peopleManager.getByPId(p.getP_id());
		if (peopleById == null) {
			return new Msg(Statu.NOTFIND);
		}else {
			try {
				JDBCUtil.getConn().setAutoCommit(false);
				peopleManager.update(p);
				JDBCUtil.getConn().commit();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return new Msg(Statu.SUCCESS);
		}
	}
	
	public Msg updatePd(People p) {
		if (p.getP_id() <= 0) {
			return new Msg(Statu.INPUTERROR);
		}
		People peopleById = peopleManager.getByPId(p.getP_id());
		if (peopleById == null) {
			return new Msg(Statu.NOTFIND);
		}else {
			try {
				JDBCUtil.getConn().setAutoCommit(false);
				peopleManager.updatePd(p);
				JDBCUtil.getConn().commit();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return new Msg(Statu.SUCCESS);
		}
	}*/
	
	//修改
	public Msg update(People p,People p1) {
		if (p.getP_id() <= 0) {
			return new Msg(Statu.INPUTERROR);
		}
		People peopleById = peopleManager.getByPId(p.getP_id());
		if (peopleById == null) {
			return new Msg(Statu.NOTFIND);
		}else {
			try {
				JDBCUtil.getConn().setAutoCommit(false);
				peopleManager.update(p);
				peopleManager.updatePd(p1);
				JDBCUtil.getConn().commit();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return new Msg(Statu.SUCCESS);
		}
	}

	@Override
	public Msg getByPId(int p_id) {
		Msg msg = new Msg(Statu.SUCCESS);
		try {
			msg.setObj(peopleManager.getByPId(p_id));
		} catch (Exception e) {
			return new Msg(Statu.ERROR);
		}
		return msg;
	}
	public Msg getByPdId(int p_id) {
		Msg msg = new Msg(Statu.SUCCESS);
		try {
			msg.setObj(peopleManager.getByPdId(p_id));
		} catch (Exception e) {
			return new Msg(Statu.ERROR);
		}
		return msg;
	}

	@Override
	public Msg getPeople() {
		Msg msg = new Msg(Statu.SUCCESS);
		try {
			msg.setObj(peopleManager.getPeople());
		} catch (Exception e) {
			return new Msg(Statu.ERROR);
		}
		return msg;
	}

	@Override
	public Msg loginByPId(int p_id,String p_pwd) {
		Msg msg = new Msg(Statu.SUCCESS);
		try {
			msg.setObj(peopleManager.loginByPId(p_id, p_pwd));
		} catch (Exception e) {
			return new Msg(Statu.ERROR);
		}
		return msg;
	}
	
	public Msg quanxian(int p_id) {
		Msg msg = new Msg(Statu.SUCCESS);
		try {
			msg.setObj(peopleManager.quanxian(p_id));
		} catch (Exception e) {
			return new Msg(Statu.ERROR);
		}
		return msg;
	}
}
