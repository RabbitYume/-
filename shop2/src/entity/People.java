package entity;

/*员工*/
public class People {
	private int p_id;
	private String p_name;
	private String p_gender;
	private int p_wage;
	private String p_position;
	private String p_pwd;
	
	private String p_dang;
	private String p_resume;
	private String p_photo;
	public int getP_id() {
		return p_id;
	}
	public void setP_id(int p_id) {
		this.p_id = p_id;
	}
	public String getP_name() {
		return p_name;
	}
	public void setP_name(String p_name) {
		this.p_name = p_name;
	}
	public String getP_gender() {
		return p_gender;
	}
	public void setP_gender(String p_gender) {
		this.p_gender = p_gender;
	}
	public int getP_wage() {
		return p_wage;
	}
	public void setP_wage(int p_wage) {
		this.p_wage = p_wage;
	}
	public String getP_position() {
		return p_position;
	}
	public void setP_position(String p_position) {
		this.p_position = p_position;
	}
	public String getP_pwd() {
		return p_pwd;
	}
	public void setP_pwd(String p_pwd) {
		this.p_pwd = p_pwd;
	}
	public String getP_dang() {
		return p_dang;
	}
	public void setP_dang(String p_dang) {
		this.p_dang = p_dang;
	}
	public String getP_resume() {
		return p_resume;
	}
	public void setP_resume(String p_resume) {
		this.p_resume = p_resume;
	}
	public String getP_photo() {
		return p_photo;
	}
	public void setP_photo(String p_photo) {
		this.p_photo = p_photo;
	}
	public People(int p_id, String p_name, String p_gender, int p_wage, String p_position, String p_pwd, String p_dang,
			String p_resume, String p_photo) {
		super();
		this.p_id = p_id;
		this.p_name = p_name;
		this.p_gender = p_gender;
		this.p_wage = p_wage;
		this.p_position = p_position;
		this.p_pwd = p_pwd;
		this.p_dang = p_dang;
		this.p_resume = p_resume;
		this.p_photo = p_photo;
	}
	public People(int p_id, String p_name, String p_gender, int p_wage, String p_position, String p_pwd) {
		super();
		this.p_id = p_id;
		this.p_name = p_name;
		this.p_gender = p_gender;
		this.p_wage = p_wage;
		this.p_position = p_position;
		this.p_pwd = p_pwd;
	}
	public People(int p_id, String p_name, String p_dang, String p_resume, String p_photo) {
		super();
		this.p_id = p_id;
		this.p_name = p_name;
		this.p_dang = p_dang;
		this.p_resume = p_resume;
		this.p_photo = p_photo;
	}
	
	public People(int p_id, String p_pwd) {
		super();
		this.p_id = p_id;
		this.p_pwd = p_pwd;
	}
	public People(String p_position) {
		super();
		this.p_position = p_position;
	}
	public People() {
		super();
	}
	@Override
	public String toString() {
		return "people [员工编号=" + p_id + ", 员工姓名=" + p_name + ", 性别=" + p_gender + ", 基本工资=" + p_wage
				+ ", 职务=" + p_position + ", 密码=" + p_pwd + ", 是否党员=" + p_dang + ", 简历=" + p_resume
				+ ", 照片=" + p_photo + "]";
	}
	
}
