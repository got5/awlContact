package org.wl.orm.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="CONTACT")
public class Contact implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 8685005015455978726L;
	@Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;
	
    public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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
	
	public void setNom(String nom) {
		this.nom = nom;
	}

	
	public String getPrenom() {
		return prenom;
	}

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

//	public int compareTo(Contact contact) {
//
//		if (this.getNom().equals(contact.getNom())) {
//			return this.getPrenom().compareTo(contact.getPrenom());
//		}
//
//		return this.getNom().compareTo(contact.getNom());
//	}

}
