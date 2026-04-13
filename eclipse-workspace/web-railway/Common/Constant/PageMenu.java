package Constant;

import org.openqa.selenium.By;

public enum PageMenu {
	// Menu items
	LOGIN("Login", "Login"),
    LOGOUT("Logout", "Logout"),
    FAQ("FAQ", "FAQ"),
    REGISTER("Register", "Register"),
    BOOK_TICKET("BookTicketPage", "Book Ticket"),
    TIMETABLE("TrainTimeListPage", "Timetable"),
    MY_TICKET("ManageTicket", "My Ticket");
	
	//Locators
	private final By locator;
	private final String displayName;
	
	//Build locator for each menu item
	PageMenu(String href, String displayName){
		this.locator = By.xpath("//div[@id='menu']//a[contains(@href,'" + href + "')]");
		this.displayName = displayName;
	}
	
	//Accessory
	public By getLocator() {
		return locator;
	}

	@Override
	public String toString() {
		return displayName;
	}
}
