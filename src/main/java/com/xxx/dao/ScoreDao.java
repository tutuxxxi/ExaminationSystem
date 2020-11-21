package com.xxx.dao;

import com.xxx.pojo.Score;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScoreDao {

    int addScore(Score score);

    List<Score> getScoreByUserId(@Param("id") String id);

    List<Score> getScoreByExamId(@Param("id") String id);

}
