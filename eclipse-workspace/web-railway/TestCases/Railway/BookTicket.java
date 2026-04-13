package Railway;

import java.time.LocalDate;
import java.util.EnumMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import Common.DataProviders;
import Common.Log4j;
import Common.Utilities;
import Constant.Constant;
import Constant.PageMenu;
import Constant.SeatType;
import Constant.TicketTableCol;

public class BookTicket extends TestBase {

	@Test(dataProvider = "getBookTicketData", dataProviderClass = DataProviders.class)
	public void TC12(BookTicketData bookTicketData) {
		HomePage homePage = new HomePage();
		RegisterAccount account = PreconditionHelper.generateRandomRegisterAccount();

		Log4j.info("TC12: Verify that user can book 1 ticket at a time");
		Log4j.info("Pre-condition: an actived account is existing");
		account = PreconditionHelper.createAnActiveAccount(account);

		Log4j.info("Step 1: Navigate to QA Railway Website");
		homePage = homePage.open();

		Log4j.info("Step 2: Login with a valid account");
		LoginPage loginPage = homePage.gotoPage(PageMenu.LOGIN, LoginPage.class);
		homePage = loginPage.login(account);

		Log4j.info("Step 3: Click on \"Book ticket\" tab");
		BookTicketPage bookTicketPage = homePage.gotoPage(PageMenu.BOOK_TICKET, BookTicketPage.class);
		LocalDate targetDate = bookTicketPage.getSelectedDepartDate(2, Constant.DATE_FORMAT);
		bookTicketData.setDepartDate(targetDate);

		Log4j.info("Step 4: Select the next 2 days from \"Depart date\"");
		Log4j.info("Step 5: Select Depart from \"Nha Trang\" and Arrive at \"Huế\"");
		Log4j.info("Step 6: Select \"Soft bed with air conditioner\" for \"Seat type\"");
		Log4j.info("Step 7: Select \"1\" for \"Ticket amount\"");
		Log4j.info("Step 8: Click on \"Book ticket\" button");
		bookTicketPage = bookTicketPage.bookTicket(bookTicketData);

		Log4j.info(
				"VP: Message \"Ticket booked successfully!\" displays. Ticket information display correctly (Depart Date,  Depart Station,  Arrive Station,  Seat Type,  Amount)");
		String actualCenterMsg = bookTicketPage.getCenterMsg();
		Assert.assertEquals(actualCenterMsg, "Ticket booked successfully!", "The message is not displayed as expected");

		String actualDepartStation = bookTicketPage
				.getTableCellValue(bookTicketData.getDepartStation().getDisplayText(), TicketTableCol.DEPART_STATION);
		Assert.assertEquals(actualDepartStation, bookTicketData.getDepartStation().getDisplayText(),
				"Departure information is not displaying correctly");

		String actualArriveStation = bookTicketPage
				.getTableCellValue(bookTicketData.getArriveStation().getDisplayText(), TicketTableCol.ARRIVE_STATION);
		Assert.assertEquals(actualArriveStation, bookTicketData.getArriveStation().getDisplayText(),
				"Arrival station information is displayed incorrectly");

		String actualSeatType = bookTicketPage.getTableCellValue(bookTicketData.getSeatType().getDisplayText(),
				TicketTableCol.SEAT_TYPE);
		Assert.assertEquals(actualSeatType, bookTicketData.getSeatType().getDisplayText(),
				"Seat type information is displayed incorrectly");

		String actualDepartDate = bookTicketPage.getTableCellValue(
				Utilities.formatDate(bookTicketData.getDepartDate()).toString(), TicketTableCol.DEPART_DATE);
		Assert.assertEquals(actualDepartDate, Utilities.formatDate(bookTicketData.getDepartDate()).toString(),
				"Departure date information is displayed incorrectly");

		String actualAmount = bookTicketPage.getTableCellValue(String.valueOf(bookTicketData.getTicketAmount()),
				TicketTableCol.AMOUNT);
		Assert.assertEquals(actualAmount, String.valueOf(bookTicketData.getTicketAmount()),
				"Ticket amount information is displayed incorrectly");
	}

