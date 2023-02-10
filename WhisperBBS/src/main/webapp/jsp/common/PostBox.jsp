<%--投稿用 --%>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %><%--jstl --%>


	<form method="post" action="InsertPOST" class="box">
		<input type="hidden" name="R" value="<%=request.getParameter("R") %>">
		ユーザー<input type="text" name="name"><br><%--単一行のテキスト入力欄 1行を超えると消えちゃう --%>
		コメント<input type="text" name="content" class="coment" placeholder="コメント"></textarea><br><%--複数行入力できる->textarea --%>
		<input type="submit" value="投稿">
	</from>
