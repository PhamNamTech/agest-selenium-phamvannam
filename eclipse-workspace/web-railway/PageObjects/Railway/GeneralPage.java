package Railway;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import Constant.Constant;
import Constant.PageMenu;
import io.qameta.allure.Step;

public class GeneralPage {
	// Locators
	private final By _lblWelcomeMsg = By.xpath("//div[@id='banner']//strong");

	// Elements
	private WebElement getLblWelcomeMessage() {
		return Constant.WEBDRIVER.findElement(_lblWelcomeMsg);
	}

	// Methods
	public String getWelcomeMsg() {
		return this.getLblWelcomeMessage().getText();
	}

	@Step("Click on '{menu}' tab")
	public <T> T gotoPage(PageMenu menu, Class<T> pageClass) {
		Constant.WEBDRIVER.findElement(menu.getLocator()).click();
		try {
			return pageClass.getDeclaredConstructor().newInstance();
		} catch (Exception e) {
			throw new RuntimeException("Cannot navigate to page: " + pageClass.getSimpleName(), e);
		}
	}

	public boolean isMenuDisplayed(PageMenu menu) {
		return !Constant.WEBDRIVER.findElements(menu.getLocator()).isEmpty();
	}

}
