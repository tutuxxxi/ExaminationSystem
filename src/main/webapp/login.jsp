<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="jstl"%>
<html>
<head>
    <title>登陆</title>
    <link rel="stylesheet" type="text/css" href="layui/css/layui.css">
</head>
<body>

<div style="width: 100%; margin-top: 50px; text-align:center">
    <h2>简化版在线答题系统 - 登陆界面</h2>
    <div class="layui-input-block" style="border: #2D93CA 2px solid; width: 60%; margin: 50px auto; padding: 80px 30px;">
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
                </div>
                <div class="layui-input-block" style="margin-left: 0px">
                    <button class="layui-btn" lay-submit lay-filter="formDemo">登陆</button>
                    <button type="reset" class="layui-btn layui-btn-primary">重置</button>
                </div>
                <div class="layui-input-block" style="margin-top: 20px; font-size: 14px; margin-left: 0px">
                    还没有账号？
                    <a href="register.jsp">点击注册</a>
                </div>
            </div>
        </form>
    </div>

</div>

<script src="layui/layui.js"></script>
<script>
    layui.use(['layer', 'form'], function(){
        var layer = layui.layer
            ,form = layui.form;


        layer.open({
            title: 'tips'
            ,content: '供测试使用：<br>账号：teacher1<br>账号：student1<br>账号：student2<br>账号：student3<br>账号：student4<br><br>密码均为：123456'
        });
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
