package hotelReservation;

import java.util.Date;

public class Booking {
	private int bookingId;
	private int userId;
	private int roomNumber;
	private Date checkIn;
	private Date checkOut;
	private int totalCost;
	private RoomType roomTypeAtBooking;
	private int pricePerNightAtBooking;
	private int userBalanceAtBooking;



	public Booking(int bookingId, int userId, int roomNumber, Date checkIn, Date checkOut, int totalCost,
			RoomType roomTypeAtBooking, int pricePerNightAtBooking, int userBalanceAtBooking) {
		super();
		this.bookingId = bookingId;
		this.userId = userId;
		this.roomNumber = roomNumber;
		this.checkIn = checkIn;
		this.checkOut = checkOut;
		this.totalCost = totalCost;
		this.roomTypeAtBooking = roomTypeAtBooking;
		this.pricePerNightAtBooking = pricePerNightAtBooking;
		this.userBalanceAtBooking = userBalanceAtBooking;
	}



	public int getBookingId() {
		return bookingId;
	}



	public int getUserId() {
		return userId;
	}



	public int getRoomNumber() {
		return roomNumber;
	}



	public Date getCheckIn() {
		return checkIn;
	}



	public Date getCheckOut() {
		return checkOut;
	}



	public int getTotalCost() {
		return totalCost;
	}



	public RoomType getRoomTypeAtBooking() {
		return roomTypeAtBooking;
	}



	public int getPricePerNightAtBooking() {
		return pricePerNightAtBooking;
	}



	public int getUserBalanceAtBooking() {
		return userBalanceAtBooking;
	}



	@Override
	public String toString() {
		return "Booking{" +
				"bookingId=" + bookingId +
				", userId=" + userId +
				", roomNumber=" + roomNumber +
				", checkIn=" + checkIn +
				", checkOut=" + checkOut +
				", totalCost=" + totalCost +
				", roomTypeAtBooking=" + roomTypeAtBooking +
				", pricePerNightAtBooking=" + pricePerNightAtBooking +
				", userBalanceAtBooking=" + userBalanceAtBooking +
				'}';
	}

}
