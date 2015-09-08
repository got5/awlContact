package com.atosworldline.com.jsf2.test;

import java.io.File;
import java.util.Map.Entry;
import java.util.logging.Logger;

import net.bootsfaces.component.Alert;

import org.apache.ziplock.JarLocation;
import org.jboss.shrinkwrap.api.ArchivePath;
import org.jboss.shrinkwrap.api.Filters;
import org.jboss.shrinkwrap.api.GenericArchive;
import org.jboss.shrinkwrap.api.Node;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.importer.ExplodedImporter;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.primefaces.context.ApplicationContext;

import com.atosworldline.jsf2.FacesUtil;
import com.atosworldline.jsf2.bean.GenericDAO;
import com.atosworldline.jsf2.controller.view.ContactMB;
import com.atosworldline.jsf2.converters.SecuredObjectConverter;
import com.atosworldline.jsf2.filters.AjaxAwareUserFilter;
import com.atosworldline.jsf2.interceptors.ShiroSecuredInterceptor;
import com.atosworldline.jsf2.model.Contact;
import com.atosworldline.jsf2.persistence.ContactDAO;
import com.atosworldline.jsf2.qualifiers.ShiroSecured;

public class Deployments {
	public static final String WEBAPP_SRC = "src/main/webapp";
	public static final String ROOT_FOLDER= "com/atosworldline/jsf2";
	
	private static final Logger log = Logger.getLogger(Deployments.class.getName());
	
	public static WebArchive getLoginScreenDeployment() {
		
		WebArchive war = ShrinkWrap
				.create(WebArchive.class, "awlcontact.war")

				.addAsWebInfResource(new File("src/test/resources/web.xml"))
				.addAsWebInfResource(
						new File(WEBAPP_SRC + "/WEB-INF/shiro.ini"))
				.addAsWebInfResource(
						new File(WEBAPP_SRC + "/WEB-INF/faces-config.xml"))

				.addAsResource("META-INF/beans.xml")
				.addAsResource("META-INF/persistence.xml")
				.addAsResource(ROOT_FOLDER+"/messages.properties")
				.addAsResource(ROOT_FOLDER+"/messages_en.properties")
				.addAsResource(ROOT_FOLDER+"/messages_fr.properties")

				// project classes
				.addPackage(FacesUtil.class.getPackage())
				.addPackage(GenericDAO.class.getPackage())
				.addPackage(ContactMB.class.getPackage())
				.addPackage(SecuredObjectConverter.class.getPackage())
				.addPackage(AjaxAwareUserFilter.class.getPackage())
				.addPackage(ShiroSecuredInterceptor.class.getPackage())
				.addPackage(Contact.class.getPackage())
				.addPackage(ContactDAO.class.getPackage())
				.addPackage(ShiroSecured.class.getPackage())

				// WEB-INF libs

				// jstl jar
				.addAsLibraries(
						JarLocation
								.jarLocation(javax.servlet.jsp.jstl.core.Config.class))
				// shiro-core jar
				.addAsLibraries(
						JarLocation
								.jarLocation(org.apache.shiro.SecurityUtils.class))
				// shiro-web jar
				.addAsLibraries(
						JarLocation
								.jarLocation(org.apache.shiro.web.filter.authc.PassThruAuthenticationFilter.class))
				// primefaces jar
				.addAsLibraries(
						JarLocation.jarLocation(ApplicationContext.class))
				// bootfaces jar
				.addAsLibraries(JarLocation.jarLocation(Alert.class))
				// hsqldb jar
				.addAsLibraries(
						JarLocation.jarLocation(org.hsqldb.jdbcDriver.class))
				// commons-beanutils jar
				.addAsLibraries(
						JarLocation
								.jarLocation(org.apache.commons.beanutils.PropertyUtils.class));

		// add pages, css and img
		war.merge(
				ShrinkWrap.create(GenericArchive.class)
						.as(ExplodedImporter.class).importDirectory(WEBAPP_SRC)
						.as(GenericArchive.class), "/",
				Filters.include("^.*\\.(xhtml|css|gif|png|js)$"));

		
		for (Entry<ArchivePath, Node> element : war.getContent().entrySet()) {
			log.fine(element.getValue().toString());
		}

		return war;
	}

}
