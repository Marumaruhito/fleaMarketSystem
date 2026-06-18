package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import bean.MapData;

public class MapDataDAO {
	//DB情報をフィールド変数に定義
	//private static String RDB_DRIVE = "org.mariadb.jdbc.Driver";
	//private static String URL = "jdbc:mariadb://localhost/quizdb";
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
	 * @return マップの全件情報
	 * @throws Exception
	 */
	public ArrayList<MapData> selectAll() throws Exception{
		//変数
		Connection con = null;
		Statement smt = null;

		//配列宣言
		ArrayList<MapData> list = new ArrayList<MapData>();

		//SQL文作成
		String sql = "SELECT * FROM quizinfo";

		try {
			//DB接続
			con = getConnection();
			smt = con.createStatement();

			//SQL文発行
			ResultSet rs = smt.executeQuery(sql);

			//Resultから得た情報をListに格納
			while (rs.next()) {
				MapData mapData = new MapData();
				mapData.setDataID(rs.getInt("data_id"));
				mapData.setGameType(rs.getInt("game_type"));
				mapData.setPrefectureName(rs.getString("prefecture_name"));
				mapData.setFileName(rs.getString("file_name"));

				list.add(mapData);
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

	/**
	 * @param gameType
	 * @return gameTypeに応じたマップの情報
	 * @throws Exception
	 */
	public ArrayList<MapData> selectByGameType(int gameType) throws Exception{
		//変数
		Connection con = null;
		Statement smt = null;

		//配列宣言
		ArrayList<MapData> list = new ArrayList<MapData>();

		//SQL文作成
		String sql = "SELECT * FROM quizinfo where game_type = " + gameType;

		try {
			//DB接続
			con = getConnection();
			smt = con.createStatement();

			//SQL文発行
			ResultSet rs = smt.executeQuery(sql);

			//Resultから得た情報をListに格納
			while (rs.next()) {
				MapData mapData = new MapData();
				mapData.setDataID(rs.getInt("data_id"));
				mapData.setGameType(rs.getInt("game_type"));
				mapData.setPrefectureName(rs.getString("prefecture_name"));
				mapData.setFileName(rs.getString("file_name"));
				
				list.add(mapData);
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
}
