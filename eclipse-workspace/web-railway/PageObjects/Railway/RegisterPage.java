package Railway;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import Common.Utilities;
import Constant.Constant;

public class RegisterPage extends GeneralPage {
	// Locators
	private final By _txtEmail = By.xpath("//input[@id='email']");
	private final By _txtPassword = By.xpath("//input[@id='password']");
	private final By _txtConfirmPassword = By.xpath("//input[@id='confirmPassword']");
	private final By _txtPIPNumber = By.xpath("//input[@id='pid']");
	private final By _btnRegister = By.xpath("//input[@value='Register']");
	private final By _lblMsgGeneralError = By.xpath("//div[@id='content']/p[@class='message error']");
	private final By _lblMsgRegistrationConfirmed = By.xpath("//div[@id='content']//p");
	private final By _lblMsgThankyou = By.xpath("//div[@id='content']//h1");
	private final By _lblMsgErrorPassword = By
			.xpath("//form[@id='RegisterForm']//label[@class='validation-error' and @for='password']");
	private final By _lblMsgErrorPip = By
			.xpath("//form[@id='RegisterForm']//label[@class='validation-error' and @for='pid']");

	// Elements
	private WebElement getTxtEmail() {
		return Constant.WEBDRIVER.findElement(_txtEmail);
	}

	private WebElement getTxtPassword() {
		return Constant.WEBDRIVER.findElement(_txtPassword);
	}

	private WebElement getTxtConfirmPassword() {
		return Constant.WEBDRIVER.findElement(_txtConfirmPassword);
	}

	private WebElement getTxtPIPNumber() {
		return Constant.WEBDRIVER.findElement(_txtPIPNumber);
	}

	private WebElement getBtnRegister() {
		return Constant.WEBDRIVER.findElement(_btnRegister);
	}

	private WebElement getLblMsgGeneralError() {
		return Constant.WEBDRIVER.findElement(_lblMsgGeneralError);
	}

	private WebElement getLblMsgErrorPassword() {
		return Constant.WEBDRIVER.findElement(_lblMsgErrorPassword);
	}

	private WebElement getLblMsgErrorPip() {
		return Constant.WEBDRIVER.findElement(_lblMsgErrorPip);
	}

	// Methods
	public String geMsgGeneralError() {
		return this.getLblMsgGeneralError().getText();
	}

	public String getMsgErrorPassword() {
		return this.getLblMsgErrorPassword().getText();
	}

	public String getMsgErrorPip() {
		return this.getLblMsgErrorPip().getText();
	}

	public String getMsgThankyou() {
		return Utilities.waitForVisible(_lblMsgThankyou).getText();
	}

	public String getMsgRegistrationConfirmed() {
		return Utilities.waitForVisible(_lblMsgRegistrationConfirmed).getText();
	}

	public RegisterPage registerNewAccount(RegisterAccount account) {
		if (account.getEmail() != null) {
			this.getTxtEmail().clear();
			this.getTxtEmail().sendKeys(account.getEmail());
		}

		if (account.getPassword() != null) {
			this.getTxtPassword().clear();
			this.getTxtPassword().sendKeys(account.getPassword());

			Utilities.scrollToElement(getTxtConfirmPassword());
			this.getTxtConfirmPassword().clear();
			this.getTxtConfirmPassword().sendKeys(account.getPassword());
		}

		if (account.getPip() != null) {
			Utilities.scrollToElement(getTxtPIPNumber());
			this.getTxtPIPNumber().clear();
			this.getTxtPIPNumber().sendKeys(account.getPip());
		}

		Utilities.scrollToElement(getBtnRegister());
		this.getBtnRegister().click();

		return this;
	}
}
