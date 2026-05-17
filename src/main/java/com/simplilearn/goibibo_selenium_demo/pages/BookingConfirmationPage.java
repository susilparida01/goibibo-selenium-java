package com.simplilearn.goibibo_selenium_demo.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.simplilearn.goibibo_selenium_demo.utils.Log;

public class BookingConfirmationPage extends BasePage {

    @FindBy(xpath = "//h2[contains(text(), 'Confirmed')]")
    public WebElement confirmationMessage;

    public BookingConfirmationPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public String getConfirmationMessage() {
        Log.step("Retrieving confirmation message");
        return getText(confirmationMessage);
    }
}
