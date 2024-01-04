public interface ShoppingManager {
    void addProduct();

    void deleteProduct();

    void printProducts();

    void printSecondaryProductDetails(Product product);

    Product inputProductDetails(int answer);

    Product inputSecondaryProductDetails(String ProductID, String ProductName, int ProductNOU, double ProductPrice, int answer);
}
