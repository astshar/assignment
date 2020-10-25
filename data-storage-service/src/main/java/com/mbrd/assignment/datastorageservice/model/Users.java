package com.mbrd.assignment.datastorageservice.model;

import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Users {
	private int id;
	private List<User> User;

	public Users() {

	}

	public Users(int id, List<com.mbrd.assignment.datastorageservice.model.User> user) {
		super();
		this.id = id;
		User = user;
	}

	@XmlAttribute
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@XmlElement
	public List<User> getUser() {
		return User;
	}

	public void setUser(List<User> user) {
		User = user;
	}

}
