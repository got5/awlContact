package com.atosworldline.jsf2primefaces.controller.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.CellEditEvent;

import com.atosworldline.jsf2primefaces.FacesUtil;
import com.atosworldline.jsf2primefaces.model.Contact;
import com.atosworldline.jsf2primefaces.persistence.ContactDAO;

@Named
@RequestScoped
public class ContactMB implements Serializable{

	private static final long serialVersionUID = -1890125026548028469L;
	private Contact bean = new Contact();
	private Long idToDelete;
	private List<Contact> contacts = new ArrayList<Contact>();
	private String cid;
	
	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

	@Inject
	private ContactDAO contactDAO;
	
	public Contact getBean() {
		return bean;
	}

	public void setBean(Contact bean) {
		this.bean = bean;
	}

	public String save() {
		contactDAO.persist(this.bean);
		return "ListContactPage.xhtml?faces-redirect=true";
	}

	public String add() {
		return "EditContactPage.xhtml?faces-redirect=true";
	}
	
	public String edit() {
		String value = (String) FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id");
		return "EditContactPage.xhtml?faces-redirect=true&id="+value;
	}
	
	public String delete() {
		String value = (String) FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id");
		contactDAO.delete(Long.valueOf(value));
		FacesUtil.addSuccessMessage("Deleted!");
		return "ListContactPage.xhtml?faces-redirect=true";
	}

	public void remove() {
		contactDAO.delete(idToDelete);
		FacesUtil.addSuccessMessage("Deleted!");
	}
	
	public List<Contact> getContacts() {
		return contactDAO.findAll();
	}

	public void setContacts(List<Contact> contacts) {
		this.contacts = contacts;
	}

	public Long getIdToDelete() {
		return idToDelete;
	}

	public void setIdToDelete(Long idToDelete) {
		this.idToDelete = idToDelete;
	}
	
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
