package Servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entity.Msg;
import entity.People;
import service.IPeopleService;
import service.impl.PeopleServiceImpl;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("userid"));
		String pwd = req.getParameter("password");
		IPeopleService peopleService = new PeopleServiceImpl();
		Msg msg = peopleService.loginByPId(id, pwd);
		People peopleLogin = (People) msg.getObj();
		int q = 0;
		if (peopleLogin == null) {
			req.getRequestDispatcher("/login.jsp").forward(req, resp);
		} else {
			HttpSession session=req.getSession();
			session.setAttribute("pid", id);
			Msg msg1 = peopleService.quanxian(id);
			q = (int) msg1.getObj();
			session.setAttribute("q", q);
			if (q == 1) {// 管理员
				resp.sendRedirect(req.getContextPath() + "/home.jsp");
			} else if (q == 2) { // 仓库管理(库存、供货商、商品
				resp.sendRedirect(req.getContextPath() + "/home.jsp");
			} else if (q == 3) { // 销售(销售、销售记录
				resp.sendRedirect(req.getContextPath() + "/home.jsp");
			}
		}

	}


}
