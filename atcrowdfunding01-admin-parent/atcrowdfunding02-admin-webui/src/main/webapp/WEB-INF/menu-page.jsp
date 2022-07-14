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

        // 新增
        $("#treeDemo").on("click", ".addBtn", function () {
            // 将当前节点的id作为新节点的pid保存到全局变量
            window.pid = this.id;
            // 打开模态框
            $("#menuAddModal").modal("show");
            return false;
        });
        // 编辑
        $("#treeDemo").on("click", ".editBtn", function () {
            // 获取id
            window.id = this.id;

            // 打开模态框
            $("#menuEditModal").modal("show");

            // 获取zTreeObj对象
            let zTreeObj = $.fn.zTree.getZTreeObj("treeDemo");
            // 根据id属性查询节点对象
            // 用来搜索节点的属性名
            let key = "id";
            // 用来搜索节点的属性值
            let value = window.id;
            // 获得当前节点
            let currentNode = zTreeObj.getNodeByParam(key, value);
            // 回显数据
            $("#menuEditModal [name=name]").val(currentNode.name);
            $("#menuEditModal [name=url]").val(currentNode.url);
            // radio回显的本质是和节点一样的值选定
            $("#menuEditModal [name=icon]").val([currentNode.icon]);


            return false;
        });

        // 给更新按钮绑定单击函数
        $("#menuEditBtn").click(function () {
            // 获取到页面中的值
            // id
            let id = window.id;
            // name
            let name = $("#menuEditModal [name=name]").val();
            // url
            let url = $("#menuEditModal [name=url]").val();
            // icon
            let icon = $("#menuEditModal [name=icon]:checked").val();
            // 组装数据
            let data = {
                id: id,
                name: name,
                url: url,
                icon: icon
            }

            // 发送Ajax请求
            $.ajax({
                url: "menu/update.json",
                type: "post",
                dataType: "json",
                data: data,
                success: function (response) {

                    // 获取更新结果
                    let result = response.result;
                    // 更新成功,弹窗,刷新树,关闭模态框
                    if (result == "SUCCESS") {
                        layer.msg("更新成功");
                        generationTree();
                        $("#menuEditModal").modal("hide");
                        // 更新失败弹窗提醒
                    } else {
                        layer.msg("更新失败" + response.message)
                    }
                }
            })

        })


        // 删除
        $("#treeDemo").on("click", ".removeBtn", function () {
            // 获取id
            window.id = this.id;
            // 获取zTreeObj对象
            let zTreeObj = $.fn.zTree.getZTreeObj("treeDemo");
            // 根据id属性查询节点对象
            // 用来搜索节点的属性名
            let key = "id";
            // 用来搜索节点的属性值
            let value = window.id;
            // 获得当前节点
            let currentNode = zTreeObj.getNodeByParam(key, value);
            // 回显数据
            $("#menuConfirmModal #removeNodeSpan").html("<li class='" + currentNode.icon + "' >" + currentNode.name + "</li>");

            // 打开模态框
            $("#menuConfirmModal").modal("show");
            return false;

        });

        // 给确认删除模态框中的按钮绑定单击响应函数
        $("#confirmBtn").click(function () {
            $.ajax({
                url: "menu/remove.json",
                type: "post",
                dataType: "json",
                data: {
                    id: window.id
                },
                success: function (response) {
                    let result = response.result;
                    // 删除成功,关闭模态框,重排树,发送消息提醒
                    if (result == "SUCCESS") {
                        generationTree();
                        layer.msg("删除成功");
                    } else {
                        layer.msg("删除失败:" + response.message);
                    }
                }
            });
            $("#menuConfirmModal").modal("hide")

        })


        // 给添加子节点的模态框中保存按钮绑定单击函数
        $("#menuSaveBtn").click(function () {
            let name = $.trim($("#addMenuModal [name=name]").val());
            let url = $.trim($("#addMenuModal [name=url]").val());
            let icon = $.trim($("#addMenuModal [name=icon]:checked").val());
            let data = {
                "name": name,
                "url": url,
                "icon": icon,
                "pid": window.pid
            }
            // console.log(data)
            $.ajax({
                "url": "menu/add.json",
                "dataType": "json",
                "type": "post",
                "data": data,
                "success": function (response) {
                    // 添加成功,关闭模态框,提提示添加成功,重新加载列表
                    let result = response.result;
                    if (result == "SUCCESS") {
                        $("#menuAddModal").modal("hide");
                        layer.msg("添加成功");
                        generationTree();
                        $("#menuResetBtn").click();
                    } else {
                        // 添加失败,打印失败消息
                        layer.msg("添加失败:" + response.message)
                    }
                }

            })
        })

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
<jsp:include page="menu-add-page.jsp"></jsp:include>
<jsp:include page="menu-confirm-page.jsp"></jsp:include>
<jsp:include page="menu-edit-page.jsp"></jsp:include>
</html>
