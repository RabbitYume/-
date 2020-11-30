package Servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.Msg;
import entity.Sales;
import service.ISalesService;
import service.impl.SalesServiceImpl;

@WebServlet("/SalesSumServlet")
public class SalesSumServlet extends HttpServlet{
	static ISalesService salesService = new SalesServiceImpl();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// ��ȡǰ̨���������ݲ���ѡ���Ӧ��servlet��������ҵ����
				String op = req.getParameter("op");
				
				if ("del".equals(op)) {// ɾ����¼
					deleteService(req, resp);
				} else if ("update".equals(op)) {// �޸ļ�¼
					updateService(req, resp);
				} else if ("select".equals(op)) {// ��ѯ��¼
					selectService(req, resp);
				} else if ("total".equals(op)) { // �����̵�
					totalService(req,resp);
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
		Msg msg2 = salesService.getBySId(id);
		List<Sales> sales = (List<Sales>) msg2.getObj();
		req.setAttribute("sales", sales);
		try {
			req.getRequestDispatcher("saleslist.jsp").forward(req, resp);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
		//����ǰ̨���ݵĴ�ɾ��ID������idɾ��
		int id = Integer.parseInt(req.getParameter("id"));
		Msg msg = salesService.delete(id);
		//req.getRequestDispatcher("/index.jsp").forward(req, resp);
		try {
			resp.sendRedirect(req.getContextPath()+"/salesindex.jsp");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	/**
	 * ����
	 * @param request
	 * @param response
	 */
	private void updateService(HttpServletRequest req,
			HttpServletResponse resp) {
		//��ȡǰ̨���µĲ���������id���и���
		int id = Integer.parseInt(req.getParameter("id"));
		int gid = Integer.parseInt(req.getParameter("gid"));
		int num = Integer.parseInt(req.getParameter("num"));
		String date = req.getParameter("date");
		int pid = Integer.parseInt(req.getParameter("pid"));
		Msg update = salesService.update(id,gid,num,date,pid);
		try {
			req.getRequestDispatcher("/salesindex.jsp").forward(req, resp);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * �̵�
	 * @param request
	 * @param response
	 */
	private void totalService(HttpServletRequest req,
			HttpServletResponse resp) {
		//��ȡǰ̨���µĲ���������id���и���
		String date = req.getParameter("date");
		Msg total = salesService.getSum(date);
		Sales sales = (Sales) total.getObj();
		req.setAttribute("sales", sales);
		try {
			req.getRequestDispatcher("salestotal.jsp").forward(req, resp);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
