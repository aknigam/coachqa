package com.coachqa.entity;

// Generated Dec 21, 2014 1:45:02 PM by Hibernate Tools 3.4.0.CR1

/**
 * AppUser generated by hbm2java
 */
public class AppUser implements java.io.Serializable {

	private Integer appUserId;
	private Integer userReputationId;
	private String email;
	private String password;
	private String firstName;
	private String middleName;
	private String lastName;

	public AppUser() {
	}

	public AppUser(String email, String pasword, String firstName,
			String lastName) {
		this.email = email;
		this.password = pasword;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public AppUser(Integer userReputationId, String email, String pasword,
			String firstName, String middleName, String lastName) {
		this.userReputationId = userReputationId;
		this.email = email;
		this.password = pasword;
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
	}

	public Integer getAppUserId() {
		return this.appUserId;
	}

	public void setAppUserId(Integer appUserId) {
		this.appUserId = appUserId;
	}

	public Integer getUserReputationId() {
		return this.userReputationId;
	}

	public void setUserReputationId(Integer userReputationId) {
		this.userReputationId = userReputationId;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String pasword) {
		this.password = pasword;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return this.middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getName() {
		return firstName+" "+ middleName+" "+lastName;
	}

}
