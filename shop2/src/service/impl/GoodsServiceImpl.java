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
	
	//�����Ʒ
	/*@Override
	public Msg insert(Goods g) {
		//�ж��������Ʒ�Ƿ�Ϊ��
		if (g == null) {
			return new Msg(Statu.INPUTERROR);
		}
		//�ж���Ʒ����Ƿ����
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
	
	//�����Ʒ
	public Msg add(Goods g,String s_name) {
		//�ж��������Ʒ�Ƿ�Ϊ��
				if (g == null) {
					return new Msg(Statu.INPUTERROR);
				}
				//�ж���Ʒ����Ƿ����
				Goods goods = goodsManager.getByGId(g.getG_id());
				if (goods != null) {
					return new Msg(Statu.INPUTERROR);
				}else {
					try {
						// ��������
						JDBCUtil.getConn().setAutoCommit(false);
						//�����Ʒ
						goodsManager.insert(g);
						//ȡ��������id
						int s_id = supplierManager.getBySName(s_name);
						//��ӿ��
						sumStockManager.insert(g.getG_id(),s_id,0);
						//�ύ
						JDBCUtil.getConn().commit();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					return new Msg(Statu.SUCCESS);
				}
	}
	
	//ɾ����Ʒ
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

	//������Ʒ
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

	//��ȡ��Ʒ�۸�
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
