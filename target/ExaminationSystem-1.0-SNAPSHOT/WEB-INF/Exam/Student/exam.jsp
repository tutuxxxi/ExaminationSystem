<%@ page import="com.xxx.util.DateUtil" %>
<%@ page import="java.util.List" %>
<%@ page import="com.xxx.pojo.Exam" %>
<%@ page import="com.xxx.pojo.User" %>
<%@ page import="com.xxx.pojo.Question" %>
<%@ page import="java.util.Collections" %>
<%@ page import="java.util.Comparator" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="jstl"%>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>考试系统 - 查看试卷</title>
    <link rel="stylesheet" href="${url}/layui/css/layui.css">
</head>
<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">
    <div class="layui-header">
        <div class="layui-logo">考试系统 - 学生端</div>
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
                <li class="layui-nav-item">
                    <a href="${url}/exam/listExam" disabled>查看我的试卷</a>
                </li>
                <li class="layui-nav-item">
                    <a href="javascript:;" disabled>查看我的成绩</a>
                </li>
            </ul>
        </div>
    </div>

    <div class="layui-body">
        <div style="width: 60%; margin: 100px auto; ">
            <form class="layui-form" action="${url}/exam/endExam" method="post">
                <%
                    Exam exam = (Exam) session.getAttribute("exam");

                    User user = (User) session.getAttribute("userInfo");

                    out.println("<p style=\"text-align: center\">你好: " + user.getName() + " -------- 您正在做: " + exam.getName() + "</p>");
                    out.println("<hr class=\"layui-bg-cyan\">");


                    List<Question> questions = exam.getQuestions();

                    for(Question question : questions){

                        out.println("<br><br>");
                        out.println("<p style=\"font-size; 30px;\">" + question.getQuestion() + "</p>");
                        out.println("<hr>");
                        out.println("<br>");
                        out.println("<div class=\"layui-form-item\">\n" +
                                "                    <div class=\"layui-input-inline\">");

                        char[] chars = {'A', 'B', 'C', 'D'};
                        String[] strs = question.getChoice().split("#;");
                        for(int i = 0; i < 4 ;i++){
                            out.println("<input type=\"radio\" name=\""+ question.getId() +"\" value=\"" + chars[i]  + "\" title=\"" + chars[i] + "、" + strs[i] + "\">");
                        }


                        out.println("</div>\n" +
                            "     </div>" +
                         "<br><br>");
                    }

                %>

<%--                <div class="layui-form-item">--%>
<%--                    <div class="layui-input-inline">--%>
<%--                        <input type="radio" name="sex" value="男" title="男">--%>
<%--                        <input type="radio" name="sex" value="女" title="女">--%>
<%--                    </div>--%>
<%--                </div>--%>


                <div class="layui-form-item">
                    <div class="layui-input-inline">
                        <button class="layui-btn" lay-submit lay-filter="formDemo" style="width: 200px;">交卷</button>
                    </div>
                </div>
            </form>
        </div>
    </div>

</div>
<script src="${url}/layui/layui.js"></script>
<script>
    //JavaScript代码区域
    layui.use(['element', 'table', 'laypage'], function(){
        var element = layui.element;
        var table = layui.table;
        var laypage = layui.laypage;

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







