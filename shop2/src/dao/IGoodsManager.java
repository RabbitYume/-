package dao;

import java.util.List;

import entity.Goods;
/*商品信息操作接口*/
public interface IGoodsManager {
	public boolean insert (Goods g);
	public int delete(int g_id);
	public int update(Goods g);
	public Goods getByGId(int g_id);
	public double getByPrice(int g_id);
	public List<Goods> getGoods();
}
