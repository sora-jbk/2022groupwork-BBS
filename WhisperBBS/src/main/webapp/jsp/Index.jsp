<%-- 初期ページ --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/Index.css"><%--cssファイルの呼び出し --%>
<title>Insert title here</title>
</head>
<body>
	<h1>掲示板</h1>
	<div class="Index">
		<c:forEach var="post" items="${contents}"><%--var=itemから取り出した要素を変数に格納する item=ループする配列 --%>
		<table border=1><%--枠線--%>
			<tr><th>ユーザーId</th><th>投稿</th></tr>
				<tr><td>${post.post_id}</td><td>${post.reply_to}</td><td>${post.author}</td><td>${post.content}</td><td>${post.posted_time}</td></tr>
			</c:forEach>
		</table>
	</div>
	
</body>
</html>