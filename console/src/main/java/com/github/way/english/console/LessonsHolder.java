package com.github.way.english.console;

import com.github.way.english.model.Lesson;

import java.util.List;

/**
 * Created by way on 08.08.2014.
 */
public class LessonsHolder {

    private List<Lesson> lessons;

    public LessonsHolder(List<Lesson> lessons) {
        this.lessons = lessons;
    }

    public Lesson getLesson(int lessonNumber) {
        for (Lesson lesson : lessons) {
            if (lesson.getNumber() == lessonNumber) {
                return lesson;
            }
        }

        return null;
    }
}
