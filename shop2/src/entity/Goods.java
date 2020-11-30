package entity;

/*商品*/
public class Goods {
	private int g_id;
	private String g_name;
	private String g_brand;
	private String g_model;
	private double g_price;
	private int stock;
	public int getG_id() {
		return g_id;
	}
	public void setG_id(int g_id) {
		this.g_id = g_id;
	}
	public String getG_name() {
		return g_name;
	}
	public void setG_name(String g_name) {
		this.g_name = g_name;
	}
	public String getG_brand() {
		return g_brand;
	}
	public void setG_brand(String g_brand) {
		this.g_brand = g_brand;
	}
	public String getG_model() {
		return g_model;
	}
	public void setG_model(String g_model) {
		this.g_model = g_model;
	}
	public double getG_price() {
		return g_price;
	}
	public void setG_price(double g_price) {
		this.g_price = g_price;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public Goods(int g_id, String g_name, String g_model, double g_price) {
		super();
		this.g_id = g_id;
		this.g_name = g_name;
		this.g_model = g_model;
		this.g_price = g_price;
	}
	public Goods(int g_id, String g_name, String g_brand, String g_model, double g_price) {
		super();
		this.g_id = g_id;
		this.g_name = g_name;
		this.g_brand = g_brand;
		this.g_model = g_model;
		this.g_price = g_price;
	}
	public Goods(double g_price) {
		super();
		this.g_price = g_price;
	}
	public Goods(int g_id, String g_name, String g_brand, String g_model, double g_price, int stock) {
		super();
		this.g_id = g_id;
		this.g_name = g_name;
		this.g_brand = g_brand;
		this.g_model = g_model;
		this.g_price = g_price;
		this.stock = stock;
	}
	public Goods() {
		super();
	}
	@Override
	public String toString() {
		return "goods [商品编号=" + g_id + ", 商品名称=" + g_name + ", 品牌=" + g_brand + ", 型号=" + g_model
				+ ", 销售单价=" + g_price + ", 库存=" + stock +"]";
	}
	
}
