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
import bean.RecordData;
import dao.MapDataDAO;
import dao.RecordDataDAO;

@WebServlet("/result")
public class ResultServlet extends HttpServlet {
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		String errorMsg;

		//セッションからGameData取得
		GameData gameData = (GameData)session.getAttribute("gameData");
		
		if(gameData == null) {
			//セッション切れ
			errorMsg = "セッション切れ";
			//エラーが発生していたらエラー画面へ
			//画面出力遷移
			request.setAttribute("errorMsg", errorMsg);
			request.getRequestDispatcher("/view/error.jsp").forward(request, response);
		}
		
		//回答をスコープから取得してgameDataに格納
		String answer = request.getParameter("answer");
		gameData.setSelectAnswer(answer);
		
		//解答と回答
		String trueAnswer = gameData.getAnswer(gameData.getCurrentQuestionCount() - 1);
		
		//答え合わせ
		if(answer.equals(trueAnswer)) {
			//解答と回答が一致
			gameData.setResult("正解");
			gameData.setScore(gameData.getScore() + 1);
		} else {
			//はずれ
			gameData.setResult("はずれ");
		}
		
		String status;
		//現在の問題数と総問題数を比較
		if(gameData.getTotalQuestionCount() > gameData.getCurrentQuestionCount()) {
			//途中問題
			status = "nextGame";
		} else {
			//最終問題
			status = "gameResult";
			
			//
			gameData.setGameEndTime(0);
			RecordDataDAO recordDataDAO = new RecordDataDAO();
			
			int r = recordDataDAO.getRank(gameData);
			gameData.setRanking(r);
		}
		
		
		//正常に動作している場合
		//画面出力遷移
		request.setAttribute("status", status);
		request.getRequestDispatcher("/view/result.jsp").forward(request, response);
	}
}
