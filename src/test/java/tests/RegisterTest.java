package tests;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.Base;
import pages.AccountSuccessPage;
import pages.HeaderOptions;
import pages.MyAccountPage;
import pages.NewsletterSubscriptionPage;
import pages.RegisterAccountPage;
import pages.RightColumnOptions;
import utils.CommonUtils;

public class RegisterTest extends Base {
	
	Properties prop;
	public WebDriver driver;
	HeaderOptions headerOptions;
	RegisterAccountPage registerAccountPage;
	AccountSuccessPage accountSuccessPage;
	RightColumnOptions rightColumnOptions;
	MyAccountPage myAccountPage;
	NewsletterSubscriptionPage newsletterSubscriptionPage;

	@BeforeMethod
	public void setup() {
		
		prop = CommonUtils.loadProperties();
		System.out.println("Loaded Properties: " + prop);
		driver = openBrowserAndApplicationURL();
		headerOptions = new HeaderOptions(driver);
		headerOptions.clickOnMyAccount();
		registerAccountPage = headerOptions.selectRegisterOption();

	}

 

	@AfterMethod
	public void teardown() {
		quitBrowser(driver);
	}

     

	@Test(priority = 1)
	public void verifyRegisteringUsingMandatoryFields() {

		registerAccountPage.enterFirstName(prop.getProperty("firstName"));
		registerAccountPage.enterLastName(prop.getProperty("lastName"));
		registerAccountPage.enterEmail(CommonUtils.generateNewEmail());
		registerAccountPage.enterTelephone(prop.getProperty("telephoneNumber"));
		registerAccountPage.enterPassword(prop.getProperty("validPassword"));
		registerAccountPage.enterConfirmationPassword(prop.getProperty("validPassword"));
		registerAccountPage.selectPrivacyPolicyField();
		accountSuccessPage = registerAccountPage.clickOnContinueButton();
		rightColumnOptions = accountSuccessPage.getRightColumnOptions();
		Assert.assertTrue(rightColumnOptions.isUserLoggedIn());
		accountSuccessPage = rightColumnOptions.getAccountSuccessPage();
		Assert.assertTrue(accountSuccessPage.didWeNavigateToAccountSuccessPage());
		myAccountPage = accountSuccessPage.clickOnContinueButton();
		Assert.assertTrue(myAccountPage.didWeNavigateToMyAccountPage());

	}

	@Test(priority = 2)
	public void verifyRegisteringAccountUsingAllFields() {

		registerAccountPage.enterFirstName(prop.getProperty("firstName"));
		registerAccountPage.enterLastName(prop.getProperty("lastName"));
		registerAccountPage.enterEmail(CommonUtils.generateNewEmail());
		registerAccountPage.enterTelephone(prop.getProperty("telephoneNumber"));
		registerAccountPage.enterPassword(prop.getProperty("validPassword"));
		registerAccountPage.enterConfirmationPassword(prop.getProperty("validPassword"));
		registerAccountPage.selectYesNewsletterOption();
		registerAccountPage.selectPrivacyPolicyField();
		accountSuccessPage = registerAccountPage.clickOnContinueButton();
		rightColumnOptions = accountSuccessPage.getRightColumnOptions();
		Assert.assertTrue(rightColumnOptions.isUserLoggedIn());
		accountSuccessPage = rightColumnOptions.getAccountSuccessPage();
		Assert.assertTrue(accountSuccessPage.didWeNavigateToAccountSuccessPage());
		myAccountPage = accountSuccessPage.clickOnContinueButton();
		Assert.assertTrue(myAccountPage.didWeNavigateToMyAccountPage());

	}

}
