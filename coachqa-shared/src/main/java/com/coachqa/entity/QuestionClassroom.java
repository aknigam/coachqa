package com.coachqa.entity;

// Generated Dec 21, 2014 1:45:02 PM by Hibernate Tools 3.4.0.CR1

/**
 * QuestionClassroom generated by hbm2java
 */
public class QuestionClassroom implements java.io.Serializable {

	private Integer questionClassroomId;
	private int classroomId;
	private int questionId;

	public QuestionClassroom() {
	}

	public QuestionClassroom(int classroomId, int questionId) {
		this.classroomId = classroomId;
		this.questionId = questionId;
	}

	public Integer getQuestionClassroomId() {
		return this.questionClassroomId;
	}

	public void setQuestionClassroomId(Integer questionClassroomId) {
		this.questionClassroomId = questionClassroomId;
	}

	public int getClassroomId() {
		return this.classroomId;
	}

	public void setClassroomId(int classroomId) {
		this.classroomId = classroomId;
	}

	public int getQuestionId() {
		return this.questionId;
	}

	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}

}