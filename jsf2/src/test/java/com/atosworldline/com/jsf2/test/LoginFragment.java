package com.atosworldline.com.jsf2.test;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginFragment {
	
	@FindBy(id="loginForm:username")
    private WebElement usernameInput;

    @FindBy(id="loginForm:password")
    private WebElement passwordInput;

    @FindBy(id="loginForm:login")
    private WebElement loginButton;

    public void setUsername(Object username) {
        usernameInput.sendKeys(username.toString());
    }

    public void setPassword(Object password) {
        passwordInput.sendKeys(password.toString());
    }

    public void click() {
        loginButton.click();
    }
}
