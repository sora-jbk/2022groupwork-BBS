<%--投稿用 --%>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %><%--jstl --%>

<link rel="stylesheet" href="<%=request.getContextPath()%>/css/common/PostBox.css">

	<form method="post" action="InsertPOST">
		<input type="hidden" name="R" value="<%=request.getParameter("R") %>">
		ユーザー<input type="text" name="name"><br><%--単一行のテキスト入力欄 1行を超えると消えちゃう --%>
		コメント<textarea name="content" cols=150% rows=10%></textarea><br><%--複数行入力できる->textarea --%>
		<input type="submit" value="投稿">
	</from>
