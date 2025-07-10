package com.quiz.controller;

import com.quiz.QuestionWrapper;
import com.quiz.Response;
import com.quiz.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/quiz")
public class QuizController {
    @Autowired
    QuizService quizService;
    @GetMapping("/create")
    public String createQuiz(@RequestParam String category, @RequestParam String qTitle, @RequestParam Integer noQs){
        return  quizService.createQuiz(category,qTitle,noQs);
    }
    @GetMapping("/get/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuestionsForQuiz(@PathVariable Integer id){
        return  quizService.getQuestionsForQuiz(id);

    }
    @PostMapping("submit/{id}")
    public ResponseEntity<Integer> calculateAnswer(@PathVariable Integer id,@RequestBody List<Response> responses){
    return quizService.submit(id,responses);
    }
}
