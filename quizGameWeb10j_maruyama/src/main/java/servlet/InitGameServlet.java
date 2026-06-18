package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import bean.GameData;
import bean.MapData;
import bean.QuizGameBean;
import dao.MapDataDAO;

@WebServlet("/initGame")
public class InitGameServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();

		//GameData生成
		GameData gameData = new GameData();
		QuizGameBean quizGameBean = new QuizGameBean();
		//MapDataDAOオブジェクト生成
		MapDataDAO mapDataDAO = new MapDataDAO();

		ArrayList<MapData> mapDataList = new ArrayList<MapData>();

		String test = request.getParameter("cmd");
		//スコープからcmd判別
		if (request.getParameter("cmd").equals("init")) {

			//スコープからGameType取得
			int type = Integer.parseInt(request.getParameter("gameType"));
			//各情報をgameDataに格納
			gameData.setGameType(type);
			gameData.setCurrentQuestionCount(1);
			gameData.setGameTitle(quizGameBean.showQuizName(type));
			//仮
			gameData.setTotalQuestionCount(47);

			if (type >= 7 && type <= 9) {
				//日本語地図あてクイズの場合type7-9
				try {
					mapDataList = mapDataDAO.selectAll();
				} catch (Exception e) {
					//セッション切れ
					String errorMsg = "DB接続エラーが発生しました";
					//エラーが発生していたらエラー画面へ
					//画面出力遷移
					request.setAttribute("errorMsg", errorMsg);
					request.setAttribute("cmd", "menu");
					request.getRequestDispatcher("/view/error.jsp").forward(request, response);
				}
			} else if (type >= 1 && type <= 6) {
				//地域別クイズの場合type1-6
				try {
					mapDataList = mapDataDAO.selectByGameType(type);
				} catch (Exception e) {
					//セッション切れ
					String errorMsg = "DB接続エラーが発生しました";
					//エラーが発生していたらエラー画面へ
					//画面出力遷移
					request.setAttribute("errorMsg", errorMsg);
					request.setAttribute("cmd", "menu");
					request.getRequestDispatcher("/view/error.jsp").forward(request, response);
				}
			}

			//
			quizGameBean.selectAnswerAndImage(gameData, mapDataList);
			quizGameBean.createMultipleChoice(gameData, mapDataList);
			Date date = new Date();
			gameData.setGameStartTime(0);

			//セッションにgameDataを登録
			session.setAttribute("gameData", gameData);

		} else if (request.getParameter("cmd").equals("game")) {
			//ゲーム中
			gameData = (GameData) session.getAttribute("gameData");

			if (gameData == null) {
				//セッション切れかチェック

			}
			gameData.setCurrentQuestionCount(gameData.getCurrentQuestionCount() + 1);
		}

		if (gameData.equals(mapDataList)) {
			//エラーが発生していたらエラー画面へ
			//画面出力遷移
			request.getRequestDispatcher("/view/error.jsp").forward(request, response);

		} else {
			//正常に動作している場合
			//画面出力遷移
			request.getRequestDispatcher("/view/game.jsp").forward(request, response);
		}
	}
}
