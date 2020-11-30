package service;

import entity.Msg;
import entity.Sales;

public interface ISalesService {
	//public Msg insert(Sales s);
	public Msg insertP(int danhao,int id);
	public Msg add(int danhao,int id,int num,int p_id);
	public Msg delete(int danhao);
	//public Msg deleteG(int gs_id);
	//public Msg update(Sales s);
	//public Msg updateP(Sales s);
	public Msg update(int id,int gid,int num,String date,int pid);
	public Msg getBySId(int danhao);
	public Msg getByGId(int gs_id);
	public Msg getBySPId(int danhao);
	public Msg getSales();
	public Msg getSum(String selldate);
}
