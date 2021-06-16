package service;

import model.Customer;
import model.IRoom;
import model.Reservation;

import java.util.*;

public class ReservationService {
    private static ReservationService reservationService = null;
    static Map<String, IRoom> rooms = new HashMap<>();
    static Map<Customer, List<Reservation>> customerReservations = new HashMap<>();
    static List<Reservation> reservations = new ArrayList<>();

    private ReservationService(){
    }

    public static ReservationService getInstance() {
        if(reservationService == null) {
            reservationService = new ReservationService();
        }
        return reservationService;
    }

    public void addRoom(IRoom room) {
        rooms.put(room.getRoomNumber(), room);
    }

    public IRoom getARoom(String roomId) {
        return rooms.get(roomId);
    }

    public Collection<IRoom> getAllRooms() {
        return rooms.values();
    }

    public Reservation reserveARoom(Customer customer, IRoom room, Date checkInDate, Date checkOutDate) {
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

    public void printAllReservation() {
        if(reservations.size() == 0) {
            System.out.println("No Reservations Made Yet.\n");
            return;
        }
        for(Reservation reservation : reservations) {
            System.out.println(reservation);
        }
    }

    public Collection<Reservation> getCustomersReservation(Customer customer) {
        return customerReservations.get(customer);
    }

    public Collection<IRoom> findRooms(Date checkInDate, Date checkOutDate) {
        Collection<IRoom> availableRooms;
        // Create a Set of available rooms that contains all the rooms.
        Map<String, IRoom> availableRoomsMap = new HashMap<>(rooms);
        // Now remove all the rooms that are present in reservation and has checkin and checkout
        // date in between given 'checkInDate' & 'checkOutDate' dates.
        for(Reservation reservation:reservations) {
            IRoom room = reservation.getRoom();
            String roomNumber = room.getRoomNumber();
            Date reserveCheckInDate = reservation.getCheckInDate();
            Date reserveCheckOutDate = reservation.getCheckOutDate();
            if( (availableRoomsMap.get(roomNumber) != null) &&
                    (
                            (reserveCheckInDate.getTime()>=checkInDate.getTime() && reserveCheckInDate.getTime()<=checkOutDate.getTime())
                            || (reserveCheckOutDate.getTime()>=checkInDate.getTime() && reserveCheckOutDate.getTime()<=checkOutDate.getTime())
                    )
            ) {
                availableRoomsMap.remove(roomNumber);
            }
        }
        availableRooms = availableRoomsMap.values();
        return availableRooms;
    }
}
