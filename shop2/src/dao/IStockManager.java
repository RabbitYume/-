package dao;

import java.sql.SQLException;
import java.util.List;

import entity.Stock;
import utils.JDBCUtil;
/*库存信息操作接口*/
public interface IStockManager {
	public boolean insert (Stock s);
	public int delete(int id);
	public int update(Stock s);
	public int updateSell(Stock s);
	public List<Stock> getByGId(int g_id);
	public List<Stock> getBySId(int s_id);
	public List<Stock> getStock();
}
