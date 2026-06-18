package bean;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Random;

import dao.RecordDataDAO;
import util.MapDataManager;

public class QuizGameBean {

	//変数宣言
	private static int scoreCnt; //スコア順
	private static int orderCnt; //順位
	private Random random; //Randomオブジェクト

	//コンストラクタ
	public QuizGameBean() {
		QuizGameBean.scoreCnt = 1;
		QuizGameBean.orderCnt = 1;
		this.random = new Random();
	}

	/**
	 * 出題されるすべての問題の解答と画像ファイルを格納
	 * @param gameData
	 * @param mapDataList
	 */
	public void selectAnswerAndImage(GameData gameData, ArrayList<MapData> mapDataList) {

		//問題数を確定
		int totalQuestions = getQuestionCount(gameData);

		//ランダム順番に
		Collections.shuffle(mapDataList);

		//問題数分のみリストに登録
		for (int i = 0; i < totalQuestions; i++) {
			gameData.setAnswer(mapDataList.get(i).getPrefectureName());
			gameData.setFileName(mapDataList.get(i).getFileName());
		}

		//同一問題がないかチェック

		//データベースから全情報を格納
		//for (MapData map : mapDataList) {
		//	gameData.setAnswer(map.getPrefectureName());
		//	gameData.setFileName(map.getFileName());
		//}

	}

	/**
	 * 答えリストに同じ答えがあるかをチェック
	 * @param answerList
	 * @param answer
	 */
	public void checkAnswerList(ArrayList<String> answerList, String answer) {

		for (String checkAnswer : answerList) {
			if (checkAnswer.equals(answer)) {
				//同じ答えがあったらダメ

				System.out.println("同一問題が存在しています。");

			}
		}
	}

	/**
	 * gameTypeより問題数を取得
	 * @param gameData
	 */
	public int getQuestionCount(GameData gameData) {
		int gameType = gameData.getGameType();

		switch (gameType) {
		case 1:
			//四国
			gameData.setTotalQuestionCount(4);
			break;

		case 2:
			//九州・沖縄
			gameData.setTotalQuestionCount(8);
			break;

		case 3:
			//関東
			gameData.setTotalQuestionCount(7);
			break;

		case 4:
			//東北・北海道
			gameData.setTotalQuestionCount(7);
			break;

		case 5:
			//近畿・中国
			gameData.setTotalQuestionCount(12);
			break;

		case 6:
			//中部
			gameData.setTotalQuestionCount(9);
			break;

		case 7:
			gameData.setTotalQuestionCount(10);
			break;

		case 8:
			gameData.setTotalQuestionCount(25);
			break;

		case 9:
			gameData.setTotalQuestionCount(47);
			break;

		}
		return gameData.getTotalQuestionCount();
	}

	/**
	 * 問題出題時に表示される選択肢を格納
	 * @param gameData
	 * @param mapDataList
	 */
	public void createMultipleChoice(GameData gameData, ArrayList<MapData> mapDataList) {
		gameData.setChoiceToIndividual(null);

		for (int i = 0; i < gameData.getTotalQuestionCount(); i++) {
			//総問題数文ループ

			ArrayList<String> choice = new ArrayList<String>();

			//各ループごとにリストをシャッフル
			Collections.shuffle(mapDataList);

			//現在の問題の解答を選択肢に追加
			choice.add(gameData.getAnswer(i));

			//残り３つをランダムに生成
			int j = 0;
			while (choice.size() < 4) {
				if (!checkChoiceList(choice, mapDataList.get(j).getPrefectureName())) {
					//選択肢格納
					choice.add(mapDataList.get(j).getPrefectureName());
				}
				j++;
			}

			//4つ分の選択肢を格納したのでChoiceListに追加してループ
			gameData.setChoiceToIndividual(choice);
		}
	}

	/**
	 * 選択肢リストに同じ選択肢があるかチェック
	 * @param choAnswerList
	 * @param answer
	 * @param choAnswer
	 * @return
	 */
	public boolean checkChoiceList(ArrayList<String> choAnswerList, String answer) {

		for (String str : choAnswerList) {
			if (str.equals(answer)) {
				return true;
			}
		}
		return false;
	}

	/**
	 *  ランキング登録後のランキング確認画面にて、登録したデータが表示されているかチェック
	 * @param recordData
	 * @param recordDataList
	 */
	public void searchAgreementRecord(RecordData recordData, ArrayList<RecordData> recordDataList) {

	}

	/**
	 *　ゲームの種類からクイズ名を判別
	 * @param gameType
	 * @return
	 */
	public String showQuizName(int gameType) {

		String quizName = "";

		switch (gameType) {
		case 7:
			quizName = "日本地図あてクイズ　～初級編～";
			break;

		case 8:
			quizName = "日本地図あてクイズ　～中級編～";
			break;

		case 9:
			quizName = "日本地図あてクイズ　～上級編～";
			break;

		case 1:
			quizName = "地域別クイズ　～中国・四国地方編～";
			break;

		case 2:
			quizName = "地域別クイズ　～九州・沖縄地方編～";
			break;

		case 3:
			quizName = "地域別クイズ　～関東地方編～";
			break;

		case 4:
			quizName = "地域別クイズ　～北海道・東北地方編～";
			break;

		case 5:
			quizName = "地域別クイズ　～近畿地方編～";
			break;

		case 6:
			quizName = "地域別クイズ　～中部地方編～";
			break;

		}

		return quizName;
	}
	
	/**
	 *　ゲームの種類からクイズ名を判別
	 * @param gameType
	 * @return
	 */
	public String showFileName(int gameType) {

		String fileName = "";

		switch (gameType) {
		case 7:
			fileName = "regionAll.jpg";
			break;

		case 8:
			fileName = "regionAll.jpg";
			break;

		case 9:
			fileName = "regionAll.jpg";
			break;

		case 1:
			//四国
			fileName = "region1.jpg";
			break;

		case 2:
			//九州
			fileName = "region2.jpg";
			break;

		case 3:
			//関東
			fileName = "region3.jpg";
			break;

		case 4:
			//東北北海道
			fileName = "region4.jpg";
			break;

		case 5:
			//近畿
			fileName = "region5.jpg";
			break;

		case 6:
			//中部
			fileName = "region6.jpg";
			break;

		}

		return fileName;
	}

	/**
	 * ランキング表示時の順位を判別
	 * @param index
	 * @param recordDataList
	 * @return
	 */
	public int showResultOrder(int index, ArrayList<RecordData> recordDataList) {

		//DAO
		RecordDataDAO recordDataDAO = new RecordDataDAO();
		try {
			//GameType別の順位取得
			recordDataList = recordDataDAO.selectDataAcordingToGameType(index);
		} catch(Exception e) {
			
		}
		
		//順位格納 20位まで登録するため大きい数字で初期化
		int rank = 99;
		
		
		
		return rank;
	}

}
