<%@ page import="com.xxx.util.DateUtil" %>
<%@ page import="java.util.List" %>
<%@ page import="com.xxx.pojo.Exam" %>
<%@ page import="com.xxx.pojo.Score" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="jstl"%>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>考试系统 - 查看成绩</title>
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
                    <a href="${url}/exam/listExam">查看我的试卷</a>
                </li>
                <li class="layui-nav-item layui-nav-itemed">
                    <a href="${url}/score/listScore">查看我的成绩</a>
                </li>
            </ul>
        </div>
    </div>

    <div class="layui-body">
        <div style="width: 60%; margin: 100px auto;">
            <table class="layui-table" >
                <colgroup>
                    <col width="">
                    <col width="">
                </colgroup>
                <thead>
                <tr>
                    <th>试卷名称</th>
                    <th>试题得分</th>
                </tr>
                </thead>
                <tbody>
                <%
                    List<Score> scores = (List<Score>) session.getAttribute("scores");
                    List<Exam> exams = (List<Exam>) session.getAttribute("exams");
                    int mark = -1;
                    int pageNum = (Integer) session.getAttribute("pageNum");

                    int start = (pageNum - 1) * 5;
                    int end = (pageNum * 5) > scores.size() ? scores.size() : (pageNum * 5);

                    for(; start < end; start++){
                        Score score = scores.get(start);
                        Exam exam = exams.get(start);

                        out.println("<tr>");
                        out.println("    <td>" + exam.getName() + "</td>");
                        out.println("    <td>" + score.getScore() + "</td>");
                        out.println("<tr>");
                    }
                %>
                </tbody>

            </table>
            <div id="pageSelector" style="text-align: center;"></div>
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

        laypage.render({
            elem: 'pageSelector'
            ,count: <%=exams.size()%>//数据总数
            ,limit: 5
            ,curr: ${pageNum}//起始页面
            ,groups:3
            ,prev: "<i class='layui-icon layui-icon-prev'></i>"
            ,next: "<i class='layui-icon layui-icon-next'></i>"
            ,jump: function(obj, first){
                if(!first){
                    window.location.href='${url}/exam/listExam?pageNum=' + obj.curr;
                }
            }
        });


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
