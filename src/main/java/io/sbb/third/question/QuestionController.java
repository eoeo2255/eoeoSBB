package io.sbb.third.question;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/question")
@RequiredArgsConstructor
@Controller
public class QuestionController {

    private final QuestionService questionService; //서비스가 생겼기 때문에 컨트롤러는 서비스를 통해 DB에 접근할 수 있다.

    @GetMapping("/list")
    public String list(Model model) {
        List<Question> lq = this.questionService.getQList();
        model.addAttribute("questionList", lq); // 모델 객체에 값을 담아두면 템플릿에서 사용할 수 있다.

        return "question_list";
    }

    @GetMapping("/")
    public String root() {
        return "redirect:/question/list";
    }

    @GetMapping("/detail/{id}")
    public String detail(Model model, @PathVariable("id") Integer id) {
        Question q = this.questionService.getQ(id);
        model.addAttribute("question",q);
        return "question_detail";
    }





}
