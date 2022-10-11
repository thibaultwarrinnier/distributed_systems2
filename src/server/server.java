package server;

import hotel.BookingManager;
import hotel.IBookingManager;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

public class server {
    private static final String hotelName = "Ibis";
    private static final Logger logger = Logger.getLogger(server.class.getName());

    public static void main(String[] args) throws Exception {

        IBookingManager bm = new BookingManager();


        Registry registry = null;
        try {
            registry = LocateRegistry.getRegistry();

        } catch(RemoteException e) {
            logger.log(Level.SEVERE, "Could not locate RMI registry.");
            System.exit(-1);
        }
        IBookingManager stub;

        try {
            stub = (IBookingManager) UnicastRemoteObject.exportObject(bm, 0);
            registry.rebind(hotelName, stub);

            logger.log(Level.INFO, "<{0}> Car Rental Company {0} is registered.", hotelName);
        } catch(RemoteException e) {
            logger.log(Level.SEVERE, "<{0}> Could not get stub bound of Car Rental Company {0}.", hotelName);
            e.printStackTrace();
            System.exit(-1);
        }
    }
}
