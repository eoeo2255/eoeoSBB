package io.sbb.third.question;

import io.sbb.third.answer.Answer;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(length = 200)
    private String title;
    @Column(columnDefinition = "TEXT")
    private String content;
    private LocalDateTime createDate;

    @OneToMany(mappedBy = "question", cascade = CascadeType.REMOVE) // 부모 엔티티가 삭제되면 자식 엔티티도 같이 삭제
    List<Answer> answerList; // 하나의 질문에 달릴 수 있는 답변은 여러개기 때문에 리스트 형식을 사용

}
