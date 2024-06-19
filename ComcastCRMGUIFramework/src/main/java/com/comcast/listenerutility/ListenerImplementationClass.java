package com.comcast.listenerutility;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.logging.FileHandler;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.comcast.crm.basetest.BaseClass;
import com.comcast.crm.generic_Webdriverutility.UtilityClassObject;

public class ListenerImplementationClass implements ITestListener, ISuiteListener {

    public ExtentReports report;
	public static ExtentTest test;
 
	@Override
	public void onStart(ISuite suite) {

		System.out.println("Report Configuration");
		String Time = new Date().toString().replace(" ", "_").replace(":","_");
		// spark report config
		ExtentSparkReporter spark = new ExtentSparkReporter("./AdvanceReport/report"+Time+".html");
		spark.config().setDocumentTitle("CRM Test Suite Result");
		spark.config().setReportName("CRM Report");
		spark.config().setTheme(Theme.DARK);

		// add env info and create test
	    report = new ExtentReports();
		report.attachReporter(spark);
		report.setSystemInfo("OS", "Windows-11");
		report.setSystemInfo("BROWSER", "CHROME-100");
	}

	@Override
	public void onFinish(ISuite suite) {
		System.out.println("Report backUP");
        report.flush();
	}

	@Override
	public void onTestStart(ITestResult result) {

		System.out.println("=====" + result.getMethod().getMethodName() + "===START===");
		test = report.createTest(result.getMethod().getMethodName());
		UtilityClassObject.setTest(test); 
		test.log(Status.INFO, result.getMethod().getMethodName() + "==>STARTED<==");
	}

	@Override
	public void onTestSuccess(ITestResult result) {

		System.out.println("=====" + result.getMethod().getMethodName() + "===END===");
		test.log(Status.PASS, result.getMethod().getMethodName() + "==>COMPLETED<==");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		String testName = result.getMethod().getMethodName();
		// Step 1: Create an object to EventFiringWebdriver
		TakesScreenshot ts = (TakesScreenshot) BaseClass.sdriver;
		String filepath = ts.getScreenshotAs(OutputType.BASE64);
		String Time = new Date().toString().replace(" ", "_").replace(":", "_");

		test.addScreenCaptureFromBase64String(filepath, testName + "_" + Time);

		test.log(Status.FAIL, result.getMethod().getMethodName() + "==>FAILED<==");

//		try {
//			// org.openqa.selenium.io.FileHandler.copy(src, new
//			// File("./screenshot/"+testName+Time+".png"));
//			FileUtils.copyFile(src, new File("./screenshot/" + testName + Time + ".png"));
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub

	}

}
