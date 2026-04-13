package Railway;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import Constant.Constant;

public class TicketPricePage extends GeneralPage {
	// Locators
	private final By _lblTblHeader = By
			.xpath("//table[@class='MyTable MedTable']//th[contains(text(), 'Ticket price from')]");

	// Dynamic locators
	private final String _dymSeatType = "//div[@class='DivTable']//td[text()='%s']";
	private final String _dymSeatPrice = "//div[@class='DivTable']//th[normalize-space()='Price (VND)']/following-sibling::td[%s]";

	// Elements
	private WebElement getLblTblHeader() {
		return Constant.WEBDRIVER.findElement(_lblTblHeader);
	}

	// Methods
	public String getTicketPriceTblHeader() {
		return getLblTblHeader().getText();
	}

	public String getPriceOfSeatType(String seatType) {
		int index = getCellIndexOfSeatType(seatType);
		return Constant.WEBDRIVER.findElement(By.xpath(String.format(_dymSeatPrice, index))).getText();
	}

	public int getCellIndexOfSeatType(String seatType) {
		String xpathString = String.format(_dymSeatType, seatType);
		return Integer.parseInt(Common.Utilities.waitForVisible(By.xpath(xpathString)).getDomProperty("cellIndex"));
	}
}
