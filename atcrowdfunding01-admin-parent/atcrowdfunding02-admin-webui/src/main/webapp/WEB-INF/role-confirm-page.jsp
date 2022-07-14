<%--
  Created by IntelliJ IDEA.
  User: linlingde
  Date: 2022/7/11
  Time: 19:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="roleConfirmModal" class="modal fade" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">确认删除</h4>
            </div>
            <div class="modal-body">
                <h4>请确认是否删除下列角色</h4>
                <span id="roleNameSpan"></span>
            </div>

            <div class="modal-footer">
                <button onclick="$('#roleConfirmModal').modal('hide')" type="button" class="btn btn-default"
                        data-dismiss="modal">
                    关闭
                </button>
                <button id="removeRoleBtn" type="button" class="btn btn-primary">删除</button>
            </div>
        </div>
    </div>
</div>