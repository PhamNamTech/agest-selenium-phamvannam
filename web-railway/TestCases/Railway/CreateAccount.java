package Railway;

import org.testng.Assert;
import org.testng.annotations.Test;

import Common.Log4j;
import Constant.Constant;
import Constant.PageMenu;
import Guerrilla.GuerrillaHomePage;

public class CreateAccount extends TestBase {

	@Test
	public void TC07() {
		HomePage homePage = new HomePage();
		RegisterPage registerPage = new RegisterPage();
		RegisterAccount account = PreconditionHelper.generateRandomRegisterAccount();

		Log4j.info("TC07: User can't create account with an already in-use email");
		Log4j.info("Pre-condition: an actived account is existing");
		account = PreconditionHelper.createAnActiveAccount(account);

		Log4j.info("Step 1: Navigate to QA Railway Website");
		homePage = homePage.open();

		Log4j.info("Step 2:  Click on \"Register\" tab");
		registerPage = homePage.gotoPage(PageMenu.REGISTER, RegisterPage.class);

		Log4j.info("Step 3: Enter information of the created account in Pre-condition");
		Log4j.info("Step 4: Click on \"Register\" button");
		registerPage = registerPage.registerNewAccount(account);

		Log4j.info("VP: Error message \"This email address is already in use.\" displays above the form.");
		String actualMsg = registerPage.geMsgGeneralError();
		Assert.assertEquals(actualMsg, "This email address is already in use.".trim(),
				"Error message is not displayed as expected");
	}

	@Test
	public void TC08() {
		HomePage homePage = new HomePage();
		RegisterPage registerPage = new RegisterPage();
		RegisterAccount account = new RegisterAccount(Constant.VALID_USERNAME, null);

		Log4j.info("TC08: Verify that user can't create account while password and PID fields are empty");
		Log4j.info("Step 1: Navigate to QA Railway Website");
		homePage = homePage.open();

		Log4j.info("Step 2:  Click on \"Register\" tab");
		registerPage = homePage.gotoPage(PageMenu.REGISTER, RegisterPage.class);

		Log4j.info("Step 3: Enter valid email address and leave other fields empty");
		Log4j.info("Step 4: Click on \"Register\" button");
		registerPage = registerPage.registerNewAccount(account);

		Log4j.info(
				"VP: Message \"There're errors in the form. Please correct the errors and try again.\" appears above the form.");
		String actualMsgGeneralError = registerPage.geMsgGeneralError();
		Assert.assertEquals(actualMsgGeneralError.replaceFirst("\\.$", ""),
				"There're errors in the form. Please correct the errors and try again.".trim().replaceFirst("\\.$", ""),
				"Error message is not displayed as expected");

		Log4j.info("VP: Next to password fields, error message \"Invalid password length.\" displays");
		String actualMsgPasswordError = registerPage.getMsgErrorPassword();
		Assert.assertEquals(actualMsgPasswordError.replaceFirst("\\.$", ""),
				"Invalid password length.".trim().replaceFirst("\\.$", ""),
				"Error message is not displayed as expected");

		Log4j.info("VP: Next to PID field, error message \"Invalid ID length.\" displays");
		String actualMsgPipError = registerPage.getMsgErrorPip();
		Assert.assertEquals(actualMsgPipError.replaceFirst("\\.$", ""),
				"Invalid ID length.".trim().replaceFirst("\\.$", ""), "Error message is not displayed as expected");
	}

	@Test
	public void TC09() {
		HomePage homePage = new HomePage();
		RegisterPage registerPage = new RegisterPage();
		GuerrillaHomePage guerrillaHomePage = new GuerrillaHomePage();
		RegisterAccount account = PreconditionHelper.generateRandomRegisterAccount();

		Log4j.info("TC09: Verify that user create and activate account");
		Log4j.info("Step 1: Navigate to QA Railway Website");
		homePage = homePage.open();

		Log4j.info(
				"VP: Home page is shown with guide containing href \"create an account\" to \"Register\" page");
		Assert.assertTrue(homePage.isCreateAccountLinkDisplayed(),
				"The create account link is not displayed as expected");

		Log4j.info("Step 2:  Click on \"Create an account\"");
		registerPage = homePage.clickCreateAccountLink();
		Log4j.info("VP: Register page is shown");
		Assert.assertTrue(registerPage.isMenuDisplayed(PageMenu.LOGIN), "The Register page is not shown as expected");

		Log4j.info("Step 3:  Enter valid information into all fields");
		Log4j.info("Step 4: Click on \"Register\" button");
		Log4j.info(
				"Step 5: Get email information (webmail address, mailbox and password) and navigate to that webmail ");
		Log4j.info("Step 6: Login to the mailbox");
		Log4j.info(
				"Step 7: Open email with subject containing \"Please confirm your account\"  and the email of the new account at step 3");
		Log4j.info("Step 8: Click on the activate link");
		account = PreconditionHelper.createAnEmail(account);
		registerPage = registerPage.registerNewAccount(account);

		Log4j.info("VP: \"Thank you for registering your account\" is shown");
		String actualMsgThankyou = registerPage.getMsgThankyou();
		Assert.assertEquals(actualMsgThankyou, "Thank you for registering your account",
				"Message is not displayed as expected");

		guerrillaHomePage.open();
		guerrillaHomePage.confirmRegistrationEmail(account.getEmail());
		Log4j.info(
				"VP: Redirect to Railways page and message \"Registration Confirmed! You can now log in to the site\" is shown");
		String actualMsgConfirmed = registerPage.getMsgRegistrationConfirmed();
		Assert.assertEquals(actualMsgConfirmed, "Registration Confirmed! You can now log in to the site.",
				"Message is not displayed as expected");
	}
}
