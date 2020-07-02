package com.example.FirstAngularProject.model;

import javax.persistence.Entity;

@Entity
public class User {
	
	private Long userId;
	private String firstname;
	private String lastname;
	private String username;
	private int age;
	private int salary;
	
	public User() {
		
	}
	
	public User(Long id, String f, String l, String u, int a, int s) {
        this.userId = id;
        this.firstname=f;
        this.lastname =l;
        this.username=u;
        this.age=a;
        this.salary=s;
	}


	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}


	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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

}
