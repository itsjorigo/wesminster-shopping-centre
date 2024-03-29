import java.io.Serial;
import java.io.Serializable;

abstract public class Product implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private String productID;
    private String productName;
    private int productNOU;
    private double productPrice;
    
    public Product(String productID, String productName, int productNOU, double productPrice) {
        this.productID = productID;
        this.productName = productName;
        this.productNOU = productNOU;
        this.productPrice = productPrice;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getProductNOU() {
        return productNOU;
    }

    public void setProductNOU(int productNOU) {
        this.productNOU = productNOU;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

}
