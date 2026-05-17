package com.simplilearn.goibibo_selenium_demo.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.simplilearn.goibibo_selenium_demo.utils.Log;

public class PaymentPage extends BasePage {

    @FindBy(id = "card_number")
    public WebElement cardNumber;

    @FindBy(xpath = "//button[text()='PAY NOW']")
    public WebElement payNowButton;

    public PaymentPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void processPayment(String card) {
        Log.step("Processing payment with card ending in: " + card.substring(card.length()-4));
        safeType(cardNumber, card);
        safeClick(payNowButton);
    }
}
