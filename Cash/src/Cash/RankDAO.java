package Cash;

import java.sql.SQLException;

public class RankDAO extends DBDAO{
	
	public int rankRenewal(String userId, int score, int gmLevel) {

		int result = 0;
		
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
			psmt.setString(1, userId);
			psmt.setInt(2, score);
			psmt.setInt(3, gmLevel);
			result = psmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			getClose();

		}
		return result;
	}
	
	public RankDTO levelSelect(String userId, int gmLevel) {
		
		RankDTO dto = null;
		
		try {
		getConn();
		
		String sql = "select * from rank where user_id = '?' and gm_level ='?';";
		
			psmt = conn.prepareStatement(sql);
			
			psmt.setString(1, userId);
			psmt.setInt(2, gmLevel);
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				dto.setUserId(rs.getString("user_id"));
				dto.setGmClear(rs.getString("gm_clear"));
				dto.setGmLevel(rs.getInt("gm_level"));
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {

			getClose();

		}
		
		return dto;
		
	}
	
	
	
}