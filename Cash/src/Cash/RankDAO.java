package Cash;

import java.sql.SQLException;

public class RankDAO extends DBDAO{
	
	public void rankRenewal(String user_id, int score, String gm_level) {

		try {
			
			getConn();

			String sql = "MERGE INTO RANK r\r\n"
					+ "USING (\r\n"
					+ "  SELECT \r\n"
					+ "    ? AS user_id,\r\n"
					+ "    ? AS score,       \r\n"
					+ "    ? AS gm_level,\r\n"
					+ "    'Y' AS gm_clear         \r\n"
					+ "  FROM dual\r\n"
					+ ") input\r\n"
					+ "ON (r.user_id = input.user_id AND r.gm_level = input.gm_level)\r\n"
					+ "WHEN MATCHED THEN\r\n"
					+ "  UPDATE SET \r\n"
					+ "    r.score = input.score,      \r\n"
					+ "    r.gm_clear = input.gm_clear \r\n"
					+ "WHEN NOT MATCHED THEN\r\n"
					+ "  INSERT (seq, user_id, score, gm_level, gm_clear) \r\n"
					+ "  VALUES (\r\n"
					+ "    RANK_SEQ.NEXTVAL, \r\n"
					+ "    input.user_id, \r\n"
					+ "    input.score,\r\n"
					+ "    input.gm_level,\r\n"
					+ "    input.gm_clear \r\n"
					+ "  )";
			
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, user_id);
			psmt.setInt(2, score);
			psmt.setString(3, gm_level);
			rs = psmt.executeQuery();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			getClose();

		}

	}
	
	
	
}