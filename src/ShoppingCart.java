import java.util.ArrayList;
import java.util.Iterator;
//import java.util.List;

public class ShoppingCart {
    private ArrayList<Product> products = new ArrayList<>();

    public void addProduct(Product product){
        products.add(product);
    }

    public void removeProduct(String productID){
        Iterator<Product> iterator = products.iterator();
        while (iterator.hasNext()){
            Product product = iterator.next();
            if (product.getProductID().equals(productID)) {
                iterator.remove();
            }
        }
//      products.removeIf(product -> product.getProductID().equals(productID));
    }

    public double calculateTotal(){
        double total = 0.0;
        for(Product product : products){
            total += product.getProductPrice();
        }
        return total;
    }
}
