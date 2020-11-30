package utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.Properties;

/*数据库连接工具类*/
public class JDBCUtil {
	private static String driver = null;
	private static String url = null;
	private static String name = null;
	private static String pwd = null;
	// 存放数据库连接 的线程池
	private static final ThreadLocal THREAD_LOCAL_DB = new ThreadLocal();
	// 创建属性配置对象
	static {
		try {
			Properties pt = new Properties();
			InputStream inputs = JDBCUtil.class.getClassLoader().getResourceAsStream("utils\\jdbc.properties");
			pt.load(inputs);
			driver = pt.getProperty("driver");
			url = pt.getProperty("url");
			name = pt.getProperty("name");
			pwd = pt.getProperty("password");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 获取连接
	public static Connection getConn() {
		Connection conn = (Connection) THREAD_LOCAL_DB.get();
		try {
			if (conn == null) {
				Class.forName(driver);
				conn = DriverManager.getConnection(url, name, pwd);
				// 把打开的数据库连接对象存放入到线程池中，供下次使用， 省掉创建连接的时间
				THREAD_LOCAL_DB.set(conn);
			}

			//System.out.println("连接成功");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}

	// 释放资源
	public static void release(Connection conn, PreparedStatement ps, ResultSet rs) {
		try {
			if (rs != null) {
				rs.close();
			}
			if (ps != null) {
				ps.close();
			}
			if (conn != null) {
				conn.close();
			}
			//System.out.println("关闭成功");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			rs = null;
			ps = null;
			conn = null;
		}

	}
}
