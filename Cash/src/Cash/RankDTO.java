package Cash;

public class RankDTO {
	
//	USER_ID    VARCHAR2(30)      -- 유저아이디
//    SCORE      INT             -- 클리어시 기존 시간 합데이터 
//    GM_LEVEL      VARCHAR2(10) -- 클리어 난이도
//    GM_CLEAR      VARCHAR2
	private String user_id;  // 유저 아이디
	private int score;		 // 클리어시 기존 시간 합데이터
	private String gm_level; // 클리어 난이도
	private String gm_clear; // 클리어 여부 확인
	
	public RankDTO(String user_id, int score, String gm_level, String gm_clear) {
		super();
		this.user_id = user_id;
		this.score = score;
		this.gm_level = gm_level;
		this.gm_clear = gm_clear;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public String getGm_level() {
		return gm_level;
	}

	public void setGm_level(String gm_level) {
		this.gm_level = gm_level;
	}

	public String getGm_clear() {
		return gm_clear;
	}

	public void setGm_clear(String gm_clear) {
		this.gm_clear = gm_clear;
	}
	
	
	
}