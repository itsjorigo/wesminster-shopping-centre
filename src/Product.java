abstract public class Product {
    private String productID;
    private String name;
    private int availableItems;
    private double price;

    public Product(String productID, String name, int availableItems, double price) {
        this.productID = productID;
        this.name = name;
        this.availableItems = availableItems;
        this.price = price;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAvailableItems() {
        return availableItems;
    }

    public void setAvailableItems(int availableItems) {
        this.availableItems = availableItems;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
