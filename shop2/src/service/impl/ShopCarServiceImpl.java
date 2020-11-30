package service.impl;

import java.sql.SQLException;

import dao.IGoodsManager;
import dao.ISalesManager;
import dao.IShopCarManager;
import dao.impl.GoodsManagerImpl;
import dao.impl.SalesManagerImpl;
import dao.impl.ShopCarManagerImpl;
import entity.Msg;
import entity.ShopCar;
import enumType.Statu;
import service.ISalesService;
import service.IShopCarService;
import utils.JDBCUtil;

public class ShopCarServiceImpl implements IShopCarService{
	private IShopCarManager shopCarManager = new ShopCarManagerImpl();
	private IGoodsManager goodsManager = new GoodsManagerImpl();
	private ISalesService salesService = new SalesServiceImpl();
	Msg msg = new Msg();
	@Override
	public Msg insert(int id,int pid) {
		try {
			JDBCUtil.getConn().setAutoCommit(false);
			double price = goodsManager.getByPrice(id);
			ShopCar sc = new ShopCar(id,1,price,pid);
			shopCarManager.insert(sc);
			JDBCUtil.getConn().commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return new Msg(Statu.SUCCESS);
	}

	@Override
	public Msg delete(int gc_id,int pc_id) {
		try {
			JDBCUtil.getConn().setAutoCommit(false);
			shopCarManager.delete(gc_id,pc_id);
			JDBCUtil.getConn().commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new Msg(Statu.SUCCESS);
	}
	public Msg delall(int pc_id) {
		try {
			JDBCUtil.getConn().setAutoCommit(false);
			shopCarManager.delall(pc_id);
			JDBCUtil.getConn().commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new Msg(Statu.SUCCESS);
	}
	

	@Override
	public Msg update(int id, int num) {
		try {
			JDBCUtil.getConn().setAutoCommit(false);
			double price = goodsManager.getByPrice(id);
			double sumprice = price * num;
			ShopCar shopCar = new ShopCar(id,num,sumprice);
			shopCarManager.update(shopCar);
			JDBCUtil.getConn().commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return new Msg(Statu.SUCCESS);
	}

	public Msg getByGId(int gc_id,int pc_id) {
		Msg msg = new Msg(Statu.SUCCESS);
		try {
			msg.setObj(shopCarManager.getByGId(gc_id,pc_id));
		} catch (Exception e) {
			return new Msg(Statu.ERROR);
		}
		return msg;
	}
	
	public Msg getNum(int gc_id) {
		Msg msg = new Msg(Statu.SUCCESS);
		try {
			msg.setObj(shopCarManager.getNum(gc_id));
		} catch (Exception e) {
			return new Msg(Statu.ERROR);
		}
		return msg;
	}
	
	@Override
	public Msg getShopCars(int pc_id) {
		Msg msg = new Msg(Statu.SUCCESS);
		try {
			msg.setObj(shopCarManager.getShopCars(pc_id));
		} catch (Exception e) {
			return new Msg(Statu.ERROR);
		}
		return msg;
	}

}
