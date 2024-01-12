import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class WestminsterShoppingManager implements ShoppingManager{
    private static List<Product> stocks = new ArrayList<>();
    private static final int maxStocks = 50;
    private static final String dataFile = "product.txt";

    @Override
    public void addProduct() {
        Scanner sc = new Scanner(System.in);
        boolean validInput = false;

        if (stocks.size() <= maxStocks){
            System.out.println("Select Product Category");
            System.out.println("1. Electronics");
            System.out.println("2. Clothing");

            while (!validInput){
                try {
                    System.out.print("Enter Answer (1 or 2) : ");
                    int answer = sc.nextInt();
                    System.out.println();

                    if (answer == 1 || answer == 2){
                        validInput = true;
                        stocks.add(inputProductDetails(answer));
                        System.out.println();

                    }else {
                        System.out.println("Invalid Input. Try Again");
                        System.out.println("-------------------------------------------------------------------------");
                    }

                }catch (InputMismatchException e){
                    System.out.println("Invalid datatype. Please enter again.");
                    System.out.println("-------------------------------------------------------------------------");
                    sc.next();
                }
            }
            System.out.println("Product added successfully !");
            System.out.println();

        }else {
            System.out.println("Maximum limit of products is reached");
            System.out.println("Maximum limit is " + maxStocks);
            System.out.println();
        }
    }

    @Override
    public void deleteProduct() {
        boolean state = false;
        Scanner sc = new Scanner(System.in);
        while (!state){
            String productID = inputHandlingString("Enter Product ID of the Product needed to be deleted :");

//            System.out.print("Enter Product ID of the Product needed to be deleted :");
//            String productID = sc.next();

            for (Product product : stocks){
                if (product.getProductID().equals(productID)){
                    System.out.print("Product ID " + product.getProductID());
                    String confirmation = inputHandlingString(" Found. Confirmation to delete ? ( Y or N ) :");

//                    System.out.print(" Found. Confirmation to delete ? ( Y or N ) :");
//                    String confirmation = sc.next();

                    if ("Y".equalsIgnoreCase(confirmation)){
                        System.out.println("------------------------------------------------");
                        System.out.println("Product ID              : " + product.getProductID());
                        System.out.println("Product Name            : " + product.getProductName());
                        System.out.println("No. of available items  : " + product.getProductNOU());
                        System.out.println("Product Price           : " + product.getProductPrice());
                        printSecondaryProductDetails(product);

                        stocks.remove(product);
                        System.out.println("Product ID : " + productID + " deleted");

                    } else if ("N".equalsIgnoreCase(confirmation)) {
                        System.out.println("Request to delete product cancelled.");
                    }

                    System.out.println("Total no. of products : " + stocks.size());
                    state = true;
                    break;
                }
            }

            if (!state){
                System.out.println("Product ID : " + productID + " not found");
            }
            System.out.println();
        }
    }

    @Override
    public void printProducts() {
        for (Product product : stocks){
            System.out.println("------------------------------------------------");
            System.out.println("Product ID              : " + product.getProductID());
            System.out.println("Product Name            : " + product.getProductName());
            System.out.println("No. of available items  : " + product.getProductNOU());
            System.out.println("Product Price           : " + product.getProductPrice());
            printSecondaryProductDetails(product);
        }
    }

    @Override
    public void printSecondaryProductDetails(Product product){
        if(product instanceof Electronics){
            Electronics electronicsProduct = (Electronics) product;
            System.out.println("Type                    : Electronics");
            System.out.println("Brand                   : " + electronicsProduct.getProductBrand());
            System.out.println("Warranty                : " + electronicsProduct.getProductWarranty());
        } else if (product instanceof Clothing) {
            Clothing clothesProduct = (Clothing) product;
            System.out.println("Type                    : Clothes");
            System.out.println("Size                    : " + clothesProduct.getProductSize());
            System.out.println("Color                   : " + clothesProduct.getProductColor());
        }
        System.out.println("------------------------------------------------");
        System.out.println();
    }

    @Override
    public Product inputProductDetails(int answer) {
        Scanner sc = new Scanner(System.in);

        String ProductID = inputHandlingString("Enter Product ID             : ");
        String ProductName = inputHandlingString("Enter Product Name           : ");
        int ProductNOU = inputHandlingInt("Enter No. of available items : ");
        double ProductPrice = inputHandlingDouble("Enter Product Price          : ");

        return inputSecondaryProductDetails(ProductID, ProductName, ProductNOU, ProductPrice, answer);
    }

    @Override
    public Product inputSecondaryProductDetails(String ProductID, String ProductName, int ProductNOU, double ProductPrice, int answer) {
        Scanner sc = new Scanner(System.in);

        if (answer == 1){
            String ProductBrand = inputHandlingString("Enter Product Brand          : ");
            int ProductWarranty = inputHandlingInt("Enter Product Warranty       : ");

            return new Electronics(ProductID, ProductName, ProductNOU, ProductPrice, ProductBrand, ProductWarranty);
        }else {
            String ProductColor = inputHandlingString("Enter Product Color          : ");
            String ProductSize = inputHandlingString("Enter Product Size           : ");

            return new Clothing(ProductID, ProductName, ProductNOU, ProductPrice, ProductColor, ProductSize);
        }
    }

    @Override
    public void saveProducts(){}
    @Override
    public void LoadProducts(){}
    @Override
    public String inputHandlingString(String input) {
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

    @Override
    public int inputHandlingInt(String input) {
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

    @Override
    public double inputHandlingDouble(String input) {
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


    public List<Product> getStocks() {return stocks;}
}




