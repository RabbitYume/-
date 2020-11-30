package dao;

import java.util.List;

import entity.Sales;


public interface ISalesManager {
	public boolean insert (Sales s);
	public boolean insertP (Sales s);
	public int delete(int danhao);
	public int deleteG(int gs_id);
	public int update(Sales s);
	public int updateP(Sales s);
	public List<Sales> getBySId(int danhao);
	public Sales getByGId(int gs_id);
	public Sales getBySPId(int danhao);
	public List<Sales> getSales();
	public Sales getSum(String selldate);
}
