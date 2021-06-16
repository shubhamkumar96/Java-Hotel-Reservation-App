import api.AdminResource;
import api.HotelResource;
import model.Customer;
import model.IRoom;
import model.Reservation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainMenu {
    private static Scanner scanner = new Scanner(System.in);
    private static HotelResource hotelResource = HotelResource.getInstance();
    private static AdminResource adminResource = AdminResource.getInstance();

    public static void main(String[] args) {
            try{
                while(true) {
                    optionsMainMenu();
                    int userInput = Integer.parseInt(scanner.nextLine());
                    switch(userInput) {
                        case 1:
                            findAndReserveARoom();
                            break;
                        case 2:
                            seeMyReservation();
                            break;
                        case 3:
                            createAnAccount();
                            break;
                        case 4:
                            AdminMenu.adminMenu();
                            break;
                        case 5:
                            System.out.println("-------------------- Exited ----------------");
                            System.exit(0);
                        default:
                            System.out.println("Please Enter a Number between 1 to 5 \n");
                            continue;
                    }
                }

            } catch (Exception ex) {
                ex.getLocalizedMessage();
            } finally {
                scanner.close();
            }
    }

    public static void optionsMainMenu() {
        System.out.println("\n" + "Enter any one of the below Inputs");
        System.out.println("1. Find and Reserve a Room");
        System.out.println("2. See my Reservations");
        System.out.println("3. Create an Account");
        System.out.println("4. Admin Menu");
        System.out.println("5. Exit");
    }

    public static void findAndReserveARoom() {
        Date checkInDate = new Date();
        Date checkOutDate = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("mm/dd/yyyy");

        System.out.println("Enter CheckIn Date(mm/dd/yyyy), example '01/20/2020': ");
        String checkInDateString = scanner.nextLine();
        try{
            checkInDate = formatter.parse(checkInDateString);
        } catch (ParseException e) {
            System.out.println("Entered checkInDate Date is Invalid.");
            findAndReserveARoom();
        }
        System.out.println("Enter CheckOut Date(mm/dd/yyyy), example '01/22/2020': ");
        String checkOutDateString = scanner.nextLine();
        try{
            checkOutDate = formatter.parse(checkOutDateString);
        } catch (ParseException e) {
            System.out.println("Entered CheckOut Date is Invalid.");
            findAndReserveARoom();
        }

        if(checkOutDate.before(checkInDate)) {
            System.out.println("Check-Out Date should be after Check-In Date.");
            findAndReserveARoom();
        }

        Collection<IRoom> availableRooms = hotelResource.findARoom(checkInDate, checkOutDate);
        if(availableRooms.size() == 0) {
            System.out.println("No Rooms Available Now.\n");
            return;
        }
        for(IRoom room:availableRooms) {
            System.out.println(room);
        }
        System.out.println("");

        while(true) {
            System.out.println("Want to book a Room: Type 'y' or 'n' ");
            String yesNo = scanner.nextLine();
            if(yesNo.equalsIgnoreCase("y")) {
                while(true) {
                    System.out.println("Do you have an account with us: Type 'y' or 'n' ");
                    yesNo = scanner.nextLine();
                    if(yesNo.equalsIgnoreCase("n")) {
                        System.out.println("First Create An Account");
                        createAnAccount();
                        System.out.println("Account Created");
                    } else if (yesNo.equalsIgnoreCase("y")){
                        System.out.println("Enter Your Registered Email in format: name@domain.com");
                        String emailText = scanner.nextLine();
                        Customer customer = adminResource.getCustomer(emailText);
                        if(customer == null ) {
                            System.out.println("Entered Email is not registered with us.");
                            MainMenu.main(null);
                        } else {
                            System.out.println(customer);
                            System.out.println("What Room Number would you like to reserve.");
                            for(IRoom availableRoom:availableRooms) {
                                System.out.println(availableRoom);
                            }
                            String roomNumber = scanner.nextLine();
                            IRoom room = null;
                            for(IRoom availableRoom : availableRooms) {
                                if(availableRoom.getRoomNumber().equalsIgnoreCase(roomNumber)) {
                                    room = availableRoom;
                                }
                            }
                            if(room == null) {
                                System.out.println("Entered Room Number is not available for reservation.");
                            } else {
                                Reservation reservationDone = hotelResource.bookARoom(emailText, room, checkInDate, checkOutDate);
                                System.out.println("Reservation Done:");
                                System.out.println(reservationDone);
                            }
                        }
                        break;
                    } else {
                        System.out.println("Invalid Input.");
                        continue;
                    }
                }
                break;
            } else {
                System.out.println("Invalid Input.");
            }
        }

    }

    public static void seeMyReservation() {
        while(true) {
            System.out.println("Enter Your Email in following format: name@domain.com ");
            String email = scanner.nextLine();
            String regExEmail = "^(.+)@(.+).com$";
            Pattern pattern = Pattern.compile(regExEmail);
            Matcher matcher = pattern.matcher(email);
            String firstName = "";
            String lastName = "";
            if(!matcher.matches()) {
                System.out.println("Entered Email is Invalid");
                continue;
            }
            Collection<Reservation> reservations = hotelResource.getCustomersReservations(email);
            if(reservations == null) {
                System.out.println("No Reservations made for Email : " + email + "\n");
                return;
            }
            for(Reservation reservation:reservations) {
                System.out.println(reservation);
            }
            break;
        }
    }
    public static void createAnAccount() {
        while(true) {
            System.out.println("Enter Your Email in following format: name@domain.com ");
            String email = scanner.nextLine();
            String regExEmail = "^(.+)@(.+).com$";
            Pattern pattern = Pattern.compile(regExEmail);
            Matcher matcher = pattern.matcher(email);
            String firstName = "";
            String lastName = "";
            if(!matcher.matches()) {
                System.out.println("Entered Email is Invalid");
                continue;
            } else {
                System.out.println("Enter Your First Name:");
                firstName = scanner.nextLine();
                System.out.println("Enter Your Last Name:");
                lastName = scanner.nextLine();
            }
            Customer customer = new Customer(firstName, lastName, email);
            hotelResource.createCustomer(email, firstName, lastName);
            System.out.println(customer);
            break;
        }
    }

}
