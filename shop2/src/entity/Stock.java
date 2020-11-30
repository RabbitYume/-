package entity;

import java.util.Date;

/*库存*/
public class Stock {
	private int g_id;
	private int s_id;
	private String buyDate;
	private double buyprice;
	private int stock;
	private int id;
	public String getBuyDate() {
		return buyDate;
	}
	public void setBuyDate(String buyDate) {
		this.buyDate = buyDate;
	}
	public double getBuyprice() {
		return buyprice;
	}
	public void setBuyprice(double buyprice) {
		this.buyprice = buyprice;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public int getG_id() {
		return g_id;
	}
	public void setG_id(int g_id) {
		this.g_id = g_id;
	}
	public int getS_id() {
		return s_id;
	}
	public void setS_id(int s_id) {
		this.s_id = s_id;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Stock(int g_id, int s_id, String buyDate, double buyprice, int stock) {
		super();
		this.g_id = g_id;
		this.s_id = s_id;
		this.buyDate = buyDate;
		this.buyprice = buyprice;
		this.stock = stock;
	}
	
	public Stock(int g_id, int s_id, String buyDate, double buyprice, int stock, int id) {
		super();
		this.g_id = g_id;
		this.s_id = s_id;
		this.buyDate = buyDate;
		this.buyprice = buyprice;
		this.stock = stock;
		this.id = id;
	}
	public Stock(int g_id, int stock) {
		super();
		this.g_id = g_id;
		this.stock = stock;
	}
	public Stock() {
		super();
	}
	@Override
	public String toString() {
		return "库存 [商品编号=" + g_id + ", 供货商编号=" + s_id + ", 入库日期=" + buyDate + ", 进货价=" + buyprice + ", 进货数量="
				+ stock + ", id=" + id + "]";
	}
	
}
