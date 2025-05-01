package Cash;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CashDAO extends DBDAO{

	

	// 회원가입 메소드
	public int join(String userId, String userPw, String userName) {
		int result = 0;

		try {

			getConn();

			String sql = "INSERT INTO USER_INFO	 VALUES(?,?,?)";

			psmt = conn.prepareStatement(sql);

			// ? 인자의 시작인덱스는 1이다
			psmt.setString(1, userId); // user_id 입력
			psmt.setString(2, userPw); // 입력받은
			psmt.setString(3, userName); // 입력받은 name

			result = psmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			getClose();

		}

		return result;

	}

	// 로그인 메소드
	public CashDTO login(String userId, String userPw) {

		CashDTO dto = null;
		try {
			getConn();

			String sql = "SELECT * FROM USER_INFO WHERE USER_ID = ? AND USER_PW= ?";
			psmt = conn.prepareStatement(sql);

			psmt.setString(1, userId);
			psmt.setString(2, userPw);
			rs = psmt.executeQuery();
			if (rs.next()) {
				dto = new CashDTO();
				dto.setUserId(rs.getString("user_id"));
				dto.setUserName(rs.getString("user_name"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			getClose();

		}

		return dto;

	}

	

}