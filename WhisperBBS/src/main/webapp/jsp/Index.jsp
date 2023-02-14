<%-- 初期ページ --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
	<style>
	.logo {
		width: 50px;
		height: 50px;
	}
	
	.Post {
		background-color: #70a2a2;
		
		position: sticky;
		bottom: 0;
	}
	
	.box {
		width:100%;
	}
	
	.item {
	    padding: 0.5em 1em;
	    margin: 2em 0;
	    border:solid 5px #9ba7a8;
	    
		margin: 0; 
	    padding: 0;
	}
	
	.coment {
		width: 100%;
	}
	
	</style>
	<meta charset="UTF-8">
	<a href="http://localhost:8080/WhisperBBS/Home"><img src="https://memo-labo.com/images/20211012-1.png" class="logo"></a><%--ホーム画面に戻る --%>
	<%--javaのメソッドを呼び出してくる<%= %> --%>
	<title>Insert title here</title>
	</head>
	<body>
		<div class="title">
			<form method="get" action="Home" class="search_container">
			  	<input type="text" size="25" placeholder="キーワード検索" name="S">
	  			<input type="submit" value="検索">
			</form>
		</div>
		<div class="Index">
			<c:forEach var="post" items="${contents}"><%--var=itemから取り出した要素を変数に格納する item=ループする配列 --%>
				<div class="item">
					<div class="id">${post.post_id}</div>
					<div class="reply">${post.reply_to}</div>
					<div class="author">${post.author}</div>
					<div class="content">${post.content}</div>
					<div class="time">${post.posted_time}</div>
						<c:set var="flg" value="${post.deleted}"/>	
						<c:if test="${flg==0}">
								<a href="Home?R=${post.post_id}" >返信を見る</a>
							<form method="post" action="Delete">
								<input type="hidden" name="D" value="${post.post_id}">
								<input type="submit" value="削除">
							</form>
						</c:if>
				</div>
			</c:forEach>
		</div>
		<div class="Post">
			<%@ include file="common/PostBox.jsp" %>
		</div>
	</body>
</html>