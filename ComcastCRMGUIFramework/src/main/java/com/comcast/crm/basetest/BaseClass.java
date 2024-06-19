package com.comcast.crm.basetest;

import java.sql.SQLException;

import org.apache.xmlbeans.impl.values.JavaUriHolder;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.comcast.crm.generic_Databaseutility.DataBaseUtility;
import com.comcast.crm.generic_Fileutility.ExcelUtility;
import com.comcast.crm.generic_Fileutility.FileUtility;
import com.comcast.crm.generic_Fileutility.JsonUtility;
import com.comcast.crm.generic_Webdriverutility.JavaUtility;
import com.comcast.crm.generic_Webdriverutility.UtilityClassObject;
import com.comcast.crm.generic_Webdriverutility.WebDriverUtility;
import com.comcast.crm.object_repository_utility.HomePage;
import com.comcast.crm.object_repository_utility.LoginPage;
import com.comcast.listenerutility.ListenerImplementationClass;

public class BaseClass {



	// create object of file utility
	public ExcelUtility elib = new ExcelUtility();
	public JsonUtility jslib = new JsonUtility();
	public DataBaseUtility dlib = new DataBaseUtility();
	public FileUtility flib = new FileUtility();
	public JavaUtility jlib = new JavaUtility();
	public WebDriverUtility wlib = new WebDriverUtility();
	public WebDriver driver = null;
	public static WebDriver sdriver = null;
	
	

	@BeforeSuite(groups = { "smokeTest", "regressionTest" })
	public void configBS() throws Throwable {
		System.out.println("===connect to DB ,Report Config===");
		dlib.getDbconnectionwithoutargs();
		
		}

//	@BeforeTest
//	public void configBT() {
//		System.out.println("===execute BT===");
//	}

	@Parameters("BROWSER")
	@BeforeClass(groups = { "smokeTest", "regressionTest" })
	public void configBC() throws Throwable {
		System.out.println("Launch the browser"); 
 		String BROWSER = flib.getDataFromPropertiesFile("browser");
		/*
		 * //Read data from Xml public void configBC(String browser) throws Throwable {
		 * System.out.println("Launch the browser"); String BROWSER = browser;
		 */

		if (BROWSER.contains("chrome")) {
			driver = new ChromeDriver();
		} else if (BROWSER.contains("firefox")) {
			driver = new FirefoxDriver();
		} else if (BROWSER.contains("edge")) {
			driver = new EdgeDriver();
		} else {
			driver = new ChromeDriver();
		}
		
		sdriver = driver;
		UtilityClassObject uco= new UtilityClassObject();
		uco.setDriver(driver);
	}

	@BeforeMethod(groups = { "smokeTest", "regressionTest" })
	public void cinfidBM() throws Throwable {
		System.out.println("==login==");
		String URL = flib.getDataFromPropertiesFile("url");
		String USERNAME = flib.getDataFromPropertiesFile("username");
		String PASSWORD = flib.getDataFromPropertiesFile("password");
		LoginPage lp = new LoginPage(driver);
		wlib.waitForPageToLoad(driver);
		lp.loginToApp(URL, USERNAME, PASSWORD);
		

	}

	@AfterMethod(groups = { "smokeTest", "regressionTest" })
	public void configAM() {
		System.out.println("==logout==");
		HomePage hp = new HomePage(driver);
		hp.logout();
	}

	@AfterClass(groups = { "smokeTest", "regressionTest" })
	public void configAC() {
		System.out.println("close the browser");
		driver.quit();
	}

//	@AfterTest
//	public void AM() {
//		System.out.println("===execute AT===");
//	}

	@AfterSuite(groups = { "smokeTest", "regressionTest" })
	public void configAS() throws SQLException {
		System.out.println("===close DB , Report backUP===");
		dlib.closeDbconnection();

	//	ListenerImplementationClass.report.flush();
	}

}
