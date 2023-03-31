package io.sbb.third.question;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class QuestionController {

    private final QuestionRepository questionRepository;

    @GetMapping("/question/list")
    public String list(Model model) {
        List<Question> lq = this.questionRepository.findAll();
        model.addAttribute("questionList", lq); // 모델 객체에 값을 담아두면 템플릿에서 사용할 수 있다.

        return "question_list";
    }


}
