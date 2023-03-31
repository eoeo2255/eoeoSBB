package io.sbb.third.question;

import io.sbb.third.DataNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
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

    public void saveQ(String title, String content){ //컨트롤러가 받은 정보를 DB에 저장
        Question q = new Question();
        q.setTitle(title);
        q.setContent(content);
        q.setCreateDate(LocalDateTime.now());
        this.questionRepository.save(q);
    }


}
