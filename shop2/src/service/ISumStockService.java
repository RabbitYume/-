package service;

import entity.Msg;
import entity.SumStock;

public interface ISumStockService {
	//public Msg insert(SumStock ss);
	//public Msg update(SumStock ss);
	public Msg getByGId(int gg_id);
	public Msg getGoods();
	public Msg returnST(int gg_id,int rnum);
}
