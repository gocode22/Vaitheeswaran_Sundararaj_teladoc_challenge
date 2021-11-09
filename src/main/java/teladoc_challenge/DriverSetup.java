package teladoc_challenge;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverSetup {

	private static WebDriver driver;
	public static Properties prop;

	@SuppressWarnings("deprecation")
	
	//This method initiates and returns the driver
	public static WebDriver getWebDriver() throws IOException {

		// Property file path
		prop = new Properties();

		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\main\\java\\teladoc_challenge\\data.properties");
		prop.load(fis);
		String browserName = prop.getProperty("browser");

		String path = System.getProperty("user.dir");
		String driverPathChrome = path + "\\src\\main\\java\\resources\\DriverFiles\\chromedriver.exe";
		String driverPathFirbox = path + "\\src\\main\\java\\resources\\DriverFiles\\geckodriver.exe";

		// select browser name based on the value from the properties file
		switch (browserName) {

		case "chrome":

			System.setProperty("webdriver.chrome.driver", driverPathChrome);
			driver = new ChromeDriver();
			break;
		case "firefox":
			System.setProperty("webdriver.gecko.driver", driverPathFirbox);
			driver = new FirefoxDriver();
			break;
		default:
			System.setProperty("webdriver.chrome.driver", driverPathChrome);
			driver = new ChromeDriver();
			break;
		}
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		return driver;

	}

	//This method takes the screenshot
	public String getScreenShotPath(String testCaseName, WebDriver driver) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		String destinationFile = System.getProperty("user.dir") + "\\reports\\" + testCaseName + ".png";
		FileUtils.copyFile(source, new File(destinationFile));
		return destinationFile;

	}

}
