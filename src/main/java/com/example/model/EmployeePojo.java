package com.example.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "employees")
public class EmployeePojo {
	@Id
	//@GeneratedValue annotation is used to allow the persistence implementation to assign a unique value to your identity fields automatically.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
  long id;
	@Column(name = "first_name")
  String first_name;
	@Column(name = "last_name")
  String last_name;
	@Column(name = "email")
  String email;
  
public long getId() {
	return id;
}
public void setId(long id) {
	this.id = id;
}
public String getFirst_name() {
	return first_name;
}
public void setFirst_name(String first_name) {
	this.first_name = first_name;
}
public String getLast_name() {
	return last_name;
}
public void setLast_name(String last_name) {
	this.last_name = last_name;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
}
