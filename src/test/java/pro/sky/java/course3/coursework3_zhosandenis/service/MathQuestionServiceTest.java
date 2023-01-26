package pro.sky.java.course3.coursework3_zhosandenis.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import org.springframework.web.server.ResponseStatusException;
import pro.sky.java.course3.coursework3_zhosandenis.model.Question;

import static org.junit.jupiter.api.Assertions.assertThrows;

class MathQuestionServiceTest {
    private final QuestionService out = new MathQuestionService();

    private final Question question1 = new Question("Каким словом обозначался миллион в Древней Руси?", "Тьма");

    @Test
    void add() {
        assertThrows(ResponseStatusException.class,() -> out.add(question1.getQuestion(), question1.getAnswer()));
    }

    @Test
    void addQuest() {
        assertThrows(ResponseStatusException.class,() -> out.add(question1));
    }

    @Test
    void remove() {
        assertThrows(ResponseStatusException.class,() -> out.remove(question1));
    }

    @Test
    void getAll() {
        assertThrows(ResponseStatusException.class,() -> out.getAll());
    }

    @Test
    void getRandomQuestionIsNotNull() {
        Question randomQuestion = out.getRandomQuestion();

        Assertions.assertNotNull(randomQuestion);
    }
}