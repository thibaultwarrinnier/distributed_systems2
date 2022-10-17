package hotel;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BookingManager implements IBookingManager {

	private Room[] rooms;

	public BookingManager() {
		this.rooms = initializeRooms();
	}

	public Set<Integer> getAllRooms() {
		Set<Integer> allRooms = new HashSet<Integer>();
		Iterable<Room> roomIterator = Arrays.asList(rooms);
		for (Room room : roomIterator) {
			allRooms.add(room.getRoomNumber());
		}
		return allRooms;
	}

	public boolean isRoomAvailable(Integer roomNumber, LocalDate date) {
		int size = Array.getLength(rooms);
		for (int i = 0; i < size; i++){
			if (roomNumber.equals(rooms[i].getRoomNumber())){
				List<BookingDetail> bookings = rooms[i].getBookings();
				for (BookingDetail booking : bookings) {
					LocalDate datum = booking.getDate();
					if (datum.equals(date)) {
						return false;
					}
				}
			}
		}
		return true;
	}

	public void addBooking(BookingDetail bookingDetail) {
		int size = Array.getLength(rooms);
		for (int i = 0; i < size; i++){
			if (bookingDetail.getRoomNumber().equals(rooms[i].getRoomNumber())){
				List<BookingDetail> bookings = rooms[i].getBookings();
				bookings.add(bookingDetail);
				rooms[i].setBookings(bookings);
			}
		}
	}

	public Set<Integer> getAvailableRooms(LocalDate date) {
		Set<Integer> avRooms = new HashSet<Integer>();
		Iterable<Room> roomIterator = Arrays.asList(rooms);
		for (Room room : roomIterator){
			if (isRoomAvailable(room.getRoomNumber(), date)){
				avRooms.add(room.getRoomNumber());
			}
		}
		return avRooms;
	}

	private static Room[] initializeRooms() {
		Room[] rooms = new Room[4];
		rooms[0] = new Room(101);
		rooms[1] = new Room(102);
		rooms[2] = new Room(201);
		rooms[3] = new Room(203);
		return rooms;
	}
}
