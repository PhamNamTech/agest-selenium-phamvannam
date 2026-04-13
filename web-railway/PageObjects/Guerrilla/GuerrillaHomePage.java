package Guerrilla;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import Common.Utilities;
import Constant.Constant;
import Railway.LoginPage;

public class GuerrillaHomePage {
	// Locators
	private final By _editNameBtn = By.xpath("//span[@class='editable button' and @id='inbox-id']");
	private final By _txtName = By.xpath("//span[@id='inbox-id']/input");
	private final By _linkConfirmAccount = By.xpath("//a[contains(text(), 'saferailway')]");
	private final By _checkboxScramble = By.xpath("//input[@id='use-alias']");
	private final By _fullEmailAddress = By.xpath("//span[@id='email-widget']");

	// Dynamic locators
	private final String _emailLetter = "//tbody[@id='email_list']//span[contains(text(),'%s')]";

	// Elements
	private WebElement getEditNameBtn() {
		return Constant.WEBDRIVER.findElement(_editNameBtn);
	}

	private WebElement getTxtName() {
		return Constant.WEBDRIVER.findElement(_txtName);
	}

	private WebElement getCheckboxScramble() {
		return Constant.WEBDRIVER.findElement(_checkboxScramble);
	}

	private WebElement getFullEmailAddress() {
		return Constant.WEBDRIVER.findElement(_fullEmailAddress);
	}

	private By getByEmailLetter(String letterKeyword) {
		switch (letterKeyword) {
			case "Registration":
				return By.xpath(String.format(_emailLetter, "confirmation code"));

			case "ForgotPassword":
				return By.xpath(String.format(_emailLetter, "password reset"));

			default:
				throw new IllegalArgumentException("Unsupported email type");
		}
	}

	private WebElement getEmailLetterElement(String letterKeyword) {
		return Constant.WEBDRIVER.findElement(getByEmailLetter(letterKeyword));
	}

	// Methods
	public String createNewEmail(String emailName) {
		Utilities.handleUnexpectedPopup();
		Utilities.handleGoogleVignette();
		Utilities.waitForVisible(_editNameBtn, Constant.WAIT_TIMEOUT).click();
		this.getTxtName().clear();
		this.getTxtName().sendKeys(emailName, Keys.ENTER);

		if (this.getCheckboxScramble().isEnabled() && this.getCheckboxScramble().isSelected()) {
			this.getCheckboxScramble().click();
		}

		return this.getFullEmailAddress().getText().trim();
	}

	public void confirmRegistrationEmail(String emailName) {
		Utilities.handleUnexpectedPopup();
		Utilities.handleGoogleVignette();
		this.getEditNameBtn().click();

		this.getTxtName().clear();
		this.getTxtName().sendKeys(emailName, Keys.ENTER);

		Utilities.waitForVisibleWithRefresh(getByEmailLetter("Registration"), Constant.WAIT_TIMEOUT);
		Utilities.scrollToElement(getEmailLetterElement("Registration"));
		Utilities.clickByJs(getEmailLetterElement("Registration"));

		WebElement confirmLink = Utilities.waitForClickable(_linkConfirmAccount);
		String confirmUrl = confirmLink.getDomAttribute("href");
		Utilities.openUrlInNewTab(confirmUrl);
	}

	public LoginPage confirmForgotPasswordEmail(String emailName) {
		Utilities.handleUnexpectedPopup();
		Utilities.handleGoogleVignette();
		this.getEditNameBtn().click();

		this.getTxtName().clear();
		this.getTxtName().sendKeys(emailName, Keys.ENTER);

		Utilities.waitForVisibleWithRefresh(getByEmailLetter("ForgotPassword"), Constant.WAIT_TIMEOUT);
		Utilities.scrollToElement(getEmailLetterElement("ForgotPassword"));
		Utilities.clickByJs(getEmailLetterElement("ForgotPassword"));

		Utilities.waitForClickable(_linkConfirmAccount, Constant.WAIT_TIMEOUT).click();

		for (String handle : Constant.WEBDRIVER.getWindowHandles()) {
			Constant.WEBDRIVER.switchTo().window(handle);
		}
		return new LoginPage();
	}

	public GuerrillaHomePage open() {
		Constant.WEBDRIVER.navigate().to(Constant.GUERRILLA_URL);
		return this;
	}
}
