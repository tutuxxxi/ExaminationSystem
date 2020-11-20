package com.xxx.controller;


import com.xxx.pojo.Exam;
import com.xxx.pojo.Question;
import com.xxx.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/question")
public class QuestionController {

    QuestionService questionService;
    @Autowired
    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }



    @PostMapping("/addQuestion")
    public String addQuestion(Question question, HttpServletRequest request){
        HttpSession session = request.getSession();

        Exam exam = (Exam) session.getAttribute("examInfo");

        if(exam == null){
            session.setAttribute("errorInfo", "丢失试卷信息");
        }else{
            //为每一道题编号
            int questionNum = (Integer)session.getAttribute("questionNum");
            question.setQuestion(++questionNum + "、" + question.getQuestion());
            session.setAttribute("questionNum", questionNum);

            if(!questionService.addQuestion(question, exam.getId())){
                session.setAttribute("errorInfo", "添加失败");
            }else{
                session.setAttribute("errorInfo", "添加成功");
            }
        }
        return "/Teacher/addExam";
    }

}
