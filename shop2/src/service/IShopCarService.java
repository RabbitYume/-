package service;

import entity.Msg;
import entity.ShopCar;

public interface IShopCarService {
	public Msg insert(int id, int pid);
	public Msg delete(int gc_id,int pc_id);
	public Msg delall(int pc_id);
	public Msg update(int id,int num);
	public Msg getByGId(int gc_id,int pc_id);
	public Msg getNum(int gc_id);
	public Msg getShopCars(int pc_id);
}
