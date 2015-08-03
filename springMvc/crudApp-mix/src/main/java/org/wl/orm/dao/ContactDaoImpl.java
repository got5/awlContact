package org.wl.orm.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;
import org.wl.orm.entities.Contact;


@Repository
public class ContactDaoImpl {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	public List<Contact> findAllContacts(){
		
		CriteriaBuilder cb= this.entityManager.getCriteriaBuilder();
		CriteriaQuery<Contact> cq= cb.createQuery(Contact.class);
		Root<Contact> range= cq.from(Contact.class);
		cq.select(range);

		return entityManager.createQuery(cq).getResultList();
	}
	
	public Contact findById(Integer id){
		return this.entityManager.find(Contact.class, id);
	}
	
	//@Transactional
	public void persist(Contact contact){
		this.entityManager.persist(contact);
	}
	
	//@Transactional
	public void merge(Contact contact){
		this.entityManager.merge(contact);
	}	

}


//package com.byteslounge.spring.tx.dao.impl;
//
//import java.util.List;
//
//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
//import javax.persistence.criteria.CriteriaBuilder;
//import javax.persistence.criteria.CriteriaQuery;
//import javax.persistence.criteria.Root;
//
//import org.springframework.stereotype.Repository;
//
//import com.byteslounge.spring.tx.dao.UserDAO;
//import com.byteslounge.spring.tx.model.User;
//
//@Repository
//public class UserDAOImpl implements UserDAO {
//
//  @PersistenceContext
//  private EntityManager entityManager;
//
//  @Override
//  public void insertUser(User user) {
//    entityManager.persist(user);
//  }
//
//  @Override
//  public List<User> findAllUsers() {
//    CriteriaBuilder builder = entityManager.getCriteriaBuilder();
//    CriteriaQuery<User> cq = builder.createQuery(User.class);
//    Root<User> root = cq.from(User.class);
//    cq.select(root);
//    return entityManager.createQuery(cq).getResultList();
//  }
//
//}
