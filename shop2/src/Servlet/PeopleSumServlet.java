package Servlet;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.Msg;
import entity.People;
import service.IPeopleService;
import service.impl.PeopleServiceImpl;

@WebServlet("/PeopleSumServlet")
public class PeopleSumServlet extends HttpServlet {
	static IPeopleService peopleService = new PeopleServiceImpl();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 获取前台参数，根据参数选择对应的servlet方法进行业务处理
		String op = req.getParameter("op");

		if ("update".equals(op)) {// 更新
			updateService(req, resp);
		} else if ("del".equals(op)) {// 删除
			deleteService(req, resp);
		} else if ("insert".equals(op)) {// 添加
			insertService(req, resp);
		} else if ("select".equals(op)) {// 查询
			selectService(req, resp);
		}
	}
	
	/**
	 * 查询
	 * @param request
	 * @param response
	 */
	private void selectService(HttpServletRequest req,
			HttpServletResponse resp) {
		
		int id = Integer.parseInt(req.getParameter("id"));
		Msg msg2 = peopleService.getByPdId(id);
		People people = (People) msg2.getObj();
		req.setAttribute("people", people);
		try {
			req.getRequestDispatcher("list.jsp").forward(req, resp);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 添加
	 * @param request
	 * @param response
	 */
	private void insertService(HttpServletRequest req,
			HttpServletResponse resp) {
		int id = Integer.parseInt(req.getParameter("id"));
		String name = req.getParameter("name");
		String gender = req.getParameter("gender");
		int wage = Integer.parseInt(req.getParameter("wage"));
		String position = req.getParameter("position");
		String pwd = req.getParameter("pwd");
		String dang = req.getParameter("dang");
		String resume = req.getParameter("resume");
		String photo = req.getParameter("photo");
		if (gender.equals("man")) {
			gender = "男";
		}else {
			gender = "女";
		}
		if (position.equals("sales")) {
			position = "销售";
		}else {
			position = "仓库管理";
		}
		if (dang.equals("yes")) {
			dang = "是";
		}else {
			dang = "否";
		}
		People people = new People(id, name, gender, wage, position, pwd);
		People people1 = new People(id, name, dang, resume, photo);
		Msg add = peopleService.add(people, people1);
		
		try {
			resp.sendRedirect(req.getContextPath()+"/index.jsp");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	/**
	 * 删除
	 * @param request
	 * @param response
	 */
	private void deleteService(HttpServletRequest req,
			HttpServletResponse resp) {
		
		//接收前台传递的待删除ID，根据id删除对应员工
		int id = Integer.parseInt(req.getParameter("id"));
		Msg msg = peopleService.deleteP(id);
		//req.getRequestDispatcher("/index.jsp").forward(req, resp);
		try {
			resp.sendRedirect(req.getContextPath()+"/index.jsp");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	/**
	 * 更新商品
	 * @param request
	 * @param response
	 */
	private void updateService(HttpServletRequest req,
			HttpServletResponse resp) {
		//获取前台更新的参数，根据id进行更新
		int id = Integer.parseInt(req.getParameter("id"));
		String name = req.getParameter("name");
		String gender = req.getParameter("gender");
		System.out.println("1:"+gender);
		int wage = Integer.parseInt(req.getParameter("wage"));
		String position = req.getParameter("position");
		String pwd = req.getParameter("pwd");
		String dang = req.getParameter("dang");
		String resume = req.getParameter("resume");
		String photo = req.getParameter("photo");
		if (gender.equals("man")) {
			gender = "男";
		}else {
			gender = "女";
		}
		if (position.equals("sales")) {
			position = "销售";
		}else {
			position = "仓库管理";
		}
		if (dang.equals("yes")) {
			dang = "是";
		}else {
			dang = "否";
		}
		System.out.println("2:"+gender);
		People people = new People(id, name, gender, wage, position, pwd);
		People people1 = new People(id, name, dang, resume, photo);
		Msg update = peopleService.update(people, people1);
		try {
			req.getRequestDispatcher("/index.jsp").forward(req, resp);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


}
