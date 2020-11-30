package entity;

public class Msg {
	private int code;//״̬��
	private String content;//״̬���������
	private Object obj;//���ص������Ķ���
	
	public Msg(Object obj) {
		super();
		this.obj = obj;
	}
	public Msg() {
		super();
	}
	public Msg(int code, String content, Object obj) {
		super();
		this.code = code;
		this.content = content;
		this.obj = obj;
	}
	public Msg(int code, String content) {
		super();
		this.code = code;
		this.content = content;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Object getObj() {
		return obj;
	}
	public void setObj(Object obj) {
		this.obj = obj;
	}
	
}
