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
		<div class="header">
			<a href="http://localhost:8080/WhisperBBS/Home"><img src="<%=request.getContextPath() %>\images\whispericon.JPG" class="logo"></a><%--ホーム画面に戻る --%>
			<div class="search-wrap">
				<form role="search" method="get" action="Home" class="search_container">
				  	<input type="text" size="25" name="S" id="search-text">
				  	<input type="hidden" name="R" value="<%=request.getParameter("R") %>">
				</form>
			</div>
		</div>
		<div class="Index">
			<c:set var="Rempty" value="<%=request.getParameter(\"R\")%>" />
			<c:if test="${not empty Rempty}">
				<div class="thread_title">
					<div class="title">${root.content }</div>
				</div>
			</c:if>
			<div class="Post">
				<%@ include file="common/PostBox.jsp" %>
			</div>
			<c:forEach var="post" items="${contents}"><%--var=itemから取り出した要素を変数に格納する item=ループする配列 --%>
					<div class="item">
						<div class="info">
							<p class="id">${post.post_id}</p>
							<p class="author">${post.author}</p>
							<p class="reply">${post.resNum} replies</p>

						</div>
							
						
						
						
						<c:set var="flg" value="${post.deleted}"/>	
						<c:if test="${flg==0}">
							<div class="content">${post.content}</div>
							
							<div class="time">${post.posted_time}</div>
						
							<a href="Home?R=${post.post_id}"  class="link"></a>
						</c:if>
						<c:if test="${flg==1}">
							<a class="deleted"><h1>DELETED</h1></a>
						</c:if>
					</div>
			</c:forEach>
		</div>
		<script src="<%=request.getContextPath()%>/js/Index.js"></script>
	</body>
</html>