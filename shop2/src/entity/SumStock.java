package entity;

public class SumStock {
	private int gg_id;
	private int ss_id;
	private int stock;
	public int getGg_id() {
		return gg_id;
	}
	public void setGg_id(int gg_id) {
		this.gg_id = gg_id;
	}
	public int getSs_id() {
		return ss_id;
	}
	public void setSs_id(int ss_id) {
		this.ss_id = ss_id;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public SumStock(int gg_id, int ss_id, int stock) {
		super();
		this.gg_id = gg_id;
		this.ss_id = ss_id;
		this.stock = stock;
	}
	public SumStock(int gg_id, int stock) {
		super();
		this.gg_id = gg_id;
		this.stock = stock;
	}
	public SumStock(int stock) {
		super();
		this.stock = stock;
	}
	public SumStock() {
		super();
	}
	@Override
	public String toString() {
		return "SumStock [商品编号=" + gg_id + ", 供货商编号=" + ss_id + ", 库存数量=" + stock + "]";
	}
	
}
