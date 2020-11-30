package dao;

import java.util.List;

import entity.Supplier;

public interface ISupplierManager {
	public boolean insert (Supplier s);
	public int delete(int s_id);
	public int update(Supplier s);
	public Supplier getBySId(int s_id);
	public int getBySName(String s_name);
	public List<Supplier> getSupplier();
}
