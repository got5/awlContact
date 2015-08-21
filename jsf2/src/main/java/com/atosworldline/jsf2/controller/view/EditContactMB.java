package com.atosworldline.jsf2.controller.view;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;

import com.atosworldline.jsf2.model.Contact;
import com.atosworldline.jsf2.persistence.ContactDAO;
import com.atosworldline.jsf2.qualifiers.ShiroSecured;

@Named
@RequestScoped
@ShiroSecured
public class EditContactMB implements Serializable{
	
	private static final long serialVersionUID = 5870423468328215602L;
	
	@Inject
	private ContactDAO contactDAO;
	private Contact bean;
	private String id;
	
	@PostConstruct
	public void loadBean() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		id = (String) facesContext.getExternalContext().getRequestParameterMap().get("id");
		try{
			long longId = Long.valueOf(id);
			bean = contactDAO.load(longId);
		}catch(NumberFormatException nfe){
			//id is never null but can be "null" (String) ! 
			bean = new Contact();
		}
		setBean(bean);
	}

	public Contact getBean() {
		return bean;
	}

	public void setBean(Contact bean) {
		this.bean = bean;
	}
	
	@RequiresPermissions("edit")
	public String save() {
		if(id != null){
			// update the managed bean
			contactDAO.persist(getBean());
		} else {
			//create or update
			contactDAO.update(getBean());
		}
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Edited!", "JSF2 rocks!"));
		return "ListContactPage.xhtml?faces-redirect=true";
	}
	
	public String cancel() {
		return "ListContactPage.xhtml?faces-redirect=true";
	}
	
}
