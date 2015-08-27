package com.atosworldline.jsf2;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.owasp.esapi.errors.AccessControlException;
import org.owasp.esapi.reference.RandomAccessReferenceMap;

import com.atosworldline.jsf2.model.Contact;
import com.atosworldline.jsf2.persistence.ContactDAO;

/**
 * This class takes care of the mapping between an indirect object reference (random String) and the concrete db id object.
 *
 */
@Named
@ApplicationScoped
public class ReferenceUtil {
	
	@Inject
	private ContactDAO contactDAO;
	
	private static final Logger LOGGER = Logger.getLogger(ReferenceUtil.class.getCanonicalName());
	    
	private static Set<Object> references = new HashSet<Object>();
	
	private static final RandomAccessReferenceMap REFERENCE_MAP = new RandomAccessReferenceMap(references);
	
	@PostConstruct
	public void loadData (){
		List<Contact> contacts = contactDAO.findAll();
		for (Contact contact : contacts) {
			REFERENCE_MAP.addDirectReference(contact.getId());
			references.add(contact.getId());
		}
	}
	
	public Set<String> getAllIndirectReferences() {
		Set<String> indirectReferences = new HashSet<String>();
		for (Object id : references ) {
			String indirectReference = REFERENCE_MAP.getIndirectReference(id);
			indirectReferences.add(indirectReference);
		}
		return indirectReferences;
	}
	
	public void addIdToIndirectReference(Long id) throws AccessControlException {
		LOGGER.fine("add id into REFERENCE_MAP : "+id);
		REFERENCE_MAP.addDirectReference(id);
		references.add(id);
		LOGGER.fine("id "+ id +" added!");
		LOGGER.fine("Concret id added  : "+ id);
	}
	
	public void removeDirectReference(Long id) throws AccessControlException {
		LOGGER.fine("remove id into REFERENCE_MAP : "+id);
		REFERENCE_MAP.removeDirectReference(id);
		references.remove(id);
		LOGGER.fine("id "+id+" removed!");
		LOGGER.fine("Concret id removed : "+ id);
	}
	
	public Long getIdByIndirectReference(String indirectReference) throws AccessControlException {
		Long id = (Long)REFERENCE_MAP.getDirectReference(indirectReference);
		LOGGER.fine("Concret id : "+id);
		return id;
	}
	
	public String getIndirectReference(Long id) throws AccessControlException {
		if(null == id) return null;
		String indirectRef = (String)REFERENCE_MAP.getIndirectReference(id);
		LOGGER.fine("Indirect Ref   : "+indirectRef);
		return indirectRef;
	}
	
}