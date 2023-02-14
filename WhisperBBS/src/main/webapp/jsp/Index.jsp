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
.title{

}

.Post {
	background-color: #4ec4d3;	
	<%--position: fixed;--%>
	position: sticky;
	bottom: 0;
}

.box {
	width:100%;
}

.item {
    padding: 0.5em 1em;
    margin: 2em 0;
    border: double 5px #4ec4d3;
    
	margin: 0; 
    padding: 0;
}

.coment {
	width: 100%;
}

.Index {
    padding: 0.5em 1em;
    margin: 2em 0;
    border: double 5px #4ec4d3;
    
	margin: 0; 
    padding: 0;
}

.time {
    display: flex;
    justify-content: flex-end;
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
		<table border=1><%--枠線--%>
		<tr><th>ユーザーId</th><th>投稿</th></tr>
			<c:forEach var="post" items="${contents}"><%--var=itemから取り出した要素を変数に格納する item=ループする配列 --%>
				<tr>
					<td>${post.post_id}</td><td>${post.reply_to}</td><td>${post.author}</td><td>${post.content}</td><td>${post.posted_time}</td>
					<td>
						<c:set var="flg" value="${post.deleted}"/>
						<c:if test="${flg==0}">
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