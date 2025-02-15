package base;

import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import utils.CommonUtils;

public class Base {

	WebDriver driver;
	public Properties prop;

	public WebDriver openBrowserAndApplicationURL() {

		prop = CommonUtils.loadProperties();
		String browser = prop.getProperty("browserName");
		if (browser.equals("chrome")) {
			driver = new ChromeDriver();
		} else if (browser.equals("firefox")) {
			driver = new FirefoxDriver();
		} else if (browser.equals("edge")) {
			driver = new EdgeDriver();
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.get(prop.getProperty("applicationURL"));

		return driver;

	}

	public void quitBrowser(WebDriver driver) {
		if (driver != null) {
			driver.quit();
		}
	}

}
