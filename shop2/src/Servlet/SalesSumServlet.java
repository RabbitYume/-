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
		// 获取前台参数，根据参数选择对应的servlet方法进行业务处理
				String op = req.getParameter("op");
				
				if ("del".equals(op)) {// 删除记录
					deleteService(req, resp);
				} else if ("update".equals(op)) {// 修改记录
					updateService(req, resp);
				} else if ("select".equals(op)) {// 查询记录
					selectService(req, resp);
				} else if ("total".equals(op)) { // 销售盘点
					totalService(req,resp);
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
	 * 删除
	 * @param request
	 * @param response
	 */
	private void deleteService(HttpServletRequest req,
			HttpServletResponse resp) {
		//接收前台传递的待删除ID，根据id删除
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
	 * 更新
	 * @param request
	 * @param response
	 */
	private void updateService(HttpServletRequest req,
			HttpServletResponse resp) {
		//获取前台更新的参数，根据id进行更新
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
	 * 盘点
	 * @param request
	 * @param response
	 */
	private void totalService(HttpServletRequest req,
			HttpServletResponse resp) {
		//获取前台更新的参数，根据id进行更新
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
