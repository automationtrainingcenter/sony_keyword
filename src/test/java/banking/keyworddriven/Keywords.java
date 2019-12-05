package banking.keyworddriven;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Keywords {
	WebDriver driver;
	WebDriverWait wait;
	private Alert alert;
	private String alertText;

	// openBrowser
	public void openBrowser(String locType, String locValue, String testData) {
		if(testData.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", ".//drivers//chromedriver.exe");
			driver = new ChromeDriver();
		}else {
			System.setProperty("webdriver.gecko.driver", ".//drivers//geckodriver.exe");
			driver = new FirefoxDriver();
		}
		driver.manage().window().maximize();
		wait = new WebDriverWait(driver, 30);
	}

	// navigate
	public void navigate(String locType, String locValue, String testData) {
		driver.get(testData);
	}

	// fillTextBox
	public void fillTextBox(String locType, String locValue, String testData) {
		WebElement element = driver.findElement(LocatorHelper.locateElement(locType, locValue));
		element.clear();
		element.sendKeys(testData);
//		wait.until(ExpectedConditions.presenceOfElementLocated(LocatorHelper.locateElement(locType, locValue))).sendKeys(testData);
	}

	// click
	public void click(String locType, String locValue, String testData) {
		driver.findElement(LocatorHelper.locateElement(locType, locValue)).click();
	}
	
	// wait
	public void wait(String locType, String locValue, String testData) {
		long timeInMillis = Long.parseLong(testData);
		try {
			Thread.sleep(timeInMillis);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// selectOption
	public void selectOption(String locType, String locValue, String testData) {
		Select selectEle = new Select(driver.findElement(LocatorHelper.locateElement(locType, locValue)));
		selectEle.selectByVisibleText(testData);
	}

	// switchToAlert
	public void switchToAlert(String locType, String locValue, String testData) {
		alert = driver.switchTo().alert();
		alertText = alert.getText();
		System.out.println(alertText);
	}

	// acceptAlert
	public void acceptAlert(String locType, String locValue, String testData) {
		alert.accept();
	}

	// closeBrowser
	public void closeBrowser(String locType, String locValue, String testData) {
		driver.close();
	}

}
