package com.github.way.english.console;

import com.github.way.english.model.Answer;
import com.github.way.english.model.Lesson;
import com.github.way.english.model.LessonItem;
import com.github.way.english.model.Question;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

/**
 * Created by way on 08.08.2014.
 */
public class Main {

    private static LessonsHolder holder;

    public static void main(String[] args) throws IOException {
        TxtParser parser = new TxtParser();
        List<Lesson> lessons = parser.fetchLessons("c:/sandbox/english-quest/txt/4.txt");

        holder = new LessonsHolder(lessons);

        for (Lesson lesson : lessons) {
            show(lesson);
        }
    }

    private static void show(Lesson lesson) throws IOException {
        for (LessonItem item : lesson.getItems()) {
            if (item instanceof Question) {
                Question question = (Question) item;
                System.out.println(question.getText());

                while (true) {
                    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                    String userInput = br.readLine();

                    if ("q".equals(userInput)) {
                        System.exit(0);
                    } if ("hint".equals(userInput)) {
                        System.out.println("See the following variants are acceptable. We are going to add possibility for adding you awn answer variants");
                        for (Answer answer : question.getAnswers()) {
                            System.out.println(answer.getText());
                        }
                    } else if (userInput.startsWith("open lesson ")) {
                        int lessonNumber = Integer.parseInt(userInput.substring("open lesson ".length()));
                        show(holder.getLesson(lessonNumber));
                    } else if (isCorrectAnswer(question, userInput)) {
                        break;
                    } else {
                        System.out.println("Answer in not correct. Please try again");
                    }
                }
            }
        }
    }

    private static boolean isCorrectAnswer(Question question, String userInput) {
        String userAnswer = prepareAnswer(userInput);

        for (Answer answer : question.getAnswers()) {
            String preparedAnswer = prepareAnswer(answer.getText());
            if (preparedAnswer.equals(userAnswer)) {
                return true;
            }
        }

        return false;
    }

    private static String prepareAnswer(String userInput) {
        return userInput.toLowerCase().replaceAll("[\".]", "").replaceAll("[\\s]", " ");
    }
}
