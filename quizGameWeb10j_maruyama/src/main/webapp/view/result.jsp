<%@page contentType="text/html; charset=UTF-8"%>
<%@page import="java.util.ArrayList,bean.GameData"%>

<%
//リクエストスコープからのデータの取得
String status = (String)request.getAttribute("status");
GameData gameData = (GameData)session.getAttribute("gameData");
%>

<html>
 	<head>
 		<title>日本地図あてクイズ</title>
 	</head>
 	<body>
 		<div style="text-align:center">
 			
 			<h1><%=gameData.getGameTitle() %>></h1>
 			<%if(gameData.getResult(gameData.getCurrentQuestionCount() -1).equals("正解")){ %>
 				判定： <font color="red"><%=gameData.getResult(gameData.getCurrentQuestionCount() - 1) %></font>
 			<%} else { %>
 				 判定：<font color="blue"><%=gameData.getResult(gameData.getCurrentQuestionCount() - 1) %>	</font>
 			<%} %>
 			
 			<br>
 			正解数：<%=gameData.getScore() %>/<%=gameData.getTotalQuestionCount() %>
	 				 
	 		<%if(status.equals("nextGame")){ %>
 				<br><a href="<%=request.getContextPath() %>/initGame?cmd=game">次の問題</a><br>
 			<%} else {%>
 				<br>
 				<br>
 				<font color="red">◆全問回答終了◆</font>
 				<br>
 				<br><a href="<%=request.getContextPath() %>/view/gameResult.jsp">最終結果へ</a><br>
 			<%} %>
 		</div>
 	 </body>
 </html>