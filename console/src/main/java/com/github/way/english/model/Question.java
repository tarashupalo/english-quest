package com.github.way.english.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by way on 08.08.2014.
 */
public class Question extends LessonItem {

    private String text;
    private List<Answer> answers = new ArrayList<Answer>();

    public Question() {
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void addAnswer(Answer answer) {
        answers.add(answer);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Question{text='" + text + "'}");

        for (Answer item : answers) {
            sb.append("\n").append("\t\t").append(item);
        }

        return sb.toString();
    }

    public List<Answer> getAnswers() {
        return answers;
    }
}
