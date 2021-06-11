package service;

import model.Customer;
import model.IRoom;
import model.Reservation;

import java.util.*;

public class ReservationService {
    static Map<String, IRoom> rooms = new HashMap<>();
    static Map<Customer, List<Reservation>> customerReservations = new HashMap<>();
    static List<Reservation> reservations = new ArrayList<>();

    public static void addRoom(IRoom room) {
        rooms.put(room.getRoomNumber(), room);
    }

    public static IRoom getARoom(String roomId) {
        return rooms.get(roomId);
    }

    public static Collection<IRoom> getAllRooms() {
        return rooms.values();
    }

    public static Reservation reserveARoom(Customer customer, IRoom room, Date checkInDate, Date checkOutDate) {
        Reservation reservation = new Reservation(customer, room, checkInDate, checkOutDate);
        List<Reservation> currentReservations  = customerReservations.get(customer);
        if(currentReservations == null) {
            currentReservations = new ArrayList<>();
        }
        currentReservations.add(reservation);
        reservations.add(reservation);
        customerReservations.put(customer, currentReservations);
        return reservation;
    }

    public static void printAllReservation() {
        for(Reservation reservation : reservations) {
            System.out.println(reservation);
        }
    }

    public static Collection<Reservation> getCustomersReservation(Customer customer) {
        return customerReservations.get(customer);
    }

    public static Collection<IRoom> findRooms(Date checkInDate, Date checkOutDate) {
        List<IRoom> availableRooms = new ArrayList<>();
        for(Reservation reservation:reservations) {
            if((reservation.getCheckInDate()).compareTo(checkInDate) >= 0) {
                if((reservation.getCheckOutDate()).compareTo(checkOutDate) <= 0) {
                    availableRooms.add(reservation.getRoom());
                }
            }
        }
        return availableRooms;
    }
}
