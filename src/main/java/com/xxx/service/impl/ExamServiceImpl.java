package com.xxx.service.impl;

import com.xxx.dao.ExamDao;
import com.xxx.pojo.Exam;
import com.xxx.service.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExamServiceImpl implements ExamService {

    ExamDao examDao;
    @Autowired
    public ExamServiceImpl(ExamDao examDao) {
        this.examDao = examDao;
    }



    public boolean addExam(Exam exam) {
        if(exam != null){

            return examDao.addExam(exam) != 0;
        }

        return false;
    }

    public List<Exam> getAllExam() {
        List<Exam> allExam = examDao.getAllExam();

        for(Exam exam : allExam){
            exam.setSize(exam.getQuestions().size());
        }

        return allExam;
    }

    public Exam getExam(String id) {
        return examDao.getExam(id);
    }
}
