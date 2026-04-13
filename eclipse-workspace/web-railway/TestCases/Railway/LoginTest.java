package Railway;

import org.testng.Assert;
import org.testng.annotations.Test;

import Common.Log4j;
import Common.Utilities;
import Constant.Constant;
import Constant.PageMenu;

public class LoginTest extends TestBase {

	@Test
	public void TC01() {
		HomePage homePage = new HomePage();
		RegisterAccount account = new RegisterAccount(Constant.VALID_USERNAME, Constant.VALID_PASSWORD);

		Log4j.info("TC01: Verify that user can log into Railway with valid username and password");
		Log4j.info("Step 1: Navigate to QA Railway Website");
		homePage = homePage.open();

		Log4j.info("Step 2: Click on 'Login' tab");
		LoginPage loginPage = homePage.gotoPage(PageMenu.LOGIN, LoginPage.class);

		Log4j.info("Step 3: Enter valid Email and Password");
		Log4j.info("Step 4: Click on 'Login' button");
		Log4j.info("VP: User is logged into Railway. Welcome user message is displayed.");
		String actualMsg = loginPage.login(account).getWelcomeMsg();
		Assert.assertEquals(actualMsg, "Welcome " + account.getEmail(), "Welcome message is not displayed as expected");
	}

	@Test
	public void TC02() {
		HomePage homePage = new HomePage();
		RegisterAccount account = new RegisterAccount(null, Constant.VALID_PASSWORD);

		Log4j.info("TC02: Verify that user cannot login with blank 'Username' textbox");
		Log4j.info("Step 1: Navigate to QA Railway Website");
		homePage = homePage.open();

		Log4j.info("Step 2: Click on 'Login' tab");
		LoginPage loginPage = homePage.gotoPage(PageMenu.LOGIN, LoginPage.class);

		Log4j.info(
				"Step 3: User doesn't type any words into 'Username' textbox but enter valid information into 'Password' textbox ");
		Log4j.info("Step 4: Click on 'Login' button");
		loginPage = loginPage.login(account);

		Log4j.info(
				"VP: User can't login and message \"There was a problem with your login and/or errors exist in your form. \" appears.");
		String actualMsg = loginPage.getLoginErrorMsg();
		Assert.assertEquals(actualMsg, "There was a problem with your login and/or errors exist in your form. ".trim(),
				"Error message is not displayed as expected");
	}

	@Test
	public void TC03() {
		HomePage homePage = new HomePage();
		RegisterAccount account = new RegisterAccount(Constant.VALID_USERNAME, Utilities.generateRandomPassword());

		Log4j.info("TC03: Verify that user cannot log into Railway with invalid password ");
		Log4j.info("Step 1: Navigate to QA Railway Website");
		homePage = homePage.open();

		Log4j.info("Step 2: Click on 'Login' tab");
		LoginPage loginPage = homePage.gotoPage(PageMenu.LOGIN, LoginPage.class);

		Log4j.info("Step 3: Enter valid Email and invalid Password");
		Log4j.info("Step 4: Click on 'Login' button");
		loginPage = loginPage.login(account);

		Log4j.info(
				"VP: Error message \"There was a problem with your login and/or errors exist in your form.\" is displayed");
		String actualMsg = loginPage.getLoginErrorMsg();
		Assert.assertEquals(actualMsg, "There was a problem with your login and/or errors exist in your form.",
				"Error message is not displayed as expected");
	}

	@Test
	public void TC04() {
		HomePage homePage = new HomePage();
		RegisterAccount account = new RegisterAccount(Constant.VALID_USERNAME, null);

		Log4j.info("TC04: Verify that system shows message when user enters wrong password many times");
		Log4j.info("Step 1: Navigate to QA Railway Website");
		homePage = homePage.open();

		Log4j.info("Step 2: Click on 'Login' tab");
		LoginPage loginPage = homePage.gotoPage(PageMenu.LOGIN, LoginPage.class);

		Log4j.info("Step 3: Enter valid information into 'Username' textbox except 'Password' textbox.");
		Log4j.info("Step 4: Click on 'Login' button");
		Log4j.info("Step 5: Repeat step 3 and 4 three more times");
		loginPage = loginPage.login(account);

		Log4j.info("VP: \"Invalid username or password. Please try again\" is shown");
		String actualMsg = loginPage.getLoginErrorMsg();
		Assert.assertEquals(actualMsg, "Invalid username or password. Please try again".trim(),
				"Error message is not displayed as expected");

		for (int i = 2; i <= 4; i++) {
			loginPage = loginPage.login(account);
			actualMsg = loginPage.getLoginErrorMsg();
		}
		Log4j.info(
				"VP: User can't login and message \"You have used 4 out of 5 login attempts. After all 5 have been used, you will be unable to login for 15 minutes.\" appears.");

		Assert.assertEquals(actualMsg,
				"You have used 4 out of 5 login attempts. After all 5 have been used, you will be unable to login for 15 minutes."
						.trim(),
				"Error message is not displayed as expected");
	}

	@Test
	public void TC05() {
		HomePage homePage = new HomePage();
		RegisterPage registerPage = new RegisterPage();
		RegisterAccount unactivatedAccount = PreconditionHelper.generateRandomRegisterAccount();

		Log4j.info("TC05: Verify that user can't login with an account hasn't been activated");
		Log4j.info("Pre-condition: a not-active account is existing");
		homePage = homePage.open();

		unactivatedAccount = PreconditionHelper.createInactiveAccount(unactivatedAccount);
		registerPage = registerPage.registerNewAccount(unactivatedAccount);

		Log4j.info("Step 1: Navigate to QA Railway Website");
		homePage = homePage.open();

		Log4j.info("Step 2: Click on 'Login' tab");
		LoginPage loginPage = homePage.gotoPage(PageMenu.LOGIN, LoginPage.class);

		Log4j.info("Step 3: Enter username and password of account hasn't been activated.");
		Log4j.info("Step 4: Click on 'Login' button");
		loginPage = loginPage.login(unactivatedAccount);

		Log4j.info(
				"VP: User can't login and message \"Invalid username or password. Please try again.\" appears.");
		String actualMsg = loginPage.getLoginErrorMsg();
		Assert.assertEquals(actualMsg, "Invalid username or password. Please try again.".trim(),
				"Error message is not displayed as expected");
	}

}
