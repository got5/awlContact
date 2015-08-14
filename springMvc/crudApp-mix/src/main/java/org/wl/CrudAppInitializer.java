package org.wl;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.servlet.DispatcherServlet;


public class CrudAppInitializer implements WebApplicationInitializer{

	@Override
	public void onStartup(ServletContext servletContext)
			throws ServletException {
		
		//The root context
		servletContext.addListener(org.springframework.web.context.ContextLoaderListener.class);
		servletContext.setInitParameter("contextConfigLocation", "/WEB-INF/root-context.xml");
		
		//The dispacther servlet(s)
        ServletRegistration.Dynamic registration = servletContext.addServlet("crudAppServlet", new DispatcherServlet());
        registration.setLoadOnStartup(1);
        registration.addMapping("/");
	}

}
