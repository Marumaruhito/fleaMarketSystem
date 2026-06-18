<%@page contentType="text/html; charset=UTF-8"%>
<%@page import="java.util.ArrayList,bean.GameData,dao.RecordDataDAO"%>

<%
//リクエストスコープからのデータの取得
GameData gameData = (GameData)session.getAttribute("gameData");
RecordDataDAO recordDAO = new RecordDataDAO();
%>

<html>
 	<head>
 		<title>日本地図あてクイズ</title>
 	</head>
 	<body>
 		<div style="text-align:center">
 			
 			<h1><%=gameData.getGameTitle() %>></h1>
 			
 			<%if(gameData.getRanking() <= 20){ %>
 				 <font color="red">★Rank Inn★</font>
 			<%} %>
 			
 			<br>
 			正解数：<%=gameData.getScore() %>/<%=gameData.getTotalQuestionCount() %>
 			<br><br>
 			<br><br>
	 		
	 		答え合わせ
	 		<table border="1" style="margin: 0 auto;">
	 		<tr>
	 			<th>問題の解答</th>
	 			<th>選んだ回答</th>
	 			<th>結果</th>
	 		</tr>
	 		<%for(int i = 0; i < gameData.getTotalQuestionCount(); i++){ %>
	 			<tr>
	 				<td>
	 				<a href="<%=request.getContextPath() %>/map/<%=gameData.getFileName(i) %>" target="_blank"><%=gameData.getAnswer(i) %></a>
	 				</td>
	 				<td><%=gameData.getSelectAnswer(i) %></td>
	 				<%if(gameData.getResult(i).equals("正解")) {%>
 		 				<td><font color="red"><%=gameData.getResult(i) %></font></td>
	 				<%} else { %>
		 	 			<td><font color="blue"><%=gameData.getResult(i) %></font></td>
	 				<%} %>	
	 			</tr>
	 		<%} %>
	 		
	 		</table>
	 				 
	 		<%if(gameData.getCurrentQuestionCount() != gameData.getTotalQuestionCount()){ %>
 				<br><a href="<%=request.getContextPath() %>/initGame?cmd=game">次の問題</a><br>
 			<%} else if(gameData.getRanking() <= 20) {%>
 			<br><a href="<%=request.getContextPath() %>/view/insertRanking.jsp">ランキング登録画面</a><br>
 			<%} else { %>
 				<br><a href="<%=request.getContextPath() %>/view/menu.jsp">メニューへ戻る</a><br>
 			<%} %>
 		</div>
 	 </body>
 </html>