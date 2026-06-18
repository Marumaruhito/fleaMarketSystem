package bean;

import java.util.ArrayList;

public class GameData {
	
	//変数宣言
	private static final int choiceCount = 4;					//選択肢数
	private static final int beginnerQuestionCount = 10;		//初級編の問題数
	private static final int intermediateQuestionCount = 25;	//中級編の問題数
	
	private int totalQuestionCount;								//問題数
	private int currentQuestionCount;							//現在の問題番号
	private int score;											//スコア
	private int gameType;										//ゲーム種類
	private int ranking;										//順位
	private String gameTitle;									//ゲーム名
	private long gameStartTime;									//ゲームの開始時間
	private long gameEndTime;									//ゲームの終了時間
	
	private ArrayList<String> answer;							//問題の解答（県名）
	private ArrayList<ArrayList<String>> choice;				//選択肢（県名）
	private ArrayList<String> selectAnswer;						//回答
	private ArrayList<String> result;							//結果
	private ArrayList<String> fileName;							//ファイル名
	
	//コンストラクタ
	public GameData() {
		//初期化
		this.totalQuestionCount = 0;
		this.currentQuestionCount = 0;
		this.score = 0;
		this.gameType = 0;
		this.ranking = 0;
		this.gameTitle = null;
		this.gameStartTime = 0;
		this.gameEndTime = 0;
		this.answer = new ArrayList<String>();
		this.choice = new ArrayList<ArrayList<String>>();
		this.selectAnswer = new ArrayList<String>();
		this.result = new ArrayList<String>();
		this.fileName = new ArrayList<String>();
	}
	
	//static変数用のGetter
	public static int getChoiceCount() { return choiceCount; }
	public static int getBeginnerQuestionCount() { return beginnerQuestionCount; }
	public static int getIntermediateQuestionCount() { return intermediateQuestionCount; }

	//各変数Getter
	public int getTotalQuestionCount() { return totalQuestionCount; }
	public int getCurrentQuestionCount() { return currentQuestionCount; }
	public int getScore() { return score; }
	public int getGameType() { return gameType; }
	public int getRanking() { return ranking; }
	public String getGameTitle() { return gameTitle; }
	public long getGameStartTime() { return gameStartTime; }
	public long getGameEndTime() { return gameEndTime; }
	public String getAnswer(int index) { return answer.get(index); }
	public ArrayList<String> getChoice(int index) { return choice.get(index); }
	public String getSelectAnswer(int index) { return selectAnswer.get(index); }
	public ArrayList<String> getSelectAnswer() { return selectAnswer; }
	public String getResult(int index) { return result.get(index); }
	public String getFileName(int index) { return fileName.get(index); }
	
	
	//各変数Setter
	public void setTotalQuestionCount(int totalQuestionCount) { this.totalQuestionCount = totalQuestionCount; }
	public void setCurrentQuestionCount(int currentQuestionCount) { this.currentQuestionCount = currentQuestionCount; }
	public void setScore(int score) { this.score = score; }
	public void setGameType(int gameType) { this.gameType = gameType; }
	public void setRanking(int ranking) { this.ranking = ranking; }
	public void setGameTitle(String gameTitle) { this.gameTitle = gameTitle; }
	public void setGameStartTime(long gameStartTime) { this.gameStartTime = gameStartTime; }
	public void setGameEndTime(long gameEndTime) { this.gameEndTime = gameEndTime; }
	public void setAnswer(String answer) { this.answer.add(answer); }
	public void setChoiceToIndividual(ArrayList<String> fileName) { this.choice.add(fileName); }
	public void setSelectAnswer(String selectAnswer) { this.selectAnswer.add(selectAnswer); }
	public void setResult(String result) { this.result.add(result); }
	public void setFileName(String fileName) { this.fileName.add(fileName); }
	
}
