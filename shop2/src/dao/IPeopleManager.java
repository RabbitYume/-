package dao;

import java.util.List;

import entity.People;

/*员工操作接口*/
public interface IPeopleManager {
	public boolean insert (People p);
	public boolean insertPd (People p);
	public int deleteP(int p_id);
	public int update(People p);
	public int updatePd(People p);
	public People getByPId(int p_id);
	public People getByPdId(int p_id);
	public List<People> getPeople();
	public People loginByPId(int p_id,String p_pwd);
	public int quanxian(int p_id);
}
