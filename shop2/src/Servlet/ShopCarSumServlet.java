package Servlet;

import java.io.IOException;
import java.util.List;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.impl.GoodsManagerImpl;
import entity.Goods;
import entity.Msg;
import entity.ShopCar;
import service.IGoodsService;
import service.ISalesService;
import service.IShopCarService;
import service.impl.GoodsServiceImpl;
import service.impl.SalesServiceImpl;
import service.impl.ShopCarServiceImpl;

@WebServlet("/ShopCarSumServlet")
public class ShopCarSumServlet extends HttpServlet {
	static IShopCarService shopCarService = new ShopCarServiceImpl();
	static ISalesService salesService = new SalesServiceImpl();
	static IGoodsService goodsService = new GoodsServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 获取前台参数，根据参数选择对应的servlet方法进行业务处理
		String op = req.getParameter("op");

		if ("insert".equals(op)) {// 加入购物车
			insertService(req, resp);
		} else if ("del".equals(op)) {// 删除
			deleteService(req, resp);
		} else if ("update".equals(op)) {// 修改
			updateService(req, resp);
		} else if ("total".equals(op)) { // 结算
			totalService(req, resp);
		} else if ("select".equals(op)) { // 查询
			selectService(req, resp);
		} else if ("delall".equals(op)) { // 清空
			delallService(req, resp);
		}
	}

	/**
	 * 加入购物车
	 * 
	 * @param request
	 * @param response
	 */
	private void insertService(HttpServletRequest req, HttpServletResponse resp) {
		int gid = Integer.parseInt(req.getParameter("id"));
		HttpSession session = req.getSession();
		int pid = (int) session.getAttribute("pid");
		Msg msg = shopCarService.getByGId(gid, pid);
		ShopCar shopCar = (ShopCar) msg.getObj();
		if (shopCar.getG_name() == null) {
			shopCarService.insert(gid, pid);
		} else {
			Msg msg1 = shopCarService.getNum(gid);
			int num = (int) msg1.getObj();
			int sumnum = num + 1;
			shopCarService.update(gid, sumnum);
		}

		try {
			resp.sendRedirect(req.getContextPath() + "/shopcarindex.jsp");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * 删除
	 * 
	 * @param request
	 * @param response
	 */
	private void deleteService(HttpServletRequest req, HttpServletResponse resp) {
		// 接收前台传递的待删除ID，根据id删除
		int id = Integer.parseInt(req.getParameter("id"));
		HttpSession session = req.getSession();
		int pid = (int) session.getAttribute("pid");
		Msg msg = shopCarService.delete(id, pid);
		System.out.println(msg.getCode());
		// req.getRequestDispatcher("/index.jsp").forward(req, resp);
		try {
			resp.sendRedirect(req.getContextPath() + "/shopcarindex.jsp");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * 更新
	 * 
	 * @param request
	 * @param response
	 */
	private void updateService(HttpServletRequest req, HttpServletResponse resp) {
		// 获取前台更新的参数，根据id进行更新
		int id = Integer.parseInt(req.getParameter("id"));
		int num = Integer.parseInt(req.getParameter("num"));
		Msg update = shopCarService.update(id, num);
		try {
			req.getRequestDispatcher("/shopcarindex.jsp").forward(req, resp);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 结算&添加销售记录
	 * 
	 * @param request
	 * @param response
	 */
	private void totalService(HttpServletRequest req, HttpServletResponse resp) {
		HttpSession session = req.getSession();
		int pid = (int) session.getAttribute("pid");
		// 单号
		Random random = new Random();
		int danhao = random.nextInt(899999) + 100000;
		// 查询购物车记录
		Msg msg = shopCarService.getShopCars(pid);
		List<ShopCar> shopCars = (List<ShopCar>) msg.getObj();
		req.setAttribute("shopCars", shopCars);
		// 添加销售记录
		for (ShopCar shopcar : shopCars) {
			int gid = shopcar.getGc_id();
			int num = shopcar.getNum();
			salesService.add(danhao,gid, num, pid);
		}
		salesService.insertP(danhao, pid);
		// 清空购物车
		shopCarService.delall(pid);
		try {
			resp.sendRedirect(req.getContextPath() + "/zhuye.jsp");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 查询商品
	 * 
	 * @param request
	 * @param response
	 */
	private void selectService(HttpServletRequest req, HttpServletResponse resp) {
		int id = Integer.parseInt(req.getParameter("id"));
		Msg msg2 = goodsService.getByGId(id);
		Goods good = (Goods) msg2.getObj();
		req.setAttribute("good", good);
		try {
			req.getRequestDispatcher("shopcarlist.jsp").forward(req, resp);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 清空购物车
	 * 
	 * @param request
	 * @param response
	 */
	private void delallService(HttpServletRequest req, HttpServletResponse resp) {
		HttpSession session = req.getSession();
		int pid = (int) session.getAttribute("pid");
		shopCarService.delall(pid);
		// req.getRequestDispatcher("/index.jsp").forward(req, resp);
		try {
			resp.sendRedirect(req.getContextPath() + "/shopcarindex.jsp");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
