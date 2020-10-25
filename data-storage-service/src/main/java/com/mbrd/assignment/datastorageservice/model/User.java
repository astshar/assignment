package com.mbrd.assignment.datastorageservice.model;

public class User {

	private int id;

	private String name;

	private String dob;

	private int age;

	private int salary;

	public User() {
		super();
	}

	public User(int id, String name, String dob, int age, int salary) {
		super();
		this.id = id;
		this.name = name;
		this.dob = dob;
		this.age = age;
		this.salary = salary;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	@Override
	public String toString() {
		System.out.println("id");
		return id + "," + name + "," + dob + "," + age + "," + salary;
	}

}
