package service.impl;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Random;

import dao.IGoodsManager;
import dao.ISalesManager;
import dao.ISumStockManager;
import dao.impl.GoodsManagerImpl;
import dao.impl.SalesManagerImpl;
import dao.impl.SumStockManagerImpl;
import entity.Msg;
import entity.Sales;
import entity.SumStock;
import entity.Supplier;
import enumType.Statu;
import service.ISalesService;
import utils.JDBCUtil;

public class SalesServiceImpl implements ISalesService {
	private ISalesManager salesManager = new SalesManagerImpl();
	private IGoodsManager goodsManager = new GoodsManagerImpl();
	private ISumStockManager sumStockManager = new SumStockManagerImpl();
	Msg msg = new Msg();

	/*
	 * @Override public Msg insert(Sales s) { if (s == null) { return new
	 * Msg(Statu.INPUTERROR); } /*Sales sales =
	 * salesManager.getBySId(s.getDanhao()); if (sales != null) { return new
	 * Msg(Statu.INPUTERROR); }
	 */
	/*
	 * try { JDBCUtil.getConn().setAutoCommit(false); salesManager.insert(s);
	 * JDBCUtil.getConn().commit(); } catch (SQLException e) { // TODO
	 * Auto-generated catch block e.printStackTrace(); }
	 * 
	 * return new Msg(Statu.SUCCESS); }
	 */

	public Msg insertP(int danhao, int id) {
		/*if (danhao == null) {
			return new Msg(Statu.INPUTERROR);
		}
		Sales salesP = (Sales) salesManager.getBySId(s.getDanhao());
		if (salesP != null) {
			return new Msg(Statu.INPUTERROR);
		}*/
		try {
			JDBCUtil.getConn().setAutoCommit(false);
			Sales sales = new Sales(danhao,id);
			salesManager.insertP(sales);
			JDBCUtil.getConn().commit();
		} catch (SQLException e) {
			// TODOAuto-generated catch block
			e.printStackTrace();
		}

		return new Msg(Statu.SUCCESS);
	}

