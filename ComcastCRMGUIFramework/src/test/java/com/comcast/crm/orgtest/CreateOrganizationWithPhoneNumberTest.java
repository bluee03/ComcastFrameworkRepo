package com.comcast.crm.orgtest;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class CreateOrganizationWithPhoneNumberTest {

	public static void main(String[] args) throws IOException, InterruptedException {
		
		// step 1:to get the java representation of the physicalfile
		FileInputStream fis = new FileInputStream("./configAppData/commondata.properties");
		// step 2:To load all the keys using properties class
		Properties prop = new Properties();
		prop.load(fis);
		//Step 3: read data from properties file
		String BROWSER = prop.getProperty("browser");
		String URL = prop.getProperty("url");
		String USERNAME = prop.getProperty("username");
		String PASSWORD = prop.getProperty("password");
		
		//create random number object
		Random r = new Random();
		int random = r.nextInt(1000);
		
		//Step 4:To read data from excel file
		FileInputStream efis = new FileInputStream("./testData/Book1.xlsx");
        Workbook wb = WorkbookFactory.create(efis);
        Sheet sh = wb.getSheet("org");
        Row row = sh.getRow(7);
        String ORGNAME = row.getCell(2).toString()+random;
        String phonenumber = row.getCell(3).toString();
        wb.close();

        // test scripts
		WebDriver driver = null;
		
			if (BROWSER.contains("chrome")) {
			   driver = new ChromeDriver();
		} 
			else if (BROWSER.contains("firefox")) {
			driver = new FirefoxDriver();
		}
			else if (BROWSER.contains("edge")) {
			driver = new EdgeDriver();
		}
			else {
			driver = new ChromeDriver();
		}
		
		//To login to application
		driver.get(URL);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		driver.findElement(By.id("submitButton")).click();
        //Navigate to organization module
		driver.findElement(By.linkText("Organizations")).click();
		//click on create organization button
		driver.findElement(By.xpath("//img[@alt='Create Organization...']")).click();
		
		//Enter all the madatory details and save it
		driver.findElement(By.name("accountname")).sendKeys(ORGNAME);
		driver.findElement(By.id("phone")).sendKeys(phonenumber);
		driver.findElement(By.name("button")).click();
		 
		 //Verify Header ORGNAME info Expected result 
		String actphonenum = driver.findElement(By.id("dtlview_Phone")).getText();
		if(actphonenum.equals(phonenumber)) {
			System.out.println(phonenumber+" is verified===>PASS");
		}else {
			System.out.println(phonenumber+" is not verified===>FAIL");

		}
			
		driver.quit();

	} 

}
