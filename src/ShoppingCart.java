import java.util.ArrayList;
import java.util.Iterator;
//import java.util.List;

public class ShoppingCart {
    private ArrayList<Product> cartItems = new ArrayList<>();

    public void addProduct(Product product){
        cartItems.add(product);
    }

    public void removeProduct(String productID){
        Iterator<Product> iterator = cartItems.iterator();
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
        for(Product product : cartItems){
            total += product.getProductPrice();
        }
        return total;
    }
}
