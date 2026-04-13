package Railway;

import java.time.LocalDate;

import Constant.SeatType;
import Constant.StationCity;

public class BookTicketData extends DataObjectBase {
	// Fields
	private LocalDate departDate;
	private StationCity departStation;
	private StationCity arriveStation;
	private SeatType seatType;
	private Integer ticketAmount;

	// Getters
	public LocalDate getDepartDate() {
		return departDate;
	}

	public StationCity getDepartStation() {
		return departStation;
	}

	public StationCity getArriveStation() {
		return arriveStation;
	}

	public SeatType getSeatType() {
		if (seatType == null) {
			return SeatType.HARD_SEAT;
		} else {
			return seatType;
		}
	}

	public Integer getTicketAmount() {
		return ticketAmount;
	}

	// Setters
	public void setDepartDate(LocalDate departDate) {
		this.departDate = departDate;
	}

	public void setDepartStation(StationCity departStation) {
		this.departStation = departStation;
	}

	public void setArriveStation(StationCity arriveStation) {
		this.arriveStation = arriveStation;
	}

	public void setSeatType(SeatType seatType) {
		if (seatType == null) {
			seatType = SeatType.HARD_SEAT;
		} else {
			this.seatType = seatType;
		}
	}

	public void setTicketAmount(Integer ticketAmount) {
		this.ticketAmount = ticketAmount;
	}

	// Constructor
	public BookTicketData() {
	}

	public BookTicketData(LocalDate departDate, StationCity departStation, StationCity arriveStation, SeatType seatType,
			int ticketAmount) {
		this.departDate = departDate;
		this.departStation = departStation;
		this.arriveStation = arriveStation;
		this.seatType = seatType;
		this.ticketAmount = ticketAmount;
	}

}
