package service.impl;

import java.sql.SQLException;

import dao.IGoodsManager;
import dao.ISumStockManager;
import dao.ISupplierManager;
import dao.impl.GoodsManagerImpl;
import dao.impl.SumStockManagerImpl;
import dao.impl.SupplierManagerImpl;
import entity.Goods;
import entity.Msg;
import entity.People;
import enumType.Statu;
import service.IGoodsService;
import utils.JDBCUtil;

public class GoodsServiceImpl implements IGoodsService{
	private IGoodsManager goodsManager = new GoodsManagerImpl();
	private ISupplierManager supplierManager = new SupplierManagerImpl();
	private ISumStockManager sumStockManager = new SumStockManagerImpl();
	Msg msg = new Msg();
	
	//添加商品
	/*@Override
	public Msg insert(Goods g) {
		//判断输入的商品是否为空
		if (g == null) {
			return new Msg(Statu.INPUTERROR);
		}
		//判断商品编号是否存在
		Goods goods = goodsManager.getByGId(g.getG_id());
		if (goods != null) {
			return new Msg(Statu.INPUTERROR);
		}else {
			try {
				JDBCUtil.getConn().setAutoCommit(false);
				goodsManager.insert(g);
				JDBCUtil.getConn().commit();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return new Msg(Statu.SUCCESS);
		}
	}*/
	
	//添加商品
	public Msg add(Goods g,String s_name) {
		//判断输入的商品是否为空
				if (g == null) {
					return new Msg(Statu.INPUTERROR);
				}
				//判断商品编号是否存在
				Goods goods = goodsManager.getByGId(g.getG_id());
				if (goods != null) {
					return new Msg(Statu.INPUTERROR);
				}else {
					try {
						// 开启事务
						JDBCUtil.getConn().setAutoCommit(false);
						//添加商品
						goodsManager.insert(g);
						//取出供货商id
						int s_id = supplierManager.getBySName(s_name);
						//添加库存
						sumStockManager.insert(g.getG_id(),s_id,0);
						//提交
						JDBCUtil.getConn().commit();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					return new Msg(Statu.SUCCESS);
				}
	}
	
	//删除商品
	@Override
	public Msg delete(int g_id) {
		if (g_id <= 0) {
			return new Msg(Statu.INPUTERROR);
		}
		Goods goodsById = goodsManager.getByGId(g_id);
		if (goodsById == null) {
			return new Msg(Statu.ISNULL);
		}else {
			try {
				JDBCUtil.getConn().setAutoCommit(false);
				goodsManager.delete(g_id);
				JDBCUtil.getConn().commit();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return new Msg(Statu.SUCCESS);
		}
	}

	//更新商品
	@Override
	public Msg update(Goods g) {
		if (g.getG_id() <= 0) {
			return new Msg(Statu.INPUTERROR);
		}
		Goods goodsById = goodsManager.getByGId(g.getG_id());
		if (goodsById == null) {
			return new Msg(Statu.NOTFIND);
		}else {
			try {
				JDBCUtil.getConn().setAutoCommit(false);
				goodsManager.update(g);
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
			msg.setObj(goodsManager.getByGId(g_id));
		} catch (Exception e) {
			return new Msg(Statu.ERROR);
		}
		return msg;
	}

	@Override
	public Msg getGoods() {
		Msg msg = new Msg(Statu.SUCCESS);
		try {
			msg.setObj(goodsManager.getGoods());
		} catch (Exception e) {
			return new Msg(Statu.ERROR);
		}
		return msg;
	}

	//获取商品价格
	public Msg getByPrice(int g_id) {
		Msg msg = new Msg(Statu.SUCCESS);
		try {
			msg.setObj(goodsManager.getByPrice(g_id));
		} catch (Exception e) {
			return new Msg(Statu.ERROR);
		}
		return msg;
	}
}
