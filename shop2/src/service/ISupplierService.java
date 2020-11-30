package service;

import entity.Msg;
import entity.Supplier;

public interface ISupplierService {
	public Msg insert(Supplier s);
	public Msg delete(int s_id);
	public Msg update(Supplier s);
	public Msg getBySId(int s_id);
	public Msg getBySName(String s_name);
	public Msg getSupplier();
}
