package com.coachqa.entity;

// Generated Dec 21, 2014 1:45:02 PM by Hibernate Tools 3.4.0.CR1



/**
 * Classroom generated by hbm2java
 */
public class Classroom implements java.io.Serializable {

	private Integer classroomId;
	private int minReputationToJoinId;
	private AppUser classOwner;
	private String className;
	public String description;
	private boolean isPublic;

	public Classroom() {
	}

	public Classroom(int id, String name) {
		className = name;
		classroomId = id;
	}

	public Integer getClassroomId() {
		return this.classroomId;
	}

	public void setClassroomId(Integer classroomId) {
		this.classroomId = classroomId;
	}

	public int getMinReputationToJoinId() {
		return this.minReputationToJoinId;
	}

	public void setMinReputationToJoinId(int minReputationToJoinId) {
		this.minReputationToJoinId = minReputationToJoinId;
	}

	public AppUser getClassOwner() {
		return this.classOwner;
	}

	public void setClassOwner(AppUser classOwner) {
		this.classOwner = classOwner;
	}

	public String getClassName() {
		return this.className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public boolean isIsPublic() {
		return this.isPublic;
	}

	public void setIsPublic(boolean isPublic) {
		this.isPublic = isPublic;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return className;
	}

	/*
	public static void main(String[] args) throws IOException {
		Classroom c = new Classroom();
		c.setClassName("Physics class");
		c.setIsPublic(true);
		c.setClassOwner(new AppUser(1, "email", "firstname", "middlename", "lastname"));

		ObjectMapper om = new ObjectMapper();
		om.writerWithDefaultPrettyPrinter();
		Writer w = new StringWriter();
		try {
			om.writeValue(w, c);
			System.out.println(w.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		com.fasterxml.jackson.databind.ObjectMapper omp = new com.fasterxml.jackson.databind.ObjectMapper();
		Question[] q = omp.readValue(new StringBufferInputStream("[{\"postId\":null,\"noOfViews\":0,\"postedBy\":{\"appUserId\":1,\"userReputationId\":null,\"email\":null,\"password\":null,\"firstName\":\"Anand\",\"middleName\":\"K\",\"lastName\":\"nigam\"},\"postDate\":\"2016-12-25\",\"votes\":0,\"content\":\"Body\",\"approvalStatus\":true,\"approvalComment\":\"Approved\",\"image\":null,\"questionId\":196,\"refSubjectId\":null,\"questionLevelEnum\":null,\"refQuestionStatusId\":\"NEW\",\"title\":\"ANdroid question 2\",\"lastActiveDate\":\"2016-12-25\",\"publicQuestion\":false,\"classroom\":null,\"tags\":[],\"answers\":[]},{\"postId\":null,\"noOfViews\":0,\"postedBy\":{\"appUserId\":1,\"userReputationId\":null,\"email\":null,\"password\":null,\"firstName\":\"Anand\",\"middleName\":\"K\",\"lastName\":\"nigam\"},\"postDate\":\"2016-12-26\",\"votes\":0,\"content\":\"Difficult question body\",\"approvalStatus\":true,\"approvalComment\":\"Approved\",\"image\":null,\"questionId\":197,\"refSubjectId\":null,\"questionLevelEnum\":null,\"refQuestionStatusId\":\"NEW\",\"title\":\"This is a difficult question in algebra\",\"lastActiveDate\":\"2016-12-26\",\"publicQuestion\":false,\"classroom\":null,\"tags\":[],\"answers\":[]},{\"postId\":null,\"noOfViews\":0,\"postedBy\":{\"appUserId\":1,\"userReputationId\":null,\"email\":null,\"password\":null,\"firstName\":\"Anand\",\"middleName\":\"K\",\"lastName\":\"nigam\"},\"postDate\":\"2016-12-26\",\"votes\":0,\"content\":\"Difficult question body\",\"approvalStatus\":true,\"approvalComment\":\"Approved\",\"image\":null,\"questionId\":197,\"refSubjectId\":null,\"questionLevelEnum\":null,\"refQuestionStatusId\":\"NEW\",\"title\":\"This is a difficult question in algebra\",\"lastActiveDate\":\"2016-12-26\",\"publicQuestion\":false,\"classroom\":null,\"tags\":[],\"answers\":[]},{\"postId\":null,\"noOfViews\":0,\"postedBy\":{\"appUserId\":1,\"userReputationId\":null,\"email\":null,\"password\":null,\"firstName\":\"Anand\",\"middleName\":\"K\",\"lastName\":\"nigam\"},\"postDate\":\"2016-12-26\",\"votes\":0,\"content\":\"Body\",\"approvalStatus\":true,\"approvalComment\":\"Approved\",\"image\":null,\"questionId\":198,\"refSubjectId\":null,\"questionLevelEnum\":null,\"refQuestionStatusId\":\"NEW\",\"title\":\"New question title\",\"lastActiveDate\":\"2016-12-26\",\"publicQuestion\":false,\"classroom\":null,\"tags\":[],\"answers\":[]},{\"postId\":null,\"noOfViews\":0,\"postedBy\":{\"appUserId\":1,\"userReputationId\":null,\"email\":null,\"password\":null,\"firstName\":\"Anand\",\"middleName\":\"K\",\"lastName\":\"nigam\"},\"postDate\":\"2016-12-26\",\"votes\":0,\"content\":\"body\",\"approvalStatus\":true,\"approvalComment\":\"Approved\",\"image\":null,\"questionId\":199,\"refSubjectId\":null,\"questionLevelEnum\":null,\"refQuestionStatusId\":\"NEW\",\"title\":\"New algebra question\",\"lastActiveDate\":\"2016-12-26\",\"publicQuestion\":false,\"classroom\":null,\"tags\":[],\"answers\":[]},{\"postId\":null,\"noOfViews\":0,\"postedBy\":{\"appUserId\":1,\"userReputationId\":null,\"email\":null,\"password\":null,\"firstName\":\"Anand\",\"middleName\":\"K\",\"lastName\":\"nigam\"},\"postDate\":\"2016-12-26\",\"votes\":0,\"content\":\"test\",\"approvalStatus\":true,\"approvalComment\":\"Approved\",\"image\":null,\"questionId\":200,\"refSubjectId\":null,\"questionLevelEnum\":null,\"refQuestionStatusId\":\"NEW\",\"title\":\"test\",\"lastActiveDate\":\"2016-12-26\",\"publicQuestion\":false,\"classroom\":null,\"tags\":[],\"answers\":[]},{\"postId\":null,\"noOfViews\":0,\"postedBy\":{\"appUserId\":1,\"userReputationId\":null,\"email\":null,\"password\":null,\"firstName\":\"Anand\",\"middleName\":\"K\",\"lastName\":\"nigam\"},\"postDate\":\"2016-12-27\",\"votes\":0,\"content\":\"Body\",\"approvalStatus\":true,\"approvalComment\":\"Approved\",\"image\":null,\"questionId\":201,\"refSubjectId\":null,\"questionLevelEnum\":null,\"refQuestionStatusId\":\"NEW\",\"title\":\"Scientific question\",\"lastActiveDate\":\"2016-12-27\",\"publicQuestion\":false,\"classroom\":null,\"tags\":[],\"answers\":[]}]"), omp.constructType(Question[].class));

//		System.out.println(Arrays.asList(q).get(0).getPostTypeId());
	}
	*/
}
