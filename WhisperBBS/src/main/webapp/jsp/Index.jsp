<%-- 初期ページ --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%--javaのメソッドを呼び出してくる<%= %> --%>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/Index.css"><%--cssファイルの呼び出し --%>
<title>Insert title here</title>
</head>
<body>
	<div class="title">
	<link href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">

	<form method="get" action="#" class="search_container">
  	<input type="text" size="25" placeholder="キーワード検索">
  	<input type="submit" value="&#xf002">
	</form>
	</div>
	<div class="Index">
		<c:forEach var="post" items="${contents}"><%--var=itemから取り出した要素を変数に格納する item=ループする配列 --%>
		<table border=1><%--枠線--%>
			<form method="post">			
			<tr><th>ユーザーId</th><th>投稿</th></tr>
				<tr><td>${post.post_id}</td><td>${post.reply_to}</td><td>${post.author}</td><td>${post.content}</td><td>${post.posted_time}</td></tr>
			</c:forEach>
		</table>
	</div>
	<div class="Post">
		<%@ include file="common/PostBox.jsp" %>
	</div>
	
</body>
</html>