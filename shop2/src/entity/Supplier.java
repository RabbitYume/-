package entity;

/*供应商*/
public class Supplier {
	private int s_id;
	private String s_name;
	private String s_address;
	private int s_phone;
	public int getS_id() {
		return s_id;
	}
	public void setS_id(int s_id) {
		this.s_id = s_id;
	}
	public String getS_name() {
		return s_name;
	}
	public void setS_name(String s_name) {
		this.s_name = s_name;
	}
	public String getS_address() {
		return s_address;
	}
	public void setS_address(String s_address) {
		this.s_address = s_address;
	}
	public int getS_phone() {
		return s_phone;
	}
	public void setS_phone(int s_phone) {
		this.s_phone = s_phone;
	}
	public Supplier(int s_id, String s_name, String s_address, int s_phone) {
		super();
		this.s_id = s_id;
		this.s_name = s_name;
		this.s_address = s_address;
		this.s_phone = s_phone;
	}
	public Supplier(int s_id) {
		super();
		this.s_id = s_id;
	}
	public Supplier() {
		super();
	}
	@Override
	public String toString() {
		return "supplier [供货商编号=" + s_id + ", 供货商名称=" + s_name + ", 所在地=" + s_address + ", 联系电话=" + s_phone
				+ "]";
	}
	
}
