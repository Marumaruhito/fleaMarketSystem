<%@page contentType="text/html; charset=UTF-8"%>
<%@page import="java.util.ArrayList,bean.GameData"%>

<%
//リクエストスコープからのデータの取得
GameData gameData = (GameData)session.getAttribute("gameData");
%>

<html>
 	<head>
 		<title>日本地図あてクイズ</title>
 	</head>
 	<body>
 		<div style="text-align:center">
 			
 			<h1><%=gameData.getGameTitle() %></h1>
 			
 			順位 <font color="red"><%=gameData.getRanking() %></font>位
 			<br>
 			得点 <font color="red"><%=gameData.getScore() %></font>/<%=gameData.getTotalQuestionCount() %>点
	 				 
	 		<form action="<%=request.getContextPath()%>/record">
	 			名前：<input type=text size="30" name="userName"></input>
	 			<input type="hidden" name="gameType" value="<%=gameData.getGameType() %>"></input>
				<input type="submit" name="cmd" value="insert"></input>
	 		</form> 
			
 		</div>
 	 </body>
 </html>