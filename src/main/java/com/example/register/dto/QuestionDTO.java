package com.example.register.dto;

import java.util.List;

public interface QuestionDTO {
	
	Long getId();
	CreatedBy getCreatedBy();
	String getQuestionText();
	String getCorrectAnswer();
	List<String> getAnswers();
	
	public interface CreatedBy {
		String getUsername();
	}

}
