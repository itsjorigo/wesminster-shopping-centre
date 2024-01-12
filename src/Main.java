public class Main {
    public static void ManagerConsoleContent(){
        WestminsterShoppingManager manager = new WestminsterShoppingManager();

        System.out.println("Select Your Option From The Menu");
        System.out.println();

        System.out.println("1. Add a new product");
        System.out.println("2. Delete a product");
        System.out.println("3. Print the lists of the products");
        System.out.println("4. Save in a file");
        System.out.println("5. Exit Console");
        System.out.println();

        int option = manager.inputHandlingInt("Enter Your Option : ");
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
                break;
        }
    }


    public static void main(String[] args) {

        System.out.println("--------------------------------------------------------");
        System.out.println("Welcome to Westminster Shopping Center - Manager Console");
        System.out.println("--------------------------------------------------------");
        System.out.println();

        ManagerConsoleContent();

        WestminsterShoppingGUI GUI = new WestminsterShoppingGUI();

        GUI.runGUI();
    }
}