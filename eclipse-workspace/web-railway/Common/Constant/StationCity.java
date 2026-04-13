package Constant;

public enum StationCity {
    // Station cities
	SAI_GON("Sài Gòn"),
    PHAN_THIET("Phan Thiết"),
    NHA_TRANG("Nha Trang"),
    DA_NANG("Đà Nẵng"),
    HUE("Huế"),
    QUANG_NGAI("Quảng Ngãi");
	
    // Display text
	private final String stationCity;

    // Build station city
	StationCity(String stationCity) {
        this.stationCity = stationCity;
    }

    // Accessory
    public String getDisplayText() {
        return stationCity;
    }
}
