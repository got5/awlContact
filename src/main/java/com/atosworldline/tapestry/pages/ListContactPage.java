package com.atosworldline.tapestry.pages;

import java.util.List;

import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.annotations.OnEvent;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SetupRender;
import org.apache.tapestry5.beaneditor.BeanModel;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.BeanModelSource;
import org.got5.tapestry5.jquery.JQueryEventConstants;
import org.got5.tapestry5.jquery.components.InPlaceEditor;
import org.hibernate.Session;
import org.hibernate.criterion.Order;

import com.atosworldline.tapestry.entities.Contact;

public class ListContactPage {

	@Property
	private Contact item;
	
	@Inject
	private Session session;
	
	public List<Contact> getList(){
		return session.createCriteria(Contact.class).addOrder(Order.desc("nom")).list();
	}
	
	@Inject
	private BeanModelSource beanModelSource;	
	
	@Inject
	private ComponentResources resources;
	
	@Property
	static private BeanModel contactGrid;

	@SetupRender
	void SetupRender(){
		if(contactGrid==null){
			contactGrid=beanModelSource.createDisplayModel(Contact.class,resources.getMessages());
			contactGrid.add("icon",null).label("");
			contactGrid.add("edit",null).label("");
			contactGrid.include("icon","prenom","nom","edit");
		}
	
	}
	
	@Property
	private Contact edit;
	
	void onPrepareFromEditForm(){
		edit = new Contact();
	}
	
	@CommitAfter
	Object onSuccessFromEditForm(){
		session.persist(edit);
		return ListContactPage.class;
	}
	
	@Property
	private int activeId;
	
	@OnEvent(component="DropZone",value = JQueryEventConstants.DROP)
	Object onDropFromDropZone(int id){
		activeId=id;
		return null;
	}
	
	@CommitAfter
	@OnEvent(component="inPlaceEditorForNom",value= InPlaceEditor.SAVE_EVENT)
	Object onSaveFrominPlaceEditorForNom(int id,String value){
		
		Contact toSave = (Contact)session.load(Contact.class,id);
		toSave.setNom(value);
		session.persist(toSave);
		return null;
	}
	
	@CommitAfter
	@OnEvent(component="inPlaceEditorForPrenom",value= InPlaceEditor.SAVE_EVENT)
	Object onSaveFrominPlaceEditorForPrenom(int id,String value){
		
		Contact toSave = (Contact)session.load(Contact.class,id);
		toSave.setPrenom(value);
		session.persist(toSave);
		return null;
	}
}
