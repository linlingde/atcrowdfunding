<%--
  Created by IntelliJ IDEA.
  User: linlingde
  Date: 2022/7/11
  Time: 09:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<jsp:include page="include-head.jsp"/>
<body>
<jsp:include page="include-nav.jsp"/>
<div class="container-fluid">
    <div class="row">
        <jsp:include page="include-sidebar.jsp"/>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
                <ol class="breadcrumb">
                    <li><a href="#">首页</a></li>
                    <li><a href="#">数据列表</a></li>
                    <li class="active">修改</li>
                </ol>
                <div class="panel panel-default">
                    <div class="panel-heading">表单数据
                        <div style="float:right;cursor:pointer;" data-toggle="modal" data-target="#myModal"><i
                                class="glyphicon glyphicon-question-sign"></i></div>
                    </div>
                    <div class="panel-body">
                        <form role="form" method="post" action="admin/update.html">
                            <input type="hidden" name="id" value="${requestScope.editAdmin.id}">
                            <input type="hidden" name="pageNum" value="${requestScope.pageNum}">
                            <input type="hidden" name="keyword" value="${requestScope.keyword}">
                            <div class="form-group">
                                <label for="exampleInputPassword1">登陆账号</label>
                                <input name="loginAcct" type="text" class="form-control" id="exampleInputPassword1"
                                       value="${requestScope.editAdmin.loginAcct}">
                            </div>
                            <div class="form-group">
                                <label for="exampleInputPassword1">用户名称</label>
                                <input name="userName" type="text" class="form-control" id="exampleInputPassword1"
                                       value="${requestScope.editAdmin.userName}">
                            </div>
                            <div class="form-group">
                                <label for="exampleInputEmail1">邮箱地址</label>
                                <input name="email" type="email" class="form-control" id="exampleInputEmail1"
                                       value="${requestScope.editAdmin.email}">
                                <p class="help-block label label-warning">请输入合法的邮箱地址, 格式为：
                                    xxxx@xxxx.com</p>
                            </div>
                            <button type="submit" class="btn btn-success"><i class="glyphicon glyphicon-edit"></i> 修改
                            </button>
                            <button type="reset" class="btn btn-danger"><i class="glyphicon glyphicon-refresh"></i> 重置
                            </button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>