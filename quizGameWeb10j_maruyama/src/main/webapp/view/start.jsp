<%@page contentType="text/html; charset=UTF-8"%>

<!-- 
///
/// プログラム名 : 都道府県クイズWeb版
/// プログラムの説明 : 都道府県クイズクイズです。地域別や全国で分かれています。
/// 作成者 : 丸山 蓮央
/// 作成日 : 2026/06/11
///
 -->

<html>
 	<head>
 		<title>日本地図あてクイズ</title>
 	</head>
 	<body>
 		<div style="text-align:center">
 			
 			<h1>日本地図あてクイズ</h1>
 			
 			<img src="<%=request.getContextPath() %>/map/japan.jpg" alt="日本地図"title="地図" width="300" height="300">
			 			
 			<br><a href="<%=request.getContextPath() %>/view/menu.jsp">ゲーム開始</a><br>
 			
 		</div>
 	 </body>
 </html>