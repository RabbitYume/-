package service;

import entity.Msg;
import entity.People;

public interface IPeopleService {
	/*public Msg insert(People p);
	public Msg insertPd(People p);*/
	public Msg add(People p,People p1);
	public Msg deleteP(int p_id);
	/*public Msg update(People p);
	public Msg updatePd(People p);*/
	public Msg update(People p,People p1);
	public Msg getByPId(int p_id);
	public Msg getByPdId(int p_id);
	public Msg getPeople();
	public Msg loginByPId(int p_id,String p_pwd);
	public Msg quanxian(int p_id);
}
