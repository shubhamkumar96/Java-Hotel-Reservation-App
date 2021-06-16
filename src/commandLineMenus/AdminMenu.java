package commandLineMenus;

import api.AdminResource;
import model.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;

public class AdminMenu {
    private static Scanner scanner = new Scanner(System.in);
    private static AdminResource adminResource = AdminResource.getInstance();

    public static void adminMenu() {
        try{
            while(true) {
                optionsAdminMenu();
                int userInput = Integer.parseInt(scanner.nextLine());
                switch(userInput) {
                    case 1:
                        displayAllCustomers();
                        break;
                    case 2:
                        displayAllRooms();
                        break;
                    case 3:
                        displayAllReservations();
                        break;
                    case 4:
                        addARoom();
                        break;
                    case 5:
                        MainMenu.mainMenu();
                        break;
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

    public static void optionsAdminMenu() {
        System.out.println("Enter any one of the below Inputs");
        System.out.println("1. See All Customers");
        System.out.println("2. See All Rooms");
        System.out.println("3. See All Reservations");
        System.out.println("4. Add a Room");
        System.out.println("5. Back to main menu");
    }

    public static void addARoom() {
        boolean addAnotherRoom = true;
        while(addAnotherRoom) {
            System.out.println("Enter Room Number");
            String roomNumber = scanner.nextLine();
            System.out.println("Enter Price Per Night");
            Double price = Double.parseDouble(scanner.nextLine());
            System.out.println("Enter Room Type : 1-For Single Bed, 2-For Double Bed");
            int input = Integer.parseInt(scanner.nextLine());
            RoomType roomType;
            while(true) {
                if(input == 1){
                    roomType = RoomType.SINGLE;
                    break;
                } else if(input == 2) {
                    roomType = RoomType.DOUBLE;
                    break;
                } else {
                    System.out.println("Invalid Room Type, Enter either 1 or 2");
                    input = Integer.parseInt(scanner.nextLine());
                    continue;
                }
            }

            List<IRoom> rooms = new ArrayList<>();
            Room room = new Room(roomNumber, price, roomType);
            rooms.add(room);
            adminResource.addRoom(rooms);
            System.out.println("Below Rooms Added:");
            System.out.println(room + "\n");
            System.out.println("Would you like to add another room: y/n ?");
            String yesNo = scanner.nextLine();
            while(true) {
                if(yesNo.equalsIgnoreCase("n")){
                    addAnotherRoom = false;
                    break;
                } else if(yesNo.equalsIgnoreCase("y")) {
                    break;
                } else {
                    System.out.println("Invalid Input, Please type 'y' or 'n' :");
                    yesNo = scanner.nextLine();
                    continue;
                }
            }
        }
    }

    public static void displayAllCustomers() {
        Collection<Customer> customers = adminResource.getAllCustomers();
        if(customers.size() == 0) {
            System.out.println("No Customers Added Yet.\n");
            return;
        }
        for(Customer customer : customers) {
            System.out.println(customer);
        }
        System.out.println("");
    }

    public static void displayAllRooms() {
        System.out.println("All Rooms in the Hotel :");
        Collection<IRoom> allRooms = adminResource.getAllRooms();
        if(allRooms.size() == 0) {
            System.out.println("No Rooms Added Yet.\n");
            return;
        }
        for(IRoom room:allRooms) {
            System.out.println(room);
        }
        System.out.println("");
    }

    public static void displayAllReservations() {
        adminResource.displayAllReservations();
        System.out.println("");
    }

}
