package Railway;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import Common.Utilities;
import Constant.Constant;
import Constant.StationCity;
import Constant.TicketTableCol;

public class TimetablePage extends GeneralPage {
	// Locators
	private final By _tblHeader = By.xpath("//table[contains(@class,'MyTable')]//th");

	// Dynamic locators
	private final String _linkInTbl = "//a[normalize-space()='%s']";

	// Elements

	// Methods
	public int getColIndexByHeader(TicketTableCol ticketTableCol) {
		List<WebElement> headers = Constant.WEBDRIVER.findElements(_tblHeader);

		for (int i = 0; i < headers.size(); i++) {
			if (headers.get(i).getText().trim().equalsIgnoreCase(ticketTableCol.getDisplayName())) {
				return i + 1;
			}
		}
		throw new RuntimeException("Header in table not found: " + ticketTableCol.getDisplayName());
	}

	public BookTicketPage bookTicketFromTimetable(StationCity departStation, StationCity arriveStation) {
		int departCol = getColIndexByHeader(TicketTableCol.DEPART_STATION);
		int arriveCol = getColIndexByHeader(TicketTableCol.ARRIVE_STATION);

		By linkCheckPrice = By.xpath(TableHelper.getRowBy2Cols(departCol, departStation.getDisplayText(), arriveCol,
				arriveStation.getDisplayText()) + String.format(_linkInTbl, "book ticket"));

		Utilities.scrollToElement(linkCheckPrice);
		Utilities.clickByJs(Constant.WEBDRIVER.findElement(linkCheckPrice));

		return new BookTicketPage();
	}

	public TicketPricePage clickCheckPriceLink(StationCity departStation, StationCity arriveStation) {
		int departCol = getColIndexByHeader(TicketTableCol.DEPART_STATION);
		int arriveCol = getColIndexByHeader(TicketTableCol.ARRIVE_STATION);
		By linkCheckPrice = By.xpath(TableHelper.getRowBy2Cols(departCol, departStation.getDisplayText(), arriveCol,
				arriveStation.getDisplayText()) + String.format(_linkInTbl, "check price"));

		Utilities.scrollToElement(linkCheckPrice);
		Utilities.clickByJs(Constant.WEBDRIVER.findElement(linkCheckPrice));

		return new TicketPricePage();
	}
}
