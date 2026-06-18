<%@page import="bean.GameData"%>
<%@page contentType="text/html; charset=UTF-8"%>
<%@page import="java.util.ArrayList,bean.QuizGameBean,bean.RecordData"%>

<%
//リクエストスコープからのデータの取得
QuizGameBean quizGameBean = new QuizGameBean();
GameData gameData = (GameData)session.getAttribute("gameData");
ArrayList<RecordData> recordDatas = (ArrayList<RecordData>)request.getAttribute("recordDatas");
%>

<html>
 	<head>
 		<title>日本地図あてクイズ</title>
 	</head>
 	<body>
 		<div style="text-align:center">
 			
 			<h1><%=quizGameBean.showQuizName(Integer.parseInt(request.getParameter("gameType"))) %></h1>
 			
 			<table border="1" style="margin: 0 auto;">
 			<tr>
 				<th>順位</th>
 				<th>名前</th>
 				<th>得点</th> 			
 			</tr>
 			<%for(int i = 0; i < recordDatas.size(); i++){ %>
 				<tr>
 					<%if(request.getParameter("cmd").equals("insert")){ %>
 						<%if(i + 1 == gameData.getRanking()){ %>
 							<td> <font color="red"><%=i + 1 %>位</font></td>
	 						<td> <font color="red"><%=recordDatas.get(i).getName() %></font></td>
 							<td> <font color="red"><%=recordDatas.get(i).getScore() %>点</font></td>
 						<%} else {%>
 							<td><%=i + 1 %>位</td>
	 						<td><%=recordDatas.get(i).getName() %></td>
 							<td><%=recordDatas.get(i).getScore() %>点</td>
 						<%} %>
 					<%} else { %>
 						<td><%=i + 1 %>位</td>
 						<td><%=recordDatas.get(i).getName() %></td>
 						<td><%=recordDatas.get(i).getScore() %>点</td>
 					<%} %>
 				</tr>
 			<%} %>
 			</table>
 					 
 			<br><br>
 			<%if(request.getParameter("cmd").equals("insert")){ %>
				<a href="<%=request.getContextPath() %>/view/menu.jsp">メニューへ</a><br>
			<%} else { %>
				<a href="<%=request.getContextPath() %>/view/selectRanking.jsp">ランキング選択画面へ戻る</a><br>
			<%} %>
 		</div>
 	 </body>
 </html>