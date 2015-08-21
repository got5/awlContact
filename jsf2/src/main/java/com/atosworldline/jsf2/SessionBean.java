package com.atosworldline.jsf2;

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

	public void setRootUrl(String rootUrl) {
		this.rootUrl = rootUrl;
		redirect();
	}

	public String redirect() {
		String path = FacesContext.getCurrentInstance().getViewRoot().getViewId();
		String page = path.substring(path.lastIndexOf("/")+1);
		String redirect = "/"+this.rootUrl+"/"+page+"?faces-redirect=true";
		FacesUtil.addSuccessMessage(redirect);
		return redirect;
	}
	
}
