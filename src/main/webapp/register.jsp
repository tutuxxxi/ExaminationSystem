<%@ page import="com.github.pagehelper.util.StringUtil" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="jstl"%>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="layui/css/layui.css">
</head>
<body>

    <div style="width: 100%; margin-top: 150px; text-align:center">
        <div style="margin: 0 auto; width: 40%" >
            <form class="layui-form" action="user/register" method="post" >
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
                <div class="layui-form-item">
                    <label class="layui-form-label">姓名</label>
                    <div class="layui-input-block">
                        <input type="text" name="name" required lay-verify="required" placeholder="请输入姓名" autocomplete="off" class="layui-input">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">电话</label>
                    <div class="layui-input-block">
                        <input type="text" name="telephone" required lay-verify="required" placeholder="请输入电话号码" autocomplete="off" class="layui-input">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">年龄</label>
                    <div class="layui-input-block">
                        <select name="age" lay-verify="required">
                            <jstl:forEach begin="1" end="120" step="1" var="age">
                                <option value="${age}">${age}岁</option>
                            </jstl:forEach>
                        </select>
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">性别</label>
                    <div class="layui-input-inline">
                        <input type="radio" name="sex" value="male" title="男" checked>
                        <input type="radio" name="sex" value="female" title="女">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">注册为老师?</label>
                    <div class="layui-input-inline">
                        <input type="checkbox" name="role" lay-skin="switch">
                    </div>
                </div>
                <div class="layui-form-item">
                    <div class="layui-input-block">
                        <button class="layui-btn" lay-submit lay-filter="formDemo">注册</button>
                        <button type="reset" class="layui-btn layui-btn-primary">重置</button>
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
        });
    </script>
<%
    String errorInfo = (String) session.getAttribute("errorInfo");

    if(errorInfo != null){

        session.setAttribute("errorInfo", null);
        out.println("<script>\n" +
                "            alert('" + errorInfo + "');\n" +
                "            window.location.href='register.jsp';\n" +
                "        </script>");
    }
%>


</body>
</html>
