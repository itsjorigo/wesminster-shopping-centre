import java.io.Serial;
import java.io.Serializable;

public class Clothing extends Product implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private String productSize;
    private String productColor;

    public Clothing(String productID, String productName, int productNOU, double productPrice, String productSize, String productColor) {
        super(productID, productName, productNOU, productPrice);
        this.productSize = productSize;
        this.productColor = productColor;
    }

    public String getProductSize() {
        return productSize;
    }

    public void setProductSize(String productSize) {
        this.productSize = productSize;
    }

    public String getProductColor() {
        return productColor;
    }

    public void setProductColor(String productColor) {
        this.productColor = productColor;
    }

}
