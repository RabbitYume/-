package dao;

import java.util.List;

import entity.ShopCar;

public interface IShopCarManager {
	public boolean insert(ShopCar sc);
	public int delete(int gc_id,int pc_id);
	public int delall(int pc_id);
	public int update(ShopCar sc);
	public ShopCar getByGId(int gc_id,int pc_id);
	public int getNum(int gc_id);
	public List<ShopCar> getShopCars(int pc_id);
}
