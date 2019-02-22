package com.coachqa.repository.dao;

import com.coachqa.entity.AppUser;
import com.coachqa.entity.Question;
import com.coachqa.ws.controllor.QueryCriteria;
import com.coachqa.ws.model.AnswerModel;

import java.util.List;

public interface QuestionDAO {

	Question addQuestionWithTags(Question question);

	Question getQuestionById(Integer questionId);

	void addAnswertoQuestion(AnswerModel answer);

	void updateStats(Question question);

	void incrementQuestionViews(Integer questionId);

	List<Question> getQuestionsByTag(int tagId);

	List<Question> findSimilarQuestions(Question criteria, int page, AppUser user, int noOfResults);

	Question updateQuestion(Question updatedQuestion);

    List<Question> getUsersPosts(Integer appUserId, Integer page);

    void markAsFavorite(Integer appUserId, Integer questionId, boolean isFavorite);

	List<Question> getMyFavorites(Integer appUserId, Integer page);

	boolean isFavorite(Integer questionId, Integer appUserId);

    List<Question> findByQuery(QueryCriteria searchQuery, Integer page, AppUser loggedInUser, int noOfPaginatedResults);
}
