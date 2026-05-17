package com.simplilearn.goibibo_selenium_demo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.simplilearn.goibibo_selenium_demo.utils.Log;

public class GoibiboHomePage extends BasePage {

    @FindBy(xpath = "//span[contains(@class,'icClose')] | //p[contains(@class,'icClose')] | //span[@class='logSprite icClose'] | //*[contains(@class, 'close')] | //*[contains(@class, 'Close')]")
    public WebElement closeLoginPopup;

    @FindBy(xpath = "//span[text()='From'] | //p[text()='From'] | //*[contains(text(), 'From')]")
    public WebElement fromCityDisplay;

    @FindBy(xpath = "//input[@type='text'] | //input[@placeholder='Enter city or airport']")
    public WebElement cityInput;

    @FindBy(xpath = "//span[text()='To'] | //p[text()='To'] | //*[contains(text(), 'To')]")
    public WebElement toCityDisplay;

    @FindBy(xpath = "//span[text()='Departure'] | //p[text()='Departure']")
    public WebElement departureDateDisplay;

    @FindBy(xpath = "//span[text()='Travellers & Class'] | //p[text()='Travellers & Class']")
    public WebElement travellersAndClassDisplay;

    @FindBy(xpath = "//span[text()='SEARCH FLIGHTS'] | //button[contains(text(),'SEARCH')]")
    public WebElement searchButton;

    public GoibiboHomePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void closeLoginPopupIfPresent() {
        Log.step("Checking for login popup...");
        try {
            // Using a bit longer wait for optional popup
            WebDriverWait shortWait = new WebDriverWait(driver, java.time.Duration.ofSeconds(5));
            shortWait.until(org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable(closeLoginPopup));
            if (closeLoginPopup.isDisplayed()) {
                safeClick(closeLoginPopup);
                Log.step("Closed login popup");
            }
        } catch (Exception e) {
            Log.step("Login popup not found or not clickable: " + e.getMessage());
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
