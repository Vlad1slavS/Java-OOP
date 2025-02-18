package com.example.first.Models;

import com.google.gson.annotations.Expose;

public class Question {
    @Expose
    private String topic;
    private String questionText;
    private String language;
    private String[] options;
    private String correctAnswer;

    public Question(String topic, String questionText, String language, String[] options, String correctAnswer) {
        this.topic = topic;
        this.questionText = questionText;
        this.language = language;
        this.options = options;
        this.correctAnswer = correctAnswer;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public String[] getOptions() {
        return options;
    }

    public String getLanguage() {
        return language;
    }

    public String getQuestion() {
        return questionText;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public void setOptions(String[] options) {
        this.options = options;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }
}
