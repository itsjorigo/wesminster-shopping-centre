public interface ShoppingManager {
    void addProduct(Product product);
    void deleteProduct(String productID);
    void printProducts();
    void printSecondaryProductDetails(Product product);
    void inputSecondaryProductDetails();
    void inputProductDetails() ;

}