	// 销售
	public Msg add(int danhao, int id, int num, int p_id) {
		try {
			JDBCUtil.getConn().setAutoCommit(false);
			// 获取价格
			double price = goodsManager.getByPrice(id);
			double sum = price * num;
			// 获取当前日期
			SimpleDateFormat tempDate = new SimpleDateFormat("yyyy-MM-dd");
			String selldate = tempDate.format(new java.util.Date());
			Sales sales = new Sales(danhao, id, num, sum, selldate);
			// Sales sales1 = new Sales(danhao,p_id);
			// 添加记录
			salesManager.insert(sales);
			// salesManager.insertP(sales1);
			// 修改库存
			int num1 = sumStockManager.getByGId(id);
			int snum = num1 - num;
			SumStock sumStock = new SumStock(id, snum);
			sumStockManager.update(sumStock);
			JDBCUtil.getConn().commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new Msg(Statu.SUCCESS);
	}

	// 根据单号删除
	@Override
	public Msg delete(int danhao) {
		if (danhao <= 0) {
			return new Msg(Statu.INPUTERROR);
		}
		List<Sales> salesById = salesManager.getBySId(danhao);
		if (salesById == null) {
			return new Msg(Statu.ISNULL);
		} else {
			try {
				JDBCUtil.getConn().setAutoCommit(false);
				salesManager.delete(danhao);
				JDBCUtil.getConn().commit();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			return new Msg(Statu.SUCCESS);
		}
	}

	// 根据商品编号删除
	/*
	 * public Msg deleteG(int gs_id) { if (gs_id <= 0) { return new
	 * Msg(Statu.INPUTERROR); } Sales salesById = salesManager.getByGId(gs_id); if
	 * (salesById == null) { return new Msg(Statu.ISNULL); } else { try {
	 * JDBCUtil.getConn().setAutoCommit(false); salesManager.delete(gs_id);
	 * JDBCUtil.getConn().commit(); } catch (SQLException e) { // TODO
	 * Auto-generated catch block e.printStackTrace(); }
	 * 
	 * return new Msg(Statu.SUCCESS); } }
	 */

	// 更新
	/*
	 * @Override public Msg update(Sales s) { if (s.getDanhao() <= 0) { return new
	 * Msg(Statu.INPUTERROR); } Sales salesBySId =
	 * salesManager.getBySId(s.getDanhao()); if (salesBySId == null) { return new
	 * Msg(Statu.NOTFIND); } else { try { JDBCUtil.getConn().setAutoCommit(false);
	 * salesManager.update(s); JDBCUtil.getConn().commit(); } catch (SQLException e)
	 * { // TODO Auto-generated catch block e.printStackTrace(); } return new
	 * Msg(Statu.SUCCESS); } }
	 * 
	 * public Msg updateP(Sales s) { if (s.getDanhao() <= 0) { return new
	 * Msg(Statu.INPUTERROR); } Sales salesBySId =
	 * salesManager.getBySId(s.getDanhao()); if (salesBySId == null) { return new
	 * Msg(Statu.NOTFIND); } else { try { JDBCUtil.getConn().setAutoCommit(false);
	 * salesManager.updateP(s); JDBCUtil.getConn().commit(); } catch (SQLException
	 * e) { // TODO Auto-generated catch block e.printStackTrace(); } return new
	 * Msg(Statu.SUCCESS); } }
	 */

	public Msg update(int id, int gid, int num, String date, int pid) {
		List<Sales> salesBySId = salesManager.getBySId(id);
		if (salesBySId == null) {
			return new Msg(Statu.NOTFIND);
		} else {
			try {
				JDBCUtil.getConn().setAutoCommit(false);
				// 获取价格
				double price = goodsManager.getByPrice(gid);
				double sum = price * num;
				Sales sales = new Sales(id, gid, num, sum, date, pid);
				Sales sales1 = new Sales(id, pid);
				salesManager.update(sales);
				salesManager.updateP(sales1);
				JDBCUtil.getConn().commit();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return new Msg(Statu.SUCCESS);
		}
	}

	@Override
	public Msg getBySId(int danhao) {
		Msg msg = new Msg(Statu.SUCCESS);
		try {
			JDBCUtil.getConn().setAutoCommit(false);
			msg.setObj(salesManager.getBySId(danhao));
			JDBCUtil.getConn().commit();
		} catch (Exception e) {
			return new Msg(Statu.ERROR);
		}
		return msg;
	}

	public Msg getByGId(int gs_id) {
		Msg msg = new Msg(Statu.SUCCESS);
		try {
			JDBCUtil.getConn().setAutoCommit(false);
			msg.setObj(salesManager.getByGId(gs_id));
			JDBCUtil.getConn().commit();
		} catch (Exception e) {
			return new Msg(Statu.ERROR);
		}
		return msg;
	}

	public Msg getBySPId(int danhao) {
		Msg msg = new Msg(Statu.SUCCESS);
		try {
			JDBCUtil.getConn().setAutoCommit(false);
			msg.setObj(salesManager.getBySPId(danhao));
			JDBCUtil.getConn().commit();
		} catch (Exception e) {
			return new Msg(Statu.ERROR);
		}
		return msg;
	}

	@Override
	public Msg getSales() {
		Msg msg = new Msg(Statu.SUCCESS);
		try {
			JDBCUtil.getConn().setAutoCommit(false);
			msg.setObj(salesManager.getSales());
			JDBCUtil.getConn().commit();
		} catch (Exception e) {
			return new Msg(Statu.ERROR);
		}
		return msg;
	}

	public Msg getSum(String selldate) {
		Msg msg = new Msg(Statu.SUCCESS);
		try {
			JDBCUtil.getConn().setAutoCommit(false);
			msg.setObj(salesManager.getSum(selldate));
			JDBCUtil.getConn().commit();
		} catch (Exception e) {
			return new Msg(Statu.ERROR);
		}
		return msg;
	}
}
