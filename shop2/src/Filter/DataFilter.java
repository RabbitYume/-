package Filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet Filter implementation class DataFilter
 */
@WebFilter(filterName="DataFilter",urlPatterns={"/goodsindex.jsp","/goodslist.jsp","/goodsinsert.jsp","/goodsupdate.jsp",
		"/home.jsp","/index.jsp","/insert.jsp","/list.jsp","/salesindex.jsp","/saleslist.jsp","/salestotal.jsp","/salesupdate.jsp",
		"/shopcarindex.jsp","/shopcarlist.jsp","/shopcarupdate.jsp","/stockindex.jsp","/stockinsert.jsp","/stocklist.jsp","/stockreturng.jsp","/stockupdate.jsp",
		"/supplierindex.jsp","/supplierinsert.jsp","/supplierlist.jsp","/supplierupdate.jsp","/update.jsp","/zhuye.jsp"})
public class DataFilter implements Filter {

	/**
	 * Default constructor.
	 */
	public DataFilter() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;

		// 拿出会话session中的能行证
		Object pid = req.getSession().getAttribute("pid");
		// 如sessionId不等于user里的sessionId，说明：
		// 1、登录成功后，关闭了当前的会话
		// 2、不同的客户端用同一个用户账号登录

		// 如果session中的用户信息为空，就视为未登录
		if (pid == null) {
			resp.sendRedirect(req.getContextPath() + "/login.jsp");
		}

		chain.doFilter(req, resp);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
