package Constant;

public enum TicketTableCol {
	//Ticket table column headers
    DEPART_STATION("Depart Station"),
    ARRIVE_STATION("Arrive Station"),
    SEAT_TYPE("Seat Type"),
    DEPART_DATE("Depart Date"),
    AMOUNT("Amount");

	//Display text
    private final String displayName;

    //Build ticket table column
    TicketTableCol(String displayName) {
        this.displayName = displayName;
    }

    //Accessory
    public String getDisplayName() {
        return displayName;
    }
}

