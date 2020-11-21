package com.xxx.pojo;

public class Score {

    private String userId;

    private String examId;

    private int score;

    public Score() {
    }

    public Score(String userId, String examId, int score) {
        this.userId = userId;
        this.examId = examId;
        this.score = score;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getExamId() {
        return examId;
    }

    public void setExamId(String examId) {
        this.examId = examId;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
