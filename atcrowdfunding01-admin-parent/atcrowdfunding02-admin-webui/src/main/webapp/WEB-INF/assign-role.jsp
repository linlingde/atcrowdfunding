<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: linlingde
  Date: 2022/7/14
  Time: 16:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<jsp:include page="include-head.jsp"/>
<body>
<jsp:include page="include-nav.jsp"/>
<script type="text/javascript">
    $(function () {
        $("#toRight").click(function () {
            // 找到左边选中的
            // select:eq(0):找到第一个select
            // >option:select下面的option子标签
            // :selected:选中的
            $("select:eq(0)>option:selected").appendTo("select:eq(1)");
        });
        $("#toLeft").click(function () {
            // 找到右边选中的
            // select:eq(1):找到第二个select
            // >option:select下面的option子标签
            // :selected:选中的
            $("select:eq(1)>option:selected").appendTo("select:eq(0)");
        })
        $("#submitAdminRoleBtn").click(function () {
            $("select:eq(1)>option").prop("selected", "selected")
        })
    })
</script>
<div class="container-fluid">
    <div class="row">
        <jsp:include page="include-sidebar.jsp"/>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
                <ol class="breadcrumb">
                    <li><a href="#">首页</a></li>
                    <li><a href="#">数据列表</a></li>
                    <li class="active">分配角色</li>
                </ol>
                <%--                ${requestScope.pageNum}<br/>--%>
                <%--                ${requestScope.pageNum}<br/>--%>

                <%--                ${requestScope.keyword}<br/>--%>
                <%--                ${requestScope.pageNum}<br/>--%>
                <%--                ${requestScope.assignedRoleList}<br/>--%>
                <%--                ${requestScope.unAssignedRoleList}--%>

                <div class="panel panel-default">
                    <div class="panel-body">
                        <form role="form" class="form-inline" method="post" action="assign/do/role/assign.html">
                            <input type="hidden" name="adminId" value="${requestScope.adminId}">
                            <input type="hidden" name="pageNum" value="${requestScope.pageNum}">
                            <input type="hidden" name="keyword" value="${requestScope.keyword}">
                            <div class="form-group">
                                <label for="exampleInputPassword1">未分配角色列表</label><br>
                                <select class="form-control" multiple="" size="10" style="width:100px;overflow-y:auto;">
                                    <c:forEach items="${requestScope.unAssignedRoleList}" var="role">
                                        <option value="${role.id}">${role.name}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="form-group">
                                <ul>
                                    <li id="toRight" class="btn btn-default glyphicon glyphicon-chevron-right"></li>
                                    <br>
                                    <li id="toLeft" class="btn btn-default glyphicon glyphicon-chevron-left"
                                        style="margin-top:20px;"></li>
                                </ul>
                            </div>
                            <div class="form-group" style="margin-left:40px;">
                                <label for="exampleInputPassword1">已分配角色列表</label><br>
                                <select name="roleIdList" class="form-control" multiple="multiple" size="10"
                                        style="width:100px;overflow-y:auto;">
                                    <c:forEach items="${requestScope.assignedRoleList}" var="role">
                                        <option value="${role.id}">${role.name}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <button id="submitAdminRoleBtn" type="submit" style="width: 150px"
                                    class="btn btn-lg btn-success btn-block">保存
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