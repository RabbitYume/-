package service;

import entity.Goods;
import entity.Msg;

public interface IGoodsService {
	//public Msg insert(Goods g);
	public Msg add(Goods g,String s_name);
	public Msg delete(int g_id);
	public Msg update(Goods g);
	public Msg getByGId(int g_id);
	public Msg getByPrice(int g_id);
	public Msg getGoods();
}
