package com.Actitime.GenericLibrary;

import java.io.IOException;
import java.time.Duration;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.Actitime.pom_ObjectRepository.LoginPage;

public class baseClass {
	public static WebDriver driver;
	FileLibrary fl = new FileLibrary();
	@BeforeSuite
	public void databaseConnect() {
		Reporter.log("Database Connected",true);
	}
	@BeforeClass
	public void lauchBrowser () throws IOException {
		 driver= new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		
		String url = fl.dataFromPropertyFile("url");
		driver.get(url);
		Reporter.log("Browser is launched",true);
	}
	@BeforeMethod
	public void logIn() throws IOException {
		
		LoginPage lp = new LoginPage(driver);
		
		String username = fl.dataFromPropertyFile("username");
		String password = fl.dataFromPropertyFile("password");
		
		lp.getUntbx().sendKeys(username);
		lp.getPwtbx().sendKeys(password);
		lp.getLgbtn().click();
		
		//Reporter.log("LoggedIn successfully",true);
	}
	@Test
	public void createCustomer() throws EncryptedDocumentException, IOException {
		Reporter.log("Customer is created",true);
	}
	@AfterMethod
	public void logOut() {
		driver.findElement(By.id("logoutLink")).click();
		Reporter.log("Logout sucessfully",true);
	}
	@AfterClass
	public void closeBrowser() {
		driver.close();
		Reporter.log("Browser is closed", true);
	}
	@AfterSuite
	public void DisconnectDatabase() {
		Reporter.log("Database is Disconnected",true);
	}

}
