<%@page contentType="text/html; charset=UTF-8"%>
<%@page import="bean.QuizGameBean"%>

<%
//リクエストスコープからのデータの取得
String gameType = request.getParameter("gameType");
QuizGameBean quizGameBean = new QuizGameBean();
String title = quizGameBean.showQuizName(Integer.parseInt(gameType));
String picture = quizGameBean.showFileName(Integer.parseInt(gameType));
%>

<html>
 	<head>
 		<title>日本地図あてクイズ</title>
 	</head>
 	<body>
 		<div style="text-align:center">
 			
 			<h1><%=title %></h1>
 			
 			<img src="<%=request.getContextPath() %>/map/<%=picture %>" alt="日本地図"title="地図" width="300" height="300">
			 			
 			<br><a href="<%=request.getContextPath() %>/initGame?cmd=init&gameType=<%=gameType%>">ゲーム開始</a><br>
 			<br><a href="<%=request.getContextPath() %>/view/menu.jsp">メニューに戻る</a><br>
 			
 		</div>
 	 </body>
 </html>