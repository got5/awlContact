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
import org.owasp.esapi.errors.AccessControlException;

import com.atosworldline.jsf2.ReferenceUtil;
import com.atosworldline.jsf2.model.Contact;
import com.atosworldline.jsf2.persistence.ContactDAO;
import com.atosworldline.jsf2.qualifiers.ShiroSecured;

@Named
@RequestScoped
@ShiroSecured
@RequiresAuthentication
public class EditContactMB implements Serializable{
	
	private static final long serialVersionUID = 5870423468328215602L;
	
	@Inject
	private ContactDAO contactDAO;
	
	@Inject 
	private ReferenceUtil referenceUtil;
	
	private Contact bean;
	
	private String id;
	
	@PostConstruct
	public void loadBean() throws AccessControlException {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		id = (String) facesContext.getExternalContext().getRequestParameterMap().get("id");
		if(isEmpty(id)) {
			bean = new Contact();
		}else{
			Long longId = referenceUtil.getIdByIndirectReference(id);  
			bean = contactDAO.load(longId);
		}
		setBean(bean);
	}

	public Contact getBean() {
		return bean;
	}

	public void setBean(Contact bean) {
		this.bean = bean;
	}
	
	public String getId() throws AccessControlException{
		return referenceUtil.getIndirectReference(this.bean.getId());
	}
	
	public void setId(String id){
		this.id = id;
	}
	
	@RequiresPermissions("edit")
	public String save() throws AccessControlException {
		if(id != null){
			// update the managed bean
			contactDAO.persist(getBean());
		} else {
			//create or update
			contactDAO.update(getBean());			
		}
		referenceUtil.addIdToIndirectReference(getBean().getId());
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Edited!", "JSF2 rocks!"));
		return "ListContactPage.xhtml?faces-redirect=true";
	}
	
	public String cancel() {
		return "ListContactPage.xhtml?faces-redirect=true";
	}
	
	private boolean isEmpty(String id2) {
		return null == id || id.length() == 0 || id.equalsIgnoreCase("null");
	}
}
