<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="jstl"%>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="layui/css/layui.css">
</head>
<body>

<div style="width: 100%; margin-top: 150px; text-align:center">
    <form class="layui-form" action="user/login" method="post" >
        <div style="margin: 0 auto; width: 40%" >
            <div class="layui-form-item">
                <label class="layui-form-label">用户名</label>
                <div class="layui-input-block">
                    <input type="text" name="username" required  lay-verify="required" placeholder="请输入用户名" autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">密码</label>
                <div class="layui-input-block">
                    <input type="password" name="password" required lay-verify="required" placeholder="请输入密码" autocomplete="off" class="layui-input">
                </div>
            </div>
        </div>
        <div style="margin: 0 auto; width: 40%" >
            <div class="layui-form-item">
                <div class="layui-input-block">
                    <button class="layui-btn" lay-submit lay-filter="formDemo">登陆</button>
                    <button type="reset" class="layui-btn layui-btn-primary">重置</button>
                </div>
            </div>
        </div>
    </form>
</div>

<script src="layui/layui.js"></script>
<script>
    layui.use(['layer', 'form'], function(){
        var layer = layui.layer
            ,form = layui.form;
    });
</script>

<%
    String errorInfo = (String) session.getAttribute("errorInfo");

    if(errorInfo != null){
        session.setAttribute("errorInfo", null);
        out.println("<script>\n" +
                "            alert('" + errorInfo + "');\n" +
                "            window.location.href='login.jsp';\n" +
                "    </script>");
    }
%>


</body>
</html>
