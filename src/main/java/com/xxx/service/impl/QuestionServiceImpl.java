package com.xxx.service.impl;

import com.xxx.dao.QuestionDao;
import com.xxx.pojo.Question;
import com.xxx.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuestionServiceImpl implements QuestionService {

    QuestionDao questionDao;
    @Autowired
    public QuestionServiceImpl(QuestionDao questionDao) {
        this.questionDao = questionDao;
    }

    public boolean addQuestion(Question question, String examId) {
        return questionDao.addQuestion(question, examId) != 0;
    }
}
