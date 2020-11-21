package com.xxx.service;

import com.xxx.pojo.Score;

import java.util.List;

public interface ScoreService {

    boolean addScore(Score score);

    List<Score> getScoreByUserId(String id);


    List<Score> getScoreByExamId(String id);



}
