package org.wl.controller.util;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.wl.beans.LinkDesc;
import org.wl.constants.UrlAction;
import org.wl.constants.ViewData;


@Service("beanUtils")
@Scope(value=ConfigurableBeanFactory.SCOPE_SINGLETON)
public class Utils {
	
	@Autowired
	private MessageSource messageSource;
	
	public LinkDesc[] getMenuList(Locale locale){
		return new LinkDesc[]{
			new LinkDesc( ViewData.home,  messageSource.getMessage("menu.home", null, locale)),
			new LinkDesc( ViewData.list_contacts,  messageSource.getMessage("menu.contacts.list", null, locale)),
			new LinkDesc( ViewData.new_contacts, messageSource.getMessage("menu.contacts.new", null, locale))
		};
	}
	
	
	
	

}
