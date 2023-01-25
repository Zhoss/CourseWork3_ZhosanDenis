package pro.sky.java.course3.coursework3_zhosandenis.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import pro.sky.java.course3.coursework3_zhosandenis.model.Question;
import pro.sky.java.course3.coursework3_zhosandenis.service.ExaminerService;

import java.util.Collection;

@RestController
public class ExamController {
    private final ExaminerService examinerService;

    public ExamController(ExaminerService examinerService) {
        this.examinerService = examinerService;
    }

    @GetMapping("/exam/get/{amount}")
    public Collection<Question> questions(@PathVariable int amount) {
        return examinerService.getQuestions(amount);
    }

    @ExceptionHandler(value = ResponseStatusException.class)
    public ResponseEntity<String> handleResponseStatusException(ResponseStatusException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
