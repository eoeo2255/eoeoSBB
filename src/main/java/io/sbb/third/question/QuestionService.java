package io.sbb.third.question;

import io.sbb.third.DataNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class QuestionService {
    private final QuestionRepository questionRepository;

    public List<Question> getQList() {
        return this.questionRepository.findAll(); //컨트롤러에서 바로 받지 않고 서비스에서 받아가야 하기 때문에 서비스에서 로직을 짠다.
    }

    public Question getQ(Integer id) {
        Optional<Question> oq = this.questionRepository.findById(id);

        if (oq.isPresent()) {
            return oq.get();
        } else {
            throw new DataNotFoundException("question not found");
        }
    }




}
