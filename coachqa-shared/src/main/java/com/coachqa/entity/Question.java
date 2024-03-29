package com.coachqa.entity;

// Generated Dec 21, 2014 1:45:02 PM by Hibernate Tools 3.4.0.CR1

import com.coachqa.enums.PostTypeEnum;
import com.coachqa.enums.QuestionLevelEnum;
import com.coachqa.enums.QuestionStatusEnum;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * Question generated by hbm2java
 * NoOf View should be removed from questionin DB
 *
 */
public class Question extends Post implements java.io.Serializable {

	
	private Integer questionId;
	
	private Integer refSubjectId;

	private RefSubject subject;
	
	private QuestionLevelEnum questionLevelEnum;
	
	private QuestionStatusEnum refQuestionStatusId = QuestionStatusEnum.NEW;

	public int getStatusId() {
		return refQuestionStatusId.getId();
	}

	private int statusId;
	
	private String title;


	@JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
	private Date lastActiveDate;

	private boolean favorite;

	private List<Tag> tags = Collections.emptyList();

	private List<Answer> answers = Collections.emptyList();


	public boolean isFavorite() {
		return favorite;
	}

	public void setFavorite(boolean favorite) {
		this.favorite = favorite;
	}

	public Question() {
		setPostTypeEnum(PostTypeEnum.QUESTION);
	}


	public RefSubject getSubject() {
		return subject;
	}

	public void setSubject(RefSubject subject) {
		this.subject = subject;
	}

	public Integer getQuestionId() {
		return questionId == null ? getPostId() : questionId;
	}

	public void setQuestionId(Integer questionId) {
		this.questionId = questionId;
	}

	public Integer getRefSubjectId() {
		return refSubjectId;
	}

	public void setRefSubjectId(Integer refSubjectId) {
		this.refSubjectId = refSubjectId;
	}

	public QuestionLevelEnum getQuestionLevelEnum() {
		return questionLevelEnum;
	}

	public void setQuestionLevelEnum(QuestionLevelEnum questionLevelEnum) {
		this.questionLevelEnum = questionLevelEnum;
	}

	public QuestionStatusEnum getRefQuestionStatusId() {
		return refQuestionStatusId;
	}

	public void setRefQuestionStatusId(QuestionStatusEnum refQuestionStatusId) {
		this.refQuestionStatusId = refQuestionStatusId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<Tag> getTags() {
		return tags;
	}

	public List<Integer> getTagIds() {
		List<Integer> tagIds = new ArrayList<>();
		for(Tag t : tags ) {
			tagIds.add(t.getTagId());
		}
		return tagIds;
	}

	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}

	public List<Answer> getAnswers() {
		return answers;
	}

	public void setAnswers(List<Answer> answers) {
		this.answers = answers;
	}

	public Date getLastActiveDate() {
		return lastActiveDate;
	}

	public void setLastActiveDate(Date lastActiveDate) {
		this.lastActiveDate = lastActiveDate;
	}

}
