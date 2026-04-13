package Railway;

import org.testng.Assert;
import org.testng.annotations.Test;

import Common.Log4j;
import Common.Utilities;
import Constant.PageMenu;
import Guerrilla.GuerrillaHomePage;

public class ResetPassword extends TestBase {

	@Test
	public void TC10() {
		HomePage homePage = new HomePage();
		GuerrillaHomePage guerrillaHomePage = new GuerrillaHomePage();
		RegisterAccount account = PreconditionHelper.generateRandomRegisterAccount();

		Log4j.info("TC10: Verify that reset password shows error if the new password is same as current");
		Log4j.info("Pre-condition: an actived account is existing");
		account = PreconditionHelper.createAnActiveAccount(account);

		Log4j.info("Step 1: Navigate to QA Railway Login page");
		homePage = homePage.open();
		LoginPage loginPage = homePage.gotoPage(PageMenu.LOGIN, LoginPage.class);

		Log4j.info("Step 2: Click on \"Forgot Password page\" link");
		Log4j.info("Step 3: Enter the email address of the activated account");
		Log4j.info("Step 4: Click on \"Send Instructions\" button");
		Log4j.info("Step 5: Login to the mailbox (the same mailbox when creating account) ");
		Log4j.info(
				"Step 6: Open email with subject contaning \"Please reset your password\" and the email of the account at step 3");
		Log4j.info("Step 7: Click on reset link");
		loginPage = loginPage.forgotPassword(account);
		guerrillaHomePage = guerrillaHomePage.open();
		loginPage = guerrillaHomePage.confirmForgotPasswordEmail(account.getEmail());

		Log4j.info(
				"VP: Redirect to Railways page and Form \"Password Change Form\" is shown with the reset password token");
		Assert.assertTrue(loginPage.isResetPasswordTokenDisplayed(),
				"\"Password Change Form\" is not shown with the reset password token");

		Log4j.info("Step 8: Input same password into 2 fields \"new password\" and \"confirm password\"");
		Log4j.info("Step 9: Click Reset Password");
		loginPage = loginPage.enterResetPassword(account, account.getPassword());

		Log4j.info("VP: The new password cannot be the same with the current password\\\" is shown");
		String actualMsg = loginPage.getForgotPasswordGeneralMsg();
		Assert.assertEquals(actualMsg, "The new password cannot be the same with the current password",
				"The message is not displayed as expected");

	}

	@Test
	public void TC11() {
		HomePage homePage = new HomePage();
		GuerrillaHomePage guerrillaHomePage = new GuerrillaHomePage();
		String confirmPassword = Utilities.generateRandomPassword();
		RegisterAccount account = PreconditionHelper.generateRandomRegisterAccount();

		Log4j.info(
				"TC11: Verify that reset password shows error if the new password and confirm password doesn't match");
		Log4j.info("Pre-condition: an actived account is existing");
		account = PreconditionHelper.createAnActiveAccount(account);

		Log4j.info("Step 1: Navigate to QA Railway Login page");
		homePage = homePage.open();
		LoginPage loginPage = homePage.gotoPage(PageMenu.LOGIN, LoginPage.class);

		Log4j.info("Step 2: Click on \"Forgot Password page\" link");
		Log4j.info("Step 3: Enter the email address of the activated account");
		Log4j.info("Step 4: Click on \"Send Instructions\" button");
		Log4j.info("Step 5: Login to the mailbox (the same mailbox when creating account)");
		Log4j.info(
				"Step 6: Open email with subject contaning \"Please reset your password\" and the email of the account at step 3");
		Log4j.info("Step 7: Click on reset link");
		loginPage = loginPage.forgotPassword(account);
		guerrillaHomePage = guerrillaHomePage.open();
		loginPage = guerrillaHomePage.confirmForgotPasswordEmail(account.getEmail());

		Log4j.info(
				"VP: Redirect to Railways page and Form \"Password Change Form\" is shown with the reset password token");
		Assert.assertTrue(loginPage.isResetPasswordTokenDisplayed(),
				"\"Password Change Form\" is not shown with the reset password token");

		Log4j.info("Step 8: Input different input into 2 fields  \"new password\" and \"confirm password\"");
		Log4j.info("Step 9: Click Reset Password");
		loginPage = loginPage.enterResetPassword(account, confirmPassword);

		Log4j.info(
				"VP: Error message \"Could not reset password. Please correct the errors and try again.\" displays above the form.\r\n"
						+ "Error message \"The password confirmation did not match the new password.\" displays next to the confirm password field.");
		Assert.assertEquals(loginPage.getForgotPasswordGeneralMsg(),
				"Could not reset password. Please correct the errors and try again.",
				"The message is not displayed as expected");
		Assert.assertEquals(loginPage.getForgotConfirmPasswordMsg(),
				"The password confirmation did not match the new password.",
				"The message is not displayed as expected");
	}

}
