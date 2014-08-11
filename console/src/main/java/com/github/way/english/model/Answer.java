package com.github.way.english.model;

/**
 * Created by way on 08.08.2014.
 */
public class Answer implements HasText {

    private String text;

    public Answer() {
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    @Override
    public String toString() {
        return "Answer{" +
                "text='" + text + '\'' +
                '}';
    }
}
