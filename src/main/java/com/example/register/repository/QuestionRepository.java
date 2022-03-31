package com.example.register.repository;

import com.example.register.dto.QuestionDTO;
import com.example.register.model.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.Set;


public interface QuestionRepository extends PagingAndSortingRepository<Question, Long> {

    QuestionDTO getById(Long questionId);

    Set<QuestionDTO> findBy();

    Set<QuestionDTO> findByCreatedByUsername(String username);

    Set<Question> findByQuestionText(String questionText);

    Page<QuestionDTO> findByOrderById(Pageable pageRequest);

    @Query("SELECT q.questionText FROM Question q ORDER BY q.id")
    List<String> findAllQuestionTexts();

}