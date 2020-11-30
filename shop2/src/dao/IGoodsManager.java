package dao;

import java.util.List;

import entity.Goods;
/*��Ʒ��Ϣ�����ӿ�*/
public interface IGoodsManager {
	public boolean insert (Goods g);
	public int delete(int g_id);
	public int update(Goods g);
	public Goods getByGId(int g_id);
	public double getByPrice(int g_id);
	public List<Goods> getGoods();
}
