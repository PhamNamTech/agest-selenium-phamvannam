package Railway;

import Common.Utilities;
import Constant.Constant;
import Constant.PageMenu;
import Guerrilla.GuerrillaHomePage;

public class PreconditionHelper {

	// Account
	public static RegisterAccount createAnActiveAccount(RegisterAccount account) {
		GuerrillaHomePage guerrillaHomePage = new GuerrillaHomePage();
		HomePage homePage = new HomePage();
		homePage = homePage.open();
		String railwayHanlde = Constant.WEBDRIVER.getWindowHandle();

		Utilities.openUrlInNewTab(Constant.GUERRILLA_URL);
		String guerrillaHanlde = Constant.WEBDRIVER.getWindowHandle();
		account.setEmail(guerrillaHomePage.createNewEmail(account.getEmail()));

		Constant.WEBDRIVER.switchTo().window(railwayHanlde);
		RegisterPage registerPage = homePage.gotoPage(PageMenu.REGISTER, RegisterPage.class);
		PreconditionHelper.activateAccount(account, guerrillaHanlde);

		return account;
	}

	public static RegisterAccount createAnEmail(RegisterAccount account) {
		GuerrillaHomePage guerrillaHomePage = new GuerrillaHomePage();
		String railwayHanlde = Constant.WEBDRIVER.getWindowHandle();

		Utilities.openUrlInNewTab(Constant.GUERRILLA_URL);
		account.setEmail(guerrillaHomePage.createNewEmail(account.getEmail()));

		Constant.WEBDRIVER.switchTo().window(railwayHanlde);
		return account;
	}

	public static void activateAccount(RegisterAccount account, String guerrillaHandle) {
		RegisterPage registerPage = new RegisterPage();
		GuerrillaHomePage guerrillaHomePage = new GuerrillaHomePage();

		registerPage = registerPage.registerNewAccount(account);
		Constant.WEBDRIVER.switchTo().window(guerrillaHandle);

		guerrillaHomePage.confirmRegistrationEmail(account.getEmail());

		Constant.WEBDRIVER.close();
		Constant.WEBDRIVER.switchTo().window(guerrillaHandle);
		Constant.WEBDRIVER.close();

		Constant.WEBDRIVER.switchTo().window(Constant.WEBDRIVER.getWindowHandles().iterator().next());
	}

	public static RegisterAccount createInactiveAccount(RegisterAccount account) {
		RegisterPage registerPage = new RegisterPage();
		GuerrillaHomePage guerrillaHomePage = new GuerrillaHomePage();
		String railwayHanlde = Constant.WEBDRIVER.getWindowHandle();

		Utilities.openUrlInNewTab(Constant.GUERRILLA_URL);
		account.setEmail(guerrillaHomePage.createNewEmail(account.getEmail()));

		Constant.WEBDRIVER.switchTo().window(railwayHanlde);
		registerPage.gotoPage(PageMenu.REGISTER, RegisterPage.class);
		return account;
	}

	public static RegisterAccount generateRandomRegisterAccount() {
		return new RegisterAccount(Utilities.generateRandomEmail(), Utilities.generateRandomPassword(),
				Utilities.generateRandomPIP());
	}

}
