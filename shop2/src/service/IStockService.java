package service;

import entity.Msg;
import entity.Stock;

public interface IStockService {
	public Msg insert(Stock s);
	public Msg delete(int id);
	public Msg update(Stock s);
	public Msg updateSell(Stock s);
	public Msg getByGId(int g_id);
	public Msg getBySId(int s_id);
	public Msg getStocks();
}
