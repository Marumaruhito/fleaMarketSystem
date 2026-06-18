<%@page contentType="text/html; charset=UTF-8"%>

<%
//リクエストスコープからのデータの取得
String err = (String)request.getAttribute("errorMsg");
String cmd = (String)request.getAttribute("cmd");
%>

<html>
 	<head>
 		<title>Error</title>
 	</head>
 	<body>
 		<div style="text-align:center">
 			
			<p style="text-align: center">●●エラー●●</p>
			<p style="text-align: center"><%=err %></p>
 			
 			<a href="<%=request.getContextPath() %>/view/<%=cmd%>.jsp">スタート画面へ戻る</a><br>
 			
 	
 		</div>
 	 </body>
 </html>