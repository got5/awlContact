package com.atosworldline.jsf2.controller.view;

import java.util.logging.Logger;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.WebUtils;
 
/**
 * Simple JSF Controller demonstrating Shiro login/logout process.
 *
 * @author Glen Smith
 */
@Named
@RequestScoped
public class LoginMB {
 
    String username;
    String password;
    boolean rememberMe = false;
 
    private static final Logger log = Logger.getLogger(LoginMB.class
            .getName());
 
    public String authenticate() {
 
        // Example using most common scenario of username/password pair:
        UsernamePasswordToken token = new UsernamePasswordToken(username,
                password);
 
        // "Remember Me" built-in:
        token.setRememberMe(rememberMe);
 
        Subject currentUser = SecurityUtils.getSubject();
 
        log.info("Submitting login with username of " + username
                + " and password of " + password);
         
        try {
            currentUser.login(token);
            FacesContext fc = FacesContext.getCurrentInstance();
            fc.responseComplete();
            HttpServletRequest request = (HttpServletRequest) fc.getExternalContext().getRequest();
            HttpServletResponse response = (HttpServletResponse) fc.getExternalContext().getResponse();
            String fallbackUrl = "/WelcomePage.xhtml";
            WebUtils.redirectToSavedRequest(request, response, fallbackUrl);
            return null;
        } catch (Exception e) {
            // Could catch a subclass of AuthenticationException if you like
            log.warning(e.getMessage());
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage("Login Failed: " + e.getMessage(), e
                            .toString()));
            return "/Login.xhtml";
        }
        
 
    }
 
    public String logout() {
 
        Subject currentUser = SecurityUtils.getSubject();
        try {
            currentUser.logout();
        } catch (Exception e) {
            log.warning(e.toString());
        }
        return "/Login.xhtml?faces-redirect=true";
    }
 
    public String getUsername() {
        return username;
    }
 
    public void setUsername(String username) {
    	this.username = username;
    }
 
    public String getPassword() {
        return password;
    }
 
    public void setPassword(String password) {
    	this.password = password;
    }
 
    public boolean getRememberMe() {
        return rememberMe;
    }
 
    public void setRememberMe(boolean rememberMe) {
        this.rememberMe = rememberMe;
    }
 
}
