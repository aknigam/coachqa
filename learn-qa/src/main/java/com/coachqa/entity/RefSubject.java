package com.coachqa.entity;

// Generated Dec 21, 2014 1:45:02 PM by Hibernate Tools 3.4.0.CR1

/**
 * RefSubject generated by hbm2java
 */
public class RefSubject implements java.io.Serializable {

	private int refSubjectId;
	private String subjectName;
	private String subjectDescription;

	public RefSubject() {
	}

	public RefSubject(int refSubjectId, String subjectName,
			String subjectDescription) {
		this.refSubjectId = refSubjectId;
		this.subjectName = subjectName;
		this.subjectDescription = subjectDescription;
	}

	public int getRefSubjectId() {
		return this.refSubjectId;
	}

	public void setRefSubjectId(int refSubjectId) {
		this.refSubjectId = refSubjectId;
	}

	public String getSubjectName() {
		return this.subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public String getSubjectDescription() {
		return this.subjectDescription;
	}

	public void setSubjectDescription(String subjectDescription) {
		this.subjectDescription = subjectDescription;
	}

}