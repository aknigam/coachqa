package com.coachqa.entity;

// Generated Dec 21, 2014 1:45:02 PM by Hibernate Tools 3.4.0.CR1

import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * Question generated by hbm2java
 */
public class Question extends Post implements java.io.Serializable {

	public Integer questionId;
	private int refSubjectId;
	private int questionLevelId;
	private int refQuestionStatusId;
	
	private String title;
	private byte[] image;
	
	private Integer noOfViews;
	private Date lastActiveDate;
	private boolean isPublic;
	private Classroom classroom;

	private List<Integer> tags;


	private List<Answer> answers = Collections.emptyList();

	public Question() {
	}

	public Question(int refSubjectId, int questionLevelId, AppUser postedBy,
			int refQuestionStatusId, String title, String content,
			Date postDate, Date lastActiveDate, boolean isPublic) {
		this.refSubjectId = refSubjectId;
		this.questionLevelId = questionLevelId;
		this.refQuestionStatusId = refQuestionStatusId;
		this.title = title;
		this.lastActiveDate = lastActiveDate;
		this.isPublic = isPublic;
	}

	public Question(int refSubjectId, int questionLevelId, AppUser postedBy,
			int refQuestionStatusId, String title, String content,
			Integer noOfViews, Date postDate, Date lastActiveDate,
			Integer votes, boolean isPublic) {
		this.refSubjectId = refSubjectId;
		this.questionLevelId = questionLevelId;
		this.refQuestionStatusId = refQuestionStatusId;
		this.title = title;
		this.noOfViews = noOfViews;
		this.lastActiveDate = lastActiveDate;
		this.isPublic = isPublic;
	}

	public Integer getQuestionId() {
		return this.questionId;
	}

	public void setQuestionId(Integer questionId) {
		this.questionId = questionId;
	}

	public int getRefSubjectId() {
		return this.refSubjectId;
	}

	public void setRefSubjectId(int refSubjectId) {
		this.refSubjectId = refSubjectId;
	}

	public int getQuestionLevelId() {
		return this.questionLevelId;
	}

	public void setQuestionLevelId(int questionLevelId) {
		this.questionLevelId = questionLevelId;
	}


	public int getRefQuestionStatusId() {
		return this.refQuestionStatusId;
	}

	public void setRefQuestionStatusId(int refQuestionStatusId) {
		this.refQuestionStatusId = refQuestionStatusId;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}


	public Integer getNoOfViews() {
		return this.noOfViews;
	}

	public void setNoOfViews(Integer noOfViews) {
		this.noOfViews = noOfViews;
	}


	public Date getLastActiveDate() {
		return this.lastActiveDate;
	}

	public void setLastActiveDate(Date lastActiveDate) {
		this.lastActiveDate = lastActiveDate;
	}


	public boolean isIsPublic() {
		return this.isPublic;
	}

	public void setIsPublic(boolean isPublic) {
		this.isPublic = isPublic;
	}

	public boolean isPublic() {
		return isPublic;
	}

	public void setPublic(boolean isPublic) {
		this.isPublic = isPublic;
	}

	public List<Answer> getAnswers() {
		return answers;
	}

	public void setAnswers(List<Answer> answers) {
		this.answers = answers;
	}

	public Classroom getClassroom() {
		return classroom;
	}

	public void setClassroom(Classroom classroom) {
		this.classroom = classroom;
	}

	public boolean hasImage() {
		return false;
	}
}
