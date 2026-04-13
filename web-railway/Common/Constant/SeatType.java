package Constant;

public enum SeatType {
    // Seat type options
    HARD_SEAT("Hard seat", "HS"),
    SOFT_SEAT("Soft seat", "SS"),
    SOFT_SEAT_WITH_AIR_CONDITIONER("Soft seat with air conditioner", "SSC"),
    HARD_BED("Hard bed", "HB"),
    SOFT_BED("Soft bed", "SB"),
    SOFT_BED_WITH_AIR_CONDITIONER("Soft bed with air conditioner", "SBC");

    // Display text and abbreviation
    private final String seatType;
    private final String abbreviation;

    // Build seat type
    SeatType(String seatType, String abbreviation) {
        this.seatType = seatType;
        this.abbreviation = abbreviation;
    }

    // Accessory
    public String getDisplayText() {
        return seatType;
    }

    public String getAbbreviation() {
        return abbreviation;
    }
}
