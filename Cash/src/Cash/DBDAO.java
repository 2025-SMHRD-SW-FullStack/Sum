package Cash;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBDAO {

	protected Connection conn = null;
	protected PreparedStatement psmt = null;
	protected ResultSet rs = null;

	// DB 접근 메소드
	protected void getConn() {

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

			String url = "jdbc:oracle:thin:@project-db-campus.smhrd.com:1524:xe";
			String userName = "campus_25SW_FS_p1_1";
			String pw = "smhrd1";

			conn = DriverManager.getConnection(url, userName, pw);

//			if (conn == null) {
//				System.out.println("연결 실패");
//			} else {
//				System.out.println("연결 성공");
//			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// 사용할 자원을 반납하는 메소드

	protected void getClose() {

		try {
			if (rs != null)
				rs.close();

			if (psmt != null)
				psmt.close();

			if (conn != null)
				conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
}