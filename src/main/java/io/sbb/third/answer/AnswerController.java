package io.sbb.third.answer;

import io.sbb.third.question.Question;
import io.sbb.third.question.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/answer")
@Controller
public class AnswerController {

    private final AnswerService answerService; // answerRepository는 service를 통해서 접근할 수 있다.
    private final QuestionService questionService; // 답변이 달릴 질문을 가져와야 한다

    @PostMapping("/create/{id}") // GetMapping은 질문 상세 페이지에서 이미 호출되었기 때문에 필요 없다.
    public String create(Model model, @PathVariable("id") Integer id, @RequestParam String content){
        Question q = questionService.getQ(id);
        this.answerService.save(q, content);
        return String.format("redirect:/question/detail/%s", id);
    }



}
