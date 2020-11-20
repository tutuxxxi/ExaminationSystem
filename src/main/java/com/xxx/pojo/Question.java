package com.xxx.pojo;

public class Question {

    private String id;

    /**
     * 题目
     */
    private String question;

    /**
     * 选项 使用 #&; 分割选项
     */
    private String choice;

    /**
     * 答案
     */
    private String answer;

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getChoice() {
        return choice;
    }

    public void setChoice(String choice) {
        this.choice = choice;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
