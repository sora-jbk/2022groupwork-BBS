<%-- 初期ページ --%>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
	<head>
		<meta charset="UTF-8">
		<link rel="shortcut icon" href="<%=request.getContextPath() %>\images\whispericon.JPG">
		<link rel="stylesheet" href="<%=request.getContextPath() %>\css\Index.css">
		<%--javaのメソッドを呼び出してくる<%= %> --%>
		<title>WhiperBBS</title>
	</head>
	<body>	
		<a href="http://localhost:8080/WhisperBBS/Home"><img src="<%=request.getContextPath() %>\images\whispericon.JPG" class="logo"></a><%--ホーム画面に戻る --%>
		<div class="title">
			<form method="get" action="Home" class="search_container">
			  	<input type="text" size="25" placeholder="キーワード検索" name="S">
	  			<input type="submit" value="検索">
			</form>
		</div>
		<div class="Index">
			<c:if test="${empty R }">
				<div class="thread_title">
					<div class="title">${root.content }</div>
				</div>
			</c:if>
			<c:forEach var="post" items="${contents}"><%--var=itemから取り出した要素を変数に格納する item=ループする配列 --%>
				<c:set var="flg" value="${post.deleted}"/>	
				<c:if test="${flg==0}">
					<div class="item">
						<div class="id">投稿番号:${post.post_id}</div>
						
						<c:if test="${empty post.reply_to}">
							<div class="reply">${post.resNum} replies</div>
						</c:if>
						<c:if test="${not empty post.reply_to}">
							<div class="reply">返信先:${post.reply_to}</div>
						</c:if>
						
						<c:if test="${not empty post.author}">
							<div class="author">投稿者名:${post.author}</div>
						</c:if>
						<c:if test="${empty post.author}">
							<div class="author">投稿者名:NoName</div>
						</c:if>
						
						
						<div class="content">投稿内容:${post.content}</div>
						
						<div class="time">投稿時間:${post.posted_time}</div>
						
								<a href="Home?R=${post.post_id}" >返信を見る</a>
							<form method="post" action="Delete">
								<input type="hidden" name="D" value="${post.post_id}">
								<input type="submit" value="削除">
							</form>
					</div>
				</c:if>
			</c:forEach>
		</div>
		<div class="Post">
			<%@ include file="common/PostBox.jsp" %>
		</div>
	</body>
</html>