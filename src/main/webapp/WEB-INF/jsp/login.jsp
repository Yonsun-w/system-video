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
        <span style="color: red">${error}</span>
             <form id="inputForm" action="${ctx}/web/user/login" method="post" class="form-horizontal">
                <h3 class="agileinfo_sign">预约照相系统登录</h3>
                <div class="agileits_signin_form">
                    <input type="text" class="form-control form-group" name="userName" placeholder="请输入用户名" required>
                    <input type="password" class="form-control form-group" name="password" placeholder="请输入密码" required>
                    <input type="submit" class="form-control form-group btn-primary" value="登录">
                    <a href="${ctx}/web/user/register" class="help-block">注册</a>
                </div>
            </form>
        </div>
    </div>
    <!---添加jquery-->
   <script src="${ctx}/static/js/jquery-3.3.1.min.js"></script>
   <script src="${ctx}/static/js/bootstrap.js"></script>
</body>
</html>
