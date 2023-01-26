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
class JavaQuestionServiceTest {
    @Mock
    private QuestionRepository javaQuestionRepositoryMock;
    private QuestionService out;

    private final Question question1 = new Question("Что означает ООП?", "Объектно-ориентированное программирование");
    private final Question question2 = new Question("Основные концепции в Java?", "Инкапсуляция, наследование, полиморфизм");
    private final Question question3 = new Question("Какие два класса не наследуются от Object?", "Все классы в Java наследуются от Object");
    private final Question question4 = new Question("С каким модификатором доступа данные видны только внутри класса, где они объявлены?", "Private");
    private final Question question5 = new Question("За что отвечают типы данных в левой и правой части при создании объекта?", "Тип данных в левой части отвечает за методы, которые будут у объекта, а тип данных в правой части за реализацию этих методов");

    @BeforeEach
    public void initOut() {
        out = new JavaQuestionService(javaQuestionRepositoryMock);
    }

    @Test
    void add() {
        when(javaQuestionRepositoryMock.add(question1.getQuestion(), question1.getAnswer())).thenReturn(question1);

        Assertions.assertEquals(question1, out.add(question1.getQuestion(), question1.getAnswer()));

        verify(javaQuestionRepositoryMock, times(1)).add(question1.getQuestion(), question1.getAnswer());
    }

    @Test
    void addQuest() {
        when(javaQuestionRepositoryMock.add(eq(question1))).thenReturn(question1);

        Assertions.assertEquals(question1, out.add(question1));

        verify(javaQuestionRepositoryMock, times(1)).add(question1);
    }

    @Test
    void shouldThrowExceptionWhenRepositoryThrowsExceptionsAddingIncorrectInput() {
        when(javaQuestionRepositoryMock.add(any())).thenThrow(IllegalArgumentException.class);
        when(javaQuestionRepositoryMock.add(null, null)).thenThrow(IllegalArgumentException.class);
        when(javaQuestionRepositoryMock.add(anyString(), anyString())).thenThrow(IllegalArgumentException.class);

        Assertions.assertThrows(IllegalArgumentException.class, () -> out.add(null));
        Assertions.assertThrows(IllegalArgumentException.class, () -> out.add(null, null));
        Assertions.assertThrows(IllegalArgumentException.class, () -> out.add("", " "));
        Assertions.assertThrows(IllegalArgumentException.class, () -> out.add(" ", ""));
    }

    @Test
    void remove() {
        when(javaQuestionRepositoryMock.remove(eq(question1))).thenReturn(question1);

        Assertions.assertEquals(question1, out.remove(question1));

        verify(javaQuestionRepositoryMock, times(1)).remove(question1);
    }

    @Test
    void shouldThrowExceptionWhenRepositoryThrowsExceptionsRemovingIncorrectInput() {
        when(javaQuestionRepositoryMock.remove(any())).thenThrow(IllegalArgumentException.class);

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

        when(javaQuestionRepositoryMock.getAll()).thenReturn(questionSet);

        Assertions.assertEquals(questionSet, out.getAll());
    }

    @Test
    void getRandomQuestionIsNotNull() {
        Set<Question> questionSet = new HashSet<>(Set.of(question1));

        when(javaQuestionRepositoryMock.getAll()).thenReturn(questionSet);

        Question randomQuestion = out.getRandomQuestion();

        Assertions.assertNotNull(randomQuestion);
    }
}