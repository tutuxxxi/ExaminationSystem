<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>考试系统 - 考试中</title>
    <link rel="stylesheet" href="${url}/layui/css/layui.css">
</head>
<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">
    <div class="layui-header">
        <div class="layui-logo">考试系统 - 老师端</div>
        <ul class="layui-nav layui-layout-right">
            <li class="layui-nav-item">
                ${userInfo.name}
            </li>
            <li class="layui-nav-item"><a href="${url}/user/logout">退出登陆</a></li>
        </ul>
    </div>

    <div class="layui-side layui-bg-black">
        <div class="layui-side-scroll">
            <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
            <ul class="layui-nav layui-nav-tree"  lay-filter="test">
                <li class="layui-nav-item layui-nav-itemed">
                    <a href="${url}/exam/addExam">添加试卷</a>
                </li>
                <li class="layui-nav-item">
                    <a href="${url}/exam/listExam">查看已有试卷</a>
                </li>
                <li class="layui-nav-item">
                    <a href="javascript:;">查看成绩</a>
                </li>
            </ul>
        </div>
    </div>

    <div class="layui-body" style="text-align: center;">
        <div style="width: 50%; margin: 100px auto" >
            <form class="layui-form" action="${url}/exam/addExam" method="post">
                <div class="layui-form-item">
                    <label class="layui-form-label">试卷名称</label>
                    <div class="layui-input-block">
                        <input type="text" name="name" required  lay-verify="required" placeholder="请输入试卷名称" autocomplete="off" class="layui-input">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">开始时间</label>
                    <div class="layui-input-block">
                        <input type="text" class="layui-input" id="test1" name="startTime">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">结束时间</label>
                    <div class="layui-input-block">
                        <input type="text" class="layui-input" id="test2" name="endTime">
                    </div>
                </div>
                <div class="layui-form-item">
                    <div class="layui-input-inline">
                        <button class="layui-btn" lay-submit lay-filter="formDemo">开始录入</button>
                    </div>
                </div>
            </form>
        </div>
    </div>

</div>
<script src="${url}/layui/layui.js"></script>
<script>
    //JavaScript代码区域
    layui.use(['element', 'laydate'], function(){
        var element = layui.element;
        var laydate = layui.laydate;

        //执行一个laydate实例
        laydate.render({
            elem: '#test1', //指定元素
            type: 'datetime'
        });

        //执行一个laydate实例
        laydate.render({
            elem: '#test2', //指定元素
            type: 'datetime'
        });
    });

<%
    String errorInfo = (String) session.getAttribute("errorInfo");

    if(errorInfo != null){
        session.setAttribute("errorInfo", null);
        out.println(
                "<script>\n" +
                "      alert('" + errorInfo + "');\n" +
                "</script>");
    }
%>

</script>
</body>
</html>
