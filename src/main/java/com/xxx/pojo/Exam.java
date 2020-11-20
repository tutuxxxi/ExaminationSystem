package com.xxx.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import javafx.scene.chart.PieChart;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Exam {

    private String id;

    private String name;

    private Date startTime;

    private Date endTime;

    private int size;

    private List<Question> questions;

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy年MM月dd日 hh:mm:ss")
    public Date getStartTime() {
        return startTime;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy年MM月dd日 hh:mm:ss")
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy年MM月dd日 hh:mm:ss")
    public Date getEndTime() {
        return endTime;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy年MM月dd日 hh:mm:ss")
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Exam() {
    }

    public Exam(String name, Date startTime, Date endTime) {
        this.name = name;
        this.startTime = startTime;
        this.endTime = endTime;
    }
}
