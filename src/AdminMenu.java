import api.AdminResource;
import model.Customer;
import model.IRoom;
import model.Room;
import model.RoomType;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;

public class AdminMenu {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AdminResource adminResource = new AdminResource();
        boolean takeInput = true;
        try{
            while(takeInput) {
                System.out.println("Enter any one of the below Inputs");
                System.out.println("1. See All Customers");
                System.out.println("2. See All Rooms");
                System.out.println("3. See All Reservations");
                System.out.println("4. Add a Room");
                System.out.println("5. Back to main menu");
                int userInput = Integer.parseInt(scanner.nextLine());
                switch(userInput) {
                    case 1:
                        Collection<Customer> customers = adminResource.getAllCustomers();
                        for(Customer customer : customers) {
                            System.out.println(customer);
                        }
                        break;
                    case 2:
                        System.out.println("All Rooms in the Hotel :");
                        Collection<IRoom> allRooms = adminResource.getAllRooms();
                        if(allRooms.size() == 0) {
                            System.out.println("No Rooms Added Yet.\n");
                            break;
                        }
                        for(IRoom room:allRooms) {
                            System.out.println(room);
                        }
                        System.out.println("");
                        break;
                    case 3:
                        System.out.println("Selected Choice: " + userInput);
                        takeInput = false;
                        break;
                    case 4:
                        boolean addAnotherRoom = true;
                        while(addAnotherRoom) {
                            System.out.println("Enter Room Number");
                            String roomNumber = scanner.nextLine();
                            System.out.println("Enter Price Per Night");
                            Double price = Double.parseDouble(scanner.nextLine());
                            System.out.println("Enter Room Type : 1-For Single Bed, 2-For Double Bed");
                            int input = Integer.parseInt(scanner.nextLine());
                            RoomType roomType = RoomType.SINGLE;
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
                        break;
                    case 5:
                        MainMenu.main(null);
                        takeInput = false;
                        break;
                    default:
                        System.out.println("Please Enter a Number between 1 to 5 \n");
                        System.out.println("Enter any one of the below Inputs");
                        System.out.println("1. See All Customers");
                        System.out.println("2. See All Rooms");
                        System.out.println("3. See All Reservations");
                        System.out.println("4. Add a Room");
                        System.out.println("5. Back to main menu");
                        userInput = Integer.parseInt(scanner.nextLine());
                        continue;
                }
            }

        } catch (Exception ex) {
            ex.getLocalizedMessage();
        } finally {
            scanner.close();
        }

    }
}
