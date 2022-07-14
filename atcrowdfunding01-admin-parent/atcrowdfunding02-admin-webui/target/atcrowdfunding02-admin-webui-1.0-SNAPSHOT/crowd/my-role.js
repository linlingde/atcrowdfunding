// 声明专门的函数显示确认模态框
function showConfirmModal(roleArray) {

    // 创建全局数组,存放RoleId
    window.roleIdArray = []
    // 打开模态框
    $("#roleConfirmModal").modal("show");
    $("#roleNameSpan").empty();
    // 便利roleArray数组
    for (let i = 0; i < roleArray.length; i++) {
        let role = roleArray[i];
        let roleName = role.name;
        window.roleIdArray.push(role.id)
        $("#roleNameSpan").append(roleName + "<br/>")
    }

}


// 执行分页,生成页面效果
function generatePage() {
    // 1. 获取分页数据
    let pageInfo = getPageInfoRemote();
    // 将返回的分页数据打印出来研究研究
    console.log(pageInfo)

    // 2. 填充表格
    fillTableBody(pageInfo);


}


// 远程访问服务器端程序,获取PageInfo
function getPageInfoRemote() {
    let ajaxResult = $.ajax({
        // 请求的URL
        "url": "role/get/page/info.json",
        // 请求类型
        "type": "post",
        // 发送的数据
        "data": {
            "pageNum": window.pageNum,
            "pageSize": window.pageSize,
            "keyword": window.keyword
        },
        // 设置为同步调用
        "async": false,
        // 如何解析服务器端返回的数据
        "dataType": "json"
        // // 成功时进行的操作
        // "success": function (response) {
        //
        // },
        // // 失败时进行的操作
        // "error": function (response) {
        //
        // }
    });


    // 获取请求状态码
    let statusCode = ajaxResult.status;
    // 如果状态码不等于200,说明出错了
    if (statusCode != 200) {
        layer.msg("出错了! 状态码为:" + statusCode);
        return;
    }


    // 实体中封装的信息
    let responseMsg = ajaxResult.responseJSON.result;
    // 如果出错了打印出错的信息
    if (responseMsg == 'FAILED') {
        layer.msg("系统错误! " + ajaxResult.responseJSON.message)
        return;
    }
    // 没出错,获取数据并返回
    let pageInfo = ajaxResult.responseJSON.data;
    return pageInfo;

}

// 填充表格
function fillTableBody(pageInfo) {
    // 掏空tbody中的数据
    $("#rolePageBody").empty();
    // 掏空pagination中的信息
    $("#Pagination").empty();
    // 当没有取到数据时
    if (pageInfo == null || pageInfo == undefined || pageInfo.list == null) {
        $("#rolePageBody").append("<tr><td colspan='4'>抱歉没有查询到您需要的数据!</td></td></tr>")
        return;
    }

    // 取到了数据,进行填充
    for (let i = 0; i < pageInfo.list.length; i++) {
        // 获得数据
        // 对象
        let role = pageInfo.list[i];
        // id
        let roleId = role.id;
        // name
        let roleName = role.name;

        // 组合数据
        let numberTd = "<td>" + (i + 1) + "</td>";
        let checkBoxTd = "<td><input id='" + role.id + "' class='itemBox' type='checkbox'/></td>";
        let roleNameTd = "<td>" + roleName + "</td>";
        let checkBtn = "<button type='button' class='btn btn-success btn-xs'><i class=' glyphicon glyphicon-check'></i></button>"
        let editBtn = "<button id='" + role.id + "' type='button' class='btn btn-primary btn-xs editBtn'><i class=' glyphicon glyphicon-pencil'></i></button>"
        let deleteBtn = "<button id='" + role.id + "' type='button' class='btn btn-danger btn-xs deleteBtn'><i class=' glyphicon glyphicon-remove'></i></button>"

        let buttonTd = "<td>" + checkBtn + editBtn + deleteBtn + "</td>"
        $("#rolePageBody").append("<tr>" + numberTd + checkBoxTd + roleNameTd + buttonTd + "</tr>")
    }
    generateNavigator(pageInfo);

}

// 生成分页页码导航条
function generateNavigator(pageInfo) {
    // 获取总记录数
    let totalRecord = pageInfo.total;
    // 更新当前页码
    window.pageNum = pageInfo.pageNum;
    // 更新每页显示数据量
    window.pageSize = pageInfo.pageSize;

    // 声明一个JSON对象存储Pagination要设置的属性
    let properties = {
        num_edge_entries: 3,								// 边缘页数
        num_display_entries: 5,								// 主体页数
        callback: paginationCallBack,						// 指定用户点击“翻页”的按钮时跳转页面的回调函数
        items_per_page: pageInfo.pageSize,	// 每页要显示的数据的数量
        current_page: pageInfo.pageNum - 1,	// Pagination内部使用pageIndex来管理页码，pageIndex从0开始，pageNum从1开始，所以要减一
        prev_text: "上一页",									// 上一页按钮上显示的文本
        next_text: "下一页"									// 下一页按钮上显示的文本
    };

    // 生成页码导航条
    $("#Pagination").pagination(totalRecord, properties);
}

// 回调函数
function paginationCallBack(pageIndex, Jquery) {

    let pageNum = pageIndex + 1;

    window.pageNum = pageNum;

    generatePage();

    // 取消链接默认行为
    return false;

}

// 保存角色
function addRole() {
    // 获取文本框中的内容
    let role = $("#role").val();
    // 使用Ajax保存
    $.ajax({
        "url": "role/add.json",
        "method": "POST",
        "type": "text",
        "data": {
            roleName: role
        },
        "dataType": "json",
        "success": function (response) {
            if (response.result == "SUCCESS") {
                // 打印消息
                layer.msg("添加成功");
                // 关闭模态框
                $("#roleAddModal").modal("hide");
                // 重新分页
                window.pageNum = 111111;
                generatePage();
            } else {
                layer.msg("操作失败:" + response.message);
            }
        },
        "error": function (response) {
            layer.msg(response.message)
        }
    })
}

// 设置全选全不选功能
function extracted() {
    // 全选全部不选的设置其它多选框
    $("#rolePageBody").on("click", ".itemBox", function () {
        // 获取当前已经选中的itemBox数量
        let checkedBoxCount = $(".itemBox:checked").length;
        // 获取全部itemBox数量
        let totalItemBoxCount = $(".itemBox").length;
        // 两者数量相等--->将summaryBox设置为选中状态
        // 两者数量不相等--->将summaryBox设置为未选中状态
        $("#summaryBox").prop("checked", checkedBoxCount == totalItemBoxCount);
    });
}


