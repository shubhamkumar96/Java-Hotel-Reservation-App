import api.HotelResource;
import model.Customer;
import model.IRoom;

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

    public static void main(String[] args) {
        boolean takeInput = true;
            try{
                while(takeInput) {
                    optionsMainMenu();
                    int userInput = Integer.parseInt(scanner.nextLine());
                    switch(userInput) {
                        case 1:
                            findAndReserveARoom();
                            break;
                        case 2:
                            System.out.println("Selected Choice: " + "See My Reservations.");
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
        System.out.println("Enter any one of the below Inputs");
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
            e.printStackTrace();
            findAndReserveARoom();
        }
        System.out.println("Enter CheckOut Date(mm/dd/yyyy), example '01/22/2020': ");
        String checkOutDateString = scanner.nextLine();
        try{
            checkOutDate = formatter.parse(checkOutDateString);
        } catch (ParseException e) {
            e.printStackTrace();
            findAndReserveARoom();
        }

        Collection<IRoom> allRooms = hotelResource.findARoom(checkInDate, checkOutDate);
        if(allRooms.size() == 0) {
            System.out.println("No Rooms Added Yet.\n");
            return;
        }
        for(IRoom room:allRooms) {
            System.out.println(room);
        }
        System.out.println("");
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
            break;
        }
    }

}
