package com.xxx.controller;

import com.xxx.dao.ExamDao;
import com.xxx.pojo.Exam;
import com.xxx.pojo.Score;
import com.xxx.pojo.User;
import com.xxx.service.ExamService;
import com.xxx.service.ScoreService;
import com.xxx.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/score")
public class ScoreController {

    private ScoreService scoreService;
    private ExamService examService;
    private UserService userService;

    @Autowired
    public ScoreController(ScoreService scoreService, ExamService examService, UserService userService) {
        this.scoreService = scoreService;
        this.examService = examService;
        this.userService = userService;
    }


    @GetMapping("/listScore")
    public String listScore(String id,  HttpServletRequest request) throws Exception {

        HttpSession session = request.getSession();

        User user = (User) session.getAttribute("userInfo");


        String pageNum = request.getParameter("pageNum");
        session.setAttribute("pageNum", pageNum == null ? 1 : Integer.parseInt(pageNum));


        if(user.getRole().equals("老师")){

            if(id == null){
                session.setAttribute("errorInfo", "参数不完整");
                return "/Teacher/listExam";
            }

            Exam exam = examService.getExam(id);
            List<Score> scoreByExamId = scoreService.getScoreByExamId(id);
            List<User> userList = new ArrayList<User>(scoreByExamId.size());

            for(Score score : scoreByExamId){
                userList.add(userService.getUser(score.getUserId(), null));
            }

            session.setAttribute("students", userList);
            session.setAttribute("scores", scoreByExamId);
            session.setAttribute("examInfo", exam);

            return "/Teacher/listScore";

        }else{
            //学生 直接查

            List<Score> scoreByUserId = scoreService.getScoreByUserId(user.getId());
            List<Exam> examList = new ArrayList<Exam>(scoreByUserId.size());

            //对每个成绩进行查询，并且存入试卷信息
            for(Score score : scoreByUserId){
                examList.add(examService.getExam(score.getExamId()));
            }


            session.setAttribute("scores", scoreByUserId);
            session.setAttribute("exams", examList);
            return "/Student/listScore";
        }

    }

}
