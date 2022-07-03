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
	            <%--     测试一下   看看能不能 访问到
	            <a href="${ctx}/web/hospital/show/1">我要查看一下</a> --%>
	            
	            <form action="${ctx }/web/hospital/${action}" method="post">
	            <input type="hidden" name="id" value="${model.id }"/>
	            	名称：<input name="name" value="${model.name }"/>
	            	code：<input name="code" value="${model.code }"/>
	            	地址：<input name="address" value="${model.address }"/>
	            	备注：<input name="brief" value="${model.brief }"/>
	            	<button type="submit">提交</button>
	            </form>
	            
	       </div>    
	    </div>
	
	    <!---添加jquery-->
	   <script src="${ctx}/static/js/jquery-3.3.1.min.js"></script>
	    <script src="${ctx}/static/js/bootstrap.js"></script>
	</body>
</html>
