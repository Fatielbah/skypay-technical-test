package hotelReservation;

import java.util.Calendar;
import java.util.Date;

public class Main {
	public static void main(String[] args) {
		Service service = new Service();

		System.out.println("=== Hotel Reservation System Test ===\n");

		System.out.println("--- Creating Rooms ---");
		service.setRoom(1, RoomType.STANDARD, 1000);
		service.setRoom(2, RoomType.JUNIOR, 2000);
		service.setRoom(3, RoomType.SUITE, 3000);


		System.out.println("\n--- Creating Users ---");
		service.setUser(1, 5000);
		service.setUser(2, 10000);


		Date date1 = createDate(2026, 6, 30);  
		Date date2 = createDate(2026, 7, 7);   
		Date date3 = createDate(2026, 7, 8);   
		Date date4 = createDate(2026, 7, 9);  

		System.out.println("\n--- Booking Attempts ---");
		System.out.println("\n1. User 1 booking Room 2 from 30/06/2026 to 07/07/2026:");
		service.bookRoom(1, 2, date1, date2);
		System.out.println("\n2. User 1 booking Room 2 from 07/07/2026 to 30/06/2026:");
		service.bookRoom(1, 2, date2, date1);
		System.out.println("\n3. User 1 booking Room 1 from 07/07/2026 to 08/07/2026:");
		service.bookRoom(1, 1, date2, date3);
		System.out.println("\n4. User 2 booking Room 1 from 07/07/2026 to 09/07/2026:");
		service.bookRoom(2, 1, date2, date4);
		System.out.println("\n5. User 2 booking Room 3 from 07/07/2026 to 08/07/2026:");
		service.bookRoom(2, 3, date2, date3);
		System.out.println("\n--- Updating Room 1 ---");
		service.setRoom(1, RoomType.SUITE, 10000);
		service.printAll();
		service.printAllUsers();
	}

	private static Date createDate(int year, int month, int day) {
		Calendar cal = Calendar.getInstance();
		cal.set(year, month - 1, day, 0, 0, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}
}
