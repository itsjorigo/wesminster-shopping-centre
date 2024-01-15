public interface ShoppingManager {
    void addProduct();

    void deleteProduct();

    void printProducts();

    void printSecondaryProductDetails(Product product);

    Product inputProductDetails(int answer);

    void saveProducts();

    String inputHandleProductID(String ProductID);
}
