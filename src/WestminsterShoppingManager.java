import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class WestminsterShoppingManager implements ShoppingManager{
    private List<Product> stocks = new ArrayList<>();
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

                    }else {System.out.println("Invalid Input. Try Again");}

                }catch (InputMismatchException e){System.out.println("Invalid datatype. Please enter again.");}
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
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter Product ID of the Product needed to be deleted :");
        String productID = sc.next();

        boolean state = false;
        for (Product product : stocks){
            if (product.getProductID().equals(productID)){
                System.out.print("Confirmation to delete ? ( Y or N) :");
                String confirmation = sc.next();

                if ("Y".equalsIgnoreCase(confirmation)){
                    System.out.println("------------------------------------------------");
                    System.out.println("Product ID              : " + product.getProductID());
                    System.out.println("Product Name            : " + product.getProductName());
                    System.out.println("No. of available items  : " + product.getProductNOU());
                    System.out.println("Product Price           : " + product.getProductPrice());
                    printSecondaryProductDetails(product);

                    stocks.remove(product);
                } else if ("N".equalsIgnoreCase(confirmation)) {
                    System.out.println("Request to delete product cancelled.");
                }
                state = true;
                break;
            }
        }
        if (state){
            System.out.println("Product ID : " + productID + " deleted");
        }else {
            System.out.println("Product ID : " + productID + " not found");
        }
        System.out.println("Total no. of products : " + stocks.size());
        System.out.println();
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

        System.out.print("Enter Product ID             : ");
        String ProductID = sc.next();

        System.out.print("Enter Product Name           : ");
        String ProductName = sc.next();

        System.out.print("Enter No. of available items : ");
        int ProductNOU = sc.nextInt();

        System.out.print("Enter Product Price          : ");
        double ProductPrice = sc.nextDouble();

        return inputSecondaryProductDetails(ProductID, ProductName, ProductNOU, ProductPrice, answer);

    }

    @Override
    public Product inputSecondaryProductDetails(String ProductID, String ProductName, int ProductNOU, double ProductPrice, int answer) {
        Scanner sc = new Scanner(System.in);

        if (answer == 1){
            System.out.print("Enter Product Brand          : ");
            String ProductBrand = sc.next();

            System.out.print("Enter Product Warranty       : ");
            int ProductWarranty = sc.nextInt();

            return new Electronics(ProductID, ProductName, ProductNOU, ProductPrice, ProductBrand, ProductWarranty);

        }else {
            System.out.print("Enter Product Color          : ");
            String ProductColor = sc.next();

            System.out.print("Enter Product Size           : ");
            String ProductSize = sc.next();

            return new Clothing(ProductID, ProductName, ProductNOU, ProductPrice, ProductColor, ProductSize);

        }
    }

    public List<Product> getStocks() {
        return stocks;
    }
}
