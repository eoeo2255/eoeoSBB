package io.sbb.third.question;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class QuestionService {
    private final QuestionRepository questionRepository;

    public List<Question> getQ() {
        return this.questionRepository.findAll(); //컨트롤러에서 바로 받지 않고 서비스에서 받아가야 하기 때문에 서비스에서 로직을 짠다.
    }

}
