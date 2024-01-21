import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    static public String inputHandlingString(String input) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            try {
                System.out.print(input);
                return sc.next();
            } catch (InputMismatchException e) {
                sc.nextLine(); // Consume the invalid input
                System.out.println("Invalid Datatype. Enter input Again!");
            }
        }
    }

    static public int inputHandlingInt(String input) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            try {
                System.out.print(input);
                return sc.nextInt();
            } catch (InputMismatchException e) {
                sc.nextLine(); // Consume the invalid input
                System.out.println("Invalid Datatype. Enter input Again!");
            }
        }
    }

    static public double inputHandlingDouble(String input) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            try {
                System.out.print(input);
                return sc.nextDouble();
            } catch (InputMismatchException e) {
                sc.nextLine(); // Consume the invalid input
                System.out.println("Invalid Datatype. Enter input Again!");
            }
        }
    }

    public static void ManagerConsoleContent(){
        WestminsterShoppingManager manager = new WestminsterShoppingManager();

        System.out.println();
        System.out.println("Welcome to Westminster Shopping Center - Manager Console");
        System.out.println("--------------------------------------------------------");

        System.out.println("Select Your Option From The Menu");
        System.out.println();

        System.out.println("1. Add a new product");
        System.out.println("2. Delete a product");
        System.out.println("3. Print the lists of the products");
        System.out.println("4. Save in a file");
        System.out.println("5. Load from file");
        System.out.println("6. Exit Console");

        System.out.println();

        int option = Main.inputHandlingInt("Enter Your Option : ");
        System.out.println();

        switch (option) {
            case 1:
                manager.addProduct();
                break;
            case 2:
                manager.deleteProduct();
                break;
            case 3:
                manager.printProducts();
                break;
            case 4:
                manager.saveProducts();
                break;
            case 5:
                WestminsterShoppingManager.loadProducts();
                break;
            case 6:
                break;
            default:
                System.out.println("Invalid option. Please try again.");
        }
    }

    public static void userConsole(){
        ArrayList<User> users = User.getUsers();

        Scanner sc = new Scanner(System.in);

        System.out.println("Are you a registered customer or a new customer ?");
        System.out.println("1. Registered");
        System.out.println("2. Unregistered");
        System.out.println("Enter your option:");
        System.out.println("--------------------------------------------------------");

        boolean state = false;
        while (!state){
            int userRole = sc.nextInt();

            if (userRole == 1){



                System.out.println("Enter your enteredUsername: ");
                String enteredUsername = sc.next();

                System.out.println("Enter your enteredPassword: ");
                String enteredPassword = sc.next();

                for (User user :   User.loadUsers()){
                    System.out.println(user);
                    if (user.getUsername().equals(enteredUsername) && user.getPassword().equals(enteredPassword)){
                        System.out.println("Login successful ! Welcome!");

                        WestminsterShoppingGUI.main(null);


                     return;
                    }
                }
                System.out.println("Login unsuccessful ! Welcome!");

            }else if (userRole == 2){

                System.out.println("Enter your username: ");
                String enteredUsername = sc.next();

                System.out.println("Enter your password: ");
                String enteredPassword = sc.next();

                ArrayList<Product> test = new ArrayList<>();


                for (Product product : WestminsterShoppingManager.stocks.values()){
                    test.add(product);
                    break;
                }



                User user = new User(enteredUsername, enteredPassword, test);

                if (users == null) {
                    users = new ArrayList<>();
                }

                users.add(user);
                System.out.println("User registration successful !");
                User.setUsers(users);
                User.saveUsers(users);
                WestminsterShoppingGUI.main(null);
            }else {
                System.out.println("Invalid input, Try Again!!");
                state = true;
            }
        }

    }

    public static void main(String[] args) {
        boolean state = true;

        System.out.println("--------------------------------------------------------------");
        System.out.println("----[][]----Welcome to Westminster Shopping Center----[][]----");
        System.out.println("--------------------------------------------------------------");
        System.out.println();

        WestminsterShoppingManager.loadProducts();

        while (state){
            state = false;
            int role = Main.inputHandlingInt("Are you a Customer or a Manager (1 or 2) ? : ");

            switch (role) {
                case 1 -> userConsole();
                case 2 -> ManagerConsoleContent();
                default -> {
                    System.out.println("Invalid option. Please try again.");
                    System.out.println();
                    state = true;
                }
            }
        }
    }
}