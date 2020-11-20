package com.xxx.dao;

import com.xxx.pojo.Exam;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ExamDao {

    int addExam(Exam exam);

    List<Exam> getAllExam();

    Exam getExam(@Param("id") String id);

}
