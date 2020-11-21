<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>考试系统 - 添加试题</title>
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
            </ul>
        </div>
    </div>

    <div class="layui-body">
        <div style="padding: 15px; text-align: center">
            <a href="${url}/exam/finishAdd" class="layui-btn layui-btn-normal">结束提交</a>
            <div style="margin: 100px auto; width: 60%; ">
                <form class="layui-form" action="${url}/question/addQuestion" method="post" >
                    <div class="layui-form-item layui-form-text">
                        <label class="layui-form-label">问题描述</label>
                        <div class="layui-input-block">
                            <textarea name="question" placeholder="请输入问题描述" class="layui-textarea"></textarea>
                        </div>
                    </div>
                    <div class="layui-form-item layui-form-text">
                        <label class="layui-form-label">选项描述</label>
                        <div class="layui-input-block">
                            <textarea name="choice" placeholder="每个选项使用 #; 来分割, 示例: 10#;20#;30#;40 " class="layui-textarea"></textarea>
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label">答案</label>
                        <div class="layui-input-inline">
                            <input type="text" name="answer" required  lay-verify="required" placeholder="输入答案选项名" autocomplete="off" class="layui-input">
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <div class="layui-input-block">
                            <button class="layui-btn" lay-submit lay-filter="formDemo">添加问题</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>

</div>
<script src="${url}/layui/layui.js"></script>
<script>
    //JavaScript代码区域
    layui.use('element', function(){
        var element = layui.element;

    });
</script>

<%
    String errorInfo = (String) session.getAttribute("errorInfo");

    if(errorInfo != null){
        session.setAttribute("errorInfo", null);
        out.println("<script>\n" +
                "            alert('" + errorInfo + "');\n" +
                "    </script>");
    }
%>
</body>
</html>
