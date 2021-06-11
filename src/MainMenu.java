import api.HotelResource;
import model.Customer;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainMenu {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        HotelResource hotelResource = new HotelResource();
        boolean takeInput = true;
            try{
                while(takeInput) {
                    System.out.println("Enter any one of the below Inputs");
                    System.out.println("1. Find and Reserve a Room");
                    System.out.println("2. See my Reservations");
                    System.out.println("3. Create an Account");
                    System.out.println("4. Admin Menu");
                    System.out.println("5. Exit");
                    int userInput = Integer.parseInt(scanner.nextLine());
                    switch(userInput) {
                        case 1:
                            System.out.println("Selected Choice: " + userInput);
                            takeInput = false;
                            break;
                        case 2:
                            System.out.println("Selected Choice: " + userInput);
                            takeInput = false;
                            break;
                        case 3:
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
                            break;
                        case 4:
                            AdminMenu.main(null);
                            takeInput = false;
                            break;
                        case 5:
                            System.out.println("-------------------- Exited ----------------");
                            takeInput = false;
                            break;
                        default:
                            System.out.println("Please Enter a Number between 1 to 5 \n");
                            System.out.println("Enter any one of the below Inputs");
                            System.out.println("1. Find and Reserve a Room");
                            System.out.println("2. See my Reservations");
                            System.out.println("3. Create an Account");
                            System.out.println("4. Admin Menu");
                            System.out.println("5. Exit");
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
