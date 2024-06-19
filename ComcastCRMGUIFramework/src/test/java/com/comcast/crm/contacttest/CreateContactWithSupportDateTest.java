package com.comcast.crm.contacttest;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.util.Random;
import java.util.Scanner;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.junit.experimental.theories.suppliers.TestedOn;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

import com.comcast.crm.basetest.BaseClass;
import com.comcast.crm.generic_Fileutility.ExcelUtility;
import com.comcast.crm.generic_Fileutility.FileUtility;
import com.comcast.crm.generic_Webdriverutility.JavaUtility;
import com.comcast.crm.object_repository_utility.ContactPage;
import com.comcast.crm.object_repository_utility.CreatingNewContactPage;
import com.comcast.crm.object_repository_utility.HomePage;

public class CreateContactWithSupportDateTest extends BaseClass {

	    @Test
	    public void createContactWithSupportDateTest() throws EncryptedDocumentException, IOException {
	    //To read data from external files
		String lastName = elib.getDataFromExcel("cont", 1, 2)+jlib.getRandomNumber();
	    String startdate = jlib.getSystemDateYYYYMMDD();
	    String enddate = jlib.getRequiredDateYYYYMMDD(30);
	   	
		// Navigate to contact module
	    HomePage hp = new HomePage(driver);
		hp.getContLink().click();
		
		// click on create contact button
		ContactPage cp= new ContactPage(driver);
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
}
