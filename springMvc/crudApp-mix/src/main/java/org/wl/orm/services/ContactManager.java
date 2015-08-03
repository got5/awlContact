package org.wl.orm.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.wl.orm.dao.ContactDaoImpl;
import org.wl.orm.entities.Contact;


@Service
public class ContactManager {
	
	@Autowired
	private ContactDaoImpl dao;
	
	public List<Contact> findAllContacts(){
		return dao.findAllContacts();
	}
	
	public Contact findById(Integer id){
		return dao.findById(id);
	}
	
	@Transactional
	public void persist(Contact contact){
		dao.persist(contact);
	}
	
	@Transactional
	public void merge(Contact contact){
		dao.merge(contact);
	}
	
	

}
