package com.coachqa.ws.model;

import java.util.Date;
import java.util.List;


public class QuestionModel implements java.io.Serializable {

	private Integer questionId;
	private int refSubjectId;
	private int questionLevelId;
	private int postedBy;
	private int refQuestionStatusId;
	private String title;
	private String content;
	private byte[] image;
	private Integer noOfViews;
	private Date postDate;
	private Date lastActiveDate;

	// this won't be available at the time of posting the question
	private Integer votes;
	private boolean isPublic;
	private Integer classroomId;


	public List<Integer> getTags() {
		return tags;
	}

	public void setTags(List<Integer> tags) {
		this.tags = tags;
	}

	private List<Integer> tags;

	public List<String> getNewTags() {
		return newTags;
	}

	public void setNewTags(List<String> newTags) {
		this.newTags = newTags;
	}

	private List<String> newTags;

	public QuestionModel() {
	}

	public QuestionModel(int refSubjectId, int questionLevelId, int postedBy,
			int refQuestionStatusId, String title, String content,
			Date postDate, Date lastActiveDate, boolean isPublic) {
		this.refSubjectId = refSubjectId;
		this.questionLevelId = questionLevelId;
		this.postedBy = postedBy;
		this.refQuestionStatusId = refQuestionStatusId;
		this.title = title;
		this.content = content;
		this.postDate = postDate;
		this.lastActiveDate = lastActiveDate;
		this.isPublic = isPublic;
	}

	public QuestionModel(int refSubjectId, int questionLevelId, int postedBy,
			int refQuestionStatusId, String title, String content,
			Integer noOfViews, Date postDate, Date lastActiveDate,
			Integer votes, boolean isPublic) {
		this.refSubjectId = refSubjectId;
		this.questionLevelId = questionLevelId;
		this.postedBy = postedBy;
		this.refQuestionStatusId = refQuestionStatusId;
		this.title = title;
		this.content = content;
		this.noOfViews = noOfViews;
		this.postDate = postDate;
		this.lastActiveDate = lastActiveDate;
		this.votes = votes;
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

	public int getPostedBy() {
		return this.postedBy;
	}

	public void setPostedBy(int postedBy) {
		this.postedBy = postedBy;
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

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getNoOfViews() {
		return this.noOfViews;
	}

	public void setNoOfViews(Integer noOfViews) {
		this.noOfViews = noOfViews;
	}

	public Date getPostDate() {
		return this.postDate;
	}

	public void setPostDate(Date postDate) {
		this.postDate = postDate;
	}

	public Date getLastActiveDate() {
		return this.lastActiveDate;
	}

	public void setLastActiveDate(Date lastActiveDate) {
		this.lastActiveDate = lastActiveDate;
	}

	public Integer getVotes() {
		return this.votes;
	}

	public void setVotes(Integer votes) {
		this.votes = votes;
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

	public Integer getClassroomId() {
		return classroomId;
	}

	public void setClassroomId(Integer classroomId) {
		this.classroomId = classroomId;
	}

}
