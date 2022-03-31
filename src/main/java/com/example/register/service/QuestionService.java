package com.example.register.service;

import com.example.register.dto.QuestionDTO;
import com.example.register.model.Question;
import com.example.register.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
@Transactional
@RequiredArgsConstructor
public class QuestionService {
	

	private final QuestionRepository questionRepo;


	public Set<QuestionDTO> listAll() {
		return questionRepo.findBy();
	}

	public Set<QuestionDTO> listAllByUser(String username) {
		return questionRepo.findByCreatedByUsername(username);
	}

	public int getNumberOfQuestions() {
		return (int) questionRepo.count();
	}

	public QuestionDTO getQuestion(long questionId) {
		return questionRepo.getById(questionId);
	}

	public Question getQuestionEntity(long id) {
		return questionRepo.findById(id).orElseThrow();
	}

	public boolean exist(Question question) {
		Set<Question> fromDB = questionRepo.findByQuestionText(question.getQuestionText());
		return fromDB.contains(question);
	}

	public QuestionDTO getQuestionByIndex(int qIndex) {
		
		Pageable pageRequest = PageRequest.of(qIndex, 1);
		Page<QuestionDTO> page = questionRepo.findByOrderById(pageRequest);
		if ( ! page.hasContent())
			throw new IndexOutOfBoundsException();
		return page.getContent().get(0);
	}
	
	public void save(Question q) {

		List<String> ans = q.getAnswers();
		int markedAsCorrect = q.getSelectedAnswerIndex();  
		q.setCorrectAnswer(ans.get(markedAsCorrect));
		questionRepo.save(q);
	}
	
	public void delete(Long id) {
		questionRepo.deleteById(id);
	}
	
	public List<String> getQuestionTexts() {
		return questionRepo.findAllQuestionTexts();
	}
	
}
