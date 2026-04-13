package Railway;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import Constant.Constant;
import io.qameta.allure.Step;

public class HomePage extends GeneralPage{
	// Locators
	private final By _lblWelcomeMsg = By.xpath("//h1[contains(text(),'Welcome')]");
	private final By _linkCreateAccount = By.xpath("//div[@id='content']//a[contains(@href, 'Register')]");

	// Elements
	private WebElement getLinkCreateAccount() {
		return Constant.WEBDRIVER.findElement(_linkCreateAccount);
	}
	
	// Methods
	@Step("Navigate to QA Railway Website")
	public HomePage open() {
		Constant.WEBDRIVER.navigate().to(Constant.RAILWAY_URL);
		return this;
	}
	
	public boolean isHomePageDisplayed() {
		return !Constant.WEBDRIVER
				.findElements(_lblWelcomeMsg)
				.isEmpty();
	}
	
	public boolean isCreateAccountLinkDisplayed() {
		return !Constant.WEBDRIVER
				.findElements(_linkCreateAccount)
				.isEmpty();
	}
	
	public RegisterPage clickCreateAccountLink() {
	    getLinkCreateAccount().click();
	    return new RegisterPage();
	}
}
