package tests;

import java.util.HashMap;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.Base;
import pages.AccountSuccessPage;
import pages.HeaderOptions;
import pages.LoginPage;
import pages.MyAccountPage;
import pages.RightColumnOptions;
import utils.CommonUtils;
import utils.MyXLSReader;

public class LoginTest extends Base {

	Properties prop;
	public  WebDriver driver;
	HeaderOptions headerOptions;
	LoginPage loginPage;
	AccountSuccessPage accountSuccessPage;
	RightColumnOptions rightColumnOptions;
	MyAccountPage myAccountPage;

	@BeforeMethod
	public void setup() {

		prop = CommonUtils.loadProperties();
		System.out.println("Loaded Properties: " + prop);
		driver = openBrowserAndApplicationURL();
		headerOptions = new HeaderOptions(driver);
		headerOptions.clickOnMyAccount();
		loginPage = headerOptions.selectLoginOption();

	}

	@AfterMethod
	public void teardown() {
		quitBrowser(driver);
	}

	// @Test(dependsOnMethods = "verifyRegisteringAccountUsingAllFields()")
	@Test(priority = 3)
	public void verifyLoggingIntoApplicationUsingValidCredentials() {

		loginPage.enterEmail(prop.getProperty("existingEmail"));
		loginPage.enterPassword(prop.getProperty("validPassword"));
		myAccountPage = loginPage.clickOnLoginButton();
		rightColumnOptions = myAccountPage.getRightColumnOptions();
		Assert.assertTrue(rightColumnOptions.isUserLoggedIn());
		myAccountPage = rightColumnOptions.getMyAccountPage();
		Assert.assertTrue(myAccountPage.didWeNavigateToMyAccountPage());

	}

	@Test(priority = 4, dataProvider = "validCredentialsSupplier")
	public void verifyLoggingIntoApplicationUsingNumberOfValidCredentials(HashMap<String, String> map) {

		loginPage.enterEmail(map.get("Email"));
		loginPage.enterPassword(map.get("Password"));
		myAccountPage = loginPage.clickOnLoginButton();
		rightColumnOptions = myAccountPage.getRightColumnOptions();
		Assert.assertTrue(rightColumnOptions.isUserLoggedIn());
		myAccountPage = rightColumnOptions.getMyAccountPage();
		Assert.assertTrue(myAccountPage.didWeNavigateToMyAccountPage());

	}

	@DataProvider(name = "validCredentialsSupplier")
	public Object[][] testDataForLogin() {
		MyXLSReader myXLSReader = new MyXLSReader("\\src\\test\\resources\\TutorialsNinjaData.xlsx");
		Object[][] data = CommonUtils.getTestData(myXLSReader, "loginWithValidCredentials", "login");
		return data;    
	}

}
