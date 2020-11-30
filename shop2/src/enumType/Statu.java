package enumType;

public enum Statu {
	ERROR(1001,"ϵͳ�쳣"),SUCCESS(1002,"�����ɹ�"),ISNULL(1003,"��ֵ"),INPUTERROR(1004,"�������"),NOTFIND(1005,"�Ҳ�������");
	
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
