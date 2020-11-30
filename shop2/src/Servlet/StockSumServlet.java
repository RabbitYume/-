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
		// ��ȡǰ̨���������ݲ���ѡ���Ӧ��servlet��������ҵ����
		String op = req.getParameter("op");
		
		if ("returng".equals(op)) {// �˻�
			returngService(req, resp);
		}else if ("insert".equals(op)) {// ���
			insertService(req, resp);
		} else if ("del".equals(op)) {// ɾ����¼
			deleteService(req, resp);
		} else if ("update".equals(op)) {// �޸ļ�¼
			updateService(req, resp);
		} else if ("select".equals(op)) {// ��ѯ��¼
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
	 * ���
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
	 * ɾ��
	 * @param request
	 * @param response
	 */
	private void deleteService(HttpServletRequest req,
			HttpServletResponse resp) {
		//����ǰ̨���ݵĴ�ɾ��ID������idɾ��
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
	 * ����
	 * @param request
	 * @param response
	 */
	private void updateService(HttpServletRequest req,
			HttpServletResponse resp) {
		//��ȡǰ̨���µĲ���������id���и���
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
	 * �˻�
	 * @param request
	 * @param response
	 */
	private void returngService(HttpServletRequest req,
			HttpServletResponse resp) {
		//��ȡǰ̨���µĲ���������id���и���
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
