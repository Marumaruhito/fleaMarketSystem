<%@page contentType="text/html; charset=UTF-8"%>


<html>
 	<head>
 		<title>日本地図あてクイズ</title>
 	</head>
 	<body>
 		<div style="text-align:center">
 			
 			<h1>～メニュー～</h1>
 			
 			<h3>日本地図あてクイズ</h3><br>
 			<a href="<%=request.getContextPath() %>/view/initGame.jsp?gameType=7">初級編（全10問）</a><br>
 			<a href="<%=request.getContextPath() %>/view/initGame.jsp?gameType=8">中級編（全25問）</a><br>
 			<a href="<%=request.getContextPath() %>/view/initGame.jsp?gameType=9">上級編（全47問）</a><br>
 			
 			<h3>地域別クイズ</h3><br>
 			<a href="<%=request.getContextPath() %>/view/initGame.jsp?gameType=1">中国・四国地方編</a><br>
 			<a href="<%=request.getContextPath() %>/view/initGame.jsp?gameType=2">九州・沖縄地方編</a><br>
 			<a href="<%=request.getContextPath() %>/view/initGame.jsp?gameType=3">関東地方編</a><br>
 			<a href="<%=request.getContextPath() %>/view/initGame.jsp?gameType=4">北海道・東北地方編</a><br>
 			<a href="<%=request.getContextPath() %>/view/initGame.jsp?gameType=5">近畿地方編</a><br>
 			<a href="<%=request.getContextPath() %>/view/initGame.jsp?gameType=6">中部地方編</a><br>
 	
			<br><br><a href="<%=request.getContextPath() %>/view/selectRanking.jsp">ランキング</a><br>
 			<a href="<%=request.getContextPath() %>/view/start.jsp">ゲーム終了</a><br>	
 		
 		</div>
		 </body>
 </html>