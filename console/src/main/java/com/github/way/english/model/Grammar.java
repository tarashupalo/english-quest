package com.github.way.english.model;

/**
 * Created by way on 08.08.2014.
 */
public class Grammar extends LessonItem {

    private String text;

    public Grammar() {
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    @Override
    public String toString() {
        return "Grammar{" +
                "text='" + text + '\'' +
                '}';
    }
}
