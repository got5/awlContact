package com.atosworldline.com.jsf2.test;

import java.net.URL;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.drone.api.annotation.Drone;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

@RunWith(Arquillian.class)
public class LoginScreenGrapheneTest {

	@Deployment(testable = false)
	public static WebArchive createDeployment() {
		return Deployments.getLoginScreenDeployment();
	}

	@Drone
	WebDriver browser;

	@ArquillianResource
	URL contextPath;

	@FindBy(id = "loginForm")
	LoginFragment loginForm;

	@Test
	@RunAsClient
	public void should_login_successfully() throws Exception {
		String page = contextPath + "Login.xhtml";

		browser.get(page);

		loginForm.setUsername("admin");
		loginForm.setPassword("secret");
		loginForm.click();

		Assert.assertTrue("User should be logged in!", browser.getPageSource()
				.contains("logout"));
	}

}