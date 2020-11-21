package com.xxx.controller;


import com.alibaba.druid.util.StringUtils;
import com.github.pagehelper.util.StringUtil;
import com.xxx.pojo.Exam;
import com.xxx.pojo.Question;
import com.xxx.pojo.Score;
import com.xxx.pojo.User;
import com.xxx.service.ExamService;
import com.xxx.service.ScoreService;
import com.xxx.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.rmi.StubNotFoundException;
import java.util.*;

@Controller
@RequestMapping("/exam")
public class ExamController {

    ExamService examService;
    ScoreService scoreService;
    @Autowired
    public ExamController(ExamService examService, ScoreService scoreService) {
        this.examService = examService;
        this.scoreService = scoreService;
    }

    @PostMapping("/addExam")
    public String addExam(String name, String startTime, String endTime, HttpServletRequest request){

        HttpSession session = request.getSession();

        if(StringUtils.isEmpty(name) || StringUtil.isEmpty(startTime) || StringUtils.isEmpty(endTime)){
            session.setAttribute("errorInfo", "表单不完整");
            return "teacherPage";
        }
        else{
            Exam exam = new Exam(name, DateUtil.stringToDate(startTime), DateUtil.stringToDate(endTime));


            if(exam.getStartTime().after(exam.getEndTime()) || exam.getEndTime().before(new Date())){
                session.setAttribute("errorInfo", "填写的时间有误");
                return "teacherPage";
            }


            //添加成功 暂存试卷id，方便之后的试题添加
            if(examService.addExam(exam)){
                session.setAttribute("examInfo", exam);
                session.setAttribute("questionNum", 0);
                //跳转到添加试卷
                return "Teacher/addExam";
            }
            else{
                session.setAttribute("errorInfo", "试卷添加失败");
                return "teacherPage";
            }
        }
    }

    /**
     * 处理视图映射请求
     * @param request
     * @return
     */
    @GetMapping("/addExam")
    public String addExamPage(HttpServletRequest request){
        HttpSession session = request.getSession();

        Object examInfo = session.getAttribute("examInfo");

        if(examInfo != null){
            return "/Teacher/addExam";
        }else{
            return "teacherPage";
        }
    }



    @GetMapping("/finishAdd")
    public String finishAdd(HttpServletRequest request){
        //清除试卷信息
        HttpSession session = request.getSession();
        session.setAttribute("examInfo", null);

        return "teacherPage";
    }


    @GetMapping("/listExam")
    public String listExam(HttpServletRequest request){
        List<Exam> allExam = examService.getAllExam();

        HttpSession session = request.getSession();

        session.setAttribute("exams", allExam);
        String pageNum = request.getParameter("pageNum");
        session.setAttribute("pageNum", pageNum == null ? 1 : Integer.parseInt(pageNum));

        User user = (User) session.getAttribute("userInfo");
        if(user.getRole().equals("老师"))
            return "/Teacher/listExam";
        else{
            //如果是学生，查询它的所有成绩信息
            List<Score> scores = scoreService.getScoreByUserId(user.getId());
            session.setAttribute("scores", scores);
            return "studentPage";
        }
    }



    @GetMapping("/startExam")
    public String startExam(String id, HttpServletRequest request){

        //返回当前考试信息
        HttpSession session = request.getSession();
        Exam exam = examService.getExam(id);

        Date now = new Date();
        if(exam.getStartTime().after(now)){
            session.setAttribute("errorInfo", "考试还没开始");
            return "teacherPage";
        }

        if(exam.getEndTime().before(now)){
            session.setAttribute("errorInfo", "考试已经结束啦！");
            return "teacherPage";
        }


        Collections.sort(exam.getQuestions(), new Comparator<Question>() {

            public int compare(Question o1, Question o2) {
                return o1.getQuestion().charAt(0) - o2.getQuestion().charAt(0);
            }

        });


        session.setAttribute("exam", exam);
        return "/Student/exam";
    }



    @PostMapping("/endExam")
    public String endExam(HttpServletRequest request){

        HttpSession session = request.getSession();

        User user = (User) session.getAttribute("userInfo");
        Exam exam = (Exam) session.getAttribute("exam");


        int size = exam.getQuestions().size();

        //获取学生的答案数组
        char[] studentAnswer = new char[size];
        //获取答案数组
        char[] answer = new char[size];

        int count = 0;

        for(int i = 0; i<size; i++){
            Question question = exam.getQuestions().get(i);

            String parameter = request.getParameter(question.getId());

            studentAnswer[i] = parameter == null ? '-' : parameter.charAt(0);
            answer[i] = question.getAnswer().charAt(0);

            if(studentAnswer[i] == answer[i])
                count++;
        }

        Score score = new Score(user.getId(), exam.getId(), Math.round(((float) count / size) * 100));

        scoreService.addScore(score);

        session.setAttribute("exam", null);
        session.setAttribute("studentAnswer", Arrays.toString(studentAnswer));
        session.setAttribute("answer", Arrays.toString(answer));
        session.setAttribute("score", score.getScore());

        return "Student/exam";

    }

}
