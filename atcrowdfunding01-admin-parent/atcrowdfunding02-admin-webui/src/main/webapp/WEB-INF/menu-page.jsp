<%--
  Created by IntelliJ IDEA.
  User: linlingde
  Date: 2022/7/13
  Time: 12:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<jsp:include page="include-head.jsp"/>
<body>
<jsp:include page="include-nav.jsp"/>
<link rel="stylesheet" href="ztree/zTreeStyle.css">
<script type="text/javascript" src="ztree/jquery.ztree.all-3.5.min.js"></script>
<script type="text/javascript" src="crowd/my-menu.js"></script>
<script type="text/javascript">
    $(function () {
        // 生成树形结构的函数
        generationTree()

    })
</script>
<div class="container-fluid">
    <div class="row">
        <jsp:include page="include-sidebar.jsp"/>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <i class="glyphicon glyphicon-th-list"></i> 权限菜单列表
                    <div style="float: right; cursor: pointer;" data-toggle="modal"
                         data-target="#myModal">
                        <i class="glyphicon glyphicon-question-sign"></i>
                    </div>
                </div>
                <div class="panel-body">
                    <!-- 这个ul标签是zTree动态生成的节点所依附的静态节点 -->
                    <ul id="treeDemo" class="ztree"></ul>
                </div>
            </div>
        </div>
    </div>
</div>
</div>
</body>
</html>
