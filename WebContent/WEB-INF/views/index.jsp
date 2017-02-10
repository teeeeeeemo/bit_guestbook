<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>  
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% pageContext.setAttribute( "newLine", "\n" ); %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>방명록</title>
</head>
<body>
	<form action="${pageContext.request.contextPath }/gb" method="post">
		<input type="hidden" name="a" value="add">
		<table border=1 width=500>
			<tr>
				<td>이름</td><td><input type="text" name="name"></td>
				<td>비밀번호</td><td><input type="password" name="pass"></td>
			</tr>
			<tr>
				<td colspan=4><textarea name="content" cols=60 rows=5></textarea></td>
			</tr>
			<tr>
				<td colspan=4 align=right><input type="submit" VALUE=" 확인 "></td>
			</tr>
		</table>
	</form>
	<c:set var="countList" value="${fn:length(list) }" />
	<c:forEach var="vo" items="${list }" varStatus="status">
		<br>
		<table width=510 border=1>
			<tr>
				<td>[${countList - status.index }]</td>
				<td>${vo.name }</td>
				<td>${vo.regDate }</td>
				<td><a href="${pageContext.request.contextPath }/gb?a=deleteform&no=${vo.no }">삭제</a></td>
			</tr>
			<tr>
				<td colspan=4>${fn:replace(vo.content, newLine, "<br>") }</td>
			</tr>
		</table>
	</c:forEach>
</body>
</html>