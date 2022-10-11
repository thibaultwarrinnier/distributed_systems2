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


    }
}
