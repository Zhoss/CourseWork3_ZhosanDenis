package pro.sky.java.course3.coursework3_zhosandenis.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pro.sky.java.course3.coursework3_zhosandenis.model.Question;

import java.util.HashSet;
import java.util.Set;

class JavaQuestionRepositoryTest {
    private final QuestionRepository out = new JavaQuestionRepository();
    private final Question question1 = new Question("Что означает ООП?", "Объектно-ориентированное программирование");
    private final Question question2 = new Question("Основные концепции в Java?", "Инкапсуляция, наследование, полиморфизм");
    private final Question question3 = new Question("Какие два класса не наследуются от Object?", "Все классы в Java наследуются от Object");
    private final Question question4 = new Question("С каким модификатором доступа данные видны только внутри класса, где они объявлены?", "Private");
    private final Question question5 = new Question("За что отвечают типы данных в левой и правой части при создании объекта?", "Тип данных в левой части отвечает за методы, которые будут у объекта, а тип данных в правой части за реализацию этих методов");

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