package com.atosworldline.jsf2.controller.view;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;

import com.atosworldline.jsf2.persistence.ContactDAO;
import com.atosworldline.jsf2.qualifiers.ShiroSecured;

@Named
@SessionScoped
public class DeleteContactMB implements Serializable {

	private static final long serialVersionUID = 1L;

	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
}
