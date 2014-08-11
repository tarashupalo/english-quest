package com.github.way.english.model;

/**
 * Created by way on 08.08.2014.
 */
public abstract class LessonItem implements HasText {
    private static enum Type {
        WORD, GRAMMAR, QUESTION, DICTATION
    }
}
