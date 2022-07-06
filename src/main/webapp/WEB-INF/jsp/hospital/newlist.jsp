<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>我的预约</title>
    <link rel="stylesheet" href="${ctx}/static/css/style.css">
    <link rel="stylesheet" href="${ctx}/static/css/bootstrap.css">
</head>

<body>
<div class="container-fluid">
    <tags:head />
    <div class="row">
        <tags:left flg="2"/>
        <div class="col-md-10">

            <ol></ol>
            <div class="panel panel-default">
                <div class="panel-heading">我的独家摄影</div>
                <div class="panel-body">
                    <table class="table table-hover table-border-bottom">
                        <thead>
                        <tr>
                            <th>客户账号名</th>
                            <th>摄影师名</th>
                            <th>预约主题</th>
                            <th>是否完成</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${list}" var="cont" varStatus="status">
                            <tr>
                                <td><a href=>${cont.user}</a></td>
                                <td><a href=>${cont.video}</a></td>
                                <td><a href=>${cont.name}</a></td>
                                <td><a href=>${cont.isdone}</a></td>
                                <td><a href=" ">修改</a>
                                    <a href="">删除</a>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>

                </div>
            </div>
        </div>
    </div>

    <!---添加jquery-->
    <script src="${ctx}/static/js/jquery-3.3.1.min.js"></script>
    <script src="${ctx}/static/js/bootstrap.js"></script>
</body>
</html>
