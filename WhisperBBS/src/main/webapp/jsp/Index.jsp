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

		<form method="get" action="Home" class="search_container">
		  	<input type="text" size="25" placeholder="キーワード検索" name="S">
  			<input type="submit" value="&#xf002">
		</form>
	</div>
	<div class="Index">
		<table border=1><%--枠線--%>
		<tr><th>ユーザーId</th><th>投稿</th></tr>
			<c:forEach var="post" items="${contents}"><%--var=itemから取り出した要素を変数に格納する item=ループする配列 --%>
				<tr>
					<td>${post.post_id}</td><td>${post.reply_to}</td><td>${post.author}</td><td>${post.content}</td><td>${post.posted_time}</td>
					<td>
						<c:set var="flg" value="${post.deleted}">
						<c:if  test="${flg==0}">
							<a href="Home?R=${post.post_id}" >返信を見る</a>
						</c:if>
					</td>
					<td>
						<form method="post" action="Delete">
							<input type="hidden" name="D" value="${post.post_id}">
							<input type="submit" value="削除">
						</form>
					</td>
				</tr>
			</c:forEach>
		</table>
	</div>
	
	<div class="Post">
		<%@ include file="common/PostBox.jsp" %>
	</div>
	
</body>
</html>