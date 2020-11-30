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
import entity.Stock;
import service.IStockService;
import service.ISumStockService;
import service.impl.StockServiceImpl;
import service.impl.SumStockServiceImpl;

@WebServlet("/StockSumServlet")
public class StockSumServlet extends HttpServlet{
	static IStockService stockService = new StockServiceImpl();
	static ISumStockService sumStockService = new SumStockServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 获取前台参数，根据参数选择对应的servlet方法进行业务处理
		String op = req.getParameter("op");
		
		if ("returng".equals(op)) {// 退回
			returngService(req, resp);
		}else if ("insert".equals(op)) {// 入库
			insertService(req, resp);
		} else if ("del".equals(op)) {// 删除记录
			deleteService(req, resp);
		} else if ("update".equals(op)) {// 修改记录
			updateService(req, resp);
		} else if ("select".equals(op)) {// 查询记录
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
		Msg msg2 = stockService.getByGId(id);
		//Stock stock = (Stock) msg2.getObj();
		//req.setAttribute("stock", stock);
		List<Stock> stocks = (List<Stock>) msg2.getObj();
		req.setAttribute("stocks", stocks);
		try {
			req.getRequestDispatcher("stocklist.jsp").forward(req, resp);
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
		int sid = Integer.parseInt(req.getParameter("sid"));
		SimpleDateFormat tempDate1 = new SimpleDateFormat("yyyy-MM-dd");
		String date = tempDate1.format(new java.util.Date());
		double price = Double.parseDouble(req.getParameter("price"));
		int num = Integer.parseInt(req.getParameter("num"));
		Stock stock = new Stock(id,sid,date,price,num);
		Msg add = stockService.insert(stock);
		try {
			resp.sendRedirect(req.getContextPath()+"/stockindex.jsp");
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
		Msg msg = stockService.delete(id);
		//req.getRequestDispatcher("/index.jsp").forward(req, resp);
		try {
			resp.sendRedirect(req.getContextPath()+"/stockindex.jsp");
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
		int sid = Integer.parseInt(req.getParameter("sid"));
		String date = req.getParameter("date");
		double price = Double.parseDouble(req.getParameter("price"));
		int num = Integer.parseInt(req.getParameter("num"));
		Stock stock = new Stock(gid,sid,date,price,num,id);
		Msg update = stockService.update(stock);
		try {
			req.getRequestDispatcher("/stockindex.jsp").forward(req, resp);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 退回
	 * @param request
	 * @param response
	 */
	private void returngService(HttpServletRequest req,
			HttpServletResponse resp) {
		//获取前台更新的参数，根据id进行更新
		int id = Integer.parseInt(req.getParameter("id"));
		int num = Integer.parseInt(req.getParameter("num"));
		Stock stock = new Stock(id,num);
		Msg update = sumStockService.returnST(id, num);
		try {
			req.getRequestDispatcher("/stockindex.jsp").forward(req, resp);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
