package com.xxx.dao;

import com.xxx.pojo.Question;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionDao {

    int addQuestion(@Param("ques") Question question, @Param("examId") String examId);

}
