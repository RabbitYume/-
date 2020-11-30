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
		// ��ȡǰ̨���������ݲ���ѡ���Ӧ��servlet��������ҵ����
		String op = req.getParameter("op");

		if ("update".equals(op)) {// ����
			updateService(req, resp);
		} else if ("del".equals(op)) {// ɾ��
			deleteService(req, resp);
		} else if ("insert".equals(op)) {// ���
			insertService(req, resp);
		} else if ("select".equals(op)) {// ��ѯ
			selectService(req, resp);
		}
	}
	
	/**
	 * ��ѯ
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
	 * ���
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
			gender = "��";
		}else {
			gender = "Ů";
		}
		if (position.equals("sales")) {
			position = "����";
		}else {
			position = "�ֿ����";
		}
		if (dang.equals("yes")) {
			dang = "��";
		}else {
			dang = "��";
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
	 * ɾ��
	 * @param request
	 * @param response
	 */
	private void deleteService(HttpServletRequest req,
			HttpServletResponse resp) {
		
		//����ǰ̨���ݵĴ�ɾ��ID������idɾ����ӦԱ��
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
	 * ������Ʒ
	 * @param request
	 * @param response
	 */
	private void updateService(HttpServletRequest req,
			HttpServletResponse resp) {
		//��ȡǰ̨���µĲ���������id���и���
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
			gender = "��";
		}else {
			gender = "Ů";
		}
		if (position.equals("sales")) {
			position = "����";
		}else {
			position = "�ֿ����";
		}
		if (dang.equals("yes")) {
			dang = "��";
		}else {
			dang = "��";
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
