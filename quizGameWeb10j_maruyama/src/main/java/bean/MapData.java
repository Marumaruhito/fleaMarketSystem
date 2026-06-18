package bean;

public class MapData {

	//変数宣言
	private int dataID;				//データID
	private String prefectureName;	//県名
	private int gameType;			//ゲームタイプ
	private String fileName;
	
	//コンストラクタ
	public MapData() {
		this.dataID = 0;
		this.prefectureName = null;
		this.gameType = 0;
		this.fileName = null;
	}
	
	//各変数Getter
	public int getDataID() { return dataID; }
	public String getPrefectureName() { return prefectureName; }
	public int getGameType() { return gameType; }
	public String getFileName() { return fileName; }
	
	//各変数Setter
	public void setDataID(int dataID) { this.dataID = dataID; }	
	public void setPrefectureName(String prefectureName) { this.prefectureName = prefectureName; }	
	public void setGameType(int gameType) { this.gameType = gameType; }	
	public void setFileName(String fileName) {this.fileName = fileName; }
}
