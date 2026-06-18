package bean;

public class RecordData {

	//変数宣言
	private String name;			//名前
	private int score;				//得点
	private int gameType;			//ゲーム種類
	private long completeTime;		//完了時間
	private boolean myDataFlag;		//登録したデータフラグ
	
	//コンストラクタ
	public RecordData() {
		this.name = null;
		this.score = 0;
		this.gameType = 0;
		this.completeTime = 0;
		this.myDataFlag = false;
	}
	
	//各変数Getter
	public String getName() { return name; }
	public int getScore() { return score; }
	public int getGameType() { return gameType; }
	public long getCompleteTime() { return completeTime; }
	public boolean getMyDataFlag() { return myDataFlag; }
	
	//各変数Getter
	public void setName(String name) { this.name = name; }
	public void setScore(int score) { this.score = score; }
	public void setGameType(int gameType) { this.gameType = gameType; }
	public void setCompleteTime(long completeTime) { this.completeTime = completeTime; }
	public void setMyDataFlag(boolean myDataFlag) { this.myDataFlag = myDataFlag; }
	
}
