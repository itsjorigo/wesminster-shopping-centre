public interface ShoppingManager {
    void addProduct();

    void deleteProduct();

    void printProducts();

    void printSecondaryProductDetails(Product product);

    Product inputProductDetails(int answer);

    void saveProducts();

    void loadProducts();

//    String inputHandlingString(String input);
//
//    int inputHandlingInt(String input);
//
//    double inputHandlingDouble(String input);

    String inputHandleProductID(String ProductID);
}
