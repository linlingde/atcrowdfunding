<%--
  Created by IntelliJ IDEA.
  User: linlingde
  Date: 2022/7/11
  Time: 13:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<jsp:include page="include-head.jsp"/>
<link rel="stylesheet" href="css/pagination.css">
<script type="text/javascript" src="jquery/jquery.pagination.js"></script>
<script type="text/javascript" src="crowd/my-role.js"></script>
<script type="text/javascript">
    $(function () {

        // 1. 为分页初始化做准备
        window.pageNum = 1;
        window.pageSize = 5;
        window.keyword = "";

        // 2. 进行分页
        generatePage();
        // 3. 给查询按钮绑定单机响应函数
        $("#searchBtn").click(function () {

            // 获取关键字
            window.keyword = $("#keywordInput").val();
            window.pageNum = 1;
            // 进行分页
            generatePage();
        });

        // 4. 给新建按钮添加单击事件,单机显示模态框
        $("#showAddModalBut").click(function () {
            $("#roleAddModal").modal("show");
        })

        // 5. 点击新增,向后台发送数据,保存角色,关闭模态框,跳转到最后一页
        $("#saveRoleBtn").click(function () {
            addRole();
        })

        // 6. 更新Role
        // 使用on可以解决下面问题,首先绑定动态元素依附的静态元素,给绑定on事件
        // 参数1:绑定单击函数
        // 参数2:获取要绑定单击事件的动态元素
        // 参数3:回调函数
        $("#rolePageBody").on("click", ".editBtn", function () {
            // 显示模态框
            $("#roleEditModal").modal("show")
            // 获取id
            window.id = this.id;
            // 获取name
            let name = $(this).parent().prev().text()
            $("#roleEditModal #roleName").val(name);
        });

        $("#updateRoleBtn").click(function () {

            // 取得文本框中的新值
            let roleName = $("#roleEditModal #roleName").val();
            // 发送请求
            $.ajax({
                url: "role/update.json",
                method: "POST",
                data: {
                    id: window.id,
                    name: roleName
                },
                success: function (response) {
                    if (response.result == "SUCCESS") {
                        $("#roleEditModal").modal("hide");
                        generatePage()
                        layer.msg("保存成功")
                    }
                }
            })
        })
        // let roleArray = [{id: 5, name: "aaa"}, {id: 6, name: "bbb"}]
        // showConfirmModal(roleArray)

        $("#removeRoleBtn").click(function () {
            let requestBody = JSON.stringify(window.roleIdArray);

            $.ajax({
                url: "role/remove/by/role/id/array.json",
                method: "POST",
                data: requestBody,
                dataType: "json",
                type: "text",
                contentType: "application/json;charset=UTF-8",
                success: function (response) {
                    let result = response.result;
                    if (result == "SUCCESS") {
                        layer.msg("删除成功");
                        generatePage();
                        $("#roleConfirmModal").modal("hide");
                        // 删除完成后,取消选中
                        $("#summaryBox").prop("checked", false);

                    } else {
                        layer.msg("删除失败:" + response.message);
                    }
                },
                error: function (response) {
                    layer.msg("删除失败")
                }
            })


        })

        $("#rolePageBody").on("click", ".deleteBtn", function () {


            let roleId = this.id;
            let roleName = $(this).parent().prev().text()
            let roleArray = [{id: roleId, name: roleName}];
            // 显示模态框
            $("#roleConfirmModal").modal("show");


            showConfirmModal(roleArray);
        });

        // 给总的checkbox绑定单击响应函数
        $("#summaryBox").click(function () {
            // 获取当前多选框自身的状态
            let currentStatus = this.checked;
            // 用当前多选框的状态设置其他多选框
            $(".itemBox").prop("checked", currentStatus);
        });

        extracted();

        // 点击删除按钮执行批量删除
        $("#batchRemove").click(function () {
            // 获取到选中的item
            let checkedBoxArray = $(".itemBox:checked");
            // 创建roleArray数组
            let roleArray = [];
            // 对获取到的数组进行遍历
            checkedBoxArray.each(function () {
                // 获取id和name
                let id = this.id;
                let name = $(this).parent().next().text();
                // 将id和name填充到roleArray数组中
                roleArray.push({
                    id: id,
                    name: name
                })
            })
            // 判断roleArray长度,大于0执行删除,等于0提示"请选择元素后进行删除"
            if (roleArray.length == 0) {
                layer.msg("请选择元素后再进行删除");
                return;
            }
            // 执行删除
            showConfirmModal(roleArray);
        })

    })
</script>
<body>
<jsp:include page="include-nav.jsp"/>
<div class="container-fluid">
    <div class="row">
        <jsp:include page="include-sidebar.jsp"/>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title"><i class="glyphicon glyphicon-th"></i> 数据列表</h3>
                </div>
                <div class="panel-body">
                    <form class="form-inline" role="form" style="float:left;">
                        <div class="form-group has-feedback">
                            <div class="input-group">
                                <div class="input-group-addon">查询条件</div>
                                <input id="keywordInput" class="form-control has-success" type="text"
                                       placeholder="请输入查询条件">
                            </div>
                        </div>
                        <button id="searchBtn" type="button" class="btn btn-warning"><i
                                class="glyphicon glyphicon-search"></i> 查询
                        </button>
                    </form>
                    <button type="button" id="batchRemove" class="btn btn-danger" style="float:right;margin-left:10px;">
                        <i class=" glyphicon glyphicon-remove"></i> 删除
                    </button>
                    <button id="showAddModalBut" type="button" class="btn btn-primary" style="float:right;"
                    ><i class="glyphicon glyphicon-plus"></i> 新增
                    </button>
                    <br>
                    <hr style="clear:both;">
                    <div class="table-responsive">
                        <table class="table  table-bordered">
                            <thead>
                            <tr>
                                <th width="30">#</th>
                                <th width="30"><input id="summaryBox" type="checkbox"></th>
                                <th>名称</th>
                                <th width="100">操作</th>
                            </tr>
                            </thead>
                            <tbody id="rolePageBody">
                            </tbody>
                            <tfoot>
                            <tr>
                                <td colspan="6" align="center">
                                    <div id="Pagination" class="pagination"><!--这里显示分页--></div>
                                </td>
                            </tr>

                            </tfoot>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<jsp:include page="role-add-page.jsp"></jsp:include>
<jsp:include page="role-edit-page.jsp"></jsp:include>
<jsp:include page="role-confirm-page.jsp"></jsp:include>


</body>
</html>