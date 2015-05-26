package com.atosworldline.tapestry.pages;

import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.hibernate.Session;

import com.atosworldline.tapestry.entities.Contact;

public class EditContactPage {
	@Property
	private int activeId;
	
	void onActivate(int activationParam){
		activeId = activationParam;	
	}
	
	Object onPassivate(){
		if(activeId!=0)
		return activeId;
		else return null;
	}

}
