
package com.mbrd.assignment.frontendservice.model;

public class UserMessage {

	private User user;
	private String format;
	private String methodType;

	public UserMessage() {
		super();
	}

	public UserMessage(User user, String format, String methodType) {
		super();
		this.user = user;
		this.format = format;
		this.methodType = methodType;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public String getMethodType() {
		return methodType;
	}

	public void setMethodType(String methodType) {
		this.methodType = methodType;
	}

	@Override
	public String toString() {
		return "UserMessage [user=" + user + ", format=" + format + ", methodType=" + methodType + "]";
	}

}
