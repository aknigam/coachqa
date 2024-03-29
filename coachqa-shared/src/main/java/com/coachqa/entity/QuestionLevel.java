package com.coachqa.entity;

// Generated Dec 21, 2014 1:45:02 PM by Hibernate Tools 3.4.0.CR1

/**
 * QuestionLevel generated by hbm2java
 */
public class QuestionLevel implements java.io.Serializable {

	private int questionLevelId;
	private String levelName;
	private int levelOrder;

	public QuestionLevel() {
	}

	public QuestionLevel(int questionLevelId, String levelName, int levelOrder) {
		this.questionLevelId = questionLevelId;
		this.levelName = levelName;
		this.levelOrder = levelOrder;
	}

	public int getQuestionLevelId() {
		return this.questionLevelId;
	}

	public void setQuestionLevelId(int questionLevelId) {
		this.questionLevelId = questionLevelId;
	}

	public String getLevelName() {
		return this.levelName;
	}

	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}

	public int getLevelOrder() {
		return this.levelOrder;
	}

	public void setLevelOrder(int levelOrder) {
		this.levelOrder = levelOrder;
	}

}
