<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="${ctx}/static/css/bootstrap-table.min.css">
<link rel="stylesheet" href="${ctx}/static/bootstrap/css/bootstrap.min.css">
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
			<c:forEach items="${users.content }" var="user">
				<tr>
					<td>${user.id}</td>
					<td>${user.name}</td>
					<td>${user.username}</td>
					<td>${user.password}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<table id="table"></table>
	<form action="<c:url value='/user/save'></c:url>" method="post">
		<input type="hidden" name="_method" value="PUT"> <input type="text" name="name" /> <input type="text" name="username" /> <input type="password" name="password" /> <input type="submit" value="提交" />
	</form>


	<script type="text/javascript" src="${ctx}/static/js/jquery-3.1.1.min.js"></script>
	<script src="${ctx}/static/bootstrap/js/bootstrap.min.js"></script>
	<!-- Latest compiled and minified JavaScript -->
	<script src="${ctx}/static/js/bootstrap-table.min.js"></script>

	<!-- Latest compiled and minified Locales -->
	<script src="${ctx}/static/js/bootstrap-table-zh-CN.min.js"></script>
	<script type="text/javascript">
		$(function() {
			//获取table数据
			/* var data;
			$.ajax({
				type : 'POST',
				url : '/ao/user/getTableData',
				dataType : 'json',
				success : function(msg) {
					data = msg;
					alert(JSON.stringify(data.content));
					$('#table').bootstrapTable('refresh', JSON.stringify(data.content));
				},
				error : function() {

				}
			}); */
			$('#table').bootstrapTable({
				pagination : true,
				/* 是否启用搜索框 */
				search : true,
				/* 是否显示 刷新按钮 */
				showRefresh:true,
				/* 是否显示 切换试图（table/card）按钮 */
				showToggle:true,
				/* 是否显示 数据条数选择框 */
				showPaginationSwitch:true,
				columns : [ {
					field : 'id',
					title : '编号'
				}, {
					field : 'name',
					title : '名称'
				}, {
					field : 'username',
					title : '用户名'
				}, {
					field : 'password',
					title : '密码'
				} ],
				url : '/ao/user/getTableData',
				dataType : 'json',
				method : 'post',
				sidePagination : 'server',
				pageNumber:1,
				pageSize:2,
				queryParams : function(params) {
					return {
                        pageNumber: 3,
                        pageSize: 2,
                        sortName:'id',
                        sortOrder:'asc'
                      };
                },
			});
		});
	</script>
</body>
</html>