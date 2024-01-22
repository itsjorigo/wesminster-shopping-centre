import java.io.*;
import java.util.*;

public class WestminsterShoppingManager implements ShoppingManager {
    static Map<String, Product> stocks = new HashMap<>();
    private static final int maxStocks = 50;
    private static final String dataFile = "product.txt";

    @Override
    public void addProduct() {
        Scanner sc = new Scanner(System.in);
        boolean validInput = false;

        int count = Main.inputHandlingInt("No. of products to be added : ");

        for (int i = 1; i <= count; i++) {
            if (stocks.size() <= maxStocks) {
                System.out.println("Select Product Category");
                System.out.println("1. Electronics");
                System.out.println("2. Clothing");

                while (!validInput) {
                    try {
                        System.out.print("Enter Answer (1 or 2) : ");
                        int answer = sc.nextInt();
                        System.out.println();

                        if (answer == 1 || answer == 2) {
                            validInput = true;
                            Product product = inputProductDetails(answer);
                            stocks.put(product.getProductID(), product);
                            System.out.println();

                        } else {
                            System.out.println("Invalid Input. Try Again");
                            System.out.println("-------------------------------------------------------------------------");
                        }

                    } catch (InputMismatchException e) {
                        System.out.println("Invalid datatype. Please enter again.");
                        System.out.println("-------------------------------------------------------------------------");
                        sc.next();
                    }
                }
                System.out.println("Product added successfully !");
                System.out.println();
                validInput = false;

            } else {
                System.out.println("Maximum limit of products is reached");
                System.out.println("Maximum limit is " + maxStocks);
                System.out.println();
            }
        }
        Main.ManagerConsoleContent();
    }

    @Override
    public void deleteProduct() {
        boolean state = false;

        int count = Main.inputHandlingInt("No. of products to be deleted : ");

        for (int i = 0; i < count; i++) {
            while (!state) {
                String productID = Main.inputHandlingString("Enter Product ID of the Product needed to be deleted :");

                Iterator<Map.Entry<String, Product>> iterator = stocks.entrySet().iterator();

                while (iterator.hasNext()) {
                    Map.Entry<String, Product> entry = iterator.next();
                    Product product = entry.getValue();

                    if (product.getProductID().equals(productID)) {
                        System.out.print("Product ID " + product.getProductID());
                        String confirmation = Main.inputHandlingString(" Found. Confirmation to delete ? ( Y or N ) :");

                        if ("Y".equalsIgnoreCase(confirmation)) {
                            System.out.println("------------------------------------------------");
                            System.out.println("Product ID              : " + product.getProductID());
                            System.out.println("Product Name            : " + product.getProductName());
                            System.out.println("No. of available items  : " + product.getProductNOU());
                            System.out.println("Product Price           : " + product.getProductPrice());
                            printSecondaryProductDetails(product);

                            iterator.remove();
                            System.out.println("Product ID : " + productID + " deleted");

                        } else if ("N".equalsIgnoreCase(confirmation)) {
                            System.out.println("Request to delete product cancelled.");
                        }

                        System.out.println("Total no. of products : " + stocks.size());
                        state = true;
                        break;
                    }
                }
                if (!state) {
                    System.out.println("Product ID : " + productID + " not found");
                }
                System.out.println();
            }
        }
        Main.ManagerConsoleContent();
    }

    @Override
    public void printProducts() {
        List<Map.Entry<String, Product>> productList = new ArrayList<>(stocks.entrySet());

        // Sort the list based on product names
        productList.sort(Comparator.comparing(entry -> entry.getValue().getProductName()));

        for (Map.Entry<String, Product> entry : stocks.entrySet()) {
            Product product = entry.getValue();
            System.out.println("------------------------------------------------");
            System.out.println("Product ID              : " + product.getProductID());
            System.out.println("Product Name            : " + product.getProductName());
            System.out.println("No. of available items  : " + product.getProductNOU());
            System.out.println("Product Price           : " + product.getProductPrice());
            printSecondaryProductDetails(product);
        }
        Main.ManagerConsoleContent();
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
        String ProductID;

        do {ProductID = inputHandleProductID(Main.inputHandlingString("Enter Product ID             : "));
        } while (ProductID == null);

        String ProductName = Main.inputHandlingString("Enter Product Name           : ");
        int ProductNOU = Main.inputHandlingInt("Enter No. of available items : ");
        double ProductPrice = Main.inputHandlingDouble("Enter Product Price          : ");

        if (answer == 1){
            String ProductBrand = Main.inputHandlingString("Enter Product Brand          : ");
            int ProductWarranty = Main.inputHandlingInt("Enter Product Warranty       : ");

            return new Electronics(ProductID, ProductName, ProductNOU, ProductPrice, ProductBrand, ProductWarranty);
        }else {
            String ProductColor = Main.inputHandlingString("Enter Product Color          : ");
            String ProductSize = Main.inputHandlingString("Enter Product Size           : ");

            return new Clothing(ProductID, ProductName, ProductNOU, ProductPrice, ProductColor, ProductSize);
        }
    }

    @Override
    public void saveProducts(){
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(dataFile))) {
            out.writeObject(stocks);
            System.out.println("Products saved to file.");
        } catch (IOException e) {
            System.out.println("Error saving products to file: " + e.getMessage());
        }
    }

    static public void loadProducts(){
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(dataFile))) {
            stocks = (Map<String, Product>) in.readObject();
            System.out.println("Products loaded from file.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading products from file: " + e.getMessage());
            e.printStackTrace(); // Add this line for debugging
        }
    }

    @Override
    public String inputHandleProductID(String ProductID){
        for (Map.Entry<String, Product> entry : stocks.entrySet()) {
            if (entry.getKey().equals(ProductID)) {
                System.out.println("Entered Product ID already exists. Try Again !");
                return null;
            }
        }
        return ProductID;
    }

    public Map<String, Product> getStocks() {
        return stocks;
    }
}
