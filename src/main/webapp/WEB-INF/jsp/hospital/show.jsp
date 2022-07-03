<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
	<title>预约</title>
	<link rel="stylesheet" href="${ctx}/static/css/style.css">
    <link rel="stylesheet" href="${ctx}/static/css/bootstrap.css">
</head>

<body>
	<div class="container-fluid">
       <%--  <tags:head /> --%>
        <div class="row">
            <tags:left flg="2"/>
           <!--  这个地方 就是  html 里面的 内容 可以 写  html css -->
            	名称：<input name="name" value="${model.name }" disabled="disabled"/>
            	code：<input name="code" value="${model.code }"  disabled="disabled"/>
            	地址：<input name="address" value="${model.address }"  disabled="disabled"/>
            	备注：<input name="brief" value="${model.brief }"  disabled="disabled"/>
       </div>    
    </div>

    <!---添加jquery-->
   <script src="${ctx}/static/js/jquery-3.3.1.min.js"></script>
    <script src="${ctx}/static/js/bootstrap.js"></script>
</body>
</html>
