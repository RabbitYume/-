package utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.Properties;

/*���ݿ����ӹ�����*/
public class JDBCUtil {
	private static String driver = null;
	private static String url = null;
	private static String name = null;
	private static String pwd = null;
	// ������ݿ����� ���̳߳�
	private static final ThreadLocal THREAD_LOCAL_DB = new ThreadLocal();
	// �����������ö���
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

	// ��ȡ����
	public static Connection getConn() {
		Connection conn = (Connection) THREAD_LOCAL_DB.get();
		try {
			if (conn == null) {
				Class.forName(driver);
				conn = DriverManager.getConnection(url, name, pwd);
				// �Ѵ򿪵����ݿ����Ӷ������뵽�̳߳��У����´�ʹ�ã� ʡ���������ӵ�ʱ��
				THREAD_LOCAL_DB.set(conn);
			}

			//System.out.println("���ӳɹ�");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}

	// �ͷ���Դ
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
			//System.out.println("�رճɹ�");
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
