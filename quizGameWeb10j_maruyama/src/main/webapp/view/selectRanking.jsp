<%@page contentType="text/html; charset=UTF-8"%>


<html>
 	<head>
 		<title>日本地図あてクイズ</title>
 	</head>
 	<body>
 		<div style="text-align:center">
 			
 			<h1>～ランキング～</h1>
 			
 			<h3>・日本地図あてクイズ</h3><br>
 			<a href="<%=request.getContextPath() %>/record?gameType=7&cmd=show">初級編</a><br>
 			<a href="<%=request.getContextPath() %>/record?gameType=8&cmd=show">中級編</a><br>
 			<a href="<%=request.getContextPath() %>/record?gameType=9&cmd=show">上級編</a><br>
 			
 			<h3>・地域別クイズ</h3><br>
 			<a href="<%=request.getContextPath() %>/record?gameType=1&cmd=show">中国・四国地方編</a><br>
 			<a href="<%=request.getContextPath() %>/record?gameType=2&cmd=show">九州・沖縄地方編</a><br>
 			<a href="<%=request.getContextPath() %>/record?gameType=3&cmd=show">関東地方編</a><br>
 			<a href="<%=request.getContextPath() %>/record?gameType=4&cmd=show">北海道・東北地方編</a><br>
 			<a href="<%=request.getContextPath() %>/record?gameType=5&cmd=show">近畿地方編</a><br>
 			<a href="<%=request.getContextPath() %>/record?gameType=6&cmd=show">中部地方編</a><br>
 	
			<br><br><a href="<%=request.getContextPath() %>/view/menu.jsp">メニューに戻る</a><br>
 		
 		</div>
 	 </body>
 </html>