package teladoc_challenge;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

//This class has the page objects of Delete user
public class LandingPageDeleteRecord {

	public WebDriver driver;
	By tblWebTable = By.xpath("//table/tbody");
	By tblFNameColum = By.xpath("//table/tbody/tr/td[3]");
	By btnDeleteConfirm = By.xpath("//button[@class='btn ng-scope ng-binding btn-primary']");
	By btnDeleteCancel = By.xpath("//button[@class='btn ng-scope ng-binding']");

	String before_XPath = "//table/tbody/tr[";
	String after_XPath = "]/td[11]/button";

	
	By pageNumber = By.cssSelector("li:nth-child(2) a");
	By clickNextPage = By.cssSelector("li:nth-child(3) a");
	By clickBeforePage = By.cssSelector("li:nth-child(1) a");
	By userNameClickSort = By.xpath("//span[@class='header-content ng-scope ng-binding sort-descent']");
	By noNextBtn = By.xpath("//li[@class='ng-scope disabled'][2]/a");
	
	
	public LandingPageDeleteRecord(WebDriver driver) {
		this.driver = driver;
	}

	public WebElement getSpecificRow(String name) {

		boolean founName = false;
		WebElement nameFound = null;
		while(!founName) {
			
			List<WebElement> userNamesOnCurrentPage = driver.findElements(By.xpath("//table/tbody/tr/td[3][normalize-space()='"+name+"']"));
			List<WebElement> disableNextBtn = driver.findElements(noNextBtn);
		
			
			if(userNamesOnCurrentPage.size() >0) {
				founName = true;
				
			}else if(disableNextBtn.size()==0) {
				
				driver.findElement(clickNextPage).click();
			
			}
			else {
				return null;
			}
		
		}
		nameFound = driver.findElement(By.xpath("//table/tbody/tr/td[3][normalize-space()='"+name+"']/following-sibling::td[8]/button"));
		return nameFound;

	}

	public WebElement getbtnDeleteCancel() {
		WebElement deleteCancel = driver.findElement(btnDeleteCancel);
		return deleteCancel;

	}

	public WebElement getbtnDeleteConfirm() {
		WebElement deleteConfirm = driver.findElement(btnDeleteConfirm);
		return deleteConfirm;

	}

}
