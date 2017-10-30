<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="${ctx}/static/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="${ctx}/static/css/bootstrap-table.min.css">
<style>
    .crudDialog{padding: 0 0 2px;}
.dialog-buttons a{font-weight: bold;}
.form-group{margin-bottom: 20px;}
.form-group label{position: absolute; line-height: 2; font-size: 16px; font-weight: normal; transition: all .2s; pointer-events: none; color: #999;}
.form-group .active{transform: translateY(-65%); font-size: 12px; color: #337ab7;}
.form-group .form-control{font-size: 14px; box-shadow: none; padding-left: 0; padding-right: 0; border-radius: 0; border: none; border-bottom: 2px solid #eee; outline: none; transition: all .5s;}
.form-group .form-control:focus{box-shadow: none; border-color: #337ab7;}
    </style>
</head>
<body>
	<c:if test="${!empty message}">
		<font style="color: red;">${message}</font>
	</c:if>
	<div class="container">
		<div class="row">
			<div id="toolbar">
				<select class="form-control">
					<option value="">导出本页</option>
					<option value="all">导出所有</option>
					<option value="selected">导出选中</option>
				</select>
			</div>
			<table id="table" data-show-export="true">
				<thead>
					<tr>
						<th data-field="state" data-checkbox="true"></th>
						<th data-field="id" data-sortable="true">编号</th>
						<th data-field="name" data-sortable="true">名称</th>
						<th data-field="username" data-sortable="true">用户名</th>
						<th data-field="password" data-sortable="true">密码</th>
					</tr>
				</thead>
			</table>
			<form action="<c:url value='/user/save'></c:url>" method="post">
				<input type="hidden" name="_method" value="PUT"> <input type="text" name="name" /> <input type="text" name="username" /> <input type="password" name="password" /> <input type="submit" value="提交" />
			</form>
		</div>
	</div>

	<div id="createDialog" class="crudDialog">
		<form>
			<div class="form-group">
				<label for="input1">标题</label> <input id="input1" type="text" class="form-control">
			</div>
			<div class="form-group">
				<label for="input2">名称</label> <input id="input2" type="text" class="form-control">
			</div>
			<div class="form-group">
				<label for="input3">根目录</label> <input id="input3" type="text" class="form-control">
			</div>
			<div class="form-group">
				<label for="input4">图标</label> <input id="input4" type="text" class="form-control">
			</div>
		</form>
	</div>


	<script type="text/javascript" src="${ctx}/static/js/jquery-3.1.1.min.js"></script>
	<script src="${ctx}/static/bootstrap/js/bootstrap.min.js"></script>
	<!-- Latest compiled and minified JavaScript -->
	<script src="${ctx}/static/js/bootstrap-table.min.js"></script>
	<script type="text/javascript" src="${ctx}/static/js/bootstrap-table-export.js"></script>
	<script type="text/javascript" src="${ctx}/static/js/tableExport.js"></script>
	<script type="text/javascript" src="${ctx}/static/js/FileSaver.min.js"></script>
	<script type="text/javascript" src="${ctx}/static/js/jspdf.min.js"></script>
	<script type="text/javascript" src="${ctx}/static/js/jspdf.plugin.autotable.js"></script>
	<script type="text/javascript" src="${ctx}/static/js/html2canvas.min.js"></script>

	<script type="text/javascript">
		//设置input特效
		$(document).on('focus', 'input[type="text"]', function() {
			$(this).parent().find('label').addClass('active');
		}).on('blur', 'input[type="text"]', function() {
			if ($(this).val() == '') {
				$(this).parent().find('label').removeClass('active');
			}
		});
		// 初始化input特效
		function initMaterialInput() {
			$('form input[type="text"]').each(function() {
				if ($(this).val() != '') {
					$(this).parent().find('label').addClass('active');
				}
			});
		}
	</script>
	<!-- Latest compiled and minified Locales -->
	<script src="${ctx}/static/js/bootstrap-table-zh-CN.min.js"></script>

	<script type="text/javascript">
		$(function() {
			$('#table').bootstrapTable({
				pagination : true,
				/* 缓存 */
				cache : false,
				/* 是否启用搜索框 */
				search : true,
				/* 是否显示 刷新按钮 */
				showRefresh : true,
				/* 是否显示 切换试图（table/card）按钮 */
				showToggle : true,
				/* 是否显示 数据条数选择框 */
				showPaginationSwitch : true,
				/* 设置为 true 会有隔行变色效果 */
				striped : true,
				/* 是否显示 内容列下拉框 */
				showColumns : true,
				/* 是否显示 数据条数选择框 */
				showPaginationSwitch : true,
				//可供选择的每页的行数（*）    
				pageList : [ 3, 10, 25, 50, 100 ],
				/* 指定主键列 */
				idField : 'id',
				/* 设置为 true 可以显示详细页面模式。 */
				detailView : true,
				/* 是否显示列头 */
				showHeader : true,
				/* 是否显示列脚 */
				showFooter : false,
				/* 设置true 将在点击行时，自动选择rediobox 和 checkbox */
				clickToSelect : true,
				toolbar : '#toolbar',
				/* 设置为 true启用 全匹配搜索，否则为模糊搜索 */
				strictSearch : true,
				/* 设置为 true 在点击分页按钮或搜索按钮时，将记住checkbox的选择项 */
				maintainSelected : true,
				/* 导出格式 */
				exportTypes : [ 'json', 'xml', 'png', 'csv', 'txt', 'sql', 'doc', 'excel', 'powerpoint', 'pdf' ],
				/* 格式化详细页面模式的视图。 */
				detailFormatter : function(index, row) {
					var html = [];

					$.each(row, function(key, value) {

						html.push('<p><b>' + key + ':</b> ' + value + '</p>');

					})
					return html.join('');
				},
				url : '/ao/user/getTableData',
				dataType : 'json',
				method : 'get',
				sidePagination : 'server',
				pageNumber : 1,
				pageSize : 3,
				sortName : 'id',
				queryParamsType : "",
				sortOrder : 'asc',
				queryParams : function(params) {
					return {
						pageNumber : params.pageNumber,
						pageSize : params.pageSize,
						sortName : params.sortName,
						sortOrder : params.sortOrder,
						searchText : ''
					};
				},
			});

			$('#toolbar').find('select').change(function() {
				$table.bootstrapTable('destroy').bootstrapTable({
					exportDataType : $(this).val()
				});
			});
		});
	</script>
</body>
</html>