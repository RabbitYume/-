package entity;

public class ShopCar {
	private int gc_id;
	private String g_name;
	private int num;
	private double price;
	private int pc_id;
	public int getGc_id() {
		return gc_id;
	}
	public void setGc_id(int gc_id) {
		this.gc_id = gc_id;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getG_name() {
		return g_name;
	}
	public void setG_name(String g_name) {
		this.g_name = g_name;
	}
	public int getPc_id() {
		return pc_id;
	}
	public void setPc_id(int pc_id) {
		this.pc_id = pc_id;
	}
	public ShopCar() {
		super();
	}
	public ShopCar(int gc_id, String g_name, int num, double price, int pc_id) {
		super();
		this.gc_id = gc_id;
		this.g_name = g_name;
		this.num = num;
		this.price = price;
		this.pc_id = pc_id;
	}
	
	public ShopCar(int gc_id, int num, double price, int pc_id) {
		super();
		this.gc_id = gc_id;
		this.num = num;
		this.price = price;
		this.pc_id = pc_id;
	}
	
	public ShopCar(int gc_id, int num, double price) {
		super();
		this.gc_id = gc_id;
		this.num = num;
		this.price = price;
	}
	
	public ShopCar(int gc_id, int pc_id) {
		super();
		this.gc_id = gc_id;
		this.pc_id = pc_id;
	}
	
	public ShopCar(int num) {
		super();
		this.num = num;
	}
	@Override
	public String toString() {
		return "购物车 [商品编号=" + gc_id + ", 商品名称=" + g_name + ", 数量=" + num + ", 价格=" + price + "]";
	}

	
}
