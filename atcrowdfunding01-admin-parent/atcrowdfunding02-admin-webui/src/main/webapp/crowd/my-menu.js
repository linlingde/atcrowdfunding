function generationTree() {

    $.ajax({
        url: "menu/get/menu/list.json",
        type: "post",
        dataType: "json",
        success: function (response) {
            let result = response.result;
            if (result == "SUCCESS") {
                let setting = {
                    "view": {
                        "addDiyDom": myAddDivDom,
                        "addHoverDom": myAddHoverDom,
                        "removeHoverDom": myRemoveHoverDom
                    },
                    "data": {
                        "key": {
                            "url": "maomi"
                        }
                    }
                };
                let zNodes = response.data;
                $.fn.zTree.init($("#treeDemo"), setting, zNodes);
            }
            if (result == "FAILED") {
                layer.msg("数据获取失败:" + response.message);
            }
        }
    })


}

// 在鼠标移除节点范围时,删除按钮组
function myRemoveHoverDom(treeId, treeNode) {
    let btnGroupId = treeNode.tId + "_btnGroup";
    $("#" + btnGroupId).remove();

}


// 在鼠标移入节点范围时,添加按钮组
function myAddHoverDom(treeId, treeNode) {
    // 修改标签
    let editBtn = "<a id='" + treeNode.id + "' class='btn btn-info dropdown-toggle btn-xs editBtn' style='margin-left:10px;padding-top:0px;' href='#' title='修改'>&nbsp;&nbsp;<i class='fa fa-fw fa-edit rbg '></i></a>";
    // 增加标签
    let addBtn = "<a id='" + treeNode.id + "' class='btn btn-info dropdown-toggle btn-xs addBtn' style='margin-left:10px;padding-top:0px;' href='#' title='添加'>&nbsp;&nbsp;<i class='fa fa-fw fa-plus rbg '></i></a>";
    // 删除标签
    let removeBtn = "<a id='" + treeNode.id + "' class='btn btn-info dropdown-toggle btn-xs removeBtn' style='margin-left:10px;padding-top:0px;' href='#' title='删除'>&nbsp;&nbsp;<i class='fa fa-fw fa-times rbg '></i></a>";

    // 为了在需要移除按钮组的时候能够精确定位到按钮组所在的span,需要给span设置有规律的id
    let btnGroupId = treeNode.tId + "_btnGroup";
    // 判断之前是否添加了按钮
    if ($("#" + btnGroupId).length > 0)
        return;
    // 那个超链接的id
    let anchorId = treeNode.tId + "_a";
    // 执行在超链接构面附加span元素的操作
    // 标签等级为0,有新增按钮
    if (treeNode.level == 0)
        $("#" + anchorId).after("<span id='" + btnGroupId + "'>" + addBtn + "</span>")
    // 标签等级为1,有新增,删除,标记按钮
    else if (treeNode.level == 1)
        $("#" + anchorId).after("<span id='" + btnGroupId + "'>" + editBtn + removeBtn + addBtn + "</span>")
    // 标签等级为2,有编辑和删除按钮
    else if (treeNode.level == 2)
        $("#" + anchorId).after("<span id='" + btnGroupId + "'>" + editBtn + removeBtn + "</span>")

}


function myAddDivDom(treeId, treeNode) {
    // treeId:整个树形结构附着的ul的标签id
    console.log("treeId:", treeId);
    // 当前树形节点的全部数据,包括从后端查询得到的Menu对象的全部属性
    console.log(treeNode)

    // ztree生成id的规则

    // 例子:treeDemo_7_ico
    // 解析:ul标签的id_当前节点序号_功能
    let spanId = treeNode.tId + "_ico"

    // 根据控制图标的span标签的id找到这个span标签
    // 删除旧的class
    // 添加新的class
    $("#" + spanId).removeClass().addClass(treeNode.icon);


}