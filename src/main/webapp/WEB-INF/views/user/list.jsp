<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<c:forEach items="${users }" var="item">
		<h1>${item.username}</h1>
		<h1>${item.name}</h1>
	</c:forEach>
	<form action="<c:url value='/user/save'></c:url>" method="post">
		<input type="hidden" name="_method" value="PUT">
		<input type="text" name="name" value="1"/>
		<input type="submit" value="提交" />
	</form>
	${user.username}
</body>
</html>