public class Main {
    public static void ManagerConsoleContent(){
        System.out.println("--------------------------------------------------------");
        System.out.println("Welcome to Westminster Shopping Center - Manager Console");
        System.out.println("--------------------------------------------------------");
        System.out.println();

        System.out.println("Select Your Option From The Menu");
        System.out.println();

        System.out.println("1. Add a new product");
        System.out.println("2. Delete a product");
        System.out.println("3. Print the lists of the products");
        System.out.println("4. Save in a file");
        System.out.println();
    }
    public static void main(String[] args) {
        System.out.println("initial commit-github.");

        WestminsterShoppingManager manager = new WestminsterShoppingManager();
        ManagerConsoleContent();

        int option = manager.inputHandlingInt("Enter Your Option : ");
        System.out.println();

        switch (option) {
            case 1 -> manager.addProduct();
            case 2 -> manager.deleteProduct();
            case 3 -> manager.printProducts();
            case 4 -> manager.saveProducts();
        }
    }
}