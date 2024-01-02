import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class WestminsterShoppingManager implements ShoppingManager{
    private List<Product> stocks = new ArrayList<>();
    private static final int maxStocks = 50;
    private static final String dataFile = "product.txt";

    @Override
    public void addProduct(Product product) {
        Scanner sc = new Scanner(System.in);

        if (stocks.size() < maxStocks){
            System.out.println("Select Product Category");
            System.out.println("1. Electronics");
            System.out.println("2. Clothing");

            System.out.println("Enter Answer : ");
            int answer = sc.nextInt();

            inputProductDetails();

            stocks.add(product);
            System.out.println("Product added successfully !");

        }else {
            System.out.println("Maximum limit of products is reached");
            System.out.println("Maximum limit is " + maxStocks);
        }
    }

    @Override
    public void deleteProduct(String productID) {
        boolean state = false;
        for (Product product : stocks){
            if (product.getProductID().equals(productID)){
                stocks.remove(product);
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
            System.out.println("------------------------------------------------");
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
    }

    @Override
    public void inputSecondaryProductDetails() {

    }

    @Override
    public void inputProductDetails() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter Product ID : ");
        String ProductID = sc.next();

        System.out.println("Enter Product Name : ");
        String ProductName = sc.next();

        System.out.println("Enter No. of available items : ");
        int ProductNOU = sc.nextInt();

        System.out.println("Enter Product Price : ");
        double ProductPrice = sc.nextDouble();
    }


}
