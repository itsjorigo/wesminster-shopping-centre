public class Electronics extends Product{
    private String brand;
    private int warrantyPeriod;

    public Electronics(String productID, String name, int availableItems, double price, String brand, int warrantyPeriod) {
        super(productID, name, availableItems, price);
        this.brand = brand;
        this.warrantyPeriod = warrantyPeriod;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getWarrantyPeriod() {
        return warrantyPeriod;
    }

    public void setWarrantyPeriod(int warrantyPeriod) {
        this.warrantyPeriod = warrantyPeriod;
    }
}
