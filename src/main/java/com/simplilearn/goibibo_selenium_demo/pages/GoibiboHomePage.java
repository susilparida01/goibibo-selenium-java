package com.simplilearn.goibibo_selenium_demo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.simplilearn.goibibo_selenium_demo.utils.Log;

public class GoibiboHomePage extends BasePage {

    @FindBy(xpath = "//span[@class='logSprite icClose']")
    public WebElement closeLoginPopup;

    @FindBy(xpath = "//span[text()='From']/following-sibling::p")
    public WebElement fromCityDisplay;

    @FindBy(xpath = "//input[@type='text']")
    public WebElement cityInput;

    @FindBy(xpath = "//span[text()='To']/following-sibling::p")
    public WebElement toCityDisplay;

    @FindBy(xpath = "//span[text()='Departure']/following-sibling::p")
    public WebElement departureDateDisplay;

    @FindBy(xpath = "//span[text()='Travellers & Class']/following-sibling::p")
    public WebElement travellersAndClassDisplay;

    @FindBy(xpath = "//span[text()='SEARCH FLIGHTS']")
    public WebElement searchButton;

    public GoibiboHomePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void closeLoginPopupIfPresent() {
        try {
            waitForVisibility(closeLoginPopup);
            if (isDisplayed(closeLoginPopup)) {
                safeClick(closeLoginPopup);
                Log.step("Closed login popup");
            }
        } catch (Exception e) {
            Log.step("Login popup not found or already closed");
        }
    }

    public void searchFlights(String from, String to) {
        Log.step("Searching flights from " + from + " to " + to);
        
        safeClick(fromCityDisplay);
        safeType(cityInput, from);
        // Wait for suggestion and click the first one
        By firstSuggestion = By.xpath("//ul[@id='react-autowhatever-1']//li[1]");
        wait.until(org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable(firstSuggestion));
        driver.findElement(firstSuggestion).click();

        safeClick(toCityDisplay);
        safeType(cityInput, to);
        wait.until(org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable(firstSuggestion));
        driver.findElement(firstSuggestion).click();

        // For this demo, we'll use the default date and passengers
        safeClick(searchButton);
    }
}
