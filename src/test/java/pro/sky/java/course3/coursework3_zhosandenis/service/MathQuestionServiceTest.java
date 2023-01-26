package pro.sky.java.course3.coursework3_zhosandenis.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.sky.java.course3.coursework3_zhosandenis.model.Question;
import pro.sky.java.course3.coursework3_zhosandenis.repository.QuestionRepository;

import java.util.HashSet;
import java.util.Set;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyString;

@ExtendWith(MockitoExtension.class)
class MathQuestionServiceTest {
    @Mock
    private QuestionRepository mathQuestionRepositoryMock;
    private QuestionService out;

    private final Question question1 = new Question("Каким словом обозначался миллион в Древней Руси?", "Тьма");
    private final Question question2 = new Question("Как называется сотая часть числа?", "Процент");
    private final Question question3 = new Question("Назовите единицу массы драгоценных камней", "Карат");
    private final Question question4 = new Question("Другое название независимой переменной", "Аргумент");
    private final Question question5 = new Question("Как называется функция, графиком которой является парабола?", "Квадратичная");

    @BeforeEach
    public void initOut() {
        out = new MathQuestionService(mathQuestionRepositoryMock);
    }

    @Test
    void add() {
        when(mathQuestionRepositoryMock.add(question1.getQuestion(), question1.getAnswer())).thenReturn(question1);

        Assertions.assertEquals(question1, out.add(question1.getQuestion(), question1.getAnswer()));

        verify(mathQuestionRepositoryMock, times(1)).add(question1.getQuestion(), question1.getAnswer());
    }

    @Test
    void addQuest() {
        when(mathQuestionRepositoryMock.add(eq(question1))).thenReturn(question1);

        Assertions.assertEquals(question1, out.add(question1));

        verify(mathQuestionRepositoryMock, times(1)).add(question1);
    }

    @Test
    void shouldThrowExceptionWhenRepositoryThrowsExceptionsAddingIncorrectInput() {
        when(mathQuestionRepositoryMock.add(any())).thenThrow(IllegalArgumentException.class);
        when(mathQuestionRepositoryMock.add(null, null)).thenThrow(IllegalArgumentException.class);
        when(mathQuestionRepositoryMock.add(anyString(), anyString())).thenThrow(IllegalArgumentException.class);

        Assertions.assertThrows(IllegalArgumentException.class, () -> out.add(null));
        Assertions.assertThrows(IllegalArgumentException.class, () -> out.add(null, null));
        Assertions.assertThrows(IllegalArgumentException.class, () -> out.add("", " "));
        Assertions.assertThrows(IllegalArgumentException.class, () -> out.add(" ", ""));
    }

    @Test
    void remove() {
        when(mathQuestionRepositoryMock.remove(eq(question1))).thenReturn(question1);

        Assertions.assertEquals(question1, out.remove(question1));

        verify(mathQuestionRepositoryMock, times(1)).remove(question1);
    }

    @Test
    void shouldThrowExceptionWhenRepositoryThrowsExceptionsRemovingIncorrectInput() {
        when(mathQuestionRepositoryMock.remove(any())).thenThrow(IllegalArgumentException.class);

        Assertions.assertThrows(IllegalArgumentException.class, () -> out.remove(null));
    }

    @Test
    void getAll() {
        Set<Question> questionSet = new HashSet<>();
        questionSet.add(question1);
        questionSet.add(question2);
        questionSet.add(question3);
        questionSet.add(question4);
        questionSet.add(question5);

        when(mathQuestionRepositoryMock.getAll()).thenReturn(questionSet);

        Assertions.assertEquals(questionSet, out.getAll());
    }

    @Test
    void getRandomQuestionIsNotNull() {
        Set<Question> questionSet = new HashSet<>(Set.of(question1));

        when(mathQuestionRepositoryMock.getAll()).thenReturn(questionSet);

        Question randomQuestion = out.getRandomQuestion();

        Assertions.assertNotNull(randomQuestion);
    }
}