package com.atosworldline.jsf2;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.inject.Inject;


@ManagedBean
@ApplicationScoped
public class FacesUtil {
	
	private Context primefacesContext = Context.PRIMEFACES;
	private Context richfacesContext = Context.RICHFACES;
	private Context bootsfacesContext = Context.BOOTSFACES;

	public Context getPrimeFacesContext() {
        return primefacesContext;
    }
    
	public Context getRichFacesContext() {
        return richfacesContext;
    }
    
	public Context getBootsFacesContext() {
        return bootsfacesContext;
    }
    
    public static void addError(String message) {
		addError(message, null);
	}

	public static void addSuccessMessage(String message) {
		addSuccessMessage(message, null);
	}

	public static void addSuccessMessage(String message, String compId) {
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,
				message, message);
		FacesContext.getCurrentInstance().addMessage(compId, msg);
	}

	
	public static void addError(String message, String compId) {
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
				message, message);

		FacesContext.getCurrentInstance().addMessage(compId, msg);
	}
	
	
}
