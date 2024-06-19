package practice.TestNG;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.comcast.crm.basetest.BaseClass;
@Listeners(com.comcast.listenerutility.ListenerImplementationClass.class)
public class InvoiceTest extends BaseClass {

	@Test 
	public void createInvoiceTest() {
		System.out.println("execute createInvoiceTest");
		String actTitle = driver.getTitle();
		Assert.assertEquals(actTitle, "Login");
		System.out.println("step-1");
		System.out.println("step-2");
		System.out.println("step-3");
		System.out.println("step-4");
	}
//	@Test 
//	public void createInvoicewithContactTest() {
//		System.out.println("execute createInvoicewithContactTest");
//		System.out.println("step-1");
//		System.out.println("step-2");
//		System.out.println("step-3");
//		System.out.println("step-4");
//	}
}
