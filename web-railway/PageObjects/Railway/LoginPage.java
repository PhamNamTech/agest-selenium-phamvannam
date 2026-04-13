package Railway;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import Common.Utilities;
import Constant.Constant;
import Constant.FieldsLogin;
import Constant.PageMenu;
import io.qameta.allure.Step;

public class LoginPage extends GeneralPage {
	// Locators
	private final By _txtUsername = By.xpath("//input[@id='username']");
	private final By _txtPassword = By.xpath("//input[@id='password']");
	private final By _btnLogin = By.xpath("//input[@value='login']");
	private final By _lblLoginErrorMsg = By
			.xpath("//div[@id='content']//p[contains(@class, 'message error LoginForm')]");
	private final By _linkForgotPassword = By.xpath("//div[@id='content']//a[contains(@href,'ForgotPassword')]");
	private final By _txtEmailForgotPassword = By.xpath("//input[@id='email']");
	private final By _btnSendInstructions = By.xpath("//div[@id='content']//input[@value='Send Instructions']");
	private final By _txtNewPassword = By.xpath("//input[@id='newPassword']");
	private final By _txtConfirmNewPassword = By.xpath("//input[@id='confirmPassword']");
	private final By _btnResetPassword = By.xpath("//input[@value='Reset Password']");
	private final By _txtResetPasswordToken = By.xpath("//input[@id='resetToken']");
	private final By _lblForgotPasswordGeneralMsg = By.xpath("//div[@id='content']//p[contains(@class,'message')]");
	private final By _lblForgotPasswordConfirmPasswordMsg = By
			.xpath("//label[@for='confirmPassword' and @class='validation-error']");

	// Elements
	private WebElement getTxtUsername() {
		return Constant.WEBDRIVER.findElement(_txtUsername);
	}

	private WebElement getTxtPassword() {
		return Constant.WEBDRIVER.findElement(_txtPassword);
	}

	private WebElement getBtnLogin() {
		return Constant.WEBDRIVER.findElement(_btnLogin);
	}

	private WebElement getLblLoginErrorMsg() {
		return Constant.WEBDRIVER.findElement(_lblLoginErrorMsg);
	}

	private WebElement getLinkForgotPassword() {
		return Constant.WEBDRIVER.findElement(_linkForgotPassword);
	}

	private WebElement getTxtNewPassword() {
		return Constant.WEBDRIVER.findElement(_txtNewPassword);
	}

	private WebElement getTxtConfirmNewPassword() {
		return Constant.WEBDRIVER.findElement(_txtConfirmNewPassword);
	}

	private WebElement getBtnResetPassword() {
		return Constant.WEBDRIVER.findElement(_btnResetPassword);
	}

	private WebElement getLblForgotPasswordGeneralMsg() {
		Utilities.waitForVisible(_lblForgotPasswordGeneralMsg);
		return Constant.WEBDRIVER.findElement(_lblForgotPasswordGeneralMsg);
	}

	private WebElement getLblForgotPasswordConfirmPasswordMsg() {
		return Constant.WEBDRIVER.findElement(_lblForgotPasswordConfirmPasswordMsg);
	}

	private By getLocator(FieldsLogin element) {
		switch (element) {
		case USERNAME:
			return _txtUsername;

		case PASSWORD:
			return _txtPassword;

		case LOGIN_ERROR_MSG:
			return _lblLoginErrorMsg;

		case FORGOT_PASSWROD_GENERAL_MSG:
			return _lblForgotPasswordGeneralMsg;

		case FORGOT_PASSWORD_LINK:
			return _linkForgotPassword;

		case FORGOT_PASSWORD_CONFIRM_PASSWORD_MSG:
			return _lblForgotPasswordConfirmPasswordMsg;

		case RESET_PASSWORD_TOKEN:
			return _txtResetPasswordToken;

		default:
			throw new IllegalArgumentException("Unsupported element: " + element);
		}
	}

	// Methods
	public boolean isLoggedIn() {
		try {
			return isMenuDisplayed(PageMenu.LOGOUT);
		} catch (Exception e) {
			return false;
		}
	}

	@Step("Enter email '{account.email}' and password, then click Login")
	@SuppressWarnings("unchecked")
	public <T extends GeneralPage> T login(RegisterAccount account) {
		if (account.getEmail() != null) {
			this.getTxtUsername().clear();
			this.getTxtUsername().sendKeys(account.getEmail());
		}

		if (account.getPassword() != null) {
			this.getTxtPassword().clear();
			this.getTxtPassword().sendKeys(account.getPassword());
		}

		Utilities.scrollToElement(this.getBtnLogin());
		this.getBtnLogin().click();

		if (!this.isLoggedIn()) {
			return (T) this;
		}
		return (T) new HomePage();
	}

	public String getLoginErrorMsg() {
		return this.getLblLoginErrorMsg().getText();
	}

	public String getForgotPasswordGeneralMsg() {
		return getLblForgotPasswordGeneralMsg().getText();
	}

	public String getForgotConfirmPasswordMsg() {
		return getLblForgotPasswordConfirmPasswordMsg().getText();
	}

	public boolean isResetPasswordTokenDisplayed() {
		return Utilities.isElementHasValue(getLocator(FieldsLogin.RESET_PASSWORD_TOKEN));
	}

	public LoginPage forgotPassword(RegisterAccount account) {
		LoginPage loginPage = new LoginPage();
		loginPage.getLinkForgotPassword().click();
		return sendInstructions(account.getEmail());
	}

	public LoginPage enterResetPassword(RegisterAccount account, String confirmPassword) {
		this.getTxtNewPassword().clear();
		this.getTxtNewPassword().sendKeys(account.getPassword());

		this.getTxtConfirmNewPassword().clear();
		this.getTxtConfirmNewPassword().sendKeys(confirmPassword);

		this.getBtnResetPassword().click();

		return new LoginPage();
	}

	public LoginPage sendInstructions(String email) {
		Constant.WEBDRIVER.findElement(_txtEmailForgotPassword).clear();
		Constant.WEBDRIVER.findElement(_txtEmailForgotPassword).sendKeys(email);

		Constant.WEBDRIVER.findElement(_btnSendInstructions).click();

		return new LoginPage();
	}

}
