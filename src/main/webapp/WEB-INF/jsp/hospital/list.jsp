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
        <a href="${ctx }/web/hospital/input">添加</a>
        <form class="form-search" action="#">
				<label>名称：</label> <input type="text" name="search_LIKE_name" class="input-medium" value="${param.search_LIKE_name}"> 
				<label>地址：</label> <input type="text" name="search_LIKE_address" class="input-medium" value="${param.search_LIKE_address}">
				<button type="submit" class="btn" id="search_btn">Search</button>
		    </form>
        
        
          <table class="table table-hover table-border-bottom">
                            <thead>
                            <tr>
                                <th>摄影师机构</th>
                                  <th>家庭地址</th>
                                  <th>修改资料</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${list.content}" var="list" varStatus="status">
                                <tr>
                                    <td><a href="${ctx }/web/hospital/show/${list.id}">${list.name}</a></td>
                                      <td>${list.address}</td>
                                       <td><a href="${ctx }/web/hospital/input/${list.id}">修改</a>
                                       <a href="${ctx }/web/hospital/del/${list.id}">删除</a>
                                       </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
              <tags:pagination page="${list}" paginationSize="5"/>
       </div>    
    </div>

    <!---添加jquery-->
   <script src="${ctx}/static/js/jquery-3.3.1.min.js"></script>
    <script src="${ctx}/static/js/bootstrap.js"></script>
</body>
</html>
