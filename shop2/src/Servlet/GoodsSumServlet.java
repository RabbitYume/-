package Servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.Goods;
import entity.Msg;
import service.IGoodsService;
import service.impl.GoodsServiceImpl;

@WebServlet("/GoodsSumServlet")
public class GoodsSumServlet extends HttpServlet{
	static IGoodsService goodsService = new GoodsServiceImpl();
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
		Msg msg2 = goodsService.getByGId(id);
		Goods good = (Goods) msg2.getObj();
		req.setAttribute("good", good);
		try {
			req.getRequestDispatcher("goodslist.jsp").forward(req, resp);
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
		String brand = req.getParameter("brand");
		String model = req.getParameter("model");
		double price = Double.parseDouble(req.getParameter("price"));
		Goods goods = new Goods(id,name,model,price);
		Msg add = goodsService.add(goods, brand);
		try {
			resp.sendRedirect(req.getContextPath()+"/goodsindex.jsp");
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
		
		//接收前台传递的待删除ID，根据id删除对应商品
		int id = Integer.parseInt(req.getParameter("id"));
		Msg msg = goodsService.delete(id);
		//req.getRequestDispatcher("/index.jsp").forward(req, resp);
		try {
			resp.sendRedirect(req.getContextPath()+"/goodsindex.jsp");
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
		//获取前台更新商品的参数，根据商品id进行更新
		int id = Integer.parseInt(req.getParameter("id"));
		String name = req.getParameter("name");
		String brand = req.getParameter("brand");
		String model = req.getParameter("model");
		double price = Double.parseDouble(req.getParameter("price"));
		Goods goods = new Goods(id,name,model,price);
		Msg update = goodsService.update(goods);

		try {
			req.getRequestDispatcher("/goodsindex.jsp").forward(req, resp);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
