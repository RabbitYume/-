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
	 * 添加
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
	 * 删除
	 * @param request
	 * @param response
	 */
	private void deleteService(HttpServletRequest req,
			HttpServletResponse resp) {
		//接收前台传递的待删除ID，根据id删除
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
	 * 更新
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
		//获取前台更新的参数，根据id进行更新
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
