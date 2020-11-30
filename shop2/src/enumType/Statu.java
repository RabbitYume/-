package enumType;

public enum Statu {
	ERROR(1001,"系统异常"),SUCCESS(1002,"操作成功"),ISNULL(1003,"空值"),INPUTERROR(1004,"输入错误"),NOTFIND(1005,"找不到对象");
	
	int code;
	String msg;
	
	private Statu(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
}
