package pro.sky.java.course3.coursework3_zhosandenis.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;
import pro.sky.java.course3.coursework3_zhosandenis.model.Question;
import pro.sky.java.course3.coursework3_zhosandenis.service.JavaQuestionService;

import java.util.Collection;

@RestController
@RequestMapping("/exam/java")
public class JavaQuestionController {
    private final JavaQuestionService service;

    public JavaQuestionController(JavaQuestionService service) {
        this.service = service;
    }

    @GetMapping("/add")
    public Question addQuestion(@RequestParam String question,@RequestParam String answer) {
        return this.service.add(question, answer);
    }

    @GetMapping("/remove")
    public Question removeQuestion(@RequestParam String question,@RequestParam String answer) {
        return this.service.remove(new Question(question, answer));
    }

    @GetMapping("")
    public Collection<Question> getQuestions() {
        return this.service.getAll();
    }

    @ExceptionHandler(value = IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
