package dao;

import java.util.List;

import entity.SumStock;


public interface ISumStockManager {
	//public boolean insert (SumStock ss);
	public boolean insert(int gg_id, int s_id, int i);
	public int update(SumStock ss);
	public int getByGId(int gg_id);
	public List<SumStock> getGoods();
}
