package io.sbb.third;

import io.sbb.third.answer.Answer;
import io.sbb.third.answer.AnswerRepository;
import io.sbb.third.question.Question;
import io.sbb.third.question.QuestionRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class ThirdApplicationTests {
	@Autowired
	private QuestionRepository questionRepository;

	@Autowired
	private AnswerRepository answerRepository;

	@Test
	@DisplayName("질문 저장")
	void contextLoads() {
		Question q = new Question();
		q.setTitle("첫번째 질문");
		q.setContent("잘 들어갔는지 확인");
		q.setCreateDate(LocalDateTime.now());
		this.questionRepository.save(q);

		Question q2 = new Question();
		q2.setTitle("두번째 질문");
		q2.setContent("데이터 만들기");
		q2.setCreateDate(LocalDateTime.now());
		this.questionRepository.save(q2);

		Question q3 = new Question();
		q3.setTitle("세번째 질문");
		q3.setContent("조회용 데이터");
		q3.setCreateDate(LocalDateTime.now());
		this.questionRepository.save(q3);
	}

	@Test
	@DisplayName("질문 전체 조회")
	void contextLoads2() {
		List<Question> all = this.questionRepository.findAll();
		Question q = all.get(0);
	}

	@Test
	@DisplayName("id로 질문 조회")
	void contextLoads3() {
		Optional<Question> oq = this.questionRepository.findById(1);
		if(oq.isPresent()) {
			Question q = oq.get();
		}
	}

	@Test
	@DisplayName("제목으로 질문 조회")
	void contextLoads4() {
		Question oq = this.questionRepository.findByTitle("두번째 질문");

	}

	@Test
	@DisplayName("제목과 내용으로 질문 조회")
	void contextLoads5() {
		Question oq = this.questionRepository.findByTitleAndContent("세번째 질문", "조회용 데이터");
	}

	@Test
	@DisplayName("제목의 특정 문자열로 질문 조회")
	void contextLoads6() {
		List<Question> qlist = this.questionRepository.findByTitleLike("질문");
		Question q = qlist.get(0);
	}

	@Test
	@DisplayName("질문 수정")
	void contextLoads7() {
		Optional<Question> oq = this.questionRepository.findById(1);
		assertTrue(oq.isPresent());
		Question q = oq.get();
		q.setTitle("네번째 질문");
		this.questionRepository.save(q);
	}

	@Test
	@DisplayName("질문 삭제")
	void contextLoads8() {
		Optional<Question> oq = this.questionRepository.findById(1);
		assertTrue(oq.isPresent());
//		oq.orElseThrow(()-> new RuntimeException("해당 질문 없음"));
//		동일한 결과를 얻을 수 있음. orElseThrow()를 더 자주 쓰자
		Question q = oq.get();
		this.questionRepository.delete(q);
	}

	@Test
	@DisplayName("답변 등록")
	void contextLoads9() {
		Optional<Question> oq = this.questionRepository.findById(1);
		if(oq.isPresent()) {
			Question q = oq.get();

			Answer a = new Answer();
			a.setContent("잘 들어왔습니다.");
			a.setQuestion(q);
			a.setCreateDate(LocalDateTime.now());
			this.answerRepository.save(a);
		}

// 		밑에 코드와 위의 코드는 동일한 기능을 한다.

//		assertTrue(oq.isPresent());
//		Question q = oq.get();

//		Answer a = new Answer();
//		a.setContent("잘 들어왔습니다.");
//		a.setQuestion(q);
//		a.setCreateDate(LocalDateTime.now());
//		this.answerRepository.save(a);
	}



}
