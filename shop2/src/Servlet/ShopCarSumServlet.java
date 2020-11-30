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
		// ��ȡǰ̨���������ݲ���ѡ���Ӧ��servlet��������ҵ����
		String op = req.getParameter("op");

		if ("insert".equals(op)) {// ���빺�ﳵ
			insertService(req, resp);
		} else if ("del".equals(op)) {// ɾ��
			deleteService(req, resp);
		} else if ("update".equals(op)) {// �޸�
			updateService(req, resp);
		} else if ("total".equals(op)) { // ����
			totalService(req, resp);
		} else if ("select".equals(op)) { // ��ѯ
			selectService(req, resp);
		} else if ("delall".equals(op)) { // ���
			delallService(req, resp);
		}
	}

	/**
	 * ���빺�ﳵ
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
	 * ɾ��
	 * 
	 * @param request
	 * @param response
	 */
	private void deleteService(HttpServletRequest req, HttpServletResponse resp) {
		// ����ǰ̨���ݵĴ�ɾ��ID������idɾ��
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
	 * ����
	 * 
	 * @param request
	 * @param response
	 */
	private void updateService(HttpServletRequest req, HttpServletResponse resp) {
		// ��ȡǰ̨���µĲ���������id���и���
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
	 * ����&������ۼ�¼
	 * 
	 * @param request
	 * @param response
	 */
	private void totalService(HttpServletRequest req, HttpServletResponse resp) {
		HttpSession session = req.getSession();
		int pid = (int) session.getAttribute("pid");
		// ����
		Random random = new Random();
		int danhao = random.nextInt(899999) + 100000;
		// ��ѯ���ﳵ��¼
		Msg msg = shopCarService.getShopCars(pid);
		List<ShopCar> shopCars = (List<ShopCar>) msg.getObj();
		req.setAttribute("shopCars", shopCars);
		// ������ۼ�¼
		for (ShopCar shopcar : shopCars) {
			int gid = shopcar.getGc_id();
			int num = shopcar.getNum();
			salesService.add(danhao,gid, num, pid);
		}
		salesService.insertP(danhao, pid);
		// ��չ��ﳵ
		shopCarService.delall(pid);
		try {
			resp.sendRedirect(req.getContextPath() + "/zhuye.jsp");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * ��ѯ��Ʒ
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
	 * ��չ��ﳵ
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
