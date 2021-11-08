package teladoc_challenge;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import junit.framework.Assert;

public class DeleteRecord extends DriverSetup{

	public WebDriver driver;
	public static Logger log = LogManager.getLogger(DriverSetup.class.getName());
	
	@BeforeTest
	public void initilizeDriver() throws IOException {
		driver = getWebDriver();
		log.info("Driver is initilized");
		
	}
	
	@DataProvider(name="DeleteDatas")
	public Iterator<String> getDatafromExcel()
	{
		ArrayList<String> dataListexcel = Utilities.getRegisterData("DeleteRecord");
		//System.out.println(dataListexcel);
		return dataListexcel.iterator();
	}
	
	

	@Test(dataProvider = "DeleteDatas")
	public void DeleteUser(String userName) throws IOException {
		
			String testurl = prop.getProperty("url");
			System.out.println(testurl);
			driver.get(testurl);
			driver.manage().window().maximize();
			SoftAssert a =new SoftAssert();
			
			String[] inputdata = userName.split(";");
			
			LandingPageDeleteRecord lPageDeleteRecord = new LandingPageDeleteRecord(driver);
			log.info("Test completed");
			//lpage.getTable();
			
			lPageDeleteRecord.getSpecificRow(inputdata[0]).click();
			//lPageDeleteRecord.getbtnDeleteCancel().click();
			lPageDeleteRecord.getbtnDeleteConfirm().click();
			
			LandingPage landingPage = new LandingPage(driver);
			String	uNamerNotFound = landingPage.getSpecificRow(userName);

			Assert.assertEquals(uNamerNotFound,"");
			log.info("Test completed");
		}
		
		@AfterTest
		public void teardown() {
			driver.close();
		}
		
		
	
//		@DataProvider
//		public Object[][] getDeleteData() {
//			
//			
//			Object[][] dataObjects = new Object[1][1];
//			
//			dataObjects[0][0] = "novak";
//			return dataObjects;
//			
//		}
	
}
