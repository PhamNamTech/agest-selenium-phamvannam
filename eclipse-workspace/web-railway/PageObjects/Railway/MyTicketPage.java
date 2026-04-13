package Railway;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;

import Common.Utilities;
import Constant.Constant;
import Constant.StationCity;
import Constant.TicketTableCol;

public class MyTicketPage extends GeneralPage {
	// Dynamic locators
	private static final String _btnCancel = "//input[@value='Cancel']";

	// Elements

	// Methods
	public MyTicketPage cancelBooking(StationCity departStation, StationCity arriveStation) {
		TimetablePage timetablePage = new TimetablePage();

		int departCol = timetablePage.getColIndexByHeader(TicketTableCol.DEPART_STATION);
		int arriveCol = timetablePage.getColIndexByHeader(TicketTableCol.ARRIVE_STATION);
		By linkCheckPrice = By.xpath(TableHelper.getRowBy2Cols(departCol, departStation.getDisplayText(), arriveCol,
				arriveStation.getDisplayText()) + _btnCancel);

		Utilities.scrollToElement(linkCheckPrice);
		Utilities.clickByJs(Constant.WEBDRIVER.findElement(linkCheckPrice));

		Alert alert = Constant.WEBDRIVER.switchTo().alert();
		alert.accept();

		return new MyTicketPage();
	}

	public boolean isTicketCanceled(int departCol, int arriveCol, BookTicketData bookTicketData) {
		By bookedTicket = By
				.xpath(TableHelper.getRowBy2Cols(departCol, bookTicketData.getDepartStation().getDisplayText(),
						arriveCol, bookTicketData.getArriveStation().getDisplayText()));

		Boolean actualResult = Constant.WEBDRIVER.findElements(bookedTicket).size() > 0;

		return actualResult;
	}
}
