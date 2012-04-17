package com.atosworldline.tapestry.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.apache.tapestry5.beaneditor.Validate;

@Entity
public class Contact implements Serializable, Comparable<Contact> {

    /**
	 * 
	 */
	private static final long serialVersionUID = 8685005015455978726L;
	@Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
	
    public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	private String nom;
	private String prenom;
	private Date dateNaissance;
	private String email;

    public Contact() {
    }

    
	public String getNom() {
		return nom;
	}
	
	@Validate("required")
	public void setNom(String nom) {
		this.nom = nom;
	}

	
	public String getPrenom() {
		return prenom;
	}

	@Validate("required")
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public Date getDateNaissance() {
		return dateNaissance;
	}

	public void setDateNaissance(Date dateNaissance) {
		this.dateNaissance = dateNaissance;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int compareTo(Contact contact) {

		if (this.getNom().equals(contact.getNom())) {
			return this.getPrenom().compareTo(contact.getPrenom());
		}

		return this.getNom().compareTo(contact.getNom());
	}

}
