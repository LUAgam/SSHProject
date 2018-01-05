<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../taglib.jsp"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>宝贝毕设</title>
<link href="${ctx}/static/resources/plugins/fullPage/jquery.fullPage.css" rel="stylesheet" />
<link href="${ctx}/static/resources/plugins/bootstrap-3.3.0/css/bootstrap.min.css" rel="stylesheet" />
<link href="${ctx}/static/resources/plugins/material-design-iconic-font-2.2.0/css/material-design-iconic-font.min.css" rel="stylesheet" />
<link href="${ctx}/static/resources/plugins/waves-0.7.5/waves.min.css" rel="stylesheet" />
<link href="${ctx}/static/resources/plugins/malihu-custom-scrollbar-plugin/jquery.mCustomScrollbar.min.css" rel="stylesheet" />
<link href="${ctx}/static/resources/css/admin.css" rel="stylesheet" />
<link rel="stylesheet" href="${ctx}/static/css/bootstrap-table.min.css">
<style>
.crudDialog {
	padding: 0 0 2px;
}

.dialog-buttons a {
	font-weight: bold;
}

.form-group {
	margin-bottom: 20px;
}

.form-group label {
	position: absolute;
	line-height: 2;
	font-size: 16px;
	font-weight: normal;
	transition: all .2s;
	pointer-events: none;
	color: #999;
}

.form-group .active {
	transform: translateY(-65%);
	font-size: 12px;
	color: #337ab7;
}

.form-group .form-control {
	font-size: 14px;
	box-shadow: none;
	padding-left: 0;
	padding-right: 0;
	border-radius: 0;
	border: none;
	border-bottom: 2px solid #eee;
	outline: none;
	transition: all .5s;
}

.form-group .form-control:focus {
	box-shadow: none;
	border-color: #337ab7;
}
</style>
<style>
/** skins **/
#zheng-upms-server #header {
	background: #29A176;
}

#zheng-upms-server .content_tab {
	background: #29A176;
}

#zheng-upms-server .s-profile>a {
	background: url(${ctx}/static/resources/images/zheng-upms.png) left top no-repeat;
}

#zheng-cms-admin #header {
	background: #455EC5;
}

#zheng-cms-admin .content_tab {
	background: #455EC5;
}

#zheng-cms-admin .s-profile>a {
	background: url(${ctx}/static/resources/images/zheng-cms.png) left top no-repeat;
}

#zheng-pay-admin #header {
	background: #F06292;
}

#zheng-pay-admin .content_tab {
	background: #F06292;
}

#zheng-pay-admin .s-profile>a {
	background: url(${ctx}/static/resources/images/zheng-pay.png) left top no-repeat;
}

#zheng-ucenter-home #header {
	background: #6539B4;
}

#zheng-ucenter-home .content_tab {
	background: #6539B4;
}

#zheng-ucenter-home .s-profile>a {
	background: url(${ctx}/static/resources/images/zheng-ucenter.png) left top no-repeat;
}

#zheng-oss-web #header {
	background: #0B8DE5;
}

#zheng-oss-web .content_tab {
	background: #0B8DE5;
}

#zheng-oss-web .s-profile>a {
	background: url(${ctx}/static/resources/images/zheng-oss.png) left top no-repeat;
}

#test #header {
	background: test;
}

#test .content_tab {
	background: test;
}

