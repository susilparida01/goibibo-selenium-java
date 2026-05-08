package com.simplilearn.goibibo_selenium_demo.testcases;


import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.simplilearn.goibibo_selenium_demo.factory.DriverFactory;
import com.simplilearn.goibibo_selenium_demo.utils.ConfigReader;

public class BaseTest {
	
	protected DriverFactory driverFactory;
	protected WebDriver driver;
	protected Properties prop;
	
	@BeforeMethod(alwaysRun = true)
	@Parameters({"browser", "headless"})
	public void setUp(@Optional String browser, @Optional String headless) {
		
		ConfigReader config = new ConfigReader();
		prop = config.initProp();
		
		String br = (browser != null && !browser.isEmpty() ? browser : prop.getProperty("browser", "chrome"));
		boolean isHeadless = (headless != null)
				? Boolean.parseBoolean(headless)
				: Boolean.parseBoolean(prop.getProperty("headless", "false"));
		
		driverFactory = new DriverFactory();
		driver = driverFactory.initDriver(br, isHeadless);
		driver.get(prop.getProperty("baseUrl"));		
		
	}
	
	@AfterMethod(alwaysRun = true)
	public void tearDown() {		
		if (driverFactory != null) driverFactory.quitDriver();
	}

}