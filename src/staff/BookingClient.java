package staff;

import java.time.LocalDate;
import java.util.Set;

import hotel.BookingDetail;
import hotel.BookingManager;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class BookingClient extends AbstractScriptedSimpleTest {


	//moet dezelfde naam zijn als de rebind
	public static final String hotelName = "Ibis";

	private BookingManager bm = null;

	public static void main(String[] args) throws Exception {
		BookingClient client = new BookingClient();
		client.run();
	}

	/***************
	 * CONSTRUCTOR *
	 ***************/
	public BookingClient() {
		try {
			Registry registry = LocateRegistry.getRegistry();
			bm = (BookingManager) registry.lookup(hotelName);

		} catch (Exception exp) {
			exp.printStackTrace();
		}
	}

	@Override
	public boolean isRoomAvailable(Integer roomNumber, LocalDate date) {
		//Implement this method
		return true;
	}

	@Override
	public void addBooking(BookingDetail bookingDetail) throws Exception {
		//Implement this method
	}

	@Override
	public Set<Integer> getAvailableRooms(LocalDate date) {
		//Implement this method
		return null;
	}

	@Override
	public Set<Integer> getAllRooms() {
		return bm.getAllRooms();
	}
}