#test .s-profile>a {
	background: url(test) left top no-repeat;
}
</style>
</head>
<body>
	<header id="header">
		<ul id="menu">
			<li id="guide" class="line-trigger">
				<div class="line-wrap">
					<div class="line top"></div>
					<div class="line center"></div>
					<div class="line bottom"></div>
				</div>
			</li>
			<li id="logo" class="hidden-xs"><a href="index.html"> <img src="${ctx}/static/resources/images/logo2.png" />
			</a> <span id="system_title">电商平台</span></li>
			<li class="pull-right">
				<ul class="hi-menu">
					<!-- 搜索 -->
					<li class="dropdown"><a class="waves-effect waves-light" data-toggle="dropdown" href="javascript:;"> <i class="him-icon zmdi zmdi-search"></i>
					</a>
						<ul class="dropdown-menu dm-icon pull-right">
							<form id="search-form" class="form-inline">
								<div class="input-group">
									<input id="keywords" type="text" name="keywords" class="form-control" placeholder="搜索" />
									<div class="input-group-btn">
										<button type="submit" class="btn btn-default">
											<span class="glyphicon glyphicon-search"></span>
										</button>
									</div>
								</div>
							</form>
						</ul></li>
					<li class="dropdown"><a class="waves-effect waves-light" data-toggle="dropdown" href="javascript:;"> <i class="him-icon zmdi zmdi-dropbox"></i>
					</a>
						<ul class="dropdown-menu dm-icon pull-right">
							<li class="skin-switch">请选择系统切换</li>
							<li class="divider"></li>
							<li><a class="waves-effect switch-systems" href="javascript:;" systemid="1" systemname="zheng-upms-server" systemtitle="火车票监控程序"><i class="zmdi zmdi-shield-security"></i> 火车票监控程序</a></li>

							<li><a class="waves-effect switch-systems" href="javascript:;" systemid="2" systemname="zheng-cms-admin" systemtitle="内容管理系统"><i class="zmdi zmdi-wikipedia"></i> 内容管理系统</a></li>

							<li><a class="waves-effect switch-systems" href="javascript:;" systemid="3" systemname="zheng-pay-admin" systemtitle="支付管理系统"><i class="zmdi zmdi-paypal-alt"></i> 支付管理系统</a></li>

							<li><a class="waves-effect switch-systems" href="javascript:;" systemid="4" systemname="zheng-ucenter-home" systemtitle="用户管理系统"><i class="zmdi zmdi-account"></i> 用户管理系统</a></li>

							<li><a class="waves-effect switch-systems" href="javascript:;" systemid="5" systemname="zheng-oss-web" systemtitle="存储管理系统"><i class="zmdi zmdi-cloud"></i> 存储管理系统</a></li>
						</ul></li>
					<li class="dropdown"><a class="waves-effect waves-light" data-toggle="dropdown" href="javascript:;"> <i class="him-icon zmdi zmdi-more-vert"></i>
					</a>
						<ul class="dropdown-menu dm-icon pull-right">
							<li class="hidden-xs"><a class="waves-effect" data-ma-action="fullscreen" href="javascript:fullPage();"><i class="zmdi zmdi-fullscreen"></i> 全屏模式</a></li>
							<li><a class="waves-effect" data-ma-action="clear-localstorage" href="javascript:;"><i class="zmdi zmdi-delete"></i> 清除缓存</a></li>
							<li><a class="waves-effect" href="javascript:;"><i class="zmdi zmdi-face"></i> 隐私管理</a></li>
							<li><a class="waves-effect" href="javascript:;"><i class="zmdi zmdi-settings"></i> 系统设置</a></li>
							<li><a class="waves-effect" href="javascript:;"><i class="zmdi zmdi-run"></i> 退出登录</a></li>
						</ul></li>
				</ul>
			</li>
		</ul>
	</header>
	<section id="main">
		<!-- 左侧导航区 -->
		<aside id="sidebar">
			<!-- 个人资料区 -->
			<div class="s-profile">
				<a class="waves-effect waves-light" href="javascript:;">
					<div class="sp-pic">
						<img src="${ctx}/static/resources/images/avatar.jpg" />
					</div>
					<div class="sp-info">
						张恕征，您好！ <i class="zmdi zmdi-caret-down"></i>
					</div>
				</a>
				<ul class="main-menu">
					<li><a class="waves-effect" href="javascript:;"><i class="zmdi zmdi-account"></i> 个人资料</a></li>
					<li><a class="waves-effect" href="javascript:;"><i class="zmdi zmdi-face"></i> 隐私管理</a></li>
					<li><a class="waves-effect" href="javascript:;"><i class="zmdi zmdi-settings"></i> 系统设置</a></li>
					<li><a class="waves-effect" href="javascript:;"><i class="zmdi zmdi-run"></i> 退出登录</a></li>
				</ul>
			</div>
			<!-- /个人资料区 -->
			<!-- 菜单区 -->
			<ul class="main-menu">
				<li><a class="waves-effect" href="javascript:Tab.addTab('首页', 'home');"><i class="zmdi zmdi-home"></i> 首页</a></li>
				<li class="sub-menu system_menus system_1 0"><a class="waves-effect" href="javascript:;"><i class="zmdi zmdi-accounts-list"></i> 系统组织管理</a>
					<ul>
						<li><a class="waves-effect" href="javascript:Tab.addTab('系统管理', 'crud.html');">系统管理</a></li>
						<li><a class="waves-effect" href="javascript:Tab.addTab('组织管理', '/manage/organization/index');">组织管理</a></li>
					</ul></li>
				<li class="sub-menu system_menus system_1 3"><a class="waves-effect" href="javascript:;"><i class="zmdi zmdi-accounts"></i> 角色用户管理</a>
					<ul>
						<li><a class="waves-effect" href="javascript:Tab.addTab('角色管理', '/manage/role/index');">角色管理</a></li>
						<li><a class="waves-effect" href="javascript:Tab.addTab('用户管理', '/manage/user/index');">用户管理</a></li>
					</ul></li>
				<li class="sub-menu system_menus system_1 6"><a class="waves-effect" href="javascript:;"><i class="zmdi zmdi-lock-outline"></i> 权限资源管理</a>
					<ul>
						<li><a class="waves-effect" href="javascript:Tab.addTab('权限管理', '/manage/permission/index');">权限管理</a></li>
					</ul></li>
				<li class="sub-menu system_menus system_1 7"><a class="waves-effect" href="javascript:;"><i class="zmdi zmdi-more"></i> 其他数据管理</a>
					<ul>
						<li><a class="waves-effect" href="javascript:Tab.addTab('公共码表', '/manage/coder/index');">公共码表</a></li>
						<li><a class="waves-effect" href="javascript:Tab.addTab('会话管理', '/manage/session/index');">会话管理</a></li>
						<li><a class="waves-effect" href="javascript:Tab.addTab('日志记录', '/manage/log/index');">日志记录</a></li>
						<li><a class="waves-effect" href="javascript:Tab.addTab('键值设置', '/manage/map/index');">键值设置</a></li>
					</ul></li>
				<li class="sub-menu system_menus system_2 12" style="display: none;"><a class="waves-effect" href="javascript:;"><i class="zmdi zmdi-menu"></i> 标签类目管理</a>
					<ul>
						<li><a class="waves-effect" href="javascript:Tab.addTab('标签管理', '/manage/tag/index');">标签管理</a></li>
						<li><a class="waves-effect" href="javascript:Tab.addTab('类目管理', '/manage/category/index');">类目管理</a></li>
					</ul></li>
				<li class="sub-menu system_menus system_2 15" style="display: none;"><a class="waves-effect" href="javascript:;"><i class="zmdi zmdi-collection-text"></i> 文章内容管理</a>
					<ul>
						<li><a class="waves-effect" href="javascript:Tab.addTab('文章管理', '/manage/article/index');">文章管理</a></li>
						<li><a class="waves-effect" href="javascript:Tab.addTab('回收管理', '/manage/article/recycle');">回收管理</a></li>
					</ul></li>
				<li>
					<div class="upms-version">&copy; ZHENG-UPMS V1.0.0</div>
				</li>
			</ul>
			<!-- /菜单区 -->
		</aside>
		<!-- /左侧导航区 -->
		<section id="content">
			<div class="content_tab">
				<div class="tab_left">
					<a class="waves-effect waves-light" href="javascript:;"><i class="zmdi zmdi-chevron-left"></i></a>
				</div>
				<div class="tab_right">
					<a class="waves-effect waves-light" href="javascript:;"><i class="zmdi zmdi-chevron-right"></i></a>
				</div>
				<ul id="tabs" class="tabs">
					<li id="tab_home" data-index="home" data-closeable="false" class="cur"><a class="waves-effect waves-light">首页</a></li>
				</ul>
			</div>
			<div class="content_main">
				<!-- 内容 -->
				<div id="iframe_home" class="iframe cur">
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
								<tbody id="data">
								</tbody>
							</table>
						</div>
					</div>
				</div>
			</div>
		</section>
	</section>
	<footer id="footer"></footer>

	<script type="text/javascript" src="${ctx}/static/js/jquery-3.1.1.min.js"></script>
	<script src="${ctx}/static/bootstrap/js/bootstrap.min.js"></script>
	<script src="${ctx}/static/resources/plugins/waves-0.7.5/waves.min.js"></script>
	<script src="${ctx}/static/resources/plugins/malihu-custom-scrollbar-plugin/jquery.mCustomScrollbar.concat.min.js"></script>
	<script src="${ctx}/static/resources/plugins/BootstrapMenu.min.js"></script>
	<script src="${ctx}/static/resources/plugins/device.min.js"></script>
	<script src="${ctx}/static/resources/plugins/fullPage/jquery.fullPage.min.js"></script>
	<script src="${ctx}/static/resources/plugins/fullPage/jquery.jdirk.min.js"></script>
	<script src="${ctx}/static/resources/plugins/jquery.cookie.js"></script>

	<script src="${ctx}/static/resources/js/admin.js"></script>

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
			$('#table').bootstrapTable(
					{
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
						exportTypes : [ 'json', 'xml', 'png', 'csv', 'txt',
								'sql', 'doc', 'excel', 'powerpoint', 'pdf' ],
						/* 格式化详细页面模式的视图。 */
						detailFormatter : function(index, row) {
							var html = [];

							$.each(row, function(key, value) {

								html.push('<p><b>' + key + ':</b> ' + value
										+ '</p>');

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

			$.ajax({
				type : "GET",
				url : "/ao/train/getTrainData",
				dataType : "json",
				success : function(data) {
					$.each(data, function(index, content) {
						var result = content.result;
						$.each(result, function(index, contentNew) {
							$('#data').append(
									"<tr data-index='0'><td> <a class='detail-icon' href='#'> <i class='glyphicon glyphicon-plus icon-plus'></i> </a> </td><td class='bs-checkbox'><input data-index='0' name='btSelectItem' type='checkbox' value='110681633249558530'></td><td>" + content.flag
											+ "</td><td>" + content.map
											+ "</td><td>" + contentNew
											+ "</td></tr>");
						})
					});

				}
			});
		});
	</script>
</body>
</html>