	@Test(dataProvider = "getBookTicketData", dataProviderClass = DataProviders.class)
	public void TC13(BookTicketData bookTicketData) {
		HomePage homePage = new HomePage();
		RegisterAccount account = PreconditionHelper.generateRandomRegisterAccount();

		Log4j.info("TC13: Verify that user can book many tickets at a time");
		Log4j.info("Pre-condition: an actived account is existing");
		account = PreconditionHelper.createAnActiveAccount(account);

		Log4j.info("Step 1: Navigate to QA Railway Website");
		homePage = homePage.open();

		Log4j.info("Step 2: Login with a valid account");
		LoginPage loginPage = homePage.gotoPage(PageMenu.LOGIN, LoginPage.class);
		homePage = loginPage.login(account);

		Log4j.info("Step 3: Click on \"Book ticket\" tab");
		BookTicketPage bookTicketPage = homePage.gotoPage(PageMenu.BOOK_TICKET, BookTicketPage.class);

		Log4j.info("Step 4: Select the next 25 days from \"Depart date\"");
		Log4j.info("Step 5: Select \"Nha Trang\" for \"Depart from\" and \"Sài Gòn\" for \"Arrive at\"");
		Log4j.info("Step 6: Select \"Soft seat with air conditioner\" for \"Seat type\"");
		Log4j.info("Step 7: Select \"5\" for \"Ticket amount\"");
		Log4j.info("Step 8: Click on \"Book ticket\" button");
		LocalDate targetDate = bookTicketPage.getSelectedDepartDate(25, Constant.DATE_FORMAT);
		bookTicketData.setDepartDate(targetDate);
		bookTicketPage = bookTicketPage.bookTicket(bookTicketData);

		Log4j.info(
				"VP: Message \"Ticket booked successfully!\" displays. Ticket information display correctly (Depart Date,  Depart Station,  Arrive Station,  Seat Type,  Amount)");
		String actualCenterMsg = bookTicketPage.getCenterMsg();
		Assert.assertEquals(actualCenterMsg, "Ticket booked successfully!", "The message is not displayed as expected");

		String actualDepartStation = bookTicketPage
				.getTableCellValue(bookTicketData.getDepartStation().getDisplayText(), TicketTableCol.DEPART_STATION);
		Assert.assertEquals(actualDepartStation, bookTicketData.getDepartStation().getDisplayText(),
				"Departure information is not displaying correctly");

		String actualArriveStation = bookTicketPage
				.getTableCellValue(bookTicketData.getArriveStation().getDisplayText(), TicketTableCol.ARRIVE_STATION);
		Assert.assertEquals(actualArriveStation, bookTicketData.getArriveStation().getDisplayText(),
				"Arrival station information is displayed incorrectly");

		String actualSeatType = bookTicketPage.getTableCellValue(bookTicketData.getSeatType().getDisplayText(),
				TicketTableCol.SEAT_TYPE);
		Assert.assertEquals(actualSeatType, bookTicketData.getSeatType().getDisplayText(),
				"Seat type information is displayed incorrectly");

		String actualDepartDate = bookTicketPage.getTableCellValue(
				Utilities.formatDate(bookTicketData.getDepartDate()).toString(), TicketTableCol.DEPART_DATE);
		Assert.assertEquals(actualDepartDate, Utilities.formatDate(bookTicketData.getDepartDate()).toString(),
				"Departure date information is displayed incorrectly");

		String actualAmount = bookTicketPage.getTableCellValue(String.valueOf(bookTicketData.getTicketAmount()),
				TicketTableCol.AMOUNT);
		Assert.assertEquals(actualAmount, String.valueOf(bookTicketData.getTicketAmount()),
				"Ticket amount information is displayed incorrectly");
	}

	@Test(dataProvider = "getBookTicketData", dataProviderClass = DataProviders.class)
	public void TC14(BookTicketData bookTicketData) {
		HomePage homePage = new HomePage();
		Map<SeatType, String> prices = new EnumMap<>(SeatType.class);
		prices.put(SeatType.HARD_SEAT, "310000");
		prices.put(SeatType.SOFT_SEAT, "335000");
		prices.put(SeatType.SOFT_SEAT_WITH_AIR_CONDITIONER, "360000");
		prices.put(SeatType.HARD_BED, "410000");
		prices.put(SeatType.SOFT_BED, "460000");
		prices.put(SeatType.SOFT_BED_WITH_AIR_CONDITIONER, "510000");
		RegisterAccount account = PreconditionHelper.generateRandomRegisterAccount();

		Log4j.info("TC14: Verify that user can check price of ticket from Timetable");
		Log4j.info("Pre-condition: an actived account is existing");
		account = PreconditionHelper.createAnActiveAccount(account);

		Log4j.info("Step 1: Navigate to QA Railway Website");
		homePage = homePage.open();

		Log4j.info("Step 2: Login with a valid account");
		LoginPage loginPage = homePage.gotoPage(PageMenu.LOGIN, LoginPage.class);
		homePage = loginPage.login(account);

		Log4j.info("Step 3: Click on \"Timetable\" tab");
		TimetablePage timetablePage = homePage.gotoPage(PageMenu.TIMETABLE, TimetablePage.class);

		Log4j.info("Step 4: Click on \"check price\" link of the route from \"Đà Nẵng\" to \"Sài Gòn\"");
		TicketPricePage ticketPricePage = timetablePage.clickCheckPriceLink(bookTicketData.getDepartStation(),
				bookTicketData.getArriveStation());

		Log4j.info("VP: \"Ticket Price\" page is loaded.\r\n"
				+ "Ticket table shows \"Ticket price from Đà Nẵng to Sài Gòn\".\r\n"
				+ "Price for each seat displays correctly\r\n"
				+ "HS = 310000, SS = 335000, SSC = 360000, HB = 410000, SB = 460000, SBC = 510000");
		Assert.assertEquals(ticketPricePage.getTicketPriceTblHeader(), "Ticket price from Đà Nẵng to Sài Gòn",
				"The ticket table is not display as expected");

		for (Map.Entry<SeatType, String> entry : prices.entrySet()) {
			String actualPrice = ticketPricePage.getPriceOfSeatType(entry.getKey().getAbbreviation());
			Assert.assertEquals(actualPrice, entry.getValue(),
					"The " + entry.getKey() + " price is not display as expected");
		}
	}

