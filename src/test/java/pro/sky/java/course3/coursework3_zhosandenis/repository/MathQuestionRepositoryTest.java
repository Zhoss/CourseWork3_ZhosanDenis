package pro.sky.java.course3.coursework3_zhosandenis.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pro.sky.java.course3.coursework3_zhosandenis.model.Question;

import java.util.HashSet;
import java.util.Set;

class MathQuestionRepositoryTest {
    private final QuestionRepository out = new MathQuestionRepository();
    private final Question question1 = new Question("Каким словом обозначался миллион в Древней Руси?", "Тьма");
    private final Question question2 = new Question("Как называется сотая часть числа?", "Процент");
    private final Question question3 = new Question("Назовите единицу массы драгоценных камней", "Карат");
    private final Question question4 = new Question("Другое название независимой переменной", "Аргумент");
    private final Question question5 = new Question("Как называется функция, графиком которой является парабола?", "Квадратичная");

    @BeforeEach
    public void setUp() {
        out.add(question1);
        out.add(question2);
        out.add(question3);
        out.add(question4);
        out.add(question5);
    }

    @Test
    void add() {
        out.getAll().clear();
        out.add(question1.getQuestion(), question1.getAnswer());
        out.add(question2.getQuestion(), question2.getAnswer());
        out.add(question3.getQuestion(), question3.getAnswer());
        out.add(question4.getQuestion(), question4.getAnswer());
        out.add(question5.getQuestion(), question5.getAnswer());
        Set<Question> actual = new HashSet<>(out.getAll());

        Set<Question> expected = new HashSet<>();
        expected.add(question1);
        expected.add(question2);
        expected.add(question3);
        expected.add(question4);
        expected.add(question5);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void addingIncorrectInputThrowsIllegalArgumentException() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> out.add(null));
        Assertions.assertThrows(IllegalArgumentException.class, () -> out.add(null, null));
        Assertions.assertThrows(IllegalArgumentException.class, () -> out.add("", " "));
        Assertions.assertThrows(IllegalArgumentException.class, () -> out.add(" ", ""));
    }

    @Test
    void addQuest() {
        Set<Question> actual = new HashSet<>(out.getAll());

        Set<Question> expected = new HashSet<>();
        expected.add(question1);
        expected.add(question2);
        expected.add(question3);
        expected.add(question4);
        expected.add(question5);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void remove() {
        out.remove(question4);
        out.remove(question5);
        Set<Question> actual = new HashSet<>(out.getAll());

        Set<Question> expected = new HashSet<>();
        expected.add(question1);
        expected.add(question2);
        expected.add(question3);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void removingIncorrectInputThrowsIllegalArgumentException() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> out.remove(null));
        Assertions.assertThrows(IllegalArgumentException.class, () -> out.remove(new Question(null, null)));
        Assertions.assertThrows(IllegalArgumentException.class, () -> out.remove(new Question("", " ")));
        Assertions.assertThrows(IllegalArgumentException.class, () -> out.remove(new Question(" ", "")));
    }

    @Test
    void removingNotContainedQuestionReturnsNull() {
        Assertions.assertNull(out.remove(new Question("Главный вопрос жизни, вселенной и вообще", "42")));
    }
}