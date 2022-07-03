<%@tag pageEncoding="UTF-8"%>
<!-- 为了 判断 选中导航 -->
<%@ attribute name="flg" type="java.lang.Integer" required="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="col-md-2">
<%--	<ul class="list-group">--%>
<%--    	<a href="${ctx}/web/doctor/page" class="list-group-item <c:if test='${flg == 1}'>active</c:if>">预约挂号</a>--%>
<%--        <a href="${ctx}/web/make/page" class="list-group-item <c:if test='${flg == 2}'>active</c:if>">查看预约列表</a>--%>
<%--     </ul>--%>
    <ul class="list-group">
        <li class="list-group-item active">素人核心功能</li>
        <a href="${ctx}/web/visitInfo/page" class="list-group-item">在线预约</a>
        <a href="${ctx}/web/appointment/page" class="list-group-item">查看拍摄记录</a>

        <a href="${ctx}/web/appointment/wodeyuyue" class="list-group-item">查看我的预约</a>
    </ul>

    <div class="list-group">
        <li class="list-group-item active">系统管理员核心功能</li>
        <a href="${ctx}/web/hospital/page" class="list-group-item">摄影系统</a>
        <a href="${ctx}/web/hospital/test1" class="list-group-item">摄影项目管理</a>
        <a href="${ctx}/web/hospital/page" class="list-group-item">摄影系统</a>
    </div>
</div>
