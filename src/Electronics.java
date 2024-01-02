public class Electronics extends Product{
    private String productBrand;
    private int productWarranty;

    public Electronics(String productID, String productName, int productNOU, double productPrice, String productBrand, int productWarranty) {
        super(productID, productName, productNOU, productPrice);
        this.productBrand = productBrand;
        this.productWarranty = productWarranty;
    }

    public String getProductBrand() {
        return productBrand;
    }

    public void setProductBrand(String productBrand) {
        this.productBrand = productBrand;
    }

    public int getProductWarranty() {
        return productWarranty;
    }

    public void setProductWarranty(int productWarranty) {
        this.productWarranty = productWarranty;
    }
}
