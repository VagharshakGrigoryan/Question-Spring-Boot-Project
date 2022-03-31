package com.example.register.service;


import com.example.register.model.Question;
import com.example.register.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class QuizService {

	private final QuestionService questionService;
	private final UserService userService;
	
	
	public void storeUsersAnswer(String username, long questionId, String answer) {
		
		Question question = questionService.getQuestionEntity(questionId);
		User user = userService.getUser(username);
		user.storeAnsweredQuestion(question, answer);
		userService.update(user);
	}

	public int getUserScore(String username) {
		return userService.getScore(username);
	}
	
	public void removeQuestion(long id) {
		userService.removeFromUsersAnswers(id);
		questionService.delete(id);
	}
	
}
