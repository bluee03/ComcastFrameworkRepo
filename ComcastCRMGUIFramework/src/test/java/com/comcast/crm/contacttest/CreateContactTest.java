package com.comcast.crm.contacttest;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import org.testng.mustache.BaseChunk;

import com.comcast.crm.basetest.BaseClass;
import com.comcast.crm.generic_Fileutility.ExcelUtility;
import com.comcast.crm.generic_Fileutility.FileUtility;
import com.comcast.crm.generic_Fileutility.JsonUtility;
import com.comcast.crm.generic_Webdriverutility.JavaUtility;
import com.comcast.crm.generic_Webdriverutility.WebDriverUtility;
import com.comcast.crm.object_repository_utility.ContactPage;
import com.comcast.crm.object_repository_utility.ContactsInfoPage;
import com.comcast.crm.object_repository_utility.CreatingNewContactPage;
import com.comcast.crm.object_repository_utility.CreatingNewOrganizationPage;
import com.comcast.crm.object_repository_utility.HomePage;
import com.comcast.crm.object_repository_utility.OrganizationPage;

/**
 * @author Pankaj
 * 
 */
public class CreateContactTest extends BaseClass {

	@Test(groups ="smokeTest")
	public void createContactTest() throws Throwable, IOException {

		/* Step 1: To read data from excel file*/
		String LASTNAME = elib.getDataFromExcel("cont", 1, 2) + jlib.getRandomNumber();

		// Step 2:Navigate to contact module
		HomePage hp = new HomePage(driver);
		hp.getContLink().click();

		// Step 3: click on create contact button

		ContactPage cp = new ContactPage(driver);
		cp.getcontactplusButton().click();

		// Step 4: Enter all the mandatory details and save it
		CreatingNewContactPage cnop = new CreatingNewContactPage(driver);
		cnop.getLastNameTxtfield().sendKeys(LASTNAME);
		cnop.getSavebutton().click();

		// Step 5: Verify Header ORGNAME info Expected result
		ContactsInfoPage cip = new ContactsInfoPage(driver);

		String headermsg = cip.getContactheadermsg().getText();
		boolean status = headermsg.contains(LASTNAME);
		Assert.assertEquals(status, true);
	
//		if (actLastName.contains(LASTNAME)) {
//			System.out.println(LASTNAME + " is verified===>PASS");
//		} else {
//			System.out.println(LASTNAME + " is not verified===>FAIL");
//		}
		
		String actinnerLastName = cip.getcontactInnermsg().getText();
	    SoftAssert sa=new SoftAssert();
	    sa.assertEquals(actinnerLastName, LASTNAME );
	    sa.assertAll();
	   
	}
	

	@Test(groups= "regressionTest")
	public void createContactWithSupportDateTest() throws Throwable, IOException {
		// To read    data from external files
		String lastName = elib.getDataFromExcel("cont", 1, 2) + jlib.getRandomNumber();
		String startdate = jlib.getSystemDateYYYYMMDD();
		String enddate = jlib.getRequiredDateYYYYMMDD(30);

		// Navigate to contact module
		HomePage hp = new HomePage(driver);
		hp.getContLink().click();

		// click on create contact button
		ContactPage cp = new ContactPage(driver);
		cp.getcontactplusButton().click();

		// Enter all the mandatory details and save it
		CreatingNewContactPage cnop = new CreatingNewContactPage(driver);
		cnop.createContactWithSupportDate(lastName, startdate, enddate);

		// Verify date info Expected result
		String actstartdate = driver.findElement(By.id("dtlview_Support Start Date")).getText();

		if (actstartdate.equals(startdate)) {
			System.out.println(startdate + " is verified===>PASS");
		} else {
			System.out.println(startdate + " is not verified===>FAIL");

		}

		String actualenddate = driver.findElement(By.id("dtlview_Support End Date")).getText();

		if (actualenddate.equals(enddate)) {
			System.out.println(enddate + " is verified===>PASS");
		} else {
			System.out.println(enddate + " is not verified===>FAIL");
		}
	}

	@Test(groups= "regressionTest")
	public void createContactsWithOrgTest() throws EncryptedDocumentException, IOException {

		// To read data from excel file
		String CONLASTNAME = elib.getDataFromExcel("cont", 7, 3);
		String ORGNAME = elib.getDataFromExcel("cont", 7, 2) + jlib.getRandomNumber();

		// :Navigate to organization module
		HomePage hp = new HomePage(driver);
		hp.getOrgLink().click();

		// click on org+ button
		OrganizationPage op = new OrganizationPage(driver);
		op.getCreateOrgImage().click();

		// enter details
		CreatingNewOrganizationPage cnop = new CreatingNewOrganizationPage(driver);
		cnop.getOrgName().sendKeys(ORGNAME);
		cnop.getSaveButton().click();

		// verify Header msg Expected Result
		String headerInfo = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if (headerInfo.contains(ORGNAME)) {
			System.out.println(ORGNAME + "is created successfully===>PASS");
		} else {
	 		System.out.println(ORGNAME + "is not created====>FAIL");
		}

		// navigate to contact module
		hp.getContLink().click();

		// click on create contact button
		ContactPage cp = new ContactPage(driver);
		cp.getcontactplusButton().click();

		// Enter all the mandatory details and save it
		CreatingNewContactPage cncp = new CreatingNewContactPage(driver);
		cncp.getLastNameTxtfield().sendKeys(CONLASTNAME);

		// click on org +icon
		driver.findElement(By.xpath("//input[@name='account_name']/following-sibling::img")).click();

		// switch driver control to child
		wlib.switchToTabOnUrl(driver, "module=Accounts");

		driver.findElement(By.name("search_text")).sendKeys(ORGNAME);
		driver.findElement(By.name("search")).click();
		driver.findElement(By.xpath("//a[text()='" + ORGNAME + "']")).click();

		// switch to parent window
		wlib.switchToTabOnUrl(driver, "module=Contacts");
		driver.findElement(By.name("button")).click();

		// verify inner orgname
		String ActualOrgName = driver.findElement(By.id("mouseArea_Organization Name")).getText();

		if (ActualOrgName.contains(ORGNAME)) {
			System.out.println("Organization name=" + ORGNAME + "is verified===>PASS");
		} else {
			System.out.println("Organization name=" + ORGNAME + "is not verified====>FAIL");
		}

	}
}
