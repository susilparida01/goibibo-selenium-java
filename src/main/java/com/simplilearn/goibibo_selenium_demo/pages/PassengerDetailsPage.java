package com.simplilearn.goibibo_selenium_demo.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.simplilearn.goibibo_selenium_demo.utils.Log;

public class PassengerDetailsPage extends BasePage {

    @FindBy(name = "firstname")
    public WebElement firstName;

    @FindBy(name = "lastname")
    public WebElement lastName;

    @FindBy(xpath = "//button[text()='PROCEED']")
    public WebElement proceedButton;

    public PassengerDetailsPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void fillDetails(String fname, String lname) {
        Log.step("Filling passenger details: " + fname + " " + lname);
        safeType(firstName, fname);
        safeType(lastName, lname);
        safeClick(proceedButton);
    }
}
