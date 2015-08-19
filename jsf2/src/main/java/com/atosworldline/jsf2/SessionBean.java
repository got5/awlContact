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
		if(null == rootUrl) return Context.PRIMEFACES.name();
		return rootUrl;
	}

	public void setRootUrl(String rootUrl) {
		this.rootUrl = rootUrl;
	}

	public String redirect() {
		String context = (String) FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("context");
		HttpServletRequest req = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
		String path = req.getServletPath();
		String page = path.substring(path.lastIndexOf("/")+1);
		String redirect = "/"+context+"/"+page+"?faces-redirect=true";
		FacesUtil.addSuccessMessage(redirect);
		setRootUrl(context);
		return redirect;
	}
	
}
