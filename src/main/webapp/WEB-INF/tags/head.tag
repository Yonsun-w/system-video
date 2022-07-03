<%@tag import="com.neusoft.java.hars.entity.User"%>
<%@tag pageEncoding="UTF-8"%>
 <!-- 获取 session 为了 显示 登录人的用户名 -->
 <%
	User user=(User)session.getAttribute("user");
	String name =user.getName();
%>
<div class="row">
<%--  挂号系统--%>
<%--   <span>用户名： <%=name%></span>--%>
    <div class="col-md-12">
        <!-----------------导航条----------------->
        <nav class="navbar navbar-default">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <a class="navbar-brand" href="#">摄影拍照系统</a>
            </div>

            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav navbar-right">
                    <li><a href="#">管理员 身份</a></li>
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button"
                           aria-haspopup="true" aria-expanded="false">我的账户<!--欢迎你，admin--> <span class="caret"></span></a>
                        <ul class="dropdown-menu">
                            <li><a href="#">我的信息</a></li>
                            <li><a href="#">查看权限</a></li>
                            <li><a href="#">切换账户</a></li>
                            <li role="separator" class="divider"></li>
                            <li><a href="#">退出登录</a></li>
                        </ul>
                    </li>
                </ul>
            </div><!-- /.navbar-collapse -->
        </nav>
    </div>
</div>
