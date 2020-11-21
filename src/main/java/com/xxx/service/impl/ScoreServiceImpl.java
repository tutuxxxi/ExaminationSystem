package com.xxx.service.impl;

import com.xxx.dao.ScoreDao;
import com.xxx.pojo.Score;
import com.xxx.service.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ScoreServiceImpl implements ScoreService {

    ScoreDao scoreDao;
    @Autowired
    public ScoreServiceImpl(ScoreDao scoreDao) {
        this.scoreDao = scoreDao;
    }



    public boolean addScore(Score score) {
        return scoreDao.addScore(score) != 0;
    }

    public List<Score> getScoreByUserId(String id) {
        return scoreDao.getScoreByUserId(id);
    }

    public List<Score> getScoreByExamId(String id) {
        return scoreDao.getScoreByExamId(id);
    }
}
