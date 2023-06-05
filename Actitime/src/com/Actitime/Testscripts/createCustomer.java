package com.Actitime.Testscripts;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.Actitime.GenericLibrary.FileLibrary;
import com.Actitime.GenericLibrary.baseClass;
import com.Actitime.pom_ObjectRepository.HomePage;
import com.Actitime.pom_ObjectRepository.TaskPage;

public class createCustomer extends baseClass{

	@Test
	public void createCustomer() throws EncryptedDocumentException, IOException {
		HomePage hp = new HomePage(driver);
		hp.getTasktab().click();
		TaskPage tp = new TaskPage(driver);
		tp.getAddnewbtn().click();
		tp.getNewcust().click();
		FileLibrary f1= new FileLibrary();
		String customer = f1.readDataFromExcel("Testdata1", 7, 1);
		tp.getCustname().sendKeys(customer);
		String description = f1.readDataFromExcel("Testdata1", 7, 2);
		tp.getCustdesc().sendKeys(description);
		tp.getCreatecust().click();
		String expectedResult = customer;
		String actualResult = driver.findElement(By.xpath("(//div[.='"+customer+"'])[2]")).getText();
		SoftAssert s = new SoftAssert();
		s.assertEquals(expectedResult, actualResult);
		
	
	}
}
