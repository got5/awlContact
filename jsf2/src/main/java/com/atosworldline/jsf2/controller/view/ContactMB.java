package com.atosworldline.jsf2.controller.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.owasp.esapi.errors.AccessControlException;
import org.primefaces.event.CellEditEvent;

import com.atosworldline.jsf2.FacesUtil;
import com.atosworldline.jsf2.ReferenceUtil;
import com.atosworldline.jsf2.model.Contact;
import com.atosworldline.jsf2.persistence.ContactDAO;
import com.atosworldline.jsf2.qualifiers.ShiroSecured;

@Named
@RequestScoped
@ShiroSecured
@RequiresAuthentication
public class ContactMB implements Serializable{

	@Inject
	private ReferenceUtil referenceUtil;
	
	private static final long serialVersionUID = -1890125026548028469L;
	
	private Contact bean;
	
	private List<Contact> contacts;
	
	@Inject
	private ContactDAO contactDAO;
	
	
	@PostConstruct
	private void init(){
		bean = new Contact();
		contacts = new ArrayList<Contact>();
	}
	
	
	@RequiresPermissions("edit")
	public String save() throws AccessControlException {
		contactDAO.persist(this.bean);
		referenceUtil.addIdToIndirectReference(this.bean.getId());
		return "ListContactPage.xhtml?faces-redirect=true";
	}

	@RequiresPermissions("add")
	public String add() {
		return "EditContactPage.xhtml?faces-redirect=true";
	}
	
	@RequiresPermissions("edit")
	public String edit() {
		String value = (String) FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id");
		return edit(value);
	}
	
	@RequiresPermissions("edit")
	public String edit(String id) {
		return "EditContactPage.xhtml?faces-redirect=true&id="+id;
	}
	
	@RequiresPermissions("remove")
	public void remove(String idToDelete) throws AccessControlException {
		Long id = referenceUtil.getIdByIndirectReference(idToDelete);
		contactDAO.delete(id);
		referenceUtil.removeDirectReference(id);
		FacesUtil.addSuccessMessage("Deleted!");
	}
	
	@RequiresPermissions("list")
	public List<Contact> getContacts() {
		return contactDAO.findAll();
	}

	public void setContacts(List<Contact> contacts) {
		this.contacts = contacts;
	}

	@RequiresPermissions("edit")
	public void onCellEdit(CellEditEvent event) {
        Object oldValue = event.getOldValue();
        Object newValue = event.getNewValue();
         
        if(newValue != null && !newValue.equals(oldValue)) {
        	FacesContext context = FacesContext.getCurrentInstance();
            Contact entity = context.getApplication().evaluateExpressionGet(context, "#{contact}", Contact.class);
            
        	contactDAO.persist(entity);
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cell Changed", "Old: " + oldValue + ", New:" + newValue);
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }
}
