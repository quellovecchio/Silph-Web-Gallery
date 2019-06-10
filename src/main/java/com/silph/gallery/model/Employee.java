package com.silph.gallery.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@Column
	private String name;
	@Column
	private String surname;
	@Column
	private String email;
	@Column
	private String pwd;
	@Column
	private String role;
	
	public Employee() {
		name = "foo";
		surname = "bar";
		email = "foo@bar.it";
		pwd = "foobar";
		role = "EMPLOYEE";
	}
	
	public Employee(String name, String surname, String email, String password, String role) {
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.pwd = password;
		this.role = role;
	}

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	
	private String getRole() {
		return role;
	}

	private void setRole(String role) {
		this.role = role;
	}

	
	public boolean isPasswordCorrect(String insertedPsw) {
		if (pwd.equals(insertedPsw))
			return true;
		return false;
	}
	
}
