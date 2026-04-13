package Railway;

import org.testng.Assert;
import org.testng.annotations.Test;

import Common.Log4j;
import Constant.Constant;
import Constant.PageMenu;

public class LogoutTest extends TestBase {

	@Test
	public void TC06() {
		HomePage homePage = new HomePage();
		RegisterAccount account = new RegisterAccount(Constant.VALID_USERNAME, Constant.VALID_PASSWORD);

		Log4j.info("TC06: Verify that user is redirected to Home page after logging out ");
		Log4j.info("Step 1: Navigate to QA Railway Website");
		homePage = homePage.open();

		Log4j.info("Step 2: Login with valid Email and Password");
		LoginPage loginPage = homePage.gotoPage(PageMenu.LOGIN, LoginPage.class);
		homePage = loginPage.login(account);

		Log4j.info("Step 3:  Click on 'FAQ' tab");
		FAQPage faqPage = homePage.gotoPage(PageMenu.FAQ, FAQPage.class);

		Log4j.info("Step 4: Click on 'Log out' tab");
		homePage = homePage.gotoPage(PageMenu.LOGOUT, HomePage.class);

		Log4j.info("VP: Home page displays.");
		Assert.assertTrue(homePage.isHomePageDisplayed(), "Home page is not displays");

		Log4j.info("VP: \"Log out\" tab is disappeared.");
		Assert.assertFalse(homePage.isMenuDisplayed(PageMenu.LOGOUT), "\"Log out\" tab is not disappeared");
	}

}
