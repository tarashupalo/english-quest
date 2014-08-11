package com.github.way.english.console;

import com.github.way.english.model.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by way on 08.08.2014.
 */
public class TxtParser {

    public static void main(String[] args) throws IOException {
        List<Lesson> lessons = new TxtParser().fetchLessons("txt/3.txt");

//        parser.printLessons(lessons);
        new XmlWriter().writeLessons(lessons, "xml/3.xml");
    }

    public List<Lesson> fetchLessons(String path) {
        try {
            return doFetchLessons(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private List<Lesson> doFetchLessons(String path) throws IOException {
        List<Lesson> lessons = new ArrayList<Lesson>();

        BufferedReader reader = new BufferedReader(new FileReader(path));
        String line;
        while ((line = reader.readLine()) != null) {
//            System.out.println(line);
            if (line.startsWith("Lesson")) {
                Lesson lesson = new Lesson();
                lesson.setNumber(Integer.parseInt(line.substring(7)));
                lessons.add(lesson);
            } else if (line.startsWith("WORDS: ")) {
                Lesson lesson = lessons.get(lessons.size() - 1);

                String substring = line.substring(7);
                for (String string : substring.split(", ")) {
                    Word word = new Word();
                    word.setText(string);
                    lesson.addItem(word);
                }
            } else if (line.startsWith("GRAMMAR: ")) {
                Lesson lesson = lessons.get(lessons.size() - 1);

                String substring = line.substring(9);
                Grammar grammar = new Grammar();
                grammar.setText(substring);
                lesson.addItem(grammar);
            } else if (line.startsWith("QUESTION: ")) {
                Lesson lesson = lessons.get(lessons.size() - 1);

                String substring = line.substring(10);

                String[] strings = substring.split("[?.]{1}\\s");

                Question question = new Question();
                question.setText(strings[0] + "?");

                if (strings.length > 1) {
                    for (int i = 1; i < strings.length; i++) {
//                        if (Character.isUpperCase(strings[i].charAt(0))) {
                        Answer answer = new Answer();
                        answer.setText(strings[i]);
                        question.addAnswer(answer);
//                        } else {
//                            if (i == 1) {
//                                question.setText(question.getText() + ", " + strings[i]);
//                            } else {
//                                Answer lastAnswer = question.getAnswers().get(question.getAnswers().size() - 1);
//                                lastAnswer.setText(lastAnswer.getText() + ", " + strings[i]);
//                            }
//                        }
                    }
                }

                lesson.addItem(question);
            } else if (line.startsWith("DICTATION: ")) {
                Lesson lesson = lessons.get(lessons.size() - 1);

                String substring = line.substring(11);
                Dictation dictation = new Dictation();
                dictation.setText(substring);
                lesson.addItem(dictation);
            } else {
                List<LessonItem> items = lessons.get(lessons.size() - 1).getItems();
                LessonItem lastLessonItem = items.get(items.size() - 1);
                if (line.trim().isEmpty()) {
                    // just skip empty line
                } else if (lastLessonItem instanceof Grammar) {
                    Grammar lastRule = (Grammar) lastLessonItem;
                    lastRule.setText(lastRule.getText() + line);
                } else {
                    throw new RuntimeException("Cannot parse line \"" + line + "\"");
                }
            }
        }
        return lessons;
    }

    private void printLessons(List<Lesson> lessons) {
        for (Lesson lesson : lessons) {
            System.out.println(lesson);
        }
    }
}
