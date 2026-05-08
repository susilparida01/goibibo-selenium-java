package com.simplilearn.goibibo_selenium_demo.testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.simplilearn.goibibo_selenium_demo.pages.DashboardPage;
import com.simplilearn.goibibo_selenium_demo.pages.LoginPage;

public class Test_GoibiboQuickLaunch extends BaseTest {
	
	@Test(description = "Valid the web site GOIBIBO is launched")
	public void test() {
		LoginPage login = new LoginPage(driver);	

		
		//Validation page title
		Assert.assertTrue(driver.getTitle().toLowerCase().contains("goibibo"), "Page title does not contain 'Goibibo'");
	}
}