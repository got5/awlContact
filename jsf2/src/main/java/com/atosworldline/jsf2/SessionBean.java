package com.atosworldline.jsf2;

import java.io.IOException;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

@ManagedBean
@SessionScoped
public class SessionBean {
	
	private String rootUrl;

	public String getRootUrl() {
		if(null == rootUrl) return Context.PRIMEFACES.getLabel();
		return rootUrl;
	}

	public void setRootUrl(String rootUrl) throws IOException {
		this.rootUrl = rootUrl;
		redirect();
	}

	public void redirect() throws IOException {
		String path = FacesContext.getCurrentInstance().getViewRoot().getViewId();
		boolean isSubContext = path.split("/").length > 2 ? true : false; 
		String page = path.substring(path.lastIndexOf("/")+1);
		String redirect = 
				FacesContext.getCurrentInstance().getExternalContext().getContextName() + 
					(isSubContext ? "/" + this.rootUrl : "") + 
						"/" + page + "?faces-redirect=true";
		FacesUtil.addSuccessMessage(redirect);
		FacesContext.getCurrentInstance().getExternalContext().redirect(redirect);
	}
	
}
