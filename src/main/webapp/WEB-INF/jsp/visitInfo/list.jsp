<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
	<title>预约挂号</title>
	<link rel="stylesheet" href="${ctx}/static/css/style.css">
    <link rel="stylesheet" href="${ctx}/static/css/bootstrap.css">
</head>

<body>
	<div class="container-fluid">
        <tags:head />
        <div class="row">
            <tags:left flg="1"/>
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
                    <div class="panel-heading">预约拍照</div>
                    <div class="panel-body">
                        <table class="table table-hover table-border-bottom">
                            <thead>
                            <tr>
                                <th>擅长</th>
                                <th>摄影师</th>
                                <th>work time</th>
                                <th>挂号费</th>
                                <th>状态</th>
                                <th>操作</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${list.content}" var="list" varStatus="status">
                                <tr>
                                    <td>${list.doctor.department.name}</td>
                                    <td><a class="show" data-toggle="modal"  data-target="#doctorIntroduction" hospitalName="${list.doctor.department.hospital.name}" brief="${list.doctor.brief}" fee="${list.doctor.fee}" departmentName="${list.doctor.department.name}" name="${list.doctor.name}" href="javascript:void(0)">${list.doctor.name}</a></td>
                                    <td><fmt:formatDate value="${list.visitDatetime}" pattern="yyyy-MM-dd HH:mm" /> - <fmt:formatDate value="${list.visitPeriodDate}" pattern="HH:mm" /> </td>
                                    <td>${list.doctor.fee}</td>
                                    <td>
                                        <c:if test="${list.status== 1 }">
                                            <button type="button" class="btn btn-success btn-xs">有排期</button>
                                        </c:if>
                                        <c:if test="${list.status== 2 }">
                                            <button type="button" class="btn btn-warning btn-xs">无排期</button>
                                        </c:if>
                                    </td>
                                    <td>
                                    <c:if test="${list.status== 1 }">
                                    	<button id="${list.id}" class="btn btn-primary btn-xs btn_add">预约</button>
                                    </c:if>
                                    <c:if test="${list.status== 2 }">
                                        <button id="${list.id}" class="btn btn-primary btn-xs btn_add" disabled="disabled">预约</button>
                                    </c:if>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                        <tags:pagination page="${list}" paginationSize="5"/>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!--医生简介弹框-->
            <div class="modal fade"  id="doctorIntroduction" tabindex="-1" role="dialog" aria-labelledby="gridSystemModalLabel">
              <div class="modal-dialog" role="document">
                <div class="modal-content">
                  <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="gridSystemModalLabel">摄影师简介</h4>
                  </div>
                  <div class="modal-body">
                    <div class="row">
                      <div class="col-md-12 doctorlist name">摄影师姓名：马保国</div>
                     <!--  <div class="col-md-12 doctorlist userLevel" >医生级别：副主任</div> -->
                      <div class="col-md-12 doctorlist departmentName">擅长：私房照</div>
                      <div class="col-md-12 doctorlist hospitalName">所属机构：梦想家摄影楼</div>
                      <div class="col-md-12 doctorlist fee">写真价格：￥500.00</div>
                      <div class="col-md-12 doctorlist brief">备注：XXXXXXXXXX</div>
                    </div>
                  </div>
                  <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                  </div>
                </div><!-- /.modal-content -->
              </div><!-- /.modal-dialog -->
        	</div><!-- /.modal -->
    <!---添加jquery-->
    <!-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) -->
    <script src="https://cdn.jsdelivr.net/npm/jquery@1.12.4/dist/jquery.min.js"></script>
    <!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/js/bootstrap.min.js"></script>
    <script>
    
    $(".show").click(function () {
    	var doctorName = $(this).attr('name');
    	  $(".name").text("医生姓名："+doctorName);
    	  var departmentName = $(this).attr('departmentName');
    	  $(".departmentName").text("科室："+departmentName);
    	  var fee =  $(this).attr('fee');
    	  $(".fee").text("挂号费：￥"+fee);
    	  var brief = $(this).attr('brief');
    	  $(".brief").text("备注："+brief);
    	  var hospitalName = $(this).attr('hospitalName');
    	  $(".hospitalName").text("医院："+hospitalName);
    });
    $(".btn_add").click(function () {
    	var time = $("#select").val();
    	time = '${date}'+ ' ' + time
    	var id = $(this).attr("id");
    	$.ajax({
				type : "POST",
				contentType:"application/json",
				url: "${ctx}/web/appointment/create/"+id,
				dataType : "json",// 以json格式返回
				success: function(data){
					if(data.code =='200') {
						 alert("预约成功");
						 window.location.reload();
					}else {
						alert(data.message)
					}
				}
			})
 	});

    $("#btn_submit").click(function () {
    	//准备 用  ajax 提交代码  用 json 数据
    	var time = $("#select").val();
    	time = '${date}'+ ' ' + time
    	var data = {"time":time,"doctorId" :$("#id").val()};
    	$.ajax({
				type : "POST",
				contentType:"application/json",
				data: JSON.stringify(data),
				url: "${ctx}/web/make/create",
				dataType : "json",// 以json格式返回
				success: function(data){
					if(data.code =='200') {
						 $('#myModal').modal('hide')
						 alert("预约成功");
					}else {
						alert(data.message)
					}
				}
			})
    });
</script>
</body>
</html>
