package service.impl;

import java.sql.SQLException;
import java.util.List;

import dao.IStockManager;
import dao.ISumStockManager;
import dao.impl.StockManagerImpl;
import dao.impl.SumStockManagerImpl;
import entity.Goods;
import entity.Msg;
import entity.Stock;
import entity.SumStock;
import enumType.Statu;
import service.IStockService;
import utils.JDBCUtil;

public class StockServiceImpl implements IStockService {
	private IStockManager stockManager = new StockManagerImpl();
	private ISumStockManager sumStockManager = new SumStockManagerImpl();
	Msg msg = new Msg();

	// 进货
	@Override
	public Msg insert(Stock s) {
		if (s == null) {
			return new Msg(Statu.INPUTERROR);
		}
		/*
		 * Stock stock = stockManager.getByGId(s.getG_id()); if (stock != null) { return
		 * new Msg(Statu.INPUTERROR); }
		 */
		try {
			JDBCUtil.getConn().setAutoCommit(false);

			stockManager.insert(s);
			// 查询商品库存
			int num = sumStockManager.getByGId(s.getG_id());
			// 加入库存
			int anum = num + s.getStock();
			SumStock sumStock = new SumStock(s.getG_id(), anum);
			sumStockManager.update(sumStock);

			JDBCUtil.getConn().commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return new Msg(Statu.SUCCESS);
	}

	@Override
	public Msg delete(int id) {
		if (id <= 0) {
			return new Msg(Statu.INPUTERROR);
		}
		try {
			JDBCUtil.getConn().setAutoCommit(false);
			stockManager.delete(id);
			JDBCUtil.getConn().commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return new Msg(Statu.SUCCESS);
	}

	@Override
	public Msg update(Stock s) {
		if (s.getId() <= 0) {
			return new Msg(Statu.INPUTERROR);
		}
		List<Stock> stockById = stockManager.getByGId(s.getG_id());
		if (stockById == null) {
			return new Msg(Statu.NOTFIND);
		} else {
			try {
				JDBCUtil.getConn().setAutoCommit(false);
				stockManager.update(s);
				JDBCUtil.getConn().commit();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			return new Msg(Statu.SUCCESS);
		}
	}

	public Msg updateSell(Stock s) {
		if (s.getG_id() <= 0) {
			return new Msg(Statu.INPUTERROR);
		}
		List<Stock> stockById = stockManager.getByGId(s.getG_id());
		if (stockById == null) {
			return new Msg(Statu.NOTFIND);
		} else {
			try {
				JDBCUtil.getConn().setAutoCommit(false);
				stockManager.updateSell(s);
				JDBCUtil.getConn().commit();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			return new Msg(Statu.SUCCESS);
		}
	}

	@Override
	public Msg getByGId(int g_id) {
		Msg msg = new Msg(Statu.SUCCESS);
		try {
			msg.setObj(stockManager.getByGId(g_id));
		} catch (Exception e) {
			return new Msg(Statu.ERROR);
		}
		return msg;
	}

	@Override
	public Msg getBySId(int s_id) {
		Msg msg = new Msg(Statu.SUCCESS);
		try {
			msg.setObj(stockManager.getBySId(s_id));
		} catch (Exception e) {
			return new Msg(Statu.ERROR);
		}
		return msg;
	}

	@Override
	public Msg getStocks() {
		Msg msg = new Msg(Statu.SUCCESS);
		try {
			msg.setObj(stockManager.getStock());
		} catch (Exception e) {
			return new Msg(Statu.ERROR);
		}
		return msg;
	}

}
