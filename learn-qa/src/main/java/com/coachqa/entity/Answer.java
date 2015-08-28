package com.coachqa.entity;

// Generated Dec 21, 2014 1:45:02 PM by Hibernate Tools 3.4.0.CR1

/**
 * Answer generated by hbm2java
 */
public class Answer extends Post implements java.io.Serializable {

	private Integer answerId;
	private int answeredByUserId;
	private int questionId;
	private byte[] audio;
	private byte[] video;

	public Answer() {
	}

	public Answer(int answeredByUserId, int questionId, String content) {
		this.answeredByUserId = answeredByUserId;
		this.questionId = questionId;
	}

	public Answer(int answeredByUserId, int questionId, Integer votes,
			String content) {
		this.answeredByUserId = answeredByUserId;
		this.questionId = questionId;
	}

	public Integer getAnswerId() {
		return this.answerId;
	}

	public void setAnswerId(Integer answerId) {
		this.answerId = answerId;
	}

	public int getAnsweredByUserId() {
		return this.answeredByUserId;
	}

	public void setAnsweredByUserId(int answeredByUserId) {
		this.answeredByUserId = answeredByUserId;
	}

	public int getQuestionId() {
		return this.questionId;
	}

	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}


}
