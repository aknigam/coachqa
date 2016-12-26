package com.coachqa.ws.model;

public class HomePageModel {

	private String m_username;
	private Integer m_userId;
	private Integer m_appRoleId;

	public void setUsername(String name) {
		m_username=  name;
	}


	public void setUserId(Integer userId) {
		m_userId =  userId;
		
	}

	public String getUsername() {
		return m_username;
	}


	public Integer getUserId() {
		return m_userId;
	}


	public void setAppRoleId(int appRoleId)
	{
		m_appRoleId = appRoleId;
	}
	
	public Integer getAppRoleId()
	{
		return m_appRoleId;
	}

}
