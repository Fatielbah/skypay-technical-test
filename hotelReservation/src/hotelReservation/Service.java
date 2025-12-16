package hotelReservation;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Service {
	ArrayList<Room> rooms;
	ArrayList<User> users;
	ArrayList<Booking> bookings;
	private int bookingIdCounter;

	public Service() {
		this.rooms = new ArrayList<>();
		this.users = new ArrayList<>();
		this.bookings = new ArrayList<>();
		this.bookingIdCounter = 1;
	}

	void setRoom(int roomNumber, RoomType roomType, int roomPricePerNight) {
		try {
			if (roomPricePerNight < 0) {
				throw new IllegalArgumentException("Room price cannot be negative");
			}
			Room existingRoom = findRoomByNumber(roomNumber);
			if (existingRoom != null) {
				existingRoom.setRoomType(roomType);
				existingRoom.setPricePerNight(roomPricePerNight);
				System.out.println("Room " + roomNumber + " updated successfully");
			} else {
				Room newRoom = new Room(roomNumber, roomType, roomPricePerNight);
				rooms.add(0, newRoom); 
				System.out.println("Room " + roomNumber + " created successfully");
			}
		} catch (Exception e) {
			System.out.println("Error setting room: " + e.getMessage());
		}
	}

	void bookRoom(int userId, int roomNumber, Date checkIn, Date checkOut) {
		try {
			if (checkIn == null || checkOut == null) {
				throw new IllegalArgumentException("Check-in and check-out dates cannot be null");
			}
			Date normalizedCheckIn = normalizeDate(checkIn);
			Date normalizedCheckOut = normalizeDate(checkOut);
			if (normalizedCheckOut.before(normalizedCheckIn) || normalizedCheckOut.equals(normalizedCheckIn)) {
				System.out.println("Booking failed: Invalid date range. Check-out must be after check-in.");
				return;
			}
			User user = findUserById(userId);
			if (user == null) {
				System.out.println("Booking failed: User " + userId + " not found");
				return;
			}
			Room room = findRoomByNumber(roomNumber);
			if (room == null) {
				System.out.println("Booking failed: Room " + roomNumber + " not found");
				return;
			}
			
			int nights = calculateNights(normalizedCheckIn, normalizedCheckOut);
			int totalCost = nights * room.getPricePerNight();

			if (user.getBalance() < totalCost) {
				System.out.println("Booking failed: Insufficient balance. Required: " + totalCost + 
						", Available: " + user.getBalance());
				return;
			}

			if (!isRoomAvailable(roomNumber, normalizedCheckIn, normalizedCheckOut)) {
				System.out.println("Booking failed: Room " + roomNumber + 
						" is not available for the requested period");
				return;
			}

			Booking booking = new Booking(
					bookingIdCounter++,
					userId,
					roomNumber,
					normalizedCheckIn,
					normalizedCheckOut,
					totalCost,
					room.getRoomType(),
					room.getPricePerNight(),
					user.getBalance()
					);

			user.setBalance(user.getBalance() - totalCost);

			bookings.add(0, booking);

			System.out.println("Booking successful! User " + userId + " booked Room " + 
					roomNumber + " for " + nights + " night(s). Total cost: " + totalCost);

		} catch (Exception e) {
			System.out.println("Booking failed: " + e.getMessage());
		}
	}

	void printAll() {
		System.out.println("--- ROOMS ---");
		if (rooms.isEmpty()) {
			System.out.println("No rooms available");
		} else {
			for (Room room : rooms) {
				System.out.println(room);
			}
		}
		System.out.println("\n--- BOOKINGS ---");
		if (bookings.isEmpty()) {
			System.out.println("No bookings available");
		} else {
			for (Booking booking : bookings) {
				System.out.println("Booking ID: " + booking.getBookingId());
				System.out.println("  User ID: " + booking.getUserId() + 
						" (Balance at booking: " + booking.getUserBalanceAtBooking() + ")");
				System.out.println("  Room Number: " + booking.getRoomNumber());
				System.out.println("  Room Type at booking: " + booking.getRoomTypeAtBooking());
				System.out.println("  Price per night at booking: " + booking.getPricePerNightAtBooking());
				System.out.println("  Check-in: " + booking.getCheckIn());
				System.out.println("  Check-out: " + booking.getCheckOut());
				System.out.println("  Total Cost: " + booking.getTotalCost());
				System.out.println();
			}
		}
	}

	void setUser(int userId, int balance) {
		try {
			if (balance < 0) {
				throw new IllegalArgumentException("User balance cannot be negative");
			}
			User existingUser = findUserById(userId);
			if (existingUser != null) {
				existingUser.setBalance(balance);
				System.out.println("User " + userId + " updated successfully");
			} else {
				User newUser = new User(userId, balance);
				users.add(0, newUser);
				System.out.println("User " + userId + " created successfully");
			}
		} catch (Exception e) {
			System.out.println("Error setting user: " + e.getMessage());
		}
	}

	void printAllUsers() {
		System.out.println("\n========== ALL USERS ==========");
		if (users.isEmpty()) {
			System.out.println("No users available");
		} else {
			for (User user : users) {
				System.out.println(user);
			}
		}
	}

	private Room findRoomByNumber(int roomNumber) {
		for (Room room : rooms) {
			if (room.getRoomNumber() == roomNumber) {
				return room;
			}
		}
		return null;
	}

	private User findUserById(int userId) {
		for (User user : users) {
			if (user.getId() == userId) {
				return user;
			}
		}
		return null;
	}

	private Date normalizeDate(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}

	private int calculateNights(Date checkIn, Date checkOut) {
		long diffInMillis = checkOut.getTime() - checkIn.getTime();
		return (int) (diffInMillis / (1000 * 60 * 60 * 24));
	}

	private boolean isRoomAvailable(int roomNumber, Date checkIn, Date checkOut) {
		for (Booking booking : bookings) {
			if (booking.getRoomNumber() == roomNumber) {
				if (!(checkOut.before(booking.getCheckIn()) || 
						checkOut.equals(booking.getCheckIn()) ||
						checkIn.after(booking.getCheckOut()) || 
						checkIn.equals(booking.getCheckOut()))) {
					return false;
				}
			}
		}
		return true;
	}

}

