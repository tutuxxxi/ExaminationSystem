package com.xxx.controller;


import com.alibaba.druid.util.StringUtils;
import com.github.pagehelper.util.StringUtil;
import com.xxx.pojo.Exam;
import com.xxx.pojo.Question;
import com.xxx.pojo.User;
import com.xxx.service.ExamService;
import com.xxx.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.rmi.StubNotFoundException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Controller
@RequestMapping("/exam")
public class ExamController {

    ExamService examService;
    @Autowired
    public ExamController(ExamService examService) {
        this.examService = examService;
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

        for(Exam exam : allExam){
            exam.setSize(exam.getQuestions().size());
        }

        HttpSession session = request.getSession();

        session.setAttribute("exams", allExam);
        String pageNum = request.getParameter("pageNum");
        session.setAttribute("pageNum", pageNum == null ? 1 : Integer.parseInt(pageNum));

        User user = (User) session.getAttribute("userInfo");
        if(user.getRole().equals("老师"))
            return "/Teacher/listExam";
        else
            return "studentPage";
    }



    @GetMapping("/startExam")
    public String startExam(String id, HttpServletRequest request){

        //返回当前考试信息
        Exam exam = examService.getExam(id);

        Collections.sort(exam.getQuestions(), new Comparator<Question>() {

            public int compare(Question o1, Question o2) {
                return o1.getQuestion().charAt(0) - o2.getQuestion().charAt(0);
            }

        });


        request.getSession().setAttribute("exam", exam);
        return "/Student/exam";
    }



    @PostMapping("/endExam")
    public void endExam(HttpServletRequest request){

        HttpSession session = request.getSession();

        Exam exam = (Exam) session.getAttribute("exam");

        //获取学生的答案数组
        char[] studentAnswer = new char[exam.getQuestions().size()];
        //获取答案数组
        char[] answer = new char[exam.getQuestions().size()];

        int count = 0;

        for(int i = 0; i<exam.getQuestions().size(); i++){
            Question question = exam.getQuestions().get(i);

            studentAnswer[i] = request.getParameter(question.getId()).charAt(0);
            answer[i] = question.getAnswer().charAt(0);

            if(studentAnswer[i] == answer[i])
                count++;
        }

        System.out.println(Arrays.toString(studentAnswer));
        System.out.println(Arrays.toString(answer));
        System.out.println(count);

    }

}
