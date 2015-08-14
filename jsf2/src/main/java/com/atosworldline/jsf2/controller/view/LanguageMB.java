package com.atosworldline.jsf2.controller.view;

import java.util.Locale;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean(name = "localeBean")
@SessionScoped
public class LanguageMB {

    private Locale locale;

    @PostConstruct
    public void init() {
        locale = FacesContext.getCurrentInstance().getExternalContext().getRequestLocale();
        setDefaultLocale();
    }

    public Locale getLocale() {
    	setDefaultLocale();
    	return locale;
    }

    private void setDefaultLocale() {
    	if (locale == null) locale = Locale.FRENCH;
	}

	public String getLanguage() {
		setDefaultLocale();
    	return locale.getLanguage();
    }

    public void setLanguage(String language) {
        locale = new Locale(language);
        FacesContext.getCurrentInstance().getViewRoot().setLocale(locale);
    }

}