package teladoc_challenge;


import java.util.List;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

//This class has the page objects of Add user
public class LandingPage {

	public WebDriver driver;

	By tblWebTable = By.xpath("//table/tbody");
	By tblFNameColum = By.xpath("//table/tbody/tr/td[3]");

	By btnAdd = By.xpath("//button[@type='add']");
	// this is required field
	By txtFNameRequired = By.xpath("//table/tbody/tr[1]/td[3]/span[1]");
	By txtRoleeRequired = By.xpath("//table/tbody/tr[6]/td[3]/span[1]");

	By txtFirstName = By.name("FirstName");

	By txtLastName = By.cssSelector("input[name='LastName']");
	By txtUserName = By.name("UserName");
	By txtPassword = By.name("Password");
	By radioBtnCompanAAA = By.cssSelector("input[value='15']");
	By radioBtnCompanyBBB = By.cssSelector("input[value='16']");
	// this is required field
	By drpDwnSelect = By.xpath("//select[@class='ng-pristine ng-invalid ng-invalid-required']");
	By drpDwnSalesTeam = By.xpath("//option[@value='0']");
	By drpDwnCustomer = By.xpath("//option[@value='1']");
	By drpDwnAdmin = By.xpath("//option[@value='2']");

	By txtEmail = By.name("Email");
	By txtMobilephone = By.name("Mobilephone");
	By btnSave = By.xpath("//button[@class='btn btn-success']");
	By btnClose = By.xpath("//button[@class='btn btn-danger']");

	// this is to get the row dynamically based on the first name supplied from test
	By pageNumber = By.cssSelector("li:nth-child(2) a");
	By clickNextPage = By.cssSelector("li:nth-child(3) a");
	By clickBeforePage = By.cssSelector("li:nth-child(1) a");
	By userNameClickSort = By.xpath("//span[@class='header-content ng-scope ng-binding sort-descent']");
	By noNextBtn = By.xpath("//li[@class='ng-scope disabled'][2]/a");
	

	public LandingPage(WebDriver driver) {
		this.driver = driver;
	}

	public WebElement getbtnAdd() {
		WebElement addBtn = driver.findElement(btnAdd);
		return addBtn;
	}

	public boolean getReqFMElemIsVisible() {

		boolean fnVisible = driver.findElement(txtFNameRequired).isDisplayed();

		return fnVisible;
	}

	public boolean getReqRoleElemIsVisible() {

		boolean rlVisible = driver.findElement(txtRoleeRequired).isDisplayed();

		return rlVisible;
	}

	public boolean getReqFMElemIsNotVisible() {

		boolean fnVisible = driver.findElement(txtFNameRequired).isDisplayed();

		return fnVisible;
	}

	public boolean getReqRoleElemIsNotVisible() {

		boolean rlVisible = driver.findElement(txtRoleeRequired).isDisplayed();

		return rlVisible;
	}

	public WebElement gettxtFirstName() {

		WebElement fn = driver.findElement(txtFirstName);
		return fn;
	}

	public WebElement gettxtLastName() {
		WebElement ln = driver.findElement(txtLastName);
		return ln;
	}

	public WebElement gettxtUserName() {
		WebElement un = driver.findElement(txtUserName);
		return un;
	}

	public WebElement gettxtPassword() {
		WebElement pw = driver.findElement(txtPassword);
		return pw;
	}

	public WebElement radioBtnSelect(String cd) {
		WebElement rdb1 = null;

		if (Integer.parseInt(cd) == 15) {
			rdb1 = driver.findElement(radioBtnCompanAAA);
		} else if (Integer.parseInt(cd) == 16) {
			rdb1 = driver.findElement(radioBtnCompanyBBB);
		}
		return rdb1;
	}

	public void getdrpDwnSelect(String code) {
		Select sltdrpd = new Select(driver.findElement(drpDwnSelect));

		WebDriverWait waitExplitct = new WebDriverWait(driver, 5);

		sltdrpd.selectByIndex(Integer.parseInt(code));
		waitExplitct.until(ExpectedConditions.visibilityOfElementLocated(drpDwnSalesTeam));

	}

	public WebElement gettxtEmail() {
		WebElement txt = driver.findElement(txtEmail);
		return txt;
	}

	public WebElement gettxtMobilephone() {
		WebElement ph = driver.findElement(txtMobilephone);
		return ph;
	}

	public WebElement getbtnSave() {
		WebElement sv = driver.findElement(btnSave);
		return sv;
	}

	public WebElement getbtnClose() {
		WebElement cl = driver.findElement(btnClose);
		return cl;
	}

	public String getSpecificRow(String name) {

		boolean founName = false;
		String nameFound = "";
		while(!founName) {
			
			List<WebElement> userNamesOnCurrentPage = driver.findElements(By.xpath("//table/tbody/tr/td[3][normalize-space()='"+name+"']"));
			List<WebElement> disableNextBtn = driver.findElements(noNextBtn);
			
			if(userNamesOnCurrentPage.size() >0) {
				founName = true;
				
			}else if(disableNextBtn.size()==0) {
				
				driver.findElement(clickNextPage).click();
			
			}
			else {
				return "";
			}
		
		}
		nameFound = driver.findElement(By.xpath("//table/tbody/tr/td[3][normalize-space()='"+name+"']")).getText();
		return nameFound;

	}

}
