<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
	<title>预约</title>
	<link rel="stylesheet" href="${ctx}/static/css/style.css">
    <link rel="stylesheet" href="${ctx}/static/css/bootstrap.css">
</head>

<body>
	<div class="container-fluid">
        <tags:head />
        <div class="row">
            <tags:left flg="2"/>
            <div class="col-md-10">
                <!-- Provides extra visual weight and identifies the primary action in a set of buttons -->
                <button type="button" class="btn btn-primary">全部</button>

                <!-- Indicates a successful or positive action -->
                <button type="button" class="btn btn-success">可预约</button>

                <!-- Contextual button for informational alert messages -->
                <button type="button" class="btn btn-info">不可预约</button>

                <!-- Indicates caution should be taken with this action -->
                <button type="button" class="btn btn-warning">签约摄影达人</button>

                <!-- Indicates a dangerous or potentially negative action -->
                <button type="button" class="btn btn-danger">驻场摄影师</button>



                <ol></ol>
                <div class="panel panel-default">
                    <div class="panel-heading">我的独家摄影</div>
                    <div class="panel-body">
                        <table class="table table-hover table-border-bottom">
                            <thead>
                            <tr>
                                <th>预约号</th>
                                <th>摄影师</th>
                                <th>预约时间</th>
                                <th>拍摄价格</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${list.content}" var="list" varStatus="status">
                                <tr>
                                    <td>${list.apptNum}</td>
                                    <td><a href="#">${list.doctor.name}</a></td>
                                    <td><fmt:formatDate value="${list.visitInfo.visitDatetime}" pattern="yyyy-MM-dd HH:mm" /></td>
                                    <td>${list.doctor.fee}</td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                        <tags:pagination page="${list}" paginationSize="5"/>
                    </div>
                </div>
            </div>
    </div>

    <!---添加jquery-->
   <script src="${ctx}/static/js/jquery-3.3.1.min.js"></script>
    <script src="${ctx}/static/js/bootstrap.js"></script>
</body>
</html>
