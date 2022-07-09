<%--
  Created by IntelliJ IDEA.
  User: linlingde
  Date: 2022/7/8
  Time: 09:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<%--
    ${pageContext.request.serverName}:localhost
    ${pageContext.request.serverPort}:8080
    ${pageContext.request.contextPath}:atcrowdfunding02_admin_webui_war
--%>
<%--<base href="http://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/"/>--%>
<%--<script type="text/javascript" src="jquery/jquery-2.1.1.min.js"/>--%>
<base href="http://${pageContext.request.serverName }:${pageContext.request.serverPort }${pageContext.request.contextPath }/"/>
<script type="text/javascript" src="jquery/jquery-2.1.1.min.js"></script>
<script type="text/javascript" src="layer/layer.js"></script>
<script type="text/javascript">
    $(function(){
        $('#btn1').click(function () {

            $.ajax({
                "url":"send/array.html",
                "type":"post",
                "data": {
                    "array":[5, 8, 12]
                },
                "dataType":"text",      // 如何对待返回的数据
                "success":function (response) {
                    alert(response)
                },
                "error":function (response) {
                    alert(response)
                }
            })

        })

        $('#btn2').click(function () {
            layer.msg('layer的弹窗')
        })
    })
</script>
<body>
    <a href="test/ssm.html">整合ssm环境</a>
<%--http://localhost:8080/atcrowdfunding02_admin_webui_war/test/ssm.html--%>
<button id="btn1">发送数据</button>
<button id="btn2">点我弹窗</button>

</body>
</html>
