package com.atosworldline.jsf2.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity  
@Table(name="tb_contact")  
public class Contact implements Serializable{
	
	@GeneratedValue
	@Id
	private Long id;
	private String name;
	private String lastName;
	private Date birthDay;
	private String email;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getBirthDay() {
		return birthDay;
	}

	public void setBirthDay(Date birthDay) {
		this.birthDay = birthDay;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String toString() {
		return this.id + " " + this.name + " " + this.lastName + this.birthDay + " " + this.email;
	}
	
	public int compareTo(Contact contact) {

		if (this.getName().equals(contact.getName())) {
			return this.getLastName().compareTo(contact.getLastName());
		}

		return this.getName().compareTo(contact.getName());
	}
}
