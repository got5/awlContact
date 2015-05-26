package com.atosworldline.tapestry.components;

import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.hibernate.Session;

import com.atosworldline.tapestry.entities.Contact;
import com.atosworldline.tapestry.pages.ListContactPage;

public class EditContact {
	
	@Property
	private Contact edit;
	
	void onPrepareFromEditForm(){
		if(activeId==0)
			edit = new Contact();
		else
			edit = (Contact)session.load(Contact.class, activeId);
	}
	
	@Component(id = "editForm")
	private Form form;
	
	void onValidateFromEditForm() {
		if(edit.getEmail().contains("mailinator.com"))
		form.recordError("mailinator.com n'est pas un nom de domain valide");
		
	}
	
	@Inject
	private Session session;
	
	@Inject
	private ComponentResources resources;
	
	@CommitAfter
	Object onSuccessFromEditForm(){
		if(activeId!=0) edit.setId(activeId);
		session.persist(edit);
		return ListContactPage.class;
	}
	
	
	Object onActionFromCancel(){
		resources.discardPersistentFieldChanges();
		return null;
	}
	
	@Parameter
	private int activeId;

}
