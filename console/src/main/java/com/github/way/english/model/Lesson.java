package com.github.way.english.model;

import java.awt.image.renderable.ParameterBlock;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by way on 08.08.2014.
 */
public class Lesson {

    private int number;
    private List<LessonItem> items = new ArrayList<LessonItem>();

    public Lesson() {
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    public void addItem(LessonItem item) {
        items.add(item);
    }

    public List<LessonItem> getItems() {
        return items;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Lesson " + number + "\n");

        for (LessonItem item : items) {
            sb.append("\t").append(item).append("\n");
        }

        return sb.toString();
    }
}
