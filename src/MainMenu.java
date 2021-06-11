import java.util.Scanner;

public class MainMenu {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean takeInput = true;
            try{
                System.out.println("Enter any one of the below Inputs");
                System.out.println("1. Find and Reserve a Room");
                System.out.println("2. See my Reservations");
                System.out.println("3. Create an Account");
                System.out.println("4. Admin Menu");
                System.out.println("5. Exit");
                int userInput = Integer.parseInt(scanner.nextLine());
                while(takeInput) {
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
                            System.out.println("Selected Choice: " + userInput);
                            takeInput = false;
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
