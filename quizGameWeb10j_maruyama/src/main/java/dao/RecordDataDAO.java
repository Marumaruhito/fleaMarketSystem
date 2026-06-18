package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import bean.GameData;
import bean.RecordData;

public class RecordDataDAO {
	//DB情報をフィールド変数に定義
	private static String RDB_DRIVE = "com.mysql.cj.jdbc.Driver";
	private static String URL = "jdbc:mysql://localhost/quizdb";
	private static String USER = "root";
	private static String PASS = "root123";

	//データベース接続
	private static Connection getConnection() {
		try {
			Class.forName(RDB_DRIVE);
			Connection con = DriverManager.getConnection(URL, USER, PASS);
			return con;
		} catch (Exception e) {
			throw new IllegalStateException(e);
		}
	}

	/**
	 * @param gameType
	 * @return 得点を降順・ゲーム終了までの時間を昇順で20件まで取得
	 * @throws Exception
	 */
	public ArrayList<RecordData> selectDataAcordingToGameType(int gameType) throws Exception {
		//変数
		Connection con = null;
		Statement smt = null;

		//配列宣言
		ArrayList<RecordData> list = new ArrayList<RecordData>();

		//SQL文作成
		String sql = "SELECT * FROM recordinfo where game_type = " + gameType
				+ " order by score desc, complete_time asc limit 20";

		try {
			//DB接続
			con = getConnection();
			smt = con.createStatement();

			//SQL文発行
			ResultSet rs = smt.executeQuery(sql);

			//Resultから得た情報をListに格納
			while (rs.next()) {
				RecordData recordData = new RecordData();
				recordData.setCompleteTime(rs.getLong("complete_time"));
				recordData.setGameType(rs.getInt("game_type"));
				//recordData.setMyDataFlag(rs.getBoolean("my_data_flag"));
				recordData.setName(rs.getString("name"));
				recordData.setScore(rs.getInt("score"));

				list.add(recordData);
			}
		} catch (Exception e) {
			System.out.println("Errorが発生しました。" + e);
			throw e;
		} finally {
			if (smt != null) {
				try {
					smt.close();
				} catch (SQLException ignore) {
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException ignore) {
				}
			}
		}
		return list;
	}

	public int selectOrderOfResult(int score, int gameType) throws Exception {

		//変数
		Connection con = null;
		Statement smt = null;

		int rank;

		//SQL文作成
		String sql = "SELECT (od.DataCnt +1) AS OrderOfResult FROM " +
				"(SELECT count(*) AS DataCnt FROM recordinfo WHERE score > " + score + "AND game_type = " + gameType
				+ ") od";

		try {
			//DB接続
			con = getConnection();
			smt = con.createStatement();

			//SQL文発行
			ResultSet rs = smt.executeQuery(sql);

			//Resultから得た情報をListに格納
			RecordData recordData = new RecordData();
			recordData.setCompleteTime(rs.getLong("complete_time"));
			recordData.setGameType(rs.getInt("game_type"));
			//recordData.setMyDataFlag(rs.getBoolean("my_data_flag"));
			recordData.setName(rs.getString("name"));
			recordData.setScore(rs.getInt("score"));

		} catch (Exception e) {
			System.out.println("Errorが発生しました。" + e);
			throw e;
		} finally {
			if (smt != null) {
				try {
					smt.close();
				} catch (SQLException ignore) {
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException ignore) {
				}
			}
		}

		return 0;
	}

	public int getRank(GameData gameData) {
		
		//Rank
		int rank = 999;			//ランク登録できないようにするために999で初期化
		
		//レコードでーた
		ArrayList<RecordData> recordDatas = null;
		try {
			recordDatas = selectDataAcordingToGameType(gameData.getGameType());
		} catch (Exception e) {

		}
		//上から比べてスコアを算出
		for(RecordData r : recordDatas) {
			if(gameData.getScore() > r.getScore()) {
				//スコアが同値より上
				rank = recordDatas.indexOf(r) + 1;
				return rank;
			}
			
			if(gameData.getScore() == r.getScore()) {
				//スコアが同値
				if(gameData.getGameEndTime() - gameData.getGameStartTime() < r.getCompleteTime()) {
					//時間が同値以下
					rank = recordDatas.indexOf(r) + 1;
					return rank;
				}
			}
		}
		//リスト内に自身より低いスコアがなかった場合
		if(recordDatas.size() < 20) {
			//記録が２０個以下ならリストの末番
			rank = recordDatas.size();
			return rank;
		}
		
		return rank;
	}

	public void insertRecord(RecordData recordData) throws Exception {
		//変数
		Connection con = null;
		Statement smt = null;

		//SQL文
		String sql = "INSERT INTO recordinfo VALUES('" + recordData.getName() + "','" + recordData.getScore()
				+ "'," + recordData.getGameType() + ", (SELECT CURDATE()), (SELECT CURTIME()), "
				+ recordData.getCompleteTime() + ")";

		try {
			//DBに接続
			con = getConnection();
			smt = con.createStatement();

			//SQL文発行
			smt.executeUpdate(sql);
		} catch (SQLException e) {
			throw new IllegalStateException(e);
		} finally {
			if (smt != null) {
				try {
					smt.close();
				} catch (SQLException ignore) {
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException ignore) {
				}
			}
		}
	}

}
