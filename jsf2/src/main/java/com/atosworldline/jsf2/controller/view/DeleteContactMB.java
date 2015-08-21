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
@ShiroSecured
@RequiresAuthentication
public class DeleteContactMB implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject  
	private transient ContactDAO contactDAO;
	
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	@RequiresPermissions("remove")
	public void remove(){
		if(id != null){
			contactDAO.delete(id);
		}
	}
	
}
