package io.sbb.third.question;

import io.sbb.third.QuestionForm;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/question") // 겹치는 URL을 클래스가 고정시킨다.
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

    @GetMapping("/create")
    public String createQ(QuestionForm questionForm) {
        return "question_form";
    }

    @PostMapping("/create")   // 폼에서 받은 정보를 service에 넘김
    public String createQ(@Valid QuestionForm questionForm, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "question_form"; // 입력값이 비어있거나 설정한 기준에 맞지 않는 경우. 경고창이 뜬 폼 화면을 유지
        }
        this.questionService.saveQ(questionForm.getTitle(), questionForm.getContent());
        return "redirect:/question/list";
    }



}
