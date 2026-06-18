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
 			
 			<h2>第<%=Integer.toString(gameData.getCurrentQuestionCount()) %>問</h2>
 			
 			<img src="<%=request.getContextPath() %>/map/<%= gameData.getFileName(gameData.getCurrentQuestionCount() - 1) %>" alt="日本地図"title="地図" width="300" height="300">
			 
			 <form action="<%=request.getContextPath() %>/result" method="get">
			 <%
 				if(gameData.getChoice(1) != null){
 					for(int i=0;i<GameData.getChoiceCount();i++){
 				%>
 						<input type="radio" name="answer" value="<%=gameData.getChoice(gameData.getCurrentQuestionCount()).get(i) %>"><%=gameData.getChoice(gameData.getCurrentQuestionCount()).get(i) %>
 				<%
 					}
 				}
 				%>
 				<br><br><input type="submit" value="回答">
 			</form>
 			
 		</div>
 	 </body>
 </html>