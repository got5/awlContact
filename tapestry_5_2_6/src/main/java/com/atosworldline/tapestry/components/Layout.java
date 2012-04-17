package com.atosworldline.tapestry.components;

import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.ioc.annotations.Inject;

public class Layout {
	
	@Inject
	private ComponentResources resources;
	
	public boolean isDisabled(String pageName){
		return resources.getPageName().equalsIgnoreCase(pageName);
	}

}
