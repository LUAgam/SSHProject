<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../taglib.jsp"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>宝贝毕设</title>
<link href="${ctx}/static/resources/plugins/bootstrap-3.3.0/css/bootstrap.min.css" rel="stylesheet" />
<link href="${ctx}/static/resources/plugins/material-design-iconic-font-2.2.0/css/material-design-iconic-font.min.css" rel="stylesheet" />
<link href="${ctx}/static/resources/plugins/waves-0.7.5/waves.min.css" rel="stylesheet" />
<link href="${ctx}/static/resources/plugins/checkbix/css/checkbix.min.css" rel="stylesheet" />
<link href="${ctx}/static/jquery-mloading/jquery.mloading.css" rel="stylesheet" />
<link href="${ctx}/static/resources/css/login.css" rel="stylesheet" />
<style type="text/css">
.amerror {
	color: #b94a48;
}
.amright{
float: right;
}
</style>
</head>
<body>

	<div id="login-window">
		<form action="/ao/login" method="post">
			<div class="input-group m-b-20">
				<span class="input-group-addon"><i class="zmdi zmdi-account"></i></span>
				<div class="fg-line">
					<input id="username" type="text" class="form-control" name="username" placeholder="帐号" required autofocus value="${user.username }">
				</div>
			</div>
			<div class="input-group m-b-20">
				<span class="input-group-addon"><i class="zmdi zmdi-male"></i></span>
				<div class="fg-line">
					<input id="password" type="password" class="form-control" name="password" placeholder="密码" required value="${user.password }">
				</div>
			</div>
			<div class="clearfix"></div>
			<div class="checkbox">
				<input id="rememberMe" type="checkbox" class="checkbix" data-text="自动登录" name="rememberMe">
				<c:if test="${!empty message }">
					<span class="m-b-20 amerror amright">
						<span class="glyphicon glyphicon-remove"></span><font class="text-center">${message }</font>
					</span>
				</c:if>
			</div>
			<a id="login-bt" href="javascript:;" class="waves-effect waves-button waves-float"><i class="zmdi zmdi-arrow-forward"></i></a>
		</form>
	</div>
	<script src="${ctx}/static/resources/plugins/jquery.1.12.4.min.js"></script>
	<script src="${ctx}/static/resources/plugins/bootstrap-3.3.0/js/bootstrap.min.js"></script>
	<script src="${ctx}/static/resources/plugins/waves-0.7.5/waves.min.js"></script>
	<script src="${ctx}/static/resources/plugins/checkbix/js/checkbix.min.js"></script>
	<script src="${ctx}/static/jquery-mloading/jquery.mloading.js"></script>

	<script src="${ctx}/static/resources/js/login.js"></script>
	<script type="text/javascript">
		Checkbix.init();
	</script>
	<script type="text/javascript">
		// 登录
		function login() {
			$("body").mLoading();
			$('form').submit();
		}
	</script>
</body>
</html>