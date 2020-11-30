package Servlet;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.Msg;
import entity.Supplier;
import service.ISalesService;
import service.ISupplierService;
import service.impl.SupplierServiceImpl;

@WebServlet("/SupplierSumServlet")
public class SupplierSumServlet extends HttpServlet{
	static ISupplierService supplierService = new SupplierServiceImpl();
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
		Msg msg2 = supplierService.getBySId(id);
		Supplier supplier = (Supplier) msg2.getObj();
		req.setAttribute("supplier", supplier);
		try {
			req.getRequestDispatcher("supplierlist.jsp").forward(req, resp);
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
		resp.setContentType("text/html;charset=utf-8");
		resp. setCharacterEncoding("UTF-8");
		int id = Integer.parseInt(req.getParameter("id"));
		String name = req.getParameter("name");
		String address = req.getParameter("address");
		int phone = Integer.parseInt(req.getParameter("phone"));
		Supplier supplier = new Supplier(id,name,address,phone);
		Msg add = supplierService.insert(supplier);
		try {
			resp.sendRedirect(req.getContextPath()+"/supplierindex.jsp");
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
		Msg msg = supplierService.delete(id);
		//req.getRequestDispatcher("/index.jsp").forward(req, resp);
		try {
			resp.sendRedirect(req.getContextPath()+"/supplierindex.jsp");
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
		try {
			req. setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//��ȡǰ̨���µĲ���������id���и���
		int id = Integer.parseInt(req.getParameter("id"));
		String name = req.getParameter("name");
		String address = req.getParameter("address");
		int phone = Integer.parseInt(req.getParameter("phone"));
		Supplier supplier = new Supplier(id,name,address,phone);
		Msg update = supplierService.update(supplier);
		System.out.println(supplier);
		try {
			req.getRequestDispatcher("/supplierindex.jsp").forward(req, resp);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
}
