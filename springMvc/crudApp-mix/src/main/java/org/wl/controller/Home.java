package org.wl.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.wl.constants.ModelAttrs;
import org.wl.constants.UrlAction;
import org.wl.constants.ViewData;
import org.wl.controller.util.Utils;
import org.wl.orm.entities.Contact;
import org.wl.orm.services.ContactManager;

@Controller
public class Home {
	
	@Autowired
	private MessageSource messageSource;
	
	@Autowired
	private Utils utils;
	
	@Autowired
	private ContactManager contactsManager;
	
	@ModelAttribute
	public void commonModelUpdate(Locale locale, Model model, HttpServletRequest request){
		//Add the menu, common to any page 
		model.addAttribute(
				ModelAttrs.MENU_LIST.toString(),  
				utils.getMenuList(locale)
		);
	}
	
	@RequestMapping(value={"/"})
	public String indexPage(
			Model model,
			@RequestParam(value="nextView", required=false) String nextView,
			@RequestParam(value="id", required=false) Integer contactId
	){
		
		if (nextView != null){
			
			String nextUrl= UrlAction.getForString(nextView).getUrl().substring(1);
			if (contactId != null){
				nextUrl+= "?id=" + contactId;
			}
			
			model.addAttribute(ModelAttrs.PARTIAL_TO_LOAD.toString(), nextUrl);
		}else{
			model.addAttribute(ModelAttrs.PARTIAL_TO_LOAD.toString(), UrlAction.home.getUrl().substring(1));
		}

		return ViewData.index.getViewName();
	}
	
	@RequestMapping(value={"/home"})
	public String welcomePage(Model model){
		return ViewData.home.getViewName();
	}	
	
	@RequestMapping( value="/contacts/{action}", method= RequestMethod.GET )
	public String contacts(
			@PathVariable String action, 
			@RequestParam(value="id", required=false) Integer contactId, 
			Model model)
	{
		
		String viewName= null;
		
		switch (action){
			case "list":
				model.addAttribute(
						ModelAttrs.CONTACT_LIST.toString(), 
						this.contactsManager.findAllContacts());
				viewName= ViewData.list_contacts.getViewName();
				break;
			case "new":
				//Edit contact
				if (contactId != null){
					model.addAttribute(ModelAttrs.CONTACT_EDIT.toString(), true);
					model.addAttribute(ModelAttrs.CONTACT_BEAN.toString(), this.contactsManager.findById(contactId));
				}
				//Create contact
				else{
					model.addAttribute(ModelAttrs.CONTACT_CREATE.toString(), true);
					model.addAttribute(ModelAttrs.CONTACT_BEAN.toString(), new Contact());
				}

				viewName= ViewData.new_contacts.getViewName();
				break;
			default:
				break;
		}
		
		return viewName;
	}
	
	
	//Manage form
	
	@InitBinder
	public void initBinder(WebDataBinder dataBinder){
		
		//Mandatory fields
		dataBinder.setRequiredFields("nom", "prenom");

		//Replace empty strings by null (conversion errors)
		//dataBinder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
		
		// Set the date format to "dd/MM/yyyy"
		dataBinder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("dd/MM/yyyy"), true));
	}
	
	
	
	
	@RequestMapping( value="/contacts/update", method= RequestMethod.POST )
	public String createOrUpdateContact(Model model, @ModelAttribute Contact contact, BindingResult result){
		
		if (result.hasErrors()){
			
			model.addAttribute(
					contact.getId()==null? ModelAttrs.CONTACT_CREATE.toString():ModelAttrs.CONTACT_EDIT.toString(), true);//TO CHANGE
			model.addAttribute(ModelAttrs.HAS_ERRORS.toString(), true );
			model.addAttribute(ModelAttrs.FIELD_ERRORS.toString(), result.getFieldErrors());
			model.addAttribute(ModelAttrs.CONTACT_BEAN.toString(), contact);

			return ViewData.new_contacts.getViewName();
		}
		
		System.out.println("contact retrieved: " + contact.getNom());
		
		if (contact.getId()==null){
			this.contactsManager.persist(contact);			
		}else{
			this.contactsManager.merge(contact);
		}
		
		return "redirect:/contacts/list";
	}


}
