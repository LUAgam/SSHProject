<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
    <c:if test="${!empty message}">
        <font style="color: red;">${message}</font>
    </c:if>
	<table>
		<thead>
			<tr>
				<th>编号</th>
				<th>名称</th>
				<th>用户名</th>
				<th>密码</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${users }" var="user">
				<tr>
					<td>${user.id}</td>
					<td>${user.name}</td>
					<td>${user.username}</td>
					<td>${user.password}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<form action="<c:url value='/user/save'></c:url>" method="post">
		<input type="hidden" name="_method" value="PUT"> 
		<input type="text" name="name" /> 
		<input type="text" name="username"/> 
		<input type="password" name="password"/> 
		<input type="submit" value="提交" />
	</form>
</body>
</html>