	@Test(dataProvider = "getBookTicketData", dataProviderClass = DataProviders.class)
	public void TC15(BookTicketData bookTicketData) {
		HomePage homePage = new HomePage();
		LocalDate targetDate = LocalDate.now().plusDays(1);
		bookTicketData.setDepartDate(targetDate);
		RegisterAccount account = PreconditionHelper.generateRandomRegisterAccount();

		Log4j.info("TC15: Verify that user can book ticket from Timetable");
		Log4j.info("Pre-condition: an actived account is existing");
		account = PreconditionHelper.createAnActiveAccount(account);

		Log4j.info("Step 1: Navigate to QA Railway Website");
		homePage.open();

		Log4j.info("Step 2: Login with a valid account");
		LoginPage loginPage = homePage.gotoPage(PageMenu.LOGIN, LoginPage.class);
		homePage = loginPage.login(account);

		Log4j.info("Step 3: Click on \"Timetable\" tab");
		TimetablePage timetablePage = homePage.gotoPage(PageMenu.TIMETABLE, TimetablePage.class);

		Log4j.info("Step 4: Click on book ticket of route \"Quảng Ngãi\" to \"Huế\"");
		BookTicketPage bookTicketPage = timetablePage.bookTicketFromTimetable(bookTicketData.getDepartStation(),
				bookTicketData.getArriveStation());

		Log4j.info("VP: Book ticket form is shown with the corrected \"depart from\" and \"Arrive at\"");
		Assert.assertEquals(bookTicketPage.getSelectedDepartStation(),
				bookTicketData.getDepartStation().getDisplayText(),
				"The depart selection is not displayed as expected");
		Assert.assertEquals(bookTicketPage.getSelectedArriveStation(),
				bookTicketData.getArriveStation().getDisplayText(),
				"The arrive selection is not displayed as expected");

		Log4j.info("Step 5: Select Depart date = tomorrow");
		Log4j.info("Step 6: Select Ticket amount = 5");
		Log4j.info("Step 7: Click on \"Book ticket\" button");
		bookTicketPage = bookTicketPage.bookTicket(bookTicketData);

		Log4j.info("VP: Message \"Ticket booked successfully!\" displays. "
				+ "Ticket information display correctly (Depart Date,  Depart Station,  Arrive Station,  Seat Type,  Amount)");
		String actualCenterMsg = bookTicketPage.getCenterMsg();
		Assert.assertEquals(actualCenterMsg, "Ticket booked successfully!", "The message is not displayed as expected");

		String actualDepartStation = bookTicketPage
				.getTableCellValue(bookTicketData.getDepartStation().getDisplayText(), TicketTableCol.DEPART_STATION);
		Assert.assertEquals(actualDepartStation, bookTicketData.getDepartStation().getDisplayText(),
				"Departure information is not displaying correctly");

		String actualArriveStation = bookTicketPage
				.getTableCellValue(bookTicketData.getArriveStation().getDisplayText(), TicketTableCol.ARRIVE_STATION);
		Assert.assertEquals(actualArriveStation, bookTicketData.getArriveStation().getDisplayText(),
				"Arrival station information is displayed incorrectly");

		String actualSeatType = bookTicketPage.getTableCellValue(bookTicketData.getSeatType().getDisplayText(),
				TicketTableCol.SEAT_TYPE);
		Assert.assertEquals(actualSeatType, bookTicketData.getSeatType().getDisplayText(),
				"Seat type information is displayed incorrectly");

		String actualDepartDate = bookTicketPage.getTableCellValue(
				Utilities.formatDate(bookTicketData.getDepartDate()).toString(), TicketTableCol.DEPART_DATE);
		Assert.assertEquals(actualDepartDate, Utilities.formatDate(bookTicketData.getDepartDate()).toString(),
				"Departure date information is displayed incorrectly");

		String actualAmount = bookTicketPage.getTableCellValue(String.valueOf(bookTicketData.getTicketAmount()),
				TicketTableCol.AMOUNT);
		Assert.assertEquals(actualAmount, String.valueOf(bookTicketData.getTicketAmount()),
				"Ticket amount information is displayed incorrectly");
	}

}
