package Servlet;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.Msg;
import entity.Page;
import entity.People;
import service.IPeopleService;
import service.impl.PeopleServiceImpl;
import utils.JDBCUtil;

@WebServlet("/PageServlet")
public class PageServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			showtwo(req, resp);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/*public void showone(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		IPeopleService peopleService = new PeopleServiceImpl();
		Msg msg = peopleService.getPeople();
		List<People> peoples = (List<People>) msg.getObj();
		request.setAttribute("peoples", peoples);
		request.getRequestDispatcher("index1.jsp").forward(request, response);

	}*/

	public void showtwo(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		// 当前是第几页
		String currentpageStr = request.getParameter("currentpage") == null ? "1" : request.getParameter("currentpage");
		int currentpage = Integer.parseInt(currentpageStr);
		// 每页显示多少条
		int maximum = 5;
		// 可以显示多少页
		int viewperpage = 5;
		
		PreparedStatement ps = null;
		String sql = "select * from people p,people_data pd where p.p_id = pd.p_id and p.p_name = pd.p_name limit " + (currentpage - 1) * maximum + "," + maximum;
		ps = JDBCUtil.getConn().prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		List<People> peoples = new ArrayList<People>();
		while (rs.next()) {
			People people = new People();
			people.setP_id(rs.getInt("p_id"));
			people.setP_name(rs.getString("p_name"));
			people.setP_gender(rs.getString("p_gender"));
			people.setP_wage(rs.getInt("p_wage"));
			people.setP_position(rs.getString("p_position"));
			people.setP_pwd(rs.getString("p_pwd"));
			people.setP_dang(rs.getString("p_dang"));
			people.setP_resume(rs.getString("p_resume"));
			people.setP_photo(rs.getString("p_photo"));
			peoples.add(people);
		}
		long totalrecordnumber = count();
		System.out.println("people"+peoples);
		System.out.println("total"+totalrecordnumber);
		// 将数据都封装到pageView
		Page pageView = new Page(peoples, totalrecordnumber, currentpage, maximum, viewperpage);
		request.setAttribute("pageView", pageView);
		request.setAttribute("peoples", peoples);
		request.getRequestDispatcher("index1.jsp").forward(request, response);
	}
	
	public int count() {
		PreparedStatement ps = null;
		int count = 0;
		try {
			String sql = "select count(*) from people";
			ps = JDBCUtil.getConn().prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			rs.next();
			count = rs.getInt(1);
			System.out.println(count);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return count;
	}

}
