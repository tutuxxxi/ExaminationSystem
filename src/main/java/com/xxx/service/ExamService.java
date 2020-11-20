package com.xxx.service;

import com.xxx.pojo.Exam;

import java.util.List;

public interface ExamService {

    boolean addExam(Exam exam);

    List<Exam> getAllExam();

    Exam getExam(String id);
}
