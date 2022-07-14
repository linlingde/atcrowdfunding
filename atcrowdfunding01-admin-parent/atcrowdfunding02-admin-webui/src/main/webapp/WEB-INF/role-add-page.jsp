<%--
  Created by IntelliJ IDEA.
  User: linlingde
  Date: 2022/7/11
  Time: 19:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="roleAddModal" class="modal fade" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">新增角色</h4>
            </div>
            <div class="modal-body">
                <input id="role" type="text" placeholder="请输入角色名称">
            </div>
            <div class="modal-footer">
                <button onclick="$('#roleAddModal').modal('hide')" type="button" class="btn btn-default"
                        data-dismiss="modal">
                    关闭
                </button>
                <button id="saveRoleBtn" type="button" class="btn btn-primary">保存</button>
            </div>
        </div>
    </div>
</div>