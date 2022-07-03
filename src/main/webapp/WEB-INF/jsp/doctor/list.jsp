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
      <tags:head />
        <div class="row">
        	<tags:left  flg="1"/>
            <div class="col-md-10">
                <legend>约拍</legend>
                <table class="table table-hover table-border-bottom">
                        <thead>
                            <tr>
                                <th>name</th>
                                <th>work time</th>
                                <th>写真价格</th>
                                <th>简介</th>
                                <th>预约</th>
                            </tr>
                        </thead>
                        <tbody>
                         <c:forEach items="${list.content}" var="list" varStatus="status">
							<tr>
								<td>${list.name}</td>
								<td> 
								<c:forEach items="${list.visitInfos}" var="visitInfo" varStatus="status">
									<span>${visitInfo.visitDatetime}</span>
								</c:forEach>
								</td>
								<td>${list.fee}</td>
								<td>${list.brief }</td>
								<td><button  class="btn btn-primary btn-xs btn_add">预约</button></td>
								<%-- time='${list.time}' id="${list.id}" name='${list.name}' --%>
							</tr>
						</c:forEach> 
                        </tbody>
                    </table>
                   <tags:pagination page="${list}" paginationSize="5"/>
            	</div>
            </div>
    </div>
    
    <!--弹框-->
     <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
     	<!-- 医生id -->
     	<input id="id" name="id" type="hidden"/>
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
                    <h4 class="modal-title" id="myModalLabel"></h4>
                </div>
                <div class="modal-body">
                        <label for="txt_departmentname">请选择预约时间</label>
                        <select id="select" class="form-control">
                        	
                        </select>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal"><span class="glyphicon glyphicon-remove" aria-hidden="true"></span>关闭</button>
                    <button type="button" id="btn_submit" class="btn btn-primary" ><span class="glyphicon glyphicon-floppy-disk" aria-hidden="true"></span>预约</button>
                </div>
            </div>
        </div>
    </div>
    <!---添加jquery-->
   <script src="${ctx}/static/js/jquery-3.3.1.min.js"></script>
    <script src="${ctx}/static/js/bootstrap.js"></script>
    <script>
    $(".btn_add").click(function () {
    var time = $(this).attr('time');
     $("#myModalLabel").text('${date}'+"预约"+ $(this).attr('name'));
     var strs= new Array(); //定义一数组
     strs = time.split(",");
     $('#select').empty();
     var html = '<option>请选择预约时间</option>';
     for (i=0;i<strs.length ;i++ )
     {
    	 html+='<option>';
    	 html+=strs[i];
    	 html+='</option>';
     } 
     $('#select').append(html);
     $("#id").val($(this).attr('id'));
    	$('#myModal').modal();
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
