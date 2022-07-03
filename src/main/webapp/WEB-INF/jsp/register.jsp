<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<html>
<head>
	<title>登录</title>
	<link rel="stylesheet" href="${ctx}/static/css/style.css">
    <link rel="stylesheet" href="${ctx}/static/css/bootstrap.css">
</head>

<body>
	<div class="container-fluid reg_containersign">
        <div class="dialog_sign">
            <form id="inputForm" action="${ctx}/web/user/${action}" method="post" class="form-horizontal">
                <h3 class="agileinfo_sign">预约照相系统注册</h3>
                <div class="agileits_signin_form">
                    <input type="text" class="form-control form-group" name="userName" placeholder="请输入用户名" required>
                      <input type="text" class="form-control form-group" name="name" placeholder="请输入昵称" required>
                    <input type="password" class="form-control form-group" name="password" placeholder="请输入密码" required>
                    <input type="password" class="form-control form-group" name="newPassword" placeholder="请重新输入密码" required> 
                    <input type="submit" class="form-control form-group btn-primary" value="注册">
                    <a href="${ctx}/web/login" class="help-block">登录</a>
                </div>
            </form>
        </div>
    </div>
    <!---添加jquery-->
   <script src="${ctx}/static/js/jquery-3.3.1.min.js"></script>
   <script src="${ctx}/static/js/bootstrap.js"></script>
   <script>
   		$(document).ready(function() {
			//这个地方可以增加 校验判断
			//判断 两次 密码 是否一致
			$("#inputForm").validate();
		});
	</script>
</body>
</html>
