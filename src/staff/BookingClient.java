package staff;

import java.time.LocalDate;
import java.util.Set;

import hotel.BookingDetail;
import hotel.BookingManager;
import hotel.IBookingManager;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BookingClient extends AbstractScriptedSimpleTest {


	//moet dezelfde naam zijn als de rebind
	public static final String hotelName = "Ibis";

	private static final Logger clientLogger = Logger.getLogger(BookingClient.class.getName());
	private IBookingManager bm = null;

	public static void main(String[] args) throws Exception {
		BookingClient client = new BookingClient();
		client.connect();
		client.run();
	}

	/***************
	 * CONSTRUCTOR *
	 ***************/
	public BookingClient() {
		System.setSecurityManager(null);

	}
	public void connect(){
		Registry registry = null;
		try {
			registry = LocateRegistry.getRegistry();
		} catch (Exception exp) {
			clientLogger.log(Level.SEVERE, "Could not find the rmi registry.");
			exp.printStackTrace();
		}
		IBookingManager interbm = null;
		try {
			interbm = (IBookingManager) registry.lookup(hotelName);
		} catch (NotBoundException exp){
			clientLogger.log(Level.SEVERE, "Could not find car rental company with given name.");
			exp.printStackTrace();
		} catch (RemoteException ex) {
			System.err.println(ex.getMessage());
		} catch (Exception exp){
			clientLogger.log(Level.SEVERE, "other exception.");
			exp.printStackTrace();
		}
		bm = interbm;
	}

	@Override
	public boolean isRoomAvailable(Integer roomNumber, LocalDate date) {
		Boolean res= null;
		try{
			res =bm.isRoomAvailable(roomNumber,date);
		} catch (RemoteException ex) {
			System.err.println(ex.getMessage());
		}
		return res;

	}

	@Override
	public void addBooking(BookingDetail bookingDetail)  {
		try{
			this.bm.addBooking(bookingDetail);
		}catch (RemoteException ex) {
			System.err.println(ex.getMessage());
		}

	}

	@Override
	public Set<Integer> getAvailableRooms(LocalDate date) {
		Set<Integer> res  = null;
		try{
			res = bm.getAvailableRooms(date);
		} catch (RemoteException ex) {
			System.err.println(ex.getMessage());
		}
		return res ;

	}

	@Override
	public Set<Integer> getAllRooms() {
		Set<Integer> res = null;
		try {
			res = bm.getAllRooms();
		} catch (RemoteException ex) {
			System.err.println(ex.getMessage());
		}
		return res;
	}
}
