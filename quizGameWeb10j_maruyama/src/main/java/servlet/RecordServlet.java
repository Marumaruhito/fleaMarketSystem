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

@WebServlet("/record")
public class RecordServlet extends HttpServlet {
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		String errorMsg;
		
		//RecordData作成
		RecordData recordData = new RecordData();
		
		if(request.getParameter("cmd").equals("insert")) {
			//cmdがinsertだった場合
			GameData gameData = (GameData)session.getAttribute("gameData");
			if(gameData == null){
				//セッション切れ
				errorMsg = "セッション切れ";
				//エラーが発生していたらエラー画面へ
				//画面出力遷移
				request.setAttribute("errorMsg", errorMsg);
				request.setAttribute("cmd", "menu");
				request.getRequestDispatcher("/view/error.jsp").forward(request, response);
			}
			
			//recordDataに各情報登録
			if(request.getParameter("userName").equals("")) {
				recordData.setName("ななしさん");
			} else {
				recordData.setName(request.getParameter("userName"));
			}
			recordData.setScore(gameData.getScore());
			//recordData.setCompleteTime(gameData.getGameEndTime() - gameData.getGameStartTime());
			recordData.setGameType(gameData.getGameType());
			
		}else if(request.getParameter("cmd").equals("show")) {
			//cmdがshowの場合
			request.getParameter("gameType");
			
		}
		//レコードを登録
		RecordDataDAO recordDataDAO = new RecordDataDAO();
		if(request.getParameter("cmd").equals("insert")) {
			try {
				recordDataDAO.insertRecord(recordData);
		
			} catch(Exception e) {
				//セッション切れ
				errorMsg = "DB接続エラーが発生しました";
				//エラーが発生していたらエラー画面へ
				//画面出力遷移
				request.setAttribute("errorMsg", errorMsg);
				request.setAttribute("cmd", "menu");
				request.getRequestDispatcher("/view/error.jsp").forward(request, response);
			}
		}
		ArrayList<RecordData> recordDatas = null;
		try {
			recordDatas = recordDataDAO.selectDataAcordingToGameType(Integer.parseInt(request.getParameter("gameType")));
		} catch (Exception exception) {
			//セッション切れ
			errorMsg = "DB接続エラーが発生しました";
			//エラーが発生していたらエラー画面へ
			//画面出力遷移
			request.setAttribute("errorMsg", errorMsg);
			request.setAttribute("cmd", "menu");
			request.getRequestDispatcher("/view/error.jsp").forward(request, response);
		}
		
		//正常に動作している場合
		request.setAttribute("recordDatas", recordDatas);
		request.setAttribute("gameType", request.getParameter("gameType"));
		//画面出力遷移
		request.getRequestDispatcher("/view/showRanking.jsp").forward(request, response);
	}
}
