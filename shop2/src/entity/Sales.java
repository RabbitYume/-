package entity;

/*销售情况*/
public class Sales {
	private int danhao;
	private int gs_id;
	private int num;
	private double price;
	private String selldate;
	private int ps_id;
	private int id;
	public int getDanhao() {
		return danhao;
	}
	public void setDanhao(int danhao) {
		this.danhao = danhao;
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
	public String getSelldate() {
		return selldate;
	}
	public void setSelldate(String selldate) {
		this.selldate = selldate;
	}
	public int getGs_id() {
		return gs_id;
	}
	public void setGs_id(int gs_id) {
		this.gs_id = gs_id;
	}
	public int getPs_id() {
		return ps_id;
	}
	public void setPs_id(int ps_id) {
		this.ps_id = ps_id;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Sales(int danhao, int gs_id, int num, double price, String selldate, int ps_id) {
		super();
		this.danhao = danhao;
		this.gs_id = gs_id;
		this.num = num;
		this.price = price;
		this.selldate = selldate;
		this.ps_id = ps_id;
	}
	public Sales(int danhao, int gs_id, int num, double price, String selldate) {
		super();
		this.danhao = danhao;
		this.gs_id = gs_id;
		this.num = num;
		this.price = price;
		this.selldate = selldate;
	}
	
	public Sales(String selldate, double price) {
		super();
		this.selldate = selldate;
		this.price = price;
		
	}
	public Sales(int danhao, int ps_id) {
		super();
		this.danhao = danhao;
		this.ps_id = ps_id;
	}
	public Sales(int danhao, int gs_id, int num, double price, String selldate, int ps_id, int id) {
		super();
		this.danhao = danhao;
		this.gs_id = gs_id;
		this.num = num;
		this.price = price;
		this.selldate = selldate;
		this.ps_id = ps_id;
		this.id = id;
	}
	public Sales() {
		super();
	}
	@Override
	public String toString() {
		return "Sales [成交编号=" + danhao + ", 商品编号=" + gs_id + ", 销售数量=" + num + ", 总金额=" + price + ", 销售日期="
				+ selldate + ", 员工编号=" + ps_id + "]";
	}
	
}
