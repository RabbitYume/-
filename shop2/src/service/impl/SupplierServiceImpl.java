package service.impl;

import java.sql.SQLException;

import dao.ISupplierManager;
import dao.impl.SupplierManagerImpl;
import entity.Goods;
import entity.Msg;
import entity.Stock;
import entity.Supplier;
import enumType.Statu;
import service.ISupplierService;
import utils.JDBCUtil;

public class SupplierServiceImpl implements ISupplierService {
	private ISupplierManager supplierManager = new SupplierManagerImpl();
	Msg msg = new Msg();

	@Override
	public Msg insert(Supplier s) {
		if (s == null) {
			return new Msg(Statu.INPUTERROR);
		}
		Supplier supplier = supplierManager.getBySId(s.getS_id());
		if (supplier != null) {
			return new Msg(Statu.INPUTERROR);
		}
		try {
			JDBCUtil.getConn().setAutoCommit(false);
			supplierManager.insert(s);
			JDBCUtil.getConn().commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return new Msg(Statu.SUCCESS);
	}

	@Override
	public Msg delete(int s_id) {
		if (s_id <= 0) {
			return new Msg(Statu.INPUTERROR);
		}
		Supplier supplierById = supplierManager.getBySId(s_id);
		if (supplierById == null) {
			return new Msg(Statu.ISNULL);
		} else {
			try {
				JDBCUtil.getConn().setAutoCommit(false);
				supplierManager.delete(s_id);
				JDBCUtil.getConn().commit();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return new Msg(Statu.SUCCESS);
		}
	}

	@Override
	public Msg update(Supplier s) {
		if (s.getS_id() <= 0) {
			return new Msg(Statu.INPUTERROR);
		}
		Supplier supplierBySId = supplierManager.getBySId(s.getS_id());
		if (supplierBySId == null) {
			return new Msg(Statu.NOTFIND);
		} else {
			try {
				JDBCUtil.getConn().setAutoCommit(false);
				supplierManager.update(s);
				JDBCUtil.getConn().commit();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
			return new Msg(Statu.SUCCESS);
		}
	}

	@Override
	public Msg getBySId(int s_id) {
		Msg msg = new Msg(Statu.SUCCESS);
		try {
			msg.setObj(supplierManager.getBySId(s_id));
		} catch (Exception e) {
			return new Msg(Statu.ERROR);
		}
		return msg;
	}
	
	@Override
	public Msg getBySName(String s_name) {
		Msg msg = new Msg(Statu.SUCCESS);
		try {
			msg.setObj(supplierManager.getBySName(s_name));
		} catch (Exception e) {
			return new Msg(Statu.ERROR);
		}
		return msg;
	}

	@Override
	public Msg getSupplier() {
		Msg msg = new Msg(Statu.SUCCESS);
		try {
			msg.setObj(supplierManager.getSupplier());
		} catch (Exception e) {
			return new Msg(Statu.ERROR);
		}
		return msg;
	}

}
