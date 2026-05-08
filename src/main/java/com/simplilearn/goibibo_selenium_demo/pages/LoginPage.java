package com.simplilearn.goibibo_selenium_demo.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.simplilearn.goibibo_selenium_demo.utils.Log;

public class LoginPage extends BasePage {
	
	/* login Page Locators */	
	@FindBy(name = "username")
	public WebElement login_useraname;
	
	@FindBy(name = "password")
	public WebElement login_password;
	
	@FindBy(tagName = "button")
	public WebElement login_button;
	
	@FindBy(xpath = "//div[@role=\"alert\"]/div[1]/p")
	public WebElement login_invalid_credentials_label;
	
	
	public LoginPage(WebDriver driver) {
		super(driver);		
		PageFactory.initElements(driver, this);		
	}
	
	
	// Page actions using BasePage method
	public void enterUsername(String username) {
		Log.step("Entering username");
		safeType(login_useraname,username );
	}
	
	public void enterPassword(String password) {
		Log.step("Entering password");
		safeType(login_password, password );
	}
	
	public void clickLogin() {
		Log.step("Clicking login button");
		safeClick(login_button);
	}
	
	public DashboardPage loginValidUser(WebDriver driver, String user, String pass) {
		Log.step("Login Flow with valid user");
		enterUsername(user);
		enterPassword(pass);
		clickLogin();
		waitForUrlContains("/dashboard");
		return new DashboardPage(driver);
	}
	
	
	public String loginInvalidUser(WebDriver driver, String user, String pass) {
		Log.step("Login Flow with invalid user");
		enterUsername(user);
		enterPassword(pass);
		clickLogin();
		waitForVisibility(login_invalid_credentials_label);
		return getText(login_invalid_credentials_label);
		
	}
	
	

}
