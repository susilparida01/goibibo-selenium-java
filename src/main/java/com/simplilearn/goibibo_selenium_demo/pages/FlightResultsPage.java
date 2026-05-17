package com.simplilearn.goibibo_selenium_demo.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.List;
import com.simplilearn.goibibo_selenium_demo.utils.Log;

public class FlightResultsPage extends BasePage {

    @FindBy(xpath = "//button[contains(text(),'VIEW FARES')]")
    public List<WebElement> viewFaresButtons;

    @FindBy(xpath = "//input[@value='BOOK']")
    public List<WebElement> bookButtons;

    public FlightResultsPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void selectFirstFlight() {
        Log.step("Selecting the first available flight");
        if (!viewFaresButtons.isEmpty()) {
            safeClick(viewFaresButtons.get(0));
        }
        waitForVisibility(bookButtons.get(0));
        if (!bookButtons.isEmpty()) {
            safeClick(bookButtons.get(0));
        }
    }
}
