package Railway;

import java.time.LocalDate;

import org.testng.Assert;
import org.testng.annotations.Test;

import Common.DataProviders;
import Common.Log4j;
import Constant.PageMenu;
import Constant.TicketTableCol;

public class CancelBooking extends TestBase {

	@Test(dataProvider = "getBookTicketData", dataProviderClass = DataProviders.class)
	public void TC16(BookTicketData bookTicketData) {
		HomePage homePage = new HomePage();
		TimetablePage timetablePage = new TimetablePage();
		LocalDate targetDate = LocalDate.now().plusDays(3);
		bookTicketData.setDepartDate(targetDate);
		RegisterAccount account = PreconditionHelper.generateRandomRegisterAccount();

		Log4j.info("TC16: Verify that user can cancel a ticket");
		Log4j.info("Pre-condition: an actived account is existing");
		account = PreconditionHelper.createAnActiveAccount(account);

		Log4j.info("Step 1: Navigate to QA Railway Website");
		homePage = homePage.open();

		Log4j.info("Step 2: Login with a valid account");
		LoginPage loginPage = homePage.gotoPage(PageMenu.LOGIN, LoginPage.class);
		homePage = loginPage.login(account);

		Log4j.info("Step 3: Book a ticket");
		BookTicketPage bookTicketPage = homePage.gotoPage(PageMenu.BOOK_TICKET, BookTicketPage.class);

		bookTicketPage = bookTicketPage.bookTicket(bookTicketData);

		Log4j.info("Step 4:  Click on \"My ticket\" tab");
		MyTicketPage myTicketPage = bookTicketPage.gotoPage(PageMenu.MY_TICKET, MyTicketPage.class);

		Log4j.info("Step 5: Click on \"Cancel\" button of ticket which user want to cancel");
		Log4j.info("Step 6: Click on \"OK\" button on Confirmation message \"Are you sure?\"");
		myTicketPage = myTicketPage.cancelBooking(bookTicketData.getDepartStation(), bookTicketData.getArriveStation());

		Log4j.info("VP: The canceled ticket is disappeared.");
		int departCol = timetablePage.getColIndexByHeader(TicketTableCol.DEPART_STATION);
		int arriveCol = timetablePage.getColIndexByHeader(TicketTableCol.ARRIVE_STATION);
		Boolean actualResult = myTicketPage.isTicketCanceled(departCol, arriveCol, bookTicketData);

		Assert.assertFalse(actualResult, "The canceled ticket is not disappeared");
	}
}
