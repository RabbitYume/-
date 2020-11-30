package service.impl;

import java.sql.SQLException;

import dao.ISumStockManager;
import dao.impl.SumStockManagerImpl;
import entity.Goods;
import entity.Msg;
import entity.SumStock;
import enumType.Statu;
import service.ISumStockService;
import utils.JDBCUtil;

public class SumStockServiceImpl implements ISumStockService{
	private ISumStockManager sumStockManager = new SumStockManagerImpl();
	/*@Override
	public Msg insert(SumStock ss) {
		if (ss == null) {
			return new Msg(Statu.INPUTERROR);
		}
		int sumStock = sumStockManager.getByGId(ss.getGg_id());
		if (sumStock < 0) {
			return new Msg(Statu.INPUTERROR);
		}else {
			try {
				JDBCUtil.getConn().setAutoCommit(false);
				sumStockManager.insert(ss);
				JDBCUtil.getConn().commit();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return new Msg(Statu.SUCCESS);
		}
	}*/

	/*@Override
	public Msg update(SumStock ss) {
		if (ss.getGg_id() <= 0) {
			return new Msg(Statu.INPUTERROR);
		}
		int sumStockById = sumStockManager.getByGId(ss.getGg_id());
		if (sumStockById < 0) {
			return new Msg(Statu.NOTFIND);
		}else {
			try {
				JDBCUtil.getConn().setAutoCommit(false);
				sumStockManager.update(ss);
				JDBCUtil.getConn().commit();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return new Msg(Statu.SUCCESS);
		}
	}*/
	
	//商品退回
	public Msg returnST(int gg_id,int rnum) {
		if (gg_id <= 0) {
			return new Msg(Statu.INPUTERROR);
		}
		try {
			JDBCUtil.getConn().setAutoCommit(false);
			//获取库存
			int num = sumStockManager.getByGId(gg_id);
			//库存-退回数量
			rnum = num - rnum;
			SumStock sumStock = new SumStock(gg_id,rnum);
			sumStockManager.update(sumStock);
			
			JDBCUtil.getConn().commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return null;
	}

	@Override
	public Msg getByGId(int gg_id) {
		Msg msg = new Msg(Statu.SUCCESS);
		try {
			msg.setObj(sumStockManager.getByGId(gg_id));
		} catch (Exception e) {
			return new Msg(Statu.ERROR);
		}
		return msg;
	}

	@Override
	public Msg getGoods() {
		Msg msg = new Msg(Statu.SUCCESS);
		try {
			msg.setObj(sumStockManager.getGoods());
		} catch (Exception e) {
			return new Msg(Statu.ERROR);
		}
		return msg;
	}

}
