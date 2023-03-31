package io.sbb.third.question;

import io.sbb.third.answer.Answer;
import io.sbb.third.answer.AnswerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AnswerService {
    private final AnswerRepository answerRepository;

    public void save(Question question, String content) {
        Answer a = new Answer();
        a.setContent(content);
        a.setQuestion(question);
        a.setCreateDate(LocalDateTime.now());
        this.answerRepository.save(a);
    }

}
