package Cash;

public class RankDTO {
	
//	USER_ID    VARCHAR2(30)      -- 유저아이디
//    SCORE      INT             -- 클리어시 기존 시간 합데이터 
//    GM_LEVEL      VARCHAR2(10) -- 클리어 난이도
//    GM_CLEAR      VARCHAR2
	private String userId;  // 유저 아이디
	private int score;		 // 클리어시 기존 시간 합데이터
	private int gmLevel; // 클리어 난이도
	private String gmClear; // 클리어 여부 확인
	
	public RankDTO(String userId, int score, int gmLevel, String gmClear) {
		super();
		this.userId = userId;
		this.score = score;
		this.gmLevel = gmLevel;
		this.gmClear = gmClear;
	}
	
	public RankDTO() {
		
	}
	
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getGmLevel() {
		return gmLevel;
	}

	public void setGmLevel(int gmLevel) {
		this.gmLevel = gmLevel;
	}

	public String getGmClear() {
		return gmClear;
	}

	public void setGmClear(String gmClear) {
		this.gmClear = gmClear;
	}
	
	
	
}