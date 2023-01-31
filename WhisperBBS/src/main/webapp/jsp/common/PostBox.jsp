<%--投稿用 --%>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

	<from method="post" action="adduser">
		name<input type="text" name="name">
		coment<input type="text" name="coment">
		<input type="submit" value="投稿">
	</from